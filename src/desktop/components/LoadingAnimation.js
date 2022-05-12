import * as React from 'react';
import styled from "styled-components/macro";
import spinner from '../../images/downloadSpinner.gif'

export default function LoadingAnimation() {
    return (
        <StyledLoadingContainer>
            <StyledImage src={spinner}/>
            <StyledText>Login wird verarbeitet...</StyledText>
        </StyledLoadingContainer>
    )
}

const StyledText = styled.p`
  color: white;
`

const StyledImage = styled.img`
  background-color: transparent;
`

const StyledLoadingContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  z-index: 5000;
  background-color: black;
`