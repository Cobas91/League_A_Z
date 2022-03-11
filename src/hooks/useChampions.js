import {API_editChampion, API_getAllChamps, API_getAllUnplayedChampions} from "../service/ChampionService";
import {useEffect, useState} from "react";

export default function UseChampions() {
    const [onlyUnplayed, setOnlyUnplayed] = useState(false)
    const [champs, setChamps] = useState([])

        useEffect(() => {
            if (onlyUnplayed) {
                API_getAllUnplayedChampions().then((res)=>{setChamps(res)})
            } else {
                API_getAllChamps().then((res)=>{setChamps(res)})
            }
        },[]);






    const editChamp = (champ)=>{
        console.log(`Edit Champ ${champ.name}`, champ)
        API_editChampion(champ).then(() => {
            API_getAllChamps().then(res => setChamps(res))
        })
    }

    return {
        editChamp, champs, setOnlyUnplayed
    }
}


