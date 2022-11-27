import * as React from 'react';
import {useContext, useState} from 'react';
import styled from "styled-components/macro";
import {AuthContext} from "../../security/AuthProvider";
import {API_handleRegister} from "../../service/AuthService";
import {useNavigate} from "react-router-dom";
import {InputText} from 'primereact/inputtext';
import NoRobotTask from "../components/NoRobotTask";
import {toast} from "react-toastify";
import background from "../../video/background3.mp4";
import LoadingAnimation from "../components/LoadingAnimation";

export default function Register() {
    const [credentials, setCredentials] = useState({username: "", password: "", email: ""})
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const [noRobot, setNoRobot] = useState(false)
    const navigate = useNavigate();
    const {setJWT} = useContext(AuthContext)
    const [processingLogin, setProcessingLogin] = useState(false);

    const handleRegister = (e) => {
        e.preventDefault()
        if (passwordIsValid() && validateRobot()) {
            API_handleRegister(credentials)
                .then((response) => {
                    if (response.status === 208) {
                        toast.error("Username existiert bereits");
                    } else {
                        if (response.data) {
                            setJWT(response.data)
                            toast.success("Summoner registriert. Viel spaß!")
                            setProcessingLogin(true)
                            setTimeout(() => {
                                navigate("/home")
                            }, 3500)
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
            {processingLogin ? <LoadingAnimation/> :
                <RegisterForm onSubmit={handleRegister}>
                    <StyledBackgroundVideo autoPlay loop muted>
                        <source src={background} type="video/mp4"/>
                    </StyledBackgroundVideo>
                    <InputContainer>
                        <StyledInput required={true} id="username" placeholder="Username" type="username" onChange={handleInput}/>
                        <StyledInput required={true} id="email" placeholder="E-Mail" type="email" onChange={handleInput}/>
                        <StyledInput required={true} id="password" placeholder="Password" type="password" onChange={handleInput}/>
                        <StyledInput required={true} id="password_repeat" placeholder="Repeat Password" type="password" onChange={handleRepeatedPasswordChange}/>
                        <NoRobotTask handleRobot={handleRobot}/>
                    </InputContainer>
                    <ButtonArea>
                        <StyledRegisterButton onClick={() => navigate("/login")}>Zurück zum Login</StyledRegisterButton>
                        <StyledRegisterButton type="submit" onSubmit={handleRegister}>Register</StyledRegisterButton>
                    </ButtonArea>

                </RegisterForm>}


        </RegisterContainer>
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

  :hover {
    color: red;
    box-shadow: grey;
  }
`