import * as React from 'react';
import {useContext, useState} from 'react';
import styled from "styled-components/macro";
import {AuthContext} from "../security/AuthProvider";
import {API_handleRegister} from "../service/AuthService";
import {useNavigate} from "react-router-dom";
import {InputText} from 'primereact/inputtext';
import NoRobotTask from "../components/NoRobotTask";
import {toast} from "react-toastify";

export default function Register() {
    const [credentials, setCredentials] = useState({username: "", password: ""})
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [noRobot, setNoRobot] = useState(false)
    const navigate = useNavigate();
    const {setJWT} = useContext(AuthContext)

    const handleRegister = (e) => {
        e.preventDefault()
        if (usernameIsValid() && passwordIsValid() && validateRobot()) {
            API_handleRegister(credentials)
                .then((response) => {
                    if (response.status === 208) {
                        toast.error("Username existiert bereits");
                    } else {
                        if (response.data) {
                            setJWT(response.data)
                            toast.success("Summoner registriert. Du kannst dich jetzt anmelden.")
                            navigate("/login")
                        } else {
                            setJWT("")
                        }
                    }
                })
                .catch((err) => toast.error(err))
        }
    }

    function validateRobot() {
        if (noRobot) {
            return true;
        } else {
            toast.error("Die Aufgabe muss korrekt gelöst werden.")
            return false;
        }
    }

    function passwordIsValid() {
        if (repeatedPassword?.password_repeat === credentials?.password) {
            return true
        } else {
            toast.error("Beide Passwörter müssen übereinstimmen.")
            return false;
        }

    }

    function usernameIsValid() {
        if (credentials.username) {
            return true;
        } else {
            toast.error("Bitte gib einen Usernamen ein.")
            return false;
        }

    }

    const handleInput = (e) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
    }
    const handleRepeatedPasswordChange = (e) => {
        setRepeatedPassword({...repeatedPassword, [e.target.id]: e.target.value})
    }

    const handleRobot = (input) => {
        setNoRobot(input)
    }

    return (
        <RegisterContainer>
            <RegisterForm>
                <InputContainer>
                    <StyledInput id="username" placeholder="Username" type="username" onChange={handleInput}/>
                    <StyledInput id="password" placeholder="Password" type="password" onChange={handleInput}/>
                    <StyledInput id="password_repeat" placeholder="Repeat Password" type="password" onChange={handleRepeatedPasswordChange}/>
                    <NoRobotTask handleRobot={handleRobot}/>
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