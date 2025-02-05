 
import { Currency as ICurrency  } from '../../types'; 
import {  useState } from 'react';
import { Combobox, ComboboxInput, ComboboxOption, ComboboxOptions ,ComboboxButton} from '@headlessui/react'
import { CheckIcon  } from '@heroicons/react/20/solid'
 
type paramterType ={currencies:ICurrency[] ,selectChange:Function,placeholder:string,viewHelper:Function} 

export default function CurrencyList ({currencies=[], selectChange , placeholder ,viewHelper=()=>{}}:paramterType) { 
   
  const [selectedCurrency, setSelectedCurrency] = useState<ICurrency|null>(null)
  const [query, setQuery] = useState('')
   
  const chnageSelection =(v:ICurrency|null)=>{
    setSelectedCurrency(v)
    selectChange(v?.code.toUpperCase())
    viewHelper()
  } 
  const filteredCurrency =
  query === '' 
    ? currencies
    : currencies.filter((c) => {
        return c.name.toLowerCase().includes(query.toLowerCase()) || c.code.toLowerCase().includes(query.toLowerCase())
      })

  return (
    <div className='w-full h-full border-2 border-blue-400 rounded-xl'>
        <Combobox value={selectedCurrency} onChange={(value) => chnageSelection(value)} onClose={() => setQuery('')}>
        <div className="relative h-full">
          <ComboboxInput
            className={
              'rounded-lg h-full lg:w-full min-w-96     bg-slate-200 w-full pr-8 pl-3 text-base text-slate-600 font-semibold focus:outline-none  '
            }
            displayValue={(c:ICurrency) => c && c.code? c.code:''}
            onChange={(event) => setQuery(event.target.value)}
            placeholder={placeholder}
          />
          <ComboboxButton className="group absolute inset-y-0 right-8 w-full" />
        </div>

        <ComboboxOptions
          anchor="bottom"
          transition
          className={
            'w-[var(--input-width)] rounded-xl  !max-h-[50vh]  bg-slate-400 overflow-y-auto    my-1 [--anchor-gap:var(--spacing-1)] empty:invisible transition duration-100 ease-in data-[leave]:data-[closed]:opacity-0'
          }
        >
          {filteredCurrency.map((c) => (
            <ComboboxOption
              key={c.code}
              value={c}
              className={`"group flex cursor-default  items-center gap-2 rounded-lg py-1.5 px-3 select-none w-full bg-slate-400 my-1 hover:bg-slate-600 hover:text-white "  ${ selectedCurrency && c.code === selectedCurrency.code? 'text-blue-800':'' }`}
            >
            <CheckIcon className="size-4 w-1/6" />{c.name} 
            </ComboboxOption>
          ))}
        </ComboboxOptions>
      </Combobox>
   </div>
    
  )
}

 

 

 
 

 