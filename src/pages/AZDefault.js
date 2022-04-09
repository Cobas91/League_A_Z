import * as React from 'react';
import {useEffect, useState} from 'react';
import ChampionCard from "../components/ChampionCard";
import styled from "styled-components/macro";

export default function AzDefault({editChamp, champs}) {
    const [champions, setChampions] = useState();
    useEffect(() => {
        setChampions(champs)
    }, [champs])
    return (
        <CardContainer>
            {champions?.map(item => (<ChampionCard champ={item} key={item.id} editChamp={editChamp}/>))}
        </CardContainer>
    )
}
const CardContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
`