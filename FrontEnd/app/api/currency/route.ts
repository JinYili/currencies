import { NextRequest, NextResponse } from 'next/server'; 

export async function GET(request: NextRequest) {
  try {
  const response = await fetch('http://localhost:8080/api/v1/getCurrencyList');
   
    return new NextResponse(response.body, {
      status: response.status,
      headers: { 'Content-Type': 'application/json' },
    });
  
  } catch (e: unknown) {
    const error_response = {
      status: 'error',
      message: (e as Error).message,
    };
    return new NextResponse(JSON.stringify(error_response), {
      status: 500,
      headers: { 'Content-Type': 'application/json' },
    });
  }
}


export async function POST(request: NextRequest) {
  try {
     const {base,quote} = await request.json();
     
     //const token = request.headers.get('X-XSRF-TOKEN')?.trim()  
     
     const response  = await fetch('http://localhost:8080/api/v1/getRate',  {
      method: 'POST',
      headers: { 
      //  'X-XSRF-TOKEN':  token||'',
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
        body: JSON.stringify({base, quote})
    })   
     
    return new NextResponse(response.body, {
      status: response.status,
      headers: { 'Content-Type': 'application/json' },
    });
     
  } catch (e: unknown) {
    const error_response = {
      status: 'error',
      message: (e as Error).message,
    };
    return new NextResponse(JSON.stringify(error_response), {
      status: 500,
      headers: { 'Content-Type': 'application/json' },
    });
  }
}