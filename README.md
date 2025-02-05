
# Currency

This is an application for checking currency exchange by using a development key which only run on local machine 
Backend - Java spring boot
Frontend - react  


## How to run

1. Clone the project
2. Go to 'currencies/src/main/resources' to create the .env file  and give value for it like in the example. 
3. Open the project with IntelliJ
3. run the Project as backend
4. Using vs code to open the folder 'FrontEnd'
5. run "npm i" to install needed packages, make sure the port 3000 is free now
6. the "npm run build" and "npm run start"  or just "npm run dev" only
7. open browser and go to 'http://localhost:3000'

## Endpoints

1. http://localhost:8080/api/v1/getCurrencyList
    Method: GET  
    headers: {Accept:application/json,  Content-Type:application/json}
    return the list of currency names and code

2. http://localhost:8080/api/v1/getRate
    Method: POST
    headers: {Accept:application/json,  Content-Type:application/json}
    body: {base:string, quote:string} 
    return the quote rate of two currencies


## Authors

- [@JinYili](https://github.com/JinYili)

