import React, { useEffect, useState } from 'react';
import { RoomService } from '../api/RoomService';
import { Header } from '../components/Header';
import { MainMenu } from '../components/MainMenu';
import { SectionsServices } from '../components/SectionsServices';
import  { HotelService } from '../api/HotelService';

export const RoomServiceHotel = () => { 

    const [roomServices, setRoomServices] = useState([]);
    const [booking, setBooking] = useState([])
   
    const fetchServicesRoom = async () => { 
        try { 
            console.log("Find room services")
            const { data } = await RoomService.getRoomServies("HOSPITALITY")
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
        <SectionsServices propes = {roomServices} booking = {booking} serviceType = "HOSPITALITY" />
    </div>
    )
}