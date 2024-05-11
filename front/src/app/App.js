import React, {createContext, useEffect, useState} from 'react';
import {BrowserRouter} from "react-router-dom";
import AppRouter from "../components/AppRouter";
import {Context} from "../index";
import './styles/index.css'
import Navbar from "../shared/UI/Navbar/Navbar";
import AppStore from "../shared/store/AppStore";

function App() {

    return (
        <BrowserRouter>
            <Navbar/>
          <AppRouter/>
        </BrowserRouter>
    );
}

export default App;
