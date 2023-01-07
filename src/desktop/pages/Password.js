import * as React from 'react';
import {useContext, useEffect, useState} from 'react';
import styled from "styled-components/macro";
import {useNavigate, useSearchParams} from "react-router-dom";
import {toast} from "react-toastify";
import {API_changePasswordWithToken, API_handlePasswordReset} from "../../service/AuthService";
import ResetPasswordForm from "../components/ResetPasswordForm";
import ChangePasswordForm from "../components/ChangePasswordForm";
import {AuthContext} from "../../security/AuthProvider";


export default function Password() {
    const [credentials, setCredentials] = useState({email: "", token: null})
    const [searchParams] = useSearchParams();
    const [changePassword, setChangePassword] = useState(false);
    const [repeatedPassword, setRepeatedPassword] = useState("");
    const {setJWT} = useContext(AuthContext)
    const navigate = useNavigate();
    const handleRepeatedPasswordChange = (e) => {
        setRepeatedPassword({...repeatedPassword, [e.target.id]: e.target.value})
    }
    useEffect(() => {
        if (searchParams.get("token") !== null) {
            setCredentials({...credentials, token: searchParams.get("token")})
            setChangePassword(true)
        }
    }, [])

    const handleRegister = (e) => {
        e.preventDefault()
        API_handlePasswordReset(credentials).then(res => {
            if (res.status === 200) {
                toast.info("Bitte überprüfe dein Postfach!")
            }
        })
    }

    const handleInput = (e) => {
        setCredentials({...credentials, [e.target.id]: e.target.value})
    }

    function passwordIsValid() {
        if (repeatedPassword?.password_repeat === credentials?.password) {
            return true
        } else {
            toast.error("Beide Passwörter müssen übereinstimmen.")
            return false;
        }
    }

    const handleReset = (e) => {
        e.preventDefault();
        if (passwordIsValid()) {
            console.log(credentials)
            API_changePasswordWithToken(credentials).then((res) => {
                if (res.status === 200) {
                    setJWT(res.data);
                    navigate("/home")
                }
            })
        }
    }

    return (
        <RegisterContainer>
            {changePassword ? <ChangePasswordForm handleReset={handleReset} handleInput={handleInput} handleRepeatedPasswordChange={handleRepeatedPasswordChange}/> :
                <ResetPasswordForm handleRegister={handleRegister} handleInput={handleInput}/>}

        </RegisterContainer>
    )
}

const RegisterContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;

`