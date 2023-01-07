import React from 'react';
import {Navigate, Outlet} from 'react-router-dom';
import {isExpired} from "react-jwt";

const PrivateRoute = () => {
    const tokenIsValid = !isExpired(localStorage.getItem('JWT')) // determine if authorized, from context or however you're doing it

    // If authorized, return an outlet that will render child elements
    // If not, return element that will navigate to login page
    return tokenIsValid ? <Outlet/> : <Navigate to="/login"/>;
}

export default PrivateRoute;