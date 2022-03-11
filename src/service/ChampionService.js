import axios from 'axios'

const API_editChampion = (champ)=>{
    return axios
        .post('http://localhost:8080/api/champions/edit', champ)
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_getAllChamps = ()=>{
    return axios
        .get('http://localhost:8080/api/champions')
        .then(response => response.data)
        .catch(err => console.error(err))
}

const API_getAllUnplayedChampions = ()=>{
    return axios
        .get('http://localhost:8080/api/champions/played/false')
        .then(response => response.data)
        .catch(err => console.error(err))
}


export {API_editChampion, API_getAllChamps, API_getAllUnplayedChampions}