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

    static getHotelAdmin() { 
        return HTTP.get("/admin/hotel");
    }

    static registerNewHotel(newHotel) { 
        return HTTP.post("/admin/hotel", newHotel)
    }

    static registerNewRoom(newRoom, hotel_id) { 
        return HTTP.post("/admin/hotel/room/" + hotel_id, newRoom)
    }

    static registerNewService(newService) { 
        return HTTP.post("/admin/service", newService)
    }

    static getAllBookingAdmin(checkType) { 
        return HTTP.get("/admin/booking/"+checkType)
    }

    static approveCheck(checkType, bookingId) { 
        return HTTP.post("/admin/booking/"+ checkType + "/" + bookingId)
    }
}