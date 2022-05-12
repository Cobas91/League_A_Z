import axios from "axios";
import {toast} from "react-toastify";

const API_handleLogin = (credentials) => {
    return axios
        .post('/auth/login', credentials)
        .then(res => res)
        .catch(err => toast.error("Falscher Username oder Passwort"))
}

const API_handleRegister = (credentials) => {
    return axios
        .post('/auth/register', credentials)
        .then(res => res)
        .catch(err => toast.error("Etwas ist schiefgelaufen..."))
}

export {API_handleLogin, API_handleRegister}