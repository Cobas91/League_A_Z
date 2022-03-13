import * as React from 'react';
import {useEffect} from "react";
import styled from "styled-components/macro";
import ChampionCardRandom from "../components/ChampionCardRandom";

export default function RandomCard({champ, editChamp, getRandomChamp}) {
    useEffect(() => {
        getRandomChamp()
    }, []);

    return (
        <SingleCardContainer>
            <ChampionCardRandom champ={champ} key={champ?.id} getRandomChamp={getRandomChamp}/>
        </SingleCardContainer>
    )
}

const SingleCardContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
`