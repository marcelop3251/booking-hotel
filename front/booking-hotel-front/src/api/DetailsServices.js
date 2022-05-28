import axios from "axios";
import { WITH_BASE_URL } from "./Constants";
import HTTP from "./InterceptorAxios";

export class DetailsService { 

    static getDetails() { 
        return HTTP.get('hotel');
    }

    static getDetaislByRoom(roomId) { 
        return HTTP.get(`room/${roomId}`);
    }
}