import axios from "axios";
import { WITH_BASE_URL } from "./Constants";


export class LoginService { 
    static doLogin(credential) { 

        const config = { 
            headers: { 
                'Access-Control-Allow-Origin': 'http://34.148.248.225:4000',
                'Access-Control-Allow-Methods':'GET,PUT,POST,DELETE,PATCH,OPTIONS',
            }
        }
       
        return axios.post(WITH_BASE_URL('login'),
        { 
            "username": credential.email,
            "password": credential.password
        }, config)
    }

}