import { useEffect, useState } from "react"
import { Button, Form, FormGroup } from "react-bootstrap"
import { Input, Label } from "reactstrap"
import { LoginService } from "../api/LoginService"

export const Login = () => {

    const [login, setLogin] = useState([])
    const fetchLogin = async() => { 
      const data = await LoginService.doLogin();
      console.log('Values data authorization');
      console.log(data.headers.authorization);
    }
  
    useEffect(() => {
      fetchLogin();
    }, []);

    function submitForm(e) { 
        e.preventDefault();
        console.log(e)
    }
    
    return (
        <div class="d-flex align-items-center justify-content-center center-height">
            <div class="container container-login col-4 col-6">
                <form action method="post" onSubmit={(e) => this.submitForm(e)}>
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
                        />
                    </div>
                    <div class="container-button">
                        <button type="button" class="w-100 btn btn-dark">Login</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

