
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import { DetailsHotel } from "./view/DetailsHotel";
import { Home } from "./view/Home";
import { Login } from "./view/Login";
import Notfound from "./view/NotFound";
import NotFoundItems from "./view/NotFoundItems";
import { RegisterNewAccount } from "./view/RegisterNewAccount";
import React from 'react';

function App() {
  return (
      <div>
        <BrowserRouter>
            <Routes>
              <Route path="/home" element={<Home/>}/>
              <Route path="/login" element={<Login/>} />
              <Route path="/register" element = {<RegisterNewAccount/> } />
              <Route path="/details/:id" element={<DetailsHotel/>}></Route>
              <Route path="/not-found" element = {<NotFoundItems/>}></Route>
              <Route path='*' element={<Notfound/>}></Route>
            </Routes>
          </BrowserRouter>
      </div>
  )
}

export default App;
