import * as React from 'react';
import styled from "styled-components";
import { Link } from "react-router-dom";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'
export default function HeadNavBar({onlyUnplayable, setOnlyUnplayable, resetAllChampions}) {
    const handleCheckBox = (e) => {
        setOnlyUnplayable(!onlyUnplayable)
    }
    const handleButtonClick = ()=>{
        confirmAlert({
            title: 'Alle Champions zurücksetzen?',
            message: 'Alle Champions werden zurückgesetzt und du kannst die Challange von vorne beginnen.',
            buttons: [
                {
                    label: 'Ja',
                    onClick: () => resetAllChampions()
                },
                {
                    label: 'Nein'
                }
            ]
        })
    }
    return (
        <NavbarContainer>
            <SideNavigation>
                <StyledLink to="/">All Champs</StyledLink>
                <StyledLink to="/singleCard">SingleCard</StyledLink>
                <StyledLink to="/randomCard">Random Card</StyledLink>
            </SideNavigation>
            <SortPlayableContainer>
                <StyledLabel>Nur ungespielte Champions anzeigen</StyledLabel>
                <StyledCheckbox type="checkbox" onChange={handleCheckBox} checked={onlyUnplayable}/>
            </SortPlayableContainer>
            <StyledButton onClick={handleButtonClick}>Alle Champions zurücksetzen</StyledButton>
        </NavbarContainer>
    )
}

const StyledButton = styled.button`
  display: inline-flex;
  cursor: pointer;
  height: 40px;
  border: 2px solid #BFC0C0;
  margin: 20px 20px 20px 20px;
  color: #000000;
  text-transform: uppercase;
  text-decoration: none;
  font-size: .8em;
  letter-spacing: 1.5px;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  :hover{
    color: #2D3142;
  }
`
const StyledLabel = styled.label`
  margin: 0 20px 0 20px;
`

const StyledLink = styled(Link)`
  color: #000000;
  text-decoration: none;
  font-size: 1.2em;
  text-transform: uppercase;
  display: inline-block;
  -webkit-transition: all 0.2s ease-in-out;
  transition: all 0.2s ease-in-out;
  margin: 0 20px 0 20px;
  :hover{
    color: red;
    box-shadow: grey;
  }
`

const SideNavigation = styled.section`
  display: flex;
  justify-content: space-between;
`

const SortPlayableContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;

`

const NavbarContainer = styled.section`
  position: fixed;
  width: 100%;
  height: 5vh;
  display: grid;
  grid-template-columns: 33vw 33vw 300px;
  justify-content: space-around;
  background-color: ghostwhite;
  align-items: center;
  z-index: 1;
`

const StyledCheckbox = styled.input`
  height: 20px;
  width: 20px;
  cursor: pointer;
`