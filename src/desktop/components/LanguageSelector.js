import * as React from 'react';
import styled from "styled-components/macro";
import {useTranslation} from "react-i18next";

export default function LanguageSelector() {
    const [t, i18n] = useTranslation();
    const changeLanguageDE = () => {
        i18n.changeLanguage("de")
    }
    const changeLanguageEN = () => {
        i18n.changeLanguage("en")
    }
    return (
        <StyledLanguageContainer>
            <StyledLanguageButton onClick={changeLanguageDE} selected={i18n.language === "de"}>DE</StyledLanguageButton>
            /
            <StyledLanguageButton onClick={changeLanguageEN} selected={i18n.language === "en"}>EN</StyledLanguageButton>
        </StyledLanguageContainer>
    )
}
const StyledLanguageButton = styled.div`
  cursor: pointer;
  :hover{
    color: red;
  }
  text-decoration: ${props => props.selected ? 'underline red 2px' : 'none '} ;
`
const StyledLanguageContainer = styled.section`
  display: flex;
`