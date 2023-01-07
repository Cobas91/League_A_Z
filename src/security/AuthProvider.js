import {createContext, useEffect, useState} from 'react'
import jwt_decode from 'jwt-decode'

export const AuthContext = createContext({})

export default function AuthProvider({children}) {
    const [JWT, setJWT] = useState(localStorage.getItem('JWT') || '')
    const [username, setUsername] = useState('')
    const [authenticated, setAuthenticated] = useState(false);
    useEffect(() => {
        localStorage.setItem('JWT', JWT)
        if (JWT !== '') {
            setUsername(jwt_decode(JWT).sub)
            setAuthenticated(true)
        }
    }, [JWT])

    const logout = () => {
        localStorage.clear()
    }

    return (
        <AuthContext.Provider value={{setJWT, logout, username, authenticated}}>
            {children}
        </AuthContext.Provider>
    )
}
