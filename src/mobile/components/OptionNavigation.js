import * as React from 'react';
import styled from "styled-components/macro";
import {confirmAlert} from "react-confirm-alert";
import {API_resetAllChampions} from "../../service/ChampionService";

export default function OptionNavigation({playable, changePlayableFilter, resetChampions}) {
    const handleCheckBox = (e) => {
        changePlayableFilter(!playable)
    }
    const handleButtonClick = () => {
        confirmAlert({
            title: 'Alle Champions zurücksetzen?',
            message: 'Alle Champions werden zurückgesetzt und du kannst die Challange von vorne beginnen.',
            buttons: [
                {
                    label: 'Ja',
                    onClick: () => resetChamps()
                },
                {
                    label: 'Nein'
                }
            ]
        })
    }
    const resetChamps = ()=>{
        resetChampions(true)
        API_resetAllChampions()
    }
    return (
        <StyledRoundedBar>
            <StyledOptionNavigationContainer>
                <SortPlayableContainer>
                    <StyledLabel>Gespielte Champions anzeigen</StyledLabel>
                    <StyledCheckbox type="checkbox" onChange={handleCheckBox} checked={playable}/>
                </SortPlayableContainer>
                <StyledButton onClick={handleButtonClick}>Alle Champions zurücksetzen</StyledButton>
            </StyledOptionNavigationContainer>
        </StyledRoundedBar>

    )
}
const StyledRoundedBar = styled.section`
  margin: 10px;
  display:flex;
  justify-content: center;
  align-items: center;
`

const StyledOptionNavigationContainer = styled.section`
  height: 50px;
  display: flex;
  background-color: ghostwhite;
  align-items: center;
  justify-content: space-around;
`
const SortPlayableContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
`
const StyledCheckbox = styled.input`
  height: 50px;
  width: 50px;
  cursor: pointer;
`
const StyledLabel = styled.label`
  margin: 0 20px 0 20px;
  font-size: 10px;
`
const StyledButton = styled.button`
  display: inline-flex;
  cursor: pointer;
  height: 3vh;
  border: 2px solid #BFC0C0;
  margin: 20px 20px 20px 20px;
  color: #000000;
  text-transform: uppercase;
  text-decoration: none;
  font-size: 10px;
  letter-spacing: 1.5px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  :hover {
    color: #2D3142;
  }
`