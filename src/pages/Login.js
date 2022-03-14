import * as React from 'react';
import {useContext, useEffect, useState} from 'react';
import styled from "styled-components/macro";
import {API_handleLogin} from "../service/AuthService";
import {useNavigate} from "react-router-dom";
import {AuthContext} from '../security/AuthProvider'

export default function Login() {
    const [credentials, setCredentials] = useState();
    const [_JWT, _setJWT] = useState("");
    const {setJWT} = useContext(AuthContext)
    useEffect(() => {
        setJWT(_JWT)
    }, [_JWT, setJWT]);
    const navigate = useNavigate();
    const handleTextInput = (e) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
    }
    const handleLogin = (e) => {
        e.preventDefault();
        API_handleLogin(credentials)
            .then((res) => {
                res ? _setJWT(res) : console.log("No valid JWT")
            })
            .then(() => {
                    setTimeout(() => {
                        navigate("/")
                    }, 500);
                }
            )
    }
    const handleRegister = (e)=>{
        e.preventDefault();
        navigate("/register")
    }
    return (
        <LoginContainer>
            <LoginForm>
                <StyledHeadline>Login</StyledHeadline>
                <InputArea>
                    <StyledInput onChange={handleTextInput} id="username" placeholder="Username"/>
                    <StyledInput onChange={handleTextInput} placeholder="Passwort" id="password" type="password"/>
                </InputArea>

                <ButtonArea>
                    <StyledButton onClick={handleLogin} type="submit">Login</StyledButton>
                    <StyledButton onClick={handleRegister} type="submit">Register</StyledButton>
                </ButtonArea>
            </LoginForm>
        </LoginContainer>
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
  transition: all 0.2s ease-in-out;
  :hover{
    color: red;
    box-shadow: grey;
  }
`

const StyledInput = styled.input`
    margin: 5px 0 5px 0;
  height: 20px;
`

const InputArea = styled.section`
  margin: 15px 0 15px 0;
  display: flex;
  flex-direction: column;
`

const ButtonArea = styled.section`
  display: flex;
  justify-content: space-evenly;
`

const LoginForm = styled.form`
  display: flex;
  justify-content: center;
  flex-direction: column;
`

const StyledHeadline = styled.h1`
  color: red;
`

const LoginContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  height: 100vh;

`