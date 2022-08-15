import React from "react"
import { Link } from "react-router-dom"

export const MainMenuAdmin = () => { 
    return ( 
        <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark width-280">
            <span class="fs-4">Painel Administrativo</span>
            <ul class="nav nav-pills flex-column mb-auto">
                <li>
                    <Link className="nav-link text-white" to="/admin/home">Hoteis</Link>
                </li>
                <li>
                    <Link className="nav-link text-white" to="/admin/register/hotel">Cadastro de Hotel</Link>
                </li>
                <li>
                    <Link className="nav-link text-white" to="/admin/register/room">Cadastro de Quartos</Link>
                </li>
                <li>
                    <Link className="nav-link text-white" to="/admin/register/service">Cadastro Serviço de quarto</Link>
                </li>
                <li>
                    <Link className="nav-link text-white" to="/admin/check-in">Check-in</Link>   
                </li>
                <li>
                    <Link className="nav-link text-white" to="/admin/check-out">Check-out</Link>  
                </li>
            </ul>
         </div>
    )
}