import HTTP from "./InterceptorAxios";

export class RoomService { 

    static getRoomServies(servicetype) { 
        return HTTP.get('/hotel/services/'+ servicetype)
    }
}