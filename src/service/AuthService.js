import axios from "axios";

const API_handleLogin = (credentials) => {
    console.log("Login for:", credentials)
    return axios
        .post('/auth/login', credentials)
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_handleRegister = (credentials) => {
    return axios
        .post('/auth/register', credentials)
        .then(response => response.data)
        .catch(err => console.error(err))
}

export {API_handleLogin, API_handleRegister}