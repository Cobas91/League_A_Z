import * as React from 'react';
import {useEffect} from 'react';
import ChampionCardWithStats from "../components/ChampionCardWithStats";
import styled from "styled-components/macro";

export default function SingleCard({champs, editChamp, changePlayableFilter}) {
    useEffect(() => {
        changePlayableFilter(false)
    }, []);

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