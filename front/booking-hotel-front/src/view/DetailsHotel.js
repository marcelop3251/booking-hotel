import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import { DetailsService } from "../api/DetailsServices"
import { MainMenu } from "../components/MainMenu"

export const DetailsHotel = () => {


    const parametros = useParams();
    const [detailsHotel, setDetaislHotel] = useState({});
    const [detailRoom, setDetailsRoom] = useState({});
    const navigator = useNavigate()
  

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
       

    return (
        <div>
            <MainMenu />
            <section class="py-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center align-items-center">
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
                        <div class="col-6 col-12">
                            <h2>{detailRoom.type}</h2>
                            <label>R$ {detailRoom.rentValue}</label>
                            <p>{detailRoom.description}</p>
                        </div>
                    </div>
                </div>
            </section >
        </div>
    )
}