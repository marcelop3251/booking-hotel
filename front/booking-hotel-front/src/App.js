
import React from "react";
import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import { DetailsHotel } from "./view/DetailsHotel";
import { Home } from "./view/Home";
import { Login } from "./view/Login";

function App(props) {
  return (
      <div>
        <BrowserRouter>
            <Routes>
              <Route path="/home" element={<Home/>}/>
              <Route path="/login" element={<Login/>} />
              <Route path="/details" element={<DetailsHotel/>}></Route>
            </Routes>
          </BrowserRouter>
      </div>
  )
}

export default App;
