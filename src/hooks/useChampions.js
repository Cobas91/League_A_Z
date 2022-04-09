import {API_editChampion, API_getAllChampsFiltered, API_getAllUnplayedChampions, API_getRandomChamp, API_resetAllChampions} from "../service/ChampionService";
import {useEffect, useState} from "react";

export default function UseChampions() {
    const [playable, setPlayable] = useState(false)
    const [champs, setChamps] = useState([])
    const [randomChamp, setRandomChamp] = useState([])
    //TODO useChampion in useChampion, useRandomChampion und useSingleChamion aufspalten
    useEffect(() => {
        API_getAllChampsFiltered(playable).then((res) => {
            setChamps(res)
        })
    }, [playable]);

    useEffect(() => {
        getRandomChamp();
    }, []);

    const changePlayableFilter = (newState) => {
        setPlayable(newState)
        API_getAllChampsFiltered(playable).then((res) => setChamps(res))
    }

    const getRandomChamp = () => {
        API_getRandomChamp().then((res) => {
            setRandomChamp(res)
        })
    }

    const editChamp = (champ) => {
        API_editChampion(champ).then(() => {
            API_getAllChampsFiltered(playable).then(res => setChamps(res))
        })
    }

    const editChampSingleCard = (champ) => {
        API_editChampion(champ).then(() => {
            API_getAllUnplayedChampions().then(res => setChamps(res))
        })
    }

    const editRandomChamp = (champ)=>{
        API_editChampion(champ).then(() => {
            getRandomChamp()
        })
    }
    const resetAllChampions = ()=>{
        API_resetAllChampions().then((res) => setChamps(res))
    }

    return {
        editChamp, champs, playable, editChampSingleCard, resetAllChampions, randomChamp, getRandomChamp, editRandomChamp, changePlayableFilter
    }
}


