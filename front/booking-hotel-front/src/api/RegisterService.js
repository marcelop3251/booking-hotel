import axios from "axios";
import { WITH_BASE_URL } from "./Constants";

export class RegisterService { 
    static doRegister(credential) { 

        return axios.post(WITH_BASE_URL('customer'),
        { 
            "email": credential.email,
            "password": credential.password,
            "name": credential.name,
        })
    }

}