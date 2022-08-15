
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
import { RoomServiceHotel } from "./view/RoomServiceHotel";
import { MealHotel } from "./view/MealHotel";
import { BookingHotel } from "./view/BookingHotel";
import { Check } from "./view/Check";
import { CheckOut } from "./view/CheckOut";
import { LoginAdmin } from "./view/admin/LoginAdmin";
import { RegisterNewAccountAdmin } from "./view/admin/RegisterNewAccountAdmin";
import { HomeAdmin } from "./view/admin/HomeAdmin";
import { RegisterHotel } from "./view/admin/RegisterHotel";
import { RegisterRoom } from "./view/admin/RegisterRoom";
import { RegisterServiceAdmin } from "./view/admin/RegisterServiceAdmin";
import { CheckInAdmin } from "./view/admin/CheckInAdmin";
import { CheckOutAdmin } from "./view/admin/CheckOutAdmin";

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
              <Route path="/room-service" element = {<RoomServiceHotel/>}></Route>
              <Route path="/room-meal" element = {<MealHotel/>}></Route>
              <Route path="/booking" element = {<BookingHotel/>}></Route>
              <Route path="/check-in" key="check-in" element = {<Check checkType = "check-in"/>}></Route>
              <Route path="/check-out" key="check-out" element = {<CheckOut checkType = "check-out"/>}></Route>
             

              <Route path="/admin/login" element={<LoginAdmin/>}/>
              <Route path="/admin/register" element={<RegisterNewAccountAdmin/>}/>
              <Route path="/admin/home" element={<HomeAdmin/>} />
              <Route path="/admin/register/hotel" element={<RegisterHotel/>}/>
              <Route path="/admin/register/room" element={<RegisterRoom/>}/>
              <Route path="/admin/register/service" element={<RegisterServiceAdmin/>}/>
              <Route path="/admin/check-in" element={<CheckInAdmin/>}/>
              <Route path="/admin/check-out" element={<CheckOutAdmin/>}/>
              <Route path='*' element={<Notfound/>}></Route>
            </Routes>
          </BrowserRouter>
      </div>
  )
}

export default App;
