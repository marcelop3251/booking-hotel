import React, { useEffect, useState } from "react";
import { HotelService } from "../api/HotelService";
import { RoomService } from "../api/RoomService";
import { Header } from "../components/Header";
import { MainMenu } from "../components/MainMenu";
import { SectionsServices } from "../components/SectionsServices";

export const MealHotel = () => { 

    const [roomServices, setRoomServices] = useState([]);
    const [booking, setBooking] = useState([])
   
    const fetchServicesRoom = async () => { 
        try { 
            console.log("Find room MEAL")
            const { data } = await RoomService.getRoomServies("MEAL")
            setRoomServices(data)
        } catch(e) { 
            console.error(e.response);
        }
    }

    const fetchBooking = async () => { 
        try { 
            console.log("Find bookins")
            const { data } = await HotelService.getBooking("CHECK_IN_APPROVED")
            setBooking(data)
        } catch (e) { 
            console.error(e.response)
        }
    }

    useEffect(() => {
        fetchServicesRoom()
    }, [])

    useEffect(() => {
        fetchBooking()
    }, [])

    return (
    <div>
        <MainMenu />
        <SectionsServices propes = {roomServices} booking = {booking} serviceType = "MEAL" />
    </div>
    )
}