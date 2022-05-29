import { useNavigate } from "react-router-dom";
import { MainMenu } from "../components/MainMenu";
import React from 'react';

export default function NotFoundItems() {
    const navigate = useNavigate()

    const goBack = (e) => { 
        e.preventDefault();
        navigate("/home")
    }

    return (
        <div>
            <MainMenu />
            <div class="container">
                <div class="row v-200">
                    <div class="col align-self-center">
                        <h1 class="align-element"> Item não encontrado</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-4 offset-10 justify-content-end">
                    <button type="button" class='w-20 btn btn-dark' onClick={(e) => goBack(e)}> Voltar</button>
                </div>
            </div>
        </div>
    )
}