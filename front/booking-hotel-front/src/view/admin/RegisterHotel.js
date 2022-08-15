import React from "react"
import { HotelService } from "../../api/HotelService"
import { MainMenuAdmin } from "../../components/MainMenuAdmin"

export const RegisterHotel = () => { 

    let newHotel = {}
    newHotel.cnpj = ""
    newHotel.name = ""
    newHotel.numberBedrooms = ""
    newHotel.city = ""

    const onClicked = async(e) => { 
       try { 
        newHotel.cnpj = newHotel.cnpj.replace(/[^\d]+/g,'')

        if (newHotel.cnpj.length != 14) { 
            alert("O cnpj deve conter 14 digitos")
            return
        }

        if (newHotel.numberBedrooms == 0) { 
            alert("A quantidade de quartos deve ser maior a zero")
            return
        }

        if (newHotel.name.length == 0 || newHotel.city.length == 0) { 
            alert("O campo name e o campo cidade devem ser preenchidos")
            return
        }
           const reponse = await HotelService.registerNewHotel(newHotel)
           if (reponse.status === 200) { 
               alert("Hotel cadastrado com sucesso")
               document.getElementById('name').value = '';
               document.getElementById('cnpj').value = '';
               document.getElementById('city').value = '';
               document.getElementById('qt-room').value = '';
           }
       } catch(error) { 
           console.log(error)
           document.getElementById('name').value = '';
           document.getElementById('cnpj').value = "";
           document.getElementById('city').value = '';
           document.getElementById('qt-room').value = '';
       }
    }

    const setName = (e) => { 
        newHotel.name = e.target.value
    }

    const setCnpj = (e) => { 
        newHotel.cnpj = e.target.value
    }

    const setQuantityRoom = (e) => { 
        newHotel.numberBedrooms = e.target.value
    }


    const setCity = (e) => { 
        newHotel.city = e.target.value
    }

    return(
        <div>
            <MainMenuAdmin/>
            <div class="container container-admin">
                 <h1>Cadastro de notel</h1>
                 <div class="mb-3 row">
                    <label for="name" class="col-sm-1 col-form-label">Nome</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="name" onChange={(e) => setName(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="cnpj" class="col-sm-1 col-form-label">Cnpj</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control" id="cnpj" onChange={(e) => setCnpj(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="qt-room" class="col-sm-1 col-form-label">QT Quartos</label>
                    <div class="col-sm-6">
                        <input type="number" class="form-control" id="qt-room" onChange={(e) => setQuantityRoom(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                    <label for="city" class="col-sm-1 col-form-label">Cidade</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="city" onChange={(e) => setCity(e)}></input>
                    </div>
                 </div>
                 <div class="mb-3 row">
                     <div class="col-sm-8 offset-5">
                        <div class="col-sm-3">
                            <button type="submit" class="form-control" id="button" onClick={(e) => onClicked()}>Cadastrar</button>
                        </div>
                     </div>
                 </div>
            </div>
        </div>
                
    )
}