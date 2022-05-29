import { useNavigate } from "react-router-dom";
import React from 'react';

export const SectionProduct = ({ props }) => {

    const navigate = useNavigate();

    const onClicked = (e, id) => { 
        navigate(`/details/${id}`)
    }

    return (<section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                {props.map(detailsHotel =>
                    detailsHotel.room.map(room =>
                        <div class="col" key={room.id}>
                            <div class="col">
                                <div class="card mb-4 rounded-3 shadow-sm">
                                    <div class="card-header py-3">
                                        <h4 class="my-0 fw-normal">{detailsHotel.name}</h4>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="card-title pricing-card-title">{room.type}</h1>
                                        <h1 class="card-title pricing-card-title">R${room.rentValue}<small class="text-muted fw-light">/mo</small></h1>

                                        <button type="button" class="w-100 btn btn-lg btn-outline-dark" onClick={(e) => onClicked(e, room.id)}>Detalhes</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    ))}
            </div>
        </div>
    </section>)
}