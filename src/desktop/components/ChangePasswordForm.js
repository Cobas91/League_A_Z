import * as React from 'react';
import background from "../../video/background3.mp4";
import styled from "styled-components/macro";
import {InputText} from "primereact/inputtext";

export default function ChangePasswordForm({handleReset, handleInput, handleRepeatedPasswordChange}) {

    return (
        <RegisterForm onSubmit={handleReset}>
            <StyledBackgroundVideo autoPlay loop muted>
                <source src={background} type="video/mp4"/>
            </StyledBackgroundVideo>
            <InputContainer>
                <p>Hier kannst du dein Password zurücksetzen</p>
                <StyledInput required={true} id="password" placeholder="Password" type="password" onChange={handleInput}/>
                <StyledInput required={true} id="password_repeat" placeholder="Repeat Password" type="password" onChange={handleRepeatedPasswordChange}/>
            </InputContainer>
            <ButtonArea>
                <StyledRegisterButton type="submit" onSubmit={handleReset}>Passwort zurücksetzen</StyledRegisterButton>
            </ButtonArea>
        </RegisterForm>
    )
}

const ButtonArea = styled.section`
  display: flex;
  justify-content: space-evenly;
`
const StyledBackgroundVideo = styled.video`
  position: absolute;
  width: 100%;
  height: 100%;
  left: 50%;
  top: 50%;
  object-fit: cover;
  transform: translate(-50%, -50%);
  z-index: -1;
`

const RegisterForm = styled.form`
  display: flex;
  justify-content: center;
  flex-direction: column;
  background-color: #1e2328;
  border-radius: 3vw;
  align-items: center;
  padding: 40px
`

const InputContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  color: white;
  gap: 15px;
`

const StyledInput = styled(InputText)`
  height: 20px;
  margin: 10px;
  width: 50%;
`

const StyledRegisterButton = styled.button`
  display: inline-flex;
  cursor: pointer;
  height: 40px;
  width: 50%;
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
  transition: all 0.2s ease-in-out;

  :hover {
    color: red;
    box-shadow: grey;
  }
`