 

export type Currency ={
  code:string; 
  numeric_code:string; 
  decimal_digits:number; 
  name:string; 
  active:boolean
}

export type Rate ={
  base_currency:string;
  quote_currency:string;
  quote:number;
  date:string
}
export enum Languages {
  English = 'en',
  Spainnish = "es"
}