import axios from "axios";
import {toast} from "react-toastify";

const API_handleLogin = (credentials) => {
    console.log("Login for:", credentials)
    return axios
        .post('/auth/login', credentials)
        .then(response => response)
        .catch(err => toast.error("Falscher Username oder Passwort"))
}

const API_handleRegister = (credentials) => {
    return axios
        .post('/auth/register', credentials)
        .then(response => response)
        .catch(err => toast.error("Etwas ist schiefgelaufen..."))
}

export {API_handleLogin, API_handleRegister}