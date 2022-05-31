import { useEffect, useState } from "react"
import { Button, Form, FormGroup } from "react-bootstrap"
import { Input, Label } from "reactstrap"
import { LoginService } from "../api/LoginService"
import { Link, useNavigate } from "react-router-dom"
import React from 'react';

export const Login = () => {

    const credential = {};
    const navigate = useNavigate();
    const [disabled, setDisabled] = useState(false)


    const submitForm = (e) => { 
        e.preventDefault();
    
        const data = LoginService.doLogin(credential).then(data => { 
            
            if (data.headers.authorization != null) { 
                localStorage.setItem('token', data.headers.authorization);
                return navigate('/home')
            }
        }).catch(error => { 
            document.getElementById('email').value = '';
            document.getElementById('password').value = '';
            setDisabled(true)
        });
    }

    const setEmail = (e) => { 
        credential.email = e.target.value;
    }

    const setPassword = (e) => { 
        credential.password = e.target.value;
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
                            id="email"
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
                            for="examplePassword"
                        >
                            Senha
                        </label>
                        <input
                            id="password"
                            name="password"
                            placeholder="don't tell!"
                            type="password"
                            class="form-control"
                            onChange={(e) => setPassword(e)}
                        />
                    </div>
                    { disabled?
                    <div class="alert alert-danger" role="alert">
                        <p>Usuário ou senha inválido :(</p>
                    </div>
                    :null
                    }
                    <div class="container-button">
                        <button type="submit" class="w-100 btn btn-dark">Login</button>
                    </div>
                </form>

                <div>
                    <p>Ainda não possui uma conta? <Link to="/register">Registrar uma nova conta</Link></p>
                </div>
            </div>
        </div>
    )
}

