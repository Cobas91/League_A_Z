import * as React from 'react';
import {useEffect, useState} from 'react';
import ChampionCardWithStats from "../components/ChampionCardWithStats";
import styled from "styled-components/macro";
import {API_editChampion, API_getAllChampsFiltered} from "../../service/ChampionService";

export default function SingleCard() {
    const [champs, setChamps] = useState([])
    useEffect(()=>{
        getAllChamps()
    },[])

    const editChamp = (champ)=>{
        API_editChampion(champ).then(getAllChamps)
    }

    const getAllChamps = ()=>{
        API_getAllChampsFiltered(false).then(res => setChamps(res.data))

    }

    return (
        <SingleCardContainer>
            <ChampionCardWithStats champ={champs[0]} key={champs[0]?.id} editChamp={editChamp}/>
        </SingleCardContainer>
    )
}

const SingleCardContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
`