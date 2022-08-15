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

    static doRegisterAdmin(credential) { 

        return axios.post(WITH_BASE_URL('company'),
        { 
            "email": credential.email,
            "password": credential.password,
            "name": credential.name,
            "cnpj": credential.cnpj
        })
    }

}