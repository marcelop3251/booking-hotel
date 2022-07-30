import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { DetailsService } from "../api/DetailsServices"
import { MainMenu } from "../components/MainMenu"
import React from 'react';
import { BookingService } from "../api/bookingService";

export const DetailsHotel = () => {


    const parametros = useParams();
    const [detailsHotel, setDetaislHotel] = useState({});
    const [detailRoom, setDetailsRoom] = useState({});
    const navigator = useNavigate()
    const booking = {};
  

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
        console.log("botão clicado")
        console.log(booking)
        booking.description = details.description
        booking.roomId = details.id
        if ((booking.endDate < booking.startDate)) { 
            alert("Data de termino deve ser maior do que a data de inicio")
        } else { 
           BookingService.doBooking(booking).then(data => { 
            alert("Reserva realizado com sucesso")
            e.preventDefault()
            navigator("/home")
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
                            <input type="date" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"
                            onChange={(e) => setStartDate(e)}
                            ></input>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Data de final de estadia</span>
                            <input type="date" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"
                            onChange={(e) => setEndDate(e)}
                            ></input>
                        </div>
                    </div>
                    <div class="col">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            {/* <button class="btn btn-primary me-md-2" type="button">Button</button> */}
                            <button class="w-25 btn btn-lg btn-outline-dark" type="button" onClick={(e) => onClicked(e, detailRoom)}>Reservar</button>
                        </div>
                    </div>
                    
                </div>
            </section >
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Launch demo modal
                </button>

               
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                    </div>
                </div>
                </div>
        </div>
    )
}