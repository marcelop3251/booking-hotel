import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { DetailsService } from "../api/DetailsServices"
import { MainMenu } from "../components/MainMenu"
import React from 'react';
import { HotelService } from "../api/HotelService";

export const DetailsHotel = () => {


    const parametros = useParams();
    const [detailsHotel, setDetaislHotel] = useState({});
    const [detailRoom, setDetailsRoom] = useState({});
    const navigator = useNavigate()
    const booking = {};
    const [disabled, setDisabled] = useState(false)
  

    const fetchDetailsRoom = async () => { 
        try {
            const { data }  = await DetailsService.getDetaislByRoom(parametros.id)
            setDetaislHotel(data);
            setDetailsRoom(data.room[0]);
        } catch (e) { 
            console.error(e.response);
            navigator("/not-found")
        }
    }

    useEffect(() => {
        fetchDetailsRoom()
    }, [])

    const setStartDate = (e) => { 
        booking.startDate = e.target.value
    }

    const setEndDate = (e) => { 
        booking.endDate = e.target.value
    }

    const onClicked = (e, details) => { 
        booking.description = details.description
        booking.roomId = details.id
        console.log(booking)
        if (booking.endDate == null || booking.startDate == null) { 
            setDisabled(true)
            return;
        }

        if ((booking.endDate < booking.startDate)) { 
            alert("Data de termino deve ser maior do que a data de inicio")
        } else { 
           HotelService.doBooking(booking).then(data => { 
            alert("Reserva realizado com sucesso")
            e.preventDefault()
            navigator("/home")
        }).catch(error => { 
            if (error.response.data.type === 'ROOM_UNAVAILABLE') { 
                alert("Não temos mais esse quarto disponível para essa data")
            }
            document.getElementById('startDate').value = null;
            document.getElementById('endDate').value = null;
            setDisabled(false)
        })
        }
    }
       

    return (
        <div class="container">
            <MainMenu />
            <section class="py-5">
                <div class="row  row-cols-xl-2 justify-content-center align-items-center">
                    <div class="col">
                        <div class="col-6 col-12">
                            <div class="card mb-4 rounded-3 shadow-sm">
                                <div class="card-header py-3 height-200">
                                    <h4 class="my-0 fw-normal text-center">{detailsHotel.name}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="col-8 col-12">
                            <h2>{detailRoom.type}</h2>
                            <label>R$ {detailRoom.rentValue}</label>
                            <p>{detailRoom.description}</p>
                        </div>
                    </div>
                </div>
                <div class="row justify-content-center align-items-center">
                    <div class="col">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Data de inicio da estadia</span>
                            <input id="startDate" type="date" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"
                            onChange={(e) => setStartDate(e)}></input>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Data de final de estadia</span>
                            <input id="endDate" type="date" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"
                            onChange={(e) => setEndDate(e)}
                            ></input>
                        </div>
                        { disabled?
                            <div class="alert alert-danger" role="alert">
                                <p>Selecione uma data para continuar</p>
                            </div>
                            :null}
                    </div>
                    <div class="col">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <button class="w-25 btn btn-lg btn-outline-dark" type="button" onClick={(e) => onClicked(e, detailRoom)}>Reservar</button>
                        </div>
                    </div>
                    
                </div>
            </section >
        </div>
    )
}