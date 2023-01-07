import * as React from 'react';
import {useEffect, useState} from 'react';
import styled from "styled-components/macro";
import ChampionCardRandom from "../components/ChampionCardRandom";
import {API_getRandomChamp} from "../../service/ChampionService";

export default function RandomCard() {
    const [randomChamp, setRandomChamp] = useState({});
    useEffect(() => {
        getRandomChamp()
    }, []);

    const getRandomChamp = () => {
        API_getRandomChamp().then((res) => {
            console.log(res.data + "asd")
            setRandomChamp(res.data)
        })
    }

    return (
        <SingleCardContainer>
            <ChampionCardRandom champ={randomChamp} key={randomChamp?.own_id} getRandomChamp={getRandomChamp}/>
        </SingleCardContainer>
    )
}

const SingleCardContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
`