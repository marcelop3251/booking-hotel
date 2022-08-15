import React, { useEffect, useState } from "react"
import { HotelService } from "../../api/HotelService"
import { MainMenuAdmin } from "../../components/MainMenuAdmin"

export const CheckOutAdmin = () => { 
    const [booking, setBooking] = useState([])

    const onClicked = async (e) => { 
        try { 
            const { data } = await HotelService.approveCheck("check-out", e.id)
            setBooking(booking.filter(b => b.id != e.id))
            alert("Checking aprovado com sucesso")
        } catch (error) { 
            console.log(error)
        }
    }

    const fetchBooking = async () => { 
        try {
            const { data } = await HotelService.getAllBookingAdmin("CHECK_OUT")
            setBooking(data)
        } catch (error) { 
            console.log(error)
        }
    }

    useEffect(() => { 
        fetchBooking()
    },[])

    return ( 
        <div>
            <MainMenuAdmin/>
            <div class="container container-admin">
                <h1>Clientes aguardando confirmação de check out</h1>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="solicitados">
                    {booking.map(b => 
                    <div class="col">
                        <div class="col">
                            <div class="card mb-3 rounded-3 shadow-sm">
                                <div class="card-header py-3">
                                    <h4 class="my-0 fw-normal">{b.customer.name}</h4>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title pricing-card-title">{b.hotel.name}</h4>
                                    <label class="card-title pricing-card-title">Inicio {b.startDate}</label>
                                    <label class="card-title pricing-card-title">Termino {b.endDate}</label>
                                    <button type="button" class="w-100 btn btn-lg btn-outline-dark" onClick={(e) => onClicked(b)}>Aprovar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    )}
                    
                 </div>
            </div>
        </div>
    )
}