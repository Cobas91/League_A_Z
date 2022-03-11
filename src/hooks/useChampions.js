import {API_editChampion, API_getAllChamps, API_getAllUnplayedChampions, API_resetAllChampions} from "../service/ChampionService";
import {useEffect, useState} from "react";

export default function UseChampions() {
    const [onlyUnplayable, setOnlyUnplayable] = useState(false)
    const [champs, setChamps] = useState([])

    useEffect(() => {
        if (onlyUnplayable) {
            API_getAllUnplayedChampions().then((res) => {
                setChamps(res)
            })
        } else {
            API_getAllChamps().then((res) => {
                setChamps(res)
            })
        }
    }, [onlyUnplayable]);


    const editChamp = (champ) => {
        API_editChampion(champ).then(() => {
            API_getAllChamps().then(res => setChamps(res))
        })
    }

    const editChampSingleCard = (champ) => {
        API_editChampion(champ).then(() => {
            API_getAllUnplayedChampions().then(res => setChamps(res))
        })
    }
    const resetAllChampions = ()=>{
        API_resetAllChampions().then((res) => setChamps(res))
    }

    return {
        editChamp, champs, setOnlyUnplayable, onlyUnplayable, editChampSingleCard, resetAllChampions
    }
}


