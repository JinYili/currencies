import { NextRequest, NextResponse } from 'next/server'; 
export async function GET(request: NextRequest) {
  try {
  const response = await fetch('http://localhost:8080/csrf/token');

  return new NextResponse( response.body, {
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

