import * as React from 'react';
import SingleChampionCard from "../components/SingleChampionCard";
import {useEffect} from "react";
import styled from "styled-components";

export default function SingleCard({champs, editChamp, setOnlyUnplayable, onlyUnplayable}) {
    useEffect(() => {
        setOnlyUnplayable(!onlyUnplayable)
    }, []);

    return (
        <SingleCardContainer>
            <SingleChampionCard champ={champs[0]} key={champs[0]?.id} editChamp={editChamp}/>
        </SingleCardContainer>
    )
}

const SingleCardContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
`