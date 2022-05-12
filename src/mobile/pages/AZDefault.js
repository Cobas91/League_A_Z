import * as React from 'react';
import {useEffect, useState} from 'react';
import ChampionCard from "../components/ChampionCard";
import styled from "styled-components/macro";
import {API_editChampion, API_getAllChampsFiltered} from "../../service/ChampionService";

export default function AzDefault({playable, reloadChamps}) {
    const [champs, setChamps] = useState([]);
    useEffect(()=>{
        getAllChamps()
    },[reloadChamps])
    const editChamp = (champ)=>{
        API_editChampion(champ).then(getAllChamps)
    }
    const getAllChamps = ()=>{
        API_getAllChampsFiltered(playable).then(res => setChamps(res.data))
    }
    return (
        <CardContainer>
            {champs?.map(item => (<ChampionCard champ={item} key={item.id} editChamp={editChamp}/>))}
        </CardContainer>
    )
}
const CardContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
`