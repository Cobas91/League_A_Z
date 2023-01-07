import * as React from 'react';
import styled from "styled-components/macro";
import background from '../../video/background2.mp4'
import {useNavigate} from "react-router-dom";

export default function Home() {
    const navigate = useNavigate();
    const onPlayClick = () => {
        navigate("/login")
    }
    const onStatisticClick = () => {
        navigate("/overview")
    }
    return (
        <StyledHomeContainer>
            <StyledHeadline>League of Legends A-Z Mobile</StyledHeadline>
            <StyledHomeHeader>
                <StyledBackgroundVideo autoPlay loop muted>
                    <source src={background} type="video/mp4"/>
                </StyledBackgroundVideo>
            </StyledHomeHeader>
            <StyledHomeContent>
                <StyledButton onClick={onPlayClick}>Play!</StyledButton>
                <StyledButton onClick={onStatisticClick}>Show stats!</StyledButton>
            </StyledHomeContent>
            <StyledVersionLabel>Version {process.env.REACT_APP_VERSION}</StyledVersionLabel>
        </StyledHomeContainer>
    )
}
const StyledVersionLabel = styled.p`
position: absolute;
  bottom: 0;
  left: 0;
  font-size: 1rem;
`

const StyledButton = styled.button`
  width: clamp(150px, 50%, 300px);
  height: clamp(20px, 30%, 150px);
  font-size: 15px;
  font-weight: bold;
  letter-spacing: 1px;
  padding: 5px 15px;
  background: #1e2328;
  color: #cdbe91;
  box-shadow: inset 0 0 2px #000000;
  border-image: linear-gradient(to bottom, #c8aa6d, #7a5c29);
  border-image-slice: 1;
  border-width: 2px;
  :hover{
    text-shadow: 0 0 5px #ffffff80;
    box-shadow: 0 0 8px 0 #ffffff50;
    background: linear-gradient(to bottom, #1e2328, #433d2b);
    cursor: pointer;
    transition: 0.1s;
  }
  :active{
    text-shadow: none;
    box-shadow: none;
    color: #cdbe9130;
  }
`

const StyledBackgroundVideo = styled.video`
  position: absolute;
  width: 100%;
  height: 100%;
  left: 50%;
  top:50%;
  object-fit: cover;
  transform: translate(-50%, -50%);
  z-index: -1;
`
const StyledHeadline = styled.h1`
  color: white;
`

const StyledHomeContainer = styled.section`
    
`
const StyledHomeHeader = styled.section`
  
`
const StyledHomeContent = styled.section`
  width: 25vw;
  height: 200px;
  position: absolute;
  left: 40%;
  top: 40%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  flex-direction: column;
`
const StyledHomeLinkContainer = styled.section`

`