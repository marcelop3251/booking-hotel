import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { RegisterService } from "../api/RegisterService";
import React from 'react';

export const RegisterNewAccount = () => { 

    const credential = {};
    const navigate = useNavigate();
    const [disabled, setDisabled] = useState(false)


    const submitForm = (e) => { 
        e.preventDefault();
        const data = RegisterService.doRegister(credential).then(data => { 
            return navigate('/home')
        }).catch(error => { 
            console.log("Error when trying to register")
        });
    }

    const setEmail = (e) => { 
        credential.email = e.target.value;
    }

    const setPassword = (e) => { 
        credential.password = e.target.value;
    }

    const setName = (e) => { 
        credential.name = e.target.value;
    }
    
    return (
        <div class="d-flex align-items-center justify-content-center center-height">
            <div class="container container-login col-4 col-6">
                <form action method="post" onSubmit={(e) => submitForm(e)}>
                    <div class="mb-2 me-sm-2 mb-sm-0">
                        <label
                            class="me-sm-2"
                            for="exampleEmail">
                            Email
                        </label>
                        <input
                            id="exampleEmail"
                            name="email"
                            placeholder="something@idk.cool"
                            type="email"
                            class="form-control"
                            onChange={(e) => setEmail(e)}
                        />
                    </div>
                    <div class="mb-2 me-sm-2 mb-sm-0">
                        <label
                            class="me-sm-2"
                            for="name">
                            Nome
                        </label>
                        <input
                            id="name"
                            name="name"
                            placeholder="Informe seu nome aqui!"
                            type="text"
                            class="form-control"
                            onChange={(e) => setName(e)}
                        />
                    </div>
                    <div class="mb-2 me-sm-2 mb-sm-0">
                        <label
                            class="me-sm-2"
                            for="examplePassword"
                        >
                            Senha
                        </label>
                        <input
                            id="examplePassword"
                            name="password"
                            placeholder="don't tell!"
                            type="password"
                            class="form-control"
                            onChange={(e) => setPassword(e)}
                        />
                    </div>
                    <div class="container-button">
                        <button type="submit" class="w-100 btn btn-dark">Registrar</button>
                    </div>
                </form>
            </div>
        </div>
    )

}