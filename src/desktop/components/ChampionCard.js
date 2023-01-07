import * as React from 'react';
import styled from "styled-components/macro";
import {MdDoubleArrow} from "react-icons/md";

export default function ChampionCard({champ, editChamp}) {


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

    return (
        <ChampionContainer>
            <StyledHeader>
                <StyledImage src={"data:image/jpeg;base64," + champ?.iconByteArray}/>
                <NameTitleContainer>
                    <Name>{champ?.name}</Name>
                    <Title>{champ?.title}</Title>
                </NameTitleContainer>
            </StyledHeader>
            <PlayBox>
                <StyledHeadline>Gespielt</StyledHeadline>
                <StyledCheckbox type="checkbox" onChange={handleCheckBox} checked={champ?.played}/>
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
            </Statistics>
        </ChampionContainer>
    )
}

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
  cursor: pointer;
`

const PlayBox = styled.section`
  margin-top: 20px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
`

const StyledHeadline = styled.label`
  font-weight: bold;
  font-size: 20px;
`

const LooseContainer = styled.section`
  border: black 1px solid;
  height: clamp(150px, 100%, 400px);
  width: clamp(150px, 100%, 400px);
  padding: 5px;
  display: grid;
  grid-template-rows: 40px 1fr;
`

const Statistics = styled.section`
  margin-top: 50px;
  height: 100px;
  width: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
`

const ChampionContainer = styled.section`
  background-color: #5d5c5c;
  width: 400px;
  height: 400px;
  margin: 30px;
  padding: 10px;
  border-radius: 10px;
`

const StyledImage = styled.img`
  border-radius: 15px;
`

const StyledHeader = styled.section`
  display: flex;
  justify-content: space-evenly;
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