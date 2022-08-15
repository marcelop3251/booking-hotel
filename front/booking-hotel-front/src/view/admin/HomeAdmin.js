import React, { useEffect, useState } from "react"
import { HotelService } from "../../api/HotelService"
import { MainMenuAdmin } from "../../components/MainMenuAdmin"

export const HomeAdmin = () => { 

    const [hotel, setHotel] = useState([])

    const onClicked = () => { 
        alert("Você clicou aqui")
    }

    const fetchHotel = async () => { 
        try {
            const {data} = await HotelService.getHotelAdmin()
            setHotel(data)
            console.log(data)
        } catch (e) { 
            console.log(e)
        }
    }

    useEffect(() => { 
        fetchHotel()
    }, [])

    return (
        <div>
            <MainMenuAdmin/>
            <div class="container container-admin">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
               { hotel.map( h => 
                    h.room.map( r => 
                        <div class="col" >
                            <div class="col">
                                <div class="card mb-4 rounded-3 shadow-sm">
                                    <div class="card-header py-3">
                                        <h4 class="my-0 fw-normal">{h.name}</h4>
                                    </div>
                                    <div class="card-body">
                                        <h4 class="card-title pricing-card-title">{r.type}</h4>
                                        <h4 class="card-title pricing-card-title">Quantidade: <span>{r.quantity}</span></h4>
                                        <button type="button" class="w-100 btn btn-lg btn-outline-dark" onClick={(e) => onClicked()}>Editar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    )
               )
               } 
            </div>
            </div>
        </div>
       
    )
}