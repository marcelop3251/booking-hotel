import React, { useEffect, useState } from 'react';
import { HotelService } from '../api/HotelService';

export const SectionsServices = ({ propes, booking, serviceType }) => { 

    const [selectedOption, setSelectedOption] = useState([])
    const [request, setRequest] = useState([])

    const onClicked = (e) => { 
       
        if (selectedOption.length == 0) { 
            alert("Precisa selecionar uma hospedagem válida")
        } else {
            let requestToApi = {}
            requestToApi.bookingId = selectedOption
            requestToApi.serviceId =  e.id
            HotelService.doRequest(requestToApi).then(element => {
                setRequest([...request, element.data])
            })
        }
    }

    const fetchRequest = async () => { 
        try { 
            const { data } = await HotelService.getRequest(serviceType)
            console.log(data)
            setRequest(data)
        } catch (error) { 
            console.log(error)
        }
    }

    useEffect(() => { 
        fetchRequest()
    }, [])

    return (
        
        <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
            {propes.map(servicesRoom =>
                <div class="col" key={servicesRoom.id}>
                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm">
                            <div class="card-header py-3">
                                <h4 class="my-0 fw-normal">{servicesRoom.name}</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">R$ {servicesRoom.price}<small class="text-muted fw-light"></small></h1>
                                <button type="button" class="w-100 btn btn-lg btn-outline-dark" onClick={(e) => onClicked(servicesRoom)} >Solicitar</button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
            </div>
            <div>
                <select class="form-select" aria-label="Default select example"  onChange={(e) => setSelectedOption(e.target.value)}>
                <option selected>Selecione a sua reserva</option>
                    {booking.map(b => 
                        <option value={b.id} key={b.id}>{b.hotel.name} reserva {b.id} </option>
                        )}
                    </select>
            </div>
            <div>
                <h2 class="align-element text-styled">Serviços solicitados</h2>
            </div>
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="solicitados">
                { request.map(element =>
                <div class="col" key={element.id}>
                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm">
                            <div class="card-header py-3">
                                <h4 class="my-0 fw-normal">{element.service.name}</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">R$ {element.service.price}<small class="text-muted fw-light"></small></h1>
                                <button type="button" class="w-100 btn btn-lg btn-outline-dark" >Solicitado</button>
                            </div>
                        </div>
                    </div>
                </div>
                )   
                }
            </div>

        </div>
    </section>
    )

}