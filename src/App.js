import './App.css';
import {
    Routes,
    Route,
} from "react-router-dom";
import AzDefault from "./pages/AZDefault";
import HeadNavBar from "./components/HeadNavBar";
import * as React from "react";
import useChampions from "./hooks/useChampions";
import SingleCard from "./pages/SingleCard";
import styled from "styled-components/macro";
import RandomCard from "./pages/RandomCard";
import PrivateRoute from "./security/PrivateRoute";
import {Fragment} from "react";
import Login from "./pages/Login";
import Register from "./pages/Register";


function App() {
    const {editChamp, champs, setOnlyUnplayable, onlyUnplayable, editChampSingleCard, resetAllChampions, randomChamp, getRandomChamp} = useChampions();
    return (
        <Routes>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/" element={
                    <>
                        <HeadNavBar setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable} resetAllChampions={resetAllChampions}/>
                        <Content>
                            <AzDefault editChamp={editChamp} champs={champs}/>
                        </Content>
                    </>
                }/>
            </Route>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/singleCard" element={
                    <>
                        <HeadNavBar setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable} resetAllChampions={resetAllChampions}/>
                        <Content>
                            <SingleCard champs={champs} editChamp={editChampSingleCard} setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable}/>} />
                        </Content>
                    </>
                }/>
            </Route>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/randomCard" element={
                    <>
                        <HeadNavBar setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable} resetAllChampions={resetAllChampions}/>
                        <Content>
                            <RandomCard champ={randomChamp} getRandomChamp={getRandomChamp}/>
                        </Content>
                    </>
                }/>
            </Route>

        </Routes>
    );
}

const Content = styled.section`
  padding-top: 5vh;
`


export default App;
