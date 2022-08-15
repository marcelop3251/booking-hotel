import React, { useEffect, useState } from "react"
import { HotelService } from "../../api/HotelService"
import { RoomService } from "../../api/RoomService"
import { MainMenuAdmin } from "../../components/MainMenuAdmin"

export const RegisterServiceAdmin = () => { 

    let newService = { 
        "name": "",
        "price": 0,
        "description": "",
        "type": ""
    }

    const setName = (e) => { 
        newService.name =  e.target.value
    }

    const setPrice = (e) => { 
        newService.price =  e.target.value
    }

    const setDescription = (e) => { 
        newService.description =  e.target.value
    }

    const setSelected = (e) => { 
        newService.type =  e
    }

    const onClicked = async() => { 
        
        if (newService.name.length == 0) { 
            alert("O campo nome deve ser preenchido")
        }

        if (newService.price.length == 0 || newService.price == 0) { 
            alert("O campo price deve ser preenchido")
        }

        if (newService.description.length == 0) { 
            alert("O campo description deve ser preenchido")
        }
        try {
            const { data } = await HotelService.registerNewService(newService)
            if (data.type == "HOSPITALITY") { 
                setServices([...services, data])
            } else if(data.type == "MEAL") { 
                setMeal([...meal, data])
            }
            alert("Service criado com sucesso")
            document.getElementById('name').value = '';
            document.getElementById('price').value = '';
            document.getElementById('description').value = '';
            
        }catch (error) { 
            console.log(error)
        }
    }

        const [services, setServices] = useState([])
        const [meal, setMeal] = useState([])

        const fetchServices = async() => { 
            try { 
                const responseHospitality = await RoomService.getRoomServies("HOSPITALITY")
                console.log(responseHospitality.data)
                setServices(responseHospitality.data)
               
            } catch (error) { 
                console.log(error)
            }
        }

        const fetchMeal = async() => { 
            try { 
                const responseMeal = await RoomService.getRoomServies("MEAL")
                setMeal(responseMeal.data)
            } catch (error) { 
                console.log(error)
            }
        }

        useEffect(() => { 
            fetchServices()
        }, [])

        useEffect(() => { 
            fetchMeal()
        }, [])


    return ( 
        <div>
            <MainMenuAdmin/>
            <div class="container container-admin">
                <h1>Cadastro Serviço de Quartos e Refeições</h1>
                <div>
                    <select class="form-select" aria-label="Default select example"  onChange={(e) => setSelected(e.target.value)}>
                        <option selected value="HOSPITALITY">Serviço de quarto</option>
                        <option value="MEAL">Refeição</option>
                    </select>
                </div>
                <br/>
                <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">Nome</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" onChange={(e) => setName(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">Valor do Serviço</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control" id="price" onChange={(e) => setPrice(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="description" onChange={(e) => setDescription(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                     <div class="col-sm-8 offset-6">
                        <div class="col-sm-3">
                            <button type="submit" class="form-control" id="button" onClick={(e) => onClicked()}>Cadastrar</button>
                        </div>
                     </div>
                 </div>
                 <div>
                    <h2 class="align-element text-styled">Serviços Cadastrados</h2>
                 </div>
                 <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center" id="solicitados">
                    {services.map(s => 
                    <div class="col" key={s.id}>
                        <div class="col">
                            <div class="card mb-3 rounded-3 shadow-sm">
                                <div class="card-header py-3">
                                    <h4 class="my-0 fw-normal">{s.name}</h4>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title pricing-card-title">{s.description}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                )}
                 {meal.map(m => 
                    <div class="col" key={m.id}>
                        <div class="col">
                            <div class="card mb-3 rounded-3 shadow-sm">
                                <div class="card-header py-3">
                                    <h4 class="my-0 fw-normal">{m.name}</h4>
                                </div>
                                <div class="card-body">
                                    <h4 class="card-title pricing-card-title">{m.description}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                )}

                
            </div>
            </div>
        </div>
    )
}