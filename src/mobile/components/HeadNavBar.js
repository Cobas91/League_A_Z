import * as React from 'react';
import {useContext, useState} from 'react';
import styled from "styled-components/macro";
import {Link, useNavigate} from "react-router-dom";
import 'react-confirm-alert/src/react-confirm-alert.css'
import {FiAlignRight, FiLogOut} from "react-icons/fi";
import {AuthContext} from "../../security/AuthProvider";
import LanguageSelector from "./LanguageSelector";
import {GiCardBurn, GiCardJoker, GiCardPick} from "react-icons/gi";

export default function HeadNavBar() {
    const {logout, username} = useContext(AuthContext)
    const [showMenu, setShowMenu] = useState(false);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout()
        navigate("/")
    }
    const toggleMenu = () => {
        setShowMenu(!showMenu)
    }
    const Menu = (props) => {
        const showMenu = props.showMenu;
        if (showMenu) {
            return (<StyledMenuContainer>
                <LanguageSelector/>
                <StyledUserContainer>
                    <StyledLogutButton onClick={handleLogout}/>
                </StyledUserContainer>
            </StyledMenuContainer>)
        }
        return null;
    }
    return (
        <NavbarContainer>
            <SiteNavigation>
                <StyledLink to="/home"><StyledAllCardsIcon/></StyledLink>
                <StyledLink to="/singleCard"><StyledSingleCardIcon/></StyledLink>
                <StyledLink to="/overview"><StyledOverviewIcon/></StyledLink>
            </SiteNavigation>
            <StyledBurgerMenuIcon onClick={toggleMenu}/>
            <Menu showMenu={showMenu}/>

        </NavbarContainer>
    )
}
const StyledMenuContainer = styled.section`
    position: fixed;
  left: 60%;
  bottom: 5%;
  background-color: white;
  width: 150px;
  height: 15%;
  border-radius: 10px;
  display: flex;
  justify-content: left;
  align-items: start;
  gap:30px;
  padding: 15px;
`

const StyledBurgerMenuIcon = styled(FiAlignRight)`
  font-size:30px;
`

const StyledAllCardsIcon = styled(GiCardBurn)`
  font-size: 30px;
`

const StyledSingleCardIcon = styled(GiCardJoker)`
  font-size: 30px;
`

const StyledOverviewIcon = styled(GiCardPick)`
  font-size: 30px;
`

const StyledUserContainer = styled.section`
  display: flex;
  align-items: center;
`

const StyledUsernameText = styled.label`
    font-size: small;
`

const StyledLogutButton = styled(FiLogOut)`
  color: black;
  font-size: 30px;
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
  justify-content: space-between;
`


const NavbarContainer = styled.section`
  position: fixed;
  top: 95%;
  width: 100%;
  height: 5%;
  display: flex;
  grid-template-columns: 80vw  30px 100px 30px;
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