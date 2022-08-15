import React, { useEffect, useState } from "react"
import { HotelService } from "../../api/HotelService"
import { MainMenuAdmin } from "../../components/MainMenuAdmin"

export const RegisterRoom = () => { 

    let newRoom =  { 
        "rentValue": 0,
        "quantity": 0,
        "description": "",
        "type": ""
    }

    const setType = (e) => { 
        newRoom.type = e.target.value
    }

    const setRentValue = (e) => { 
        newRoom.rentValue = e.target.value
    }

    const setQtRoom = (e) => { 
        newRoom.quantity = e.target.value
    }

    const setDescription = (e) => { 
        newRoom.description = e.target.value
    }

    const onClicked = async() => { 
       
        if (selectedOption.length == 0) { 
            alert("Precisa selecionar uma hospedagem válida")
            return
        }
        if (newRoom.type.length == 0) { 
            alert("É necessário cadastrar o tipo de quarto")
            return
        }
        if (newRoom.rentValue == 0) { 
            alert("É necessário cadastrar o valor da hospedagem")
            return
        }
        if (newRoom.quantity == 0) { 
            alert("É necessário cadastrar a quantidade de quartos")
            return
        }

        if (newRoom.description == 0) { 
            alert("É necessário cadastrar uma descrição")
            return
        }

        try { 
            const { data, status } = await HotelService.registerNewRoom(newRoom, selectedOption)
            if (status == 200) { 
                alert("Cadastro realizado com sucesso")
                document.getElementById('type').value = '';
                document.getElementById('rentvalue').value = '';
                document.getElementById('qt-roomm').value = '';
                document.getElementById('description').value = '';
                document.getElementById('name').value = '';
            }
        } catch (error) { 
            console.log(error)
        }
        
       
    }

    const [selectedOption, setSelectedOption] = useState([])
    const [hotel, setHotel] = useState([])

    const fetchHotel = async () => { 
        try {
            const {data} = await HotelService.getHotelAdmin()
            setHotel(data)
           
        } catch (e) { 
            console.log(e)
        }
    }

    useEffect(() => { 
        fetchHotel()
    }, [])

    return ( 
        <div>
            <MainMenuAdmin/>
            <div class="container container-admin">
                <h1>Cadastro de Quartos</h1>
                <div class="mb-3 row">
                    <div>
                        <select class="form-select" aria-label="Default select example"  onChange={(e) => setSelectedOption(e.target.value)}>
                        <option selected>Selecione um hotel para iniciar o cadastro</option>
                            {hotel.map(b => 
                                <option value={b.id} key={b.id}>{b.name}  {b.id} </option>
                                )}
                        </select>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">Tipo de Quarto</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="type" onChange={(e) => setType(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">Valor Hospedagem</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control" id="rentvalue" onChange={(e) => setRentValue(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-2 col-form-label">QT Quarto</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control" id="qt-roomm" onChange={(e) => setQtRoom(e)}></input>
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
            </div>
        </div>
       
    )

}