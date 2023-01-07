import * as React from 'react';
import styled from "styled-components/macro";
import {AiOutlineReload} from "react-icons/ai";


export default function ChampionCardRandom({champ, getRandomChamp}) {

    const handleReload = () => {
        getRandomChamp()
    }

    const stats = [];
    if (champ) {
        Object.keys(champ?.stats).forEach(function (key) {
            stats.push({name: [key], value: champ.stats[key]})
        });
    }

    return (
        <ChampionContainer>
            <StyledHeader>
                <StyledImage src={"data:image/jpeg;base64," + champ?.iconByteArray}/>
                <NameTitleContainer>
                    <Name>{champ?.name}</Name>
                    <Title>{champ?.title}</Title>
                </NameTitleContainer>
            </StyledHeader>
            <StatContainer>
                {stats?.map(item => {
                    return (
                        <StatRow key={Math.random()}>
                            <StyledStatLabel>{item.name}</StyledStatLabel>
                            <StyledStatLabel>{item.value}</StyledStatLabel>
                        </StatRow>

                    )
                })}
            </StatContainer>
            <ReloadContainer>
                <StyledReloadIcon onClick={handleReload}/>
            </ReloadContainer>

        </ChampionContainer>
    )
}
const ReloadContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`
const StyledReloadIcon = styled(AiOutlineReload)`
  font-size: 10vh;
  cursor: pointer;
  margin: 50px;
  transition: all 0.2s ease-in-out;

  :hover {
    color: #d9d9d9;
  }
`
const StatRow = styled.section`
  display: flex;
  justify-content: center;
`

const StyledStatLabel = styled.label`
  width: 15vw;
`
const StatContainer = styled.section`
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  text-align: center;
`

const ChampionContainer = styled.section`
  background-color: #5d5c5c;
  width: 50vw;
  height: 70vh;
  margin: 30px;
  padding: 10px;
  border-radius: 10px;

`

const StyledImage = styled.img`
  border-radius: 15px;
`

const StyledHeader = styled.section`
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
`

const Name = styled.label`
  font-weight: bold;
  font-size: x-large;
`

const Title = styled.label`
  font-weight: initial;
  font-size: medium;
`

const NameTitleContainer = styled.section`
  display: flex;
  align-items: center;
  flex-direction: column;
`