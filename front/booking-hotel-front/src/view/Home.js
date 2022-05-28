import { useEffect, useState } from "react"
import { DetailsService } from "../api/DetailsServices"
import { Header } from "../components/Header"
import { MainMenu } from "../components/MainMenu"
import { SectionProduct } from "../components/SectionProduct"

export const Home = () => { 
    console.log("Inicianto o componentes");

    const [detailsHotel, setDetailsHotel] = useState([]);

    const fetchDetails = async() => { 
        const { data } = await DetailsService.getDetails()
            console.log(data);
            setDetailsHotel(data)
    }

    useEffect(() => {
        fetchDetails()
    }, [])
       

    return (
        <div>
            <MainMenu/>
            <Header />
            <SectionProduct props = {detailsHotel} /> 
        </div>
    )
}