import axios from 'axios'
import {createHeader} from "../utils/loginHelper";

const API_editChampion = (champ) => {
    return axios
        .post('/api/champions/edit', champ, createHeader(localStorage.getItem('JWT')))
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_getAllChamps = () => {
    return axios
        .get('/api/champions', createHeader(localStorage.getItem('JWT')))
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_getAllUnplayedChampions = () => {
    return axios
        .get('/api/champions/played/false', createHeader(localStorage.getItem('JWT')))
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_resetAllChampions = ()=>{
    return axios
        .get('/api/champions/reset', createHeader(localStorage.getItem('JWT')))
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_getRandomChamp = ()=>{
    return axios
        .get('/api/champion/random', createHeader(localStorage.getItem('JWT')))
        .then(response => response.data)
        .catch(err => console.error(err))
}


export {API_editChampion, API_getAllChamps, API_getAllUnplayedChampions, API_resetAllChampions, API_getRandomChamp}