import * as React from 'react';
import styled from "styled-components/macro";
import {MdDoubleArrow} from "react-icons/md";


export default function ChampionCardWithStats({champ, editChamp}) {


    const handleCheckBox = (e) => {
        champ = {...champ, played: !champ?.played}
        editChamp(champ)
    }

    const handleLooseArrowUp = (e) => {
        champ = {...champ, loose: champ?.loose + 1}
        editChamp(champ)
    }

    const handleLooseArrowDown = (e) => {
        champ = {...champ, loose: champ?.loose > 0 ? champ.loose - 1 : 0}
        editChamp(champ)
    }

    const stats = [];
    if(champ){
        Object.keys(champ?.stats).forEach(function(key) {
            stats.push({name:[key], value:champ.stats[key]})
        });
    }


    return (
        <ChampionContainer>
            <StyledHeader>
                <StyledImage src={champ?.icon}/>
                <NameTitleContainer>
                    <Name>{champ?.name}</Name>
                    <Title>{champ?.title}</Title>
                </NameTitleContainer>
            </StyledHeader>
            <PlayBox>
                <StyledHeadline>Gespielt</StyledHeadline>
                <StyledCheckbox type="checkbox" onClick={handleCheckBox} defaultChecked={champ?.played}/>
            </PlayBox>
            <Statistics>
                <LooseContainer>
                    <StyledHeadline>
                        Looses
                    </StyledHeadline>
                    <ArrowContainer>
                        <StyledHeadline>{champ?.loose}</StyledHeadline>
                        <div>
                            <ArrowUp onClick={handleLooseArrowUp}/>
                            <ArrowDown onClick={handleLooseArrowDown}/>
                        </div>
                    </ArrowContainer>
                </LooseContainer>
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
            </Statistics>
        </ChampionContainer>
    )
}
const StatRow = styled.section`
    display: flex;
  justify-content: space-between;
`

const StyledStatLabel = styled.label`
  width: 15vw;
`
const StatContainer = styled.section`
    display: flex;
  align-items: center;
  flex-direction: column;
  width: 50vw;
  text-align: right;
`

const ArrowContainer = styled.section`
  align-items: center;
  display: grid;
  grid-template-columns: 1fr 1fr;
  justify-items: center;
  align-content: center;
  justify-content: center;
`

const ArrowUp = styled(MdDoubleArrow)`
  transform: rotate(270deg);
  font-size: 50px;
  cursor: pointer;
`

const ArrowDown = styled(MdDoubleArrow)`
  transform: rotate(90deg);
  font-size: 50px;
  cursor: pointer;
`

const StyledCheckbox = styled.input`
  height: 20px;
  width: 20px;
`

const PlayBox = styled.section`
  margin-top: 20px;
  display: grid;
  grid-template-columns: 150px 1fr;
  align-items: center;
`

const StyledHeadline = styled.label`
  font-weight: bold;
  font-size: 20px;
`

const LooseContainer = styled.section`
  border: black 1px solid;
  height: clamp(150px, 60vh, 400px);
  width: 50vw;
  padding: 5px;
  display: grid;
  grid-template-rows: 40px 1fr;
`

const Statistics = styled.section`
  margin-top: 50px;
  height: 5vh;
  width: 100%;
  display: flex;
  justify-content: center;
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