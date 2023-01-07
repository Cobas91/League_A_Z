import * as React from 'react';
import {useContext, useState} from 'react';
import styled from "styled-components/macro";
import {API_handleLogin} from "../../service/AuthService";
import {Link, useNavigate} from "react-router-dom";
import {AuthContext} from '../../security/AuthProvider'
import {InputText} from 'primereact/inputtext';
import {toast} from 'react-toastify';
import useChampions from '../../hooks/useChampions'
import LoadingAnimation from "../components/LoadingAnimation";
import background from "../../video/background1.mp4";

export default function Login() {
    const [credentials, setCredentials] = useState({username: "", password: ""});
    const {setJWT} = useContext(AuthContext)
    const navigate = useNavigate();
    const {refreshAllChamps} = useChampions();
    const [processingLogin, setProcessingLogin] = useState(false);

    const handleTextInput = (e) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
    }

    const handleLogin = (e) => {
        e.preventDefault();
        if (validateCredentials()) {
            API_handleLogin(credentials)
                .then((response) => {
                    if (response.status === 200) {
                        setJWT(response.data)
                        setProcessingLogin(true)
                        setTimeout(() => {
                            navigate("/home")
                        }, 3500)
                    }
                })
        }
    }

    function validateCredentials() {
        if (credentials.username !== "" && credentials.password !== "") {
            return true
        } else {
            toast.error("Bitte Username und Passwort eingeben.")
            return false;
        }
    }

    const handleRegisterButton = (e) => {
        e.preventDefault();
        navigate("/register")
    }
    return (
        <LoginContainer>

            {
                processingLogin ? <LoadingAnimation/> :
                    <LoginForm onSubmit={handleLogin}>
                        <StyledBackgroundVideo autoPlay loop muted>
                            <source src={background} type="video/mp4"/>
                        </StyledBackgroundVideo>
                        <StyledHeadline>Login</StyledHeadline>

                        <InputArea>
                            <StyledInput required={true} onChange={handleTextInput} id="username" placeholder="Username"/>
                            <StyledInput required={true} onChange={handleTextInput} placeholder="Passwort" id="password" type="password"/>
                        </InputArea>

                        <ButtonArea>
                            <StyledButton onClick={handleLogin} type="submit">Login</StyledButton>
                            <StyledButton onClick={handleRegisterButton} type="button">Register</StyledButton>
                        </ButtonArea>
                        <StyledLink to="/password">Passwort vergessen?</StyledLink>
                    </LoginForm>
            }
        </LoginContainer>
    )
}
const StyledLink = styled(Link)`
  color: #777777;
  text-decoration: none;
  font-size: 0.8em;
  text-transform: uppercase;
  display: inline-block;
  -webkit-transition: all 0.2s ease-in-out;
  transition: all 0.2s ease-in-out;
  margin: 0 20px 0 20px;
  align-self: center;

  :hover {
    color: red;
    box-shadow: grey;
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

const StyledInput = styled(InputText)`
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
  background-color: #1e2328;
  border-radius: 20px;
  padding: 20px;
`

const StyledHeadline = styled.h1`
  color: #888787;
`

const LoginContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  list-style: none;
  height: 100vh;

`