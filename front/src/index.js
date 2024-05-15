import React, {createContext, useState} from 'react';
import ReactDOM from 'react-dom/client';
import App from './app/App';
import AppStore from "./shared/store/AppStore";

const root = ReactDOM.createRoot(document.getElementById('root'));
const store = new AppStore();

export const Context = createContext({
    store,
});

root.render(
    <Context.Provider value={{store}}>
        <App  />
    </Context.Provider>
);
