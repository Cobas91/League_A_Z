import React from 'react';
import ReactDOM from 'react-dom';
import DesktopApp from './DesktopApp';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter} from "react-router-dom";
import AuthProvider from './security/AuthProvider'
import { isBrowser} from 'react-device-detect';
import MobileApp from "./MobileApp";

ReactDOM.render(
    <React.StrictMode>
        <BrowserRouter>
            <AuthProvider>
                {isBrowser ? <DesktopApp/> : <MobileApp/>}
            </AuthProvider>
        </BrowserRouter>

    </React.StrictMode>,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
