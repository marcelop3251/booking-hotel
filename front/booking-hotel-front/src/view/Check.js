import React, { useEffect, useState } from "react"
import { HotelService } from "../api/HotelService"
import { MainMenu } from "../components/MainMenu"

export const Check = ( {checkType}) => { 

    const [check, setCheck] = useState([])

    const onClicked = async(ch) => {
        HotelService.doCheck(checkType+"/"+ch.id).then(response => {
            if (response.status === 204) { 
                setCheck(check.filter(e => ch.id !== e.id))
            }
        })
    }
    
    const fechCheckType = async() => { 
        console.log(checkType)
       const {data} = await HotelService.getCheck(checkType)
       setCheck(data)
    }

    useEffect(() => { 
        fechCheckType()
    }, [])

    return ( 
        <div class="container">
             <MainMenu />
             <section class="py-5">
              {check.map(ch => 
                    <div class="row row-cols-xl-2 justify-content-center align-items-center" key={ch.id}>
                    <div class="col">
                        <h4 class="my-0 fw-normal">Seja bem vindo a sua estadia</h4>
                        <div class="col-6 col-12">
                            <div class="card mb-4 rounded-3 shadow-sm">
                                <div class="card-header py-3 height-200">
                                    <label class="my-0 fw-normal text-center">Nome</label>
                                    <p class="my-0 fw-normal">{ch.customer.name}</p>
                                    <br/>
                                    <label class="my-0 fw-normal text-center">Hotel</label>
                                    <p class="my-0 fw-normal">{ch.hotel.name}</p>
                                    <label class="my-0 fw-normal text-center">Reserva para o dia {ch.startDate} até {ch.endDate}</label>
                                    <br/>
                                    <div>
                                        <button type="button" class="w-100 btn btn-dark" onClick={(e) => onClicked(ch)}>{(checkType === 'check-in') ? 'Check-in':'Check-out'}</button>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
              
                )}
        </section>
        </div>
       
    )
}