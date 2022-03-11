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
import styled from "styled-components";

function App() {
    const {editChamp, champs, setOnlyUnplayable, onlyUnplayable, editChampSingleCard, resetAllChampions} = useChampions();

    return (
        <>
            <HeadNavBar setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable} resetAllChampions={resetAllChampions}/>
            <Content>
                <Routes>
                    <Route path="/" element={<AzDefault editChamp={editChamp} champs={champs}/>} />
                    <Route path="/singleCard" element={<SingleCard champs={champs} editChamp={editChampSingleCard} setOnlyUnplayable={setOnlyUnplayable} onlyUnplayable={onlyUnplayable}/>} />
                </Routes>
            </Content>

        </>

    );
}
const Content = styled.section`
  padding-top: 5vh;
`


export default App;
