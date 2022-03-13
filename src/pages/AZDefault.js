import * as React from 'react';
import ChampionCard from "../components/ChampionCard";
import styled from "styled-components/macro";

export default function AzDefault({editChamp, champs}) {

    return (
        <CardContainer>
            {champs.map(item => (<ChampionCard champ={item} key={item.id} editChamp={editChamp}/>))}
        </CardContainer>
    )
}
const CardContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
`