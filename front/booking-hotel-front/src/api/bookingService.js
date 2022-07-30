import HTTP from "./InterceptorAxios";

export class BookingService { 

    static doBooking(booking) { 
        return HTTP.post('/hotel/booking', booking)
    }
}