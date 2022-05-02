import axios from "axios";
const BASE_URL = 'http://localhost:8080/';
const WITH_BASE_URL = path => `${BASE_URL}${path}`;

export class LoginService { 
    static doLogin() { 
        console.log('url encontrada is ');
        console.log(WITH_BASE_URL('login'));

        const config = { 
            headers: { 
                'Access-Control-Allow-Origin': 'http://localhost:3000',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            }
        }
       
        return axios.post('http://localhost:8080/login',
        { 
            "username": "marcelop3251@gmail.com",
            "password": "123456"
        }, config).catch(error => {
            console.log("Error retornado by acess")
            console.log(error)
        })
        
       // )
    }

}