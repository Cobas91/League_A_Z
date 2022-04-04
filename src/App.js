import './App.css';
import {Route, Routes,} from "react-router-dom";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/primereact.min.css";
import AzDefault from "./pages/AZDefault";
import HeadNavBar from "./components/HeadNavBar";
import * as React from "react";
import useChampions from "./hooks/useChampions";
import SingleCard from "./pages/SingleCard";
import styled from "styled-components/macro";
import RandomCard from "./pages/RandomCard";
import PrivateRoute from "./security/PrivateRoute";
import Login from "./pages/Login";
import Register from "./pages/Register";


function App() {
    const {editChamp, champs, playable, editChampSingleCard, resetAllChampions, randomChamp, getRandomChamp, changePlayableFilter} = useChampions();
    return (
        <Routes>
            <Route path="/login" element={<Login/>}/>
            <Route path="/register" element={<Register/>}/>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/" element={
                    <>
                        <HeadNavBar changePlayableFilter={changePlayableFilter} playable={playable} resetAllChampions={resetAllChampions}/>
                        <Content>
                            <AzDefault editChamp={editChamp} champs={champs}/>
                        </Content>
                    </>
                }/>
            </Route>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/singleCard" element={
                    <>
                        <HeadNavBar changePlayableFilter={changePlayableFilter} playable={playable} resetAllChampions={resetAllChampions}/>
                        <Content>
                            <SingleCard champs={champs} editChamp={editChampSingleCard} changePlayableFilter={changePlayableFilter} playable={playable}/>} />
                        </Content>
                    </>
                }/>
            </Route>
            <Route path="/" element={<PrivateRoute/>}>
                <Route path="/randomCard" element={
                    <>
                        <HeadNavBar changePlayableFilter={changePlayableFilter} playable={playable} resetAllChampions={resetAllChampions}/>
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
