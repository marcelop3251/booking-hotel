import HTTP from "./InterceptorAxios";

export class HotelService { 

    static doBooking(booking) { 
        return HTTP.post('/hotel/booking', booking);
    }

    static getBooking(status) { 
        return HTTP.get('/hotel/booking/'+status);
    }

    static getAllBooking() { 
        return HTTP.get('/hotel/booking/');
    }

    static doRequest(request) { 
        return HTTP.post('/hotel/request', request);
    }

    static getRequest(service) { 
        return HTTP.get("/hotel/request/"+service);
    }

    static getCheck(checkType) { 
        return HTTP.get("/hotel/"+ checkType);
    }

    static doCheck(checkType) { 
        return HTTP.post("/hotel/"+checkType);
    }
}