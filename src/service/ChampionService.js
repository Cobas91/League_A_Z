import axios from 'axios'
import {createHeader} from "../utils/loginHelper";

const API_editChampion = (champ) => {
    return axios
        .post('/api/champions/edit', champ, createHeader(localStorage.getItem('JWT')))
        .then(res => res)
        .catch(err => err.response)
}

const API_getAllUnplayedChampions = () => {
    return axios
        .get('/api/champions/played/false', createHeader(localStorage.getItem('JWT')))
        .then(res => res)
        .catch(err => err.response)
}
const API_getAllChampsFiltered = (played) => {
    return axios
        .get('/api/champions/played/' + played, createHeader(localStorage.getItem('JWT')))
        .then(res => res)
        .catch(err => err.response)
}

const API_resetAllChampions = ()=>{
    return axios
        .get('/api/champions/reset', createHeader(localStorage.getItem('JWT')))
        .then(res => res)
        .catch(err => err.response)
}

const API_getRandomChamp = ()=>{
    return axios
        .get('/api/champions/random', createHeader(localStorage.getItem('JWT')))
        .then(res => res)
        .catch(err => err.response)
}


export {API_editChampion, API_getAllUnplayedChampions, API_resetAllChampions, API_getRandomChamp, API_getAllChampsFiltered}