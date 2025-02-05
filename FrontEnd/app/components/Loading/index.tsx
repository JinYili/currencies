import { useTranslation } from 'react-i18next'; 
 
export default function Loading ( ) { 
    const { t } = useTranslation();
   return   <div className='text-blue-500 min-h-screen  font-bold text-5xl flex justify-center items-center w-full h-max my-10'> {t('loading')} </div>
  }

 

 