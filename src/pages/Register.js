import * as React from 'react';
import {useContext, useState} from 'react';
import styled from "styled-components/macro";
import {AuthContext} from "../security/AuthProvider";
import {API_handleRegister} from "../service/AuthService";
import {useNavigate} from "react-router-dom";
import {Captcha} from 'primereact/captcha';
import {InputText} from 'primereact/inputtext';
import useChamps from '../hooks/useChampions'

export default function Register() {
    const [credentials, setCredentials] = useState()
    const [repeatedPassword, setRepeatedPassword] = useState();
    const [noRobot, setNoRobot] = useState(false)
    const navigate = useNavigate();
    const {setJWT} = useContext(AuthContext)
    const {refreshAllChamps} = useChamps();

    const handleRegister = (e) => {
        e.preventDefault()
        if (repeatedPassword?.password_repeat === credentials?.password && noRobot) {
            API_handleRegister(credentials)
                .then((res) => {
                    res ? setJWT(res) : setJWT("")
                })
                .then(() => {
                        setTimeout(() => {
                            refreshAllChamps()
                            navigate("/")
                        }, 1000);
                    }
                )
        } else {
            console.log("No Equal password")
        }

    }
    const handleInput = (e) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
    }
    const handleRepeatedPasswordChange = (e) => {
        setRepeatedPassword({...repeatedPassword, [e.target.id]: e.target.value})
    }
    const showResponse = (response) => {
        setNoRobot(true);
    }
    return (
        <RegisterContainer>
            <RegisterForm>
                <InputContainer>
                    <StyledInput id="username" placeholder="Username" type="username" onChange={handleInput}/>
                    <StyledInput id="password" placeholder="Password" type="password" onChange={handleInput}/>
                    <StyledInput id="password_repeat" placeholder="Repeat Password" type="password" onChange={handleRepeatedPasswordChange}/>
                    <Captcha siteKey="6LeypUYfAAAAAFOsRrWy-CXEbISm8pG4hbXV4cYo" onResponse={showResponse}></Captcha>
                </InputContainer>
                <StyledRegisterButton onClick={handleRegister}>Register</StyledRegisterButton>
                <StyledRegisterButton onClick={()=>navigate("/login")}>Zurück zum Login</StyledRegisterButton>
            </RegisterForm>

        </RegisterContainer>
    )
}

const RegisterContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  
`
const RegisterForm = styled.form`
  display: flex;
  justify-content: center;
  flex-direction: column;
  width: 40vw;
  height: 40vh;
  background-color: #666666;
  border-radius: 3vw;
  align-items: center;
`

const InputContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
`

const StyledInput = styled(InputText)`
  height: 20px;
  margin: 10px;
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
  :hover{
    color: red;
    box-shadow: grey;
  }
`