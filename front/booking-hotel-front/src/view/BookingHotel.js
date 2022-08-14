import React, { useEffect, useState } from "react"
import { HotelService } from "../api/HotelService"
import { MainMenu } from "../components/MainMenu"

export const BookingHotel = () => { 

    const [booking, setBooking] = useState([])

    const fetchBooking = async() => { 
        try { 
            const { data } = await HotelService.getAllBooking()
            console.log(data)
            setBooking(data)
        } catch (error) { 
            console.log(error)
        }
    }

    useEffect(() => { 
        fetchBooking()
    }, [])

    return (
        <div class="container">
            <MainMenu />
            <section class="py-5">
                {booking.map(b => 
                    <div class="row  row-cols-xl-2 justify-content-center align-items-center">
                    <div class="col">
                        <div class="col-6 col-12">
                            <div class="card mb-4 rounded-3 shadow-sm">
                                <div class="card-header py-3 height-200">
                                    <h4 class="my-0 fw-normal text-center">{b.hotel.name}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="col-8 col-12">
                            <h2>{b.hotel.room.type}</h2>
                            <p>Data da reserva</p>
                            <p>{b.startDate} até {b.endDate}</p>
                            <p>{(b.status === 'CHECK_IN') ? 'Aprovado' : 'Pendente de aprovação'}</p>
                           
                        </div>
                    </div>
                </div>
                    )}
            </section >
        </div>
    )
}