import './App.css';
import styled from "styled-components";
import ChampionCard from "./components/ChampionCard";
import {useEffect, useState} from "react";
import {API_getAllChamps} from "./service/ChampionService";
import useChampions from "./hooks/useChampions";
import HeadNavBar from "./components/HeadNavBar";

function App() {
  const {editChamp, champs} = useChampions();


  return (
      <>
        <HeadNavBar/>
        <CardContainer>
          {champs.map(item => (<ChampionCard champ={item} key={item.id} editChamp={editChamp}/>))}
        </CardContainer>
      </>

  );
}

const CardContainer = styled.section`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
`
export default App;
