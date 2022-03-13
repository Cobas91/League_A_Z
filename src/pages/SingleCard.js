import * as React from 'react';
import ChampionCardWithStats from "../components/ChampionCardWithStats";
import {useEffect} from "react";
import styled from "styled-components/macro";

export default function SingleCard({champs, editChamp, setOnlyUnplayable, onlyUnplayable}) {
    useEffect(() => {
        setOnlyUnplayable(true)
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