const calculateValue =(a:number, r:number)=>{
    const num = a*r;
    return num.toFixed(2)
  } 
  const  isNumeric =(value:string):boolean =>{
    return /^\d+$/.test(value);
  }

  export {
    calculateValue,
    isNumeric
  }