import * as React from 'react';
import {useContext} from 'react';
import styled from "styled-components/macro";
import {Link, useNavigate} from "react-router-dom";
import {confirmAlert} from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css'
import {FiLogOut} from "react-icons/fi";
import {AuthContext} from "../../security/AuthProvider";
import LanguageSelector from "./LanguageSelector";

export default function HeadNavBar() {
    const {logout, username} = useContext(AuthContext)
    const navigate = useNavigate();

    const handleLogout = ()=>{
        logout()
        navigate("/")
    }
    return (
        <NavbarContainer>
            <SiteNavigation>
                <StyledLink to="/home">All Champs</StyledLink>
                <StyledLink to="/singleCard">SingleCard</StyledLink>
                <StyledLink to="/overview">Overview</StyledLink>
            </SiteNavigation>
            <LanguageSelector/>
            <StyledUserContainer>
                <StyledUsernameText>{username ? username : ''}</StyledUsernameText>
                <StyledLogutButton onClick={handleLogout}/>
            </StyledUserContainer>
        </NavbarContainer>
    )
}


const StyledUserContainer = styled.section`
  display: flex;
  align-items: center;
`

const StyledUsernameText = styled.label`
    font-size: small;
`

const StyledLogutButton = styled(FiLogOut)`
  color: black;
  width: 2vw;
  height: 2vh;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  :hover{
    color: red;
    box-shadow: grey;
  }
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

  :hover {
    color: red;
    box-shadow: grey;
  }
`

const SiteNavigation = styled.section`
  display: flex;
  justify-content: space-around;
`


const NavbarContainer = styled.section`
  position: fixed;
  width: 100%;
  height: 80px;
  display: grid;
  grid-template-columns: 80vw  30px 100px;
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