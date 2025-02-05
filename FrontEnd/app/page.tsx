'use client'
import { useState, useEffect } from 'react' 
 import './i18n';
import { Currency as ICurrency ,Languages as ILanguages} from './types'; 
import { Input ,Button} from '@headlessui/react'
import Header from './components/Header' 
import Loading from './components/Loading'
import store from '../redux/theme'
import { Provider } from 'react-redux'  
import  CurrencyList from './components/List' 
import { useTranslation } from 'react-i18next'; 
import { calculateValue,  isNumeric} from '@/app/utilities'



export default   function Home() {
 
  const [language, setLanguage] = useState<ILanguages>(ILanguages.English);
  const [isLoading, setIsLoading] = useState<boolean>(false)
  const [amount,setAmount] = useState<number|null>(0)
  const [list, setList] = useState<ICurrency[]>([])
  const [hydrated, setHydrated] = useState(false);
  const [base, setBase] = useState<string>('')
  const [quote, setQuote] = useState<string>('')
  
  const [rate, setRate] = useState<number|null>(null)
  const [onError, setOnError] = useState<boolean>(false) 
  const [xToken, setXtoken] = useState<string>('')
  const { t, i18n } = useTranslation();
  useEffect(()=>{
     async function getList() {
      fetch('api/currency').then(async(res)=>{
        const data = await res.json();
        setList(data);  
        setIsLoading(false)
      })
    }

    async function getToken(){ 
      fetch('api/token').then(async(res)=>{
        const data = await res.json();
         
        setXtoken(data.token.trim())
      })
    }
    setHydrated(true)
    setIsLoading(true)
    getList()
    // getToken();
  },[])
  
  const onChangeAmount=(value:string):void =>{
      if(isNumeric(value)) setAmount(parseFloat(value))
        else if(value.trim( )==='') setAmount(null)

        viewHelper()
  }
 
  const getRate =async()=>{
    setRate(null)
    if(base!==""&& quote!=="" && amount!==null && amount>0){
        setOnError(false)
         const rawData  = await fetch('api/currency',  {
          method: 'POST',
          headers:{
          //  'X-XSRF-TOKEN': xToken  ,
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            } ,
          body: JSON.stringify({base, quote})
        })
        if(!rawData.ok)
          setOnError(true)
        
        const response = await rawData.json()
        console.log(response)
        setRate(response.quote?response.quote:null)

    }else
      setOnError(true)
  }

  const viewHelper =()=>{
    if(rate !==null) setRate(null)
  }
 
 
  if(!hydrated) {
    return null
  }
 if(isLoading) return <Loading /> 
  return (
    <Provider store={store}>
 
      <main className={`flex min-h-screen flex-col items-center w-full ${ language === ILanguages.Spainnish ?'bg-lime-300':'bg-slate-200'}`}>
       
            <Header languageHelper={()=>{
                setLanguage( language === ILanguages.English? ILanguages.Spainnish:ILanguages.English );
                i18n.changeLanguage(language === ILanguages.English? ILanguages.Spainnish:ILanguages.English)
            }}  />
        
          <div className='w-full h-24 px-10 m-5 flex lg:flex-row gap-3 flex-col  ' >
            <div className='w-1/2 h-full'> 
              <Input   name="amount" className="border-2 border-blue-400 bg-slate-200 px-5 rounded-lg text-2xl h-full w-full lg:my-auto focus:border-blue   border-solid" value={amount?amount:''} type="text"  data-focus data-hover placeholder={t('amount')} onChange={(e)=>onChangeAmount(e.target.value)} />
              
              </div>
            <div className='w-1/2 lg:h-auto h-24   flex lg:flex-row flex-col gap-3'>
                <div className='lg:w-1/2 h-24  flex lg:flex-row flex-col'>
                  <CurrencyList currencies={list}  selectChange={setBase} viewHelper={viewHelper} placeholder={t("from")}/>
                </div>
                <div className='lg:w-1/2 h-24  flex lg:flex-row flex-col'>
                    <CurrencyList currencies={list} selectChange={setQuote}  viewHelper={viewHelper} placeholder={t("to")}/>
                </div>
            </div>
          </div>
          <div className='my-10 lg:m-5 gap-3 w-full h-12 px-10 flex flex-row lg:flex-row-reverse  '>
            <Button className="w-36 rounded bg-sky-600 py-2 px-4 text-sm text-white data-[hover]:bg-sky-500 data-[active]:bg-sky-700"  onClick={getRate}>
            {t('convert')} 
            </Button>
          </div>
          { rate && amount ?   
          <div className='my-10 lg:m-5 gap-3 w-full h-12 px-10 text-3xl font-bold text-blue-600   flex items-center justify-center'>
              <span>{ calculateValue(amount, rate) }</span>
          </div>:'' }
        
          {onError? 
            <div className='my-10 lg:m-5 gap-3 w-full h-12 px-10 text-3xl font-bold text-red-500   flex items-center justify-center'>
          {t('convert_error')}
            </div>:''}
      </main> 
         
      
    </Provider>
  )
}
