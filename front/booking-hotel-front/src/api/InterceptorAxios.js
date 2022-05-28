import { axios } from "axios";
import { useNavigate } from "react-router-dom";

const axioss = require("axios")

const HTTP = axioss.create({
    baseURL: process.env.REACT_APP_BASE_URL,
    timeout: 4000,
});


HTTP.interceptors.request.use ( 
    request => { 
        if (!request.url.includes('login')) { 
            console.log("Add request readers token value");
            request.headers['Authorization'] = 'Bearer ' +  localStorage.getItem('token');
        }
        return request;

    }, 
    error => { 
        return Promise.reject(error)
    }
);

HTTP.interceptors.response.use ( 
    response => { 
        return response;

    }, 
    error => { 
        console.log(error.response.status)
        if (error.response.status == 401) {
            console.log("Go back login");
            localStorage.removeItem('token');
            window.location.pathname = "/login"
        }
        return Promise.reject(error)
    }
);

export default HTTP;