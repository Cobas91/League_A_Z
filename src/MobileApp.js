import './App.css';
import './font/Friz_Quadrata-Bold.otf';
import './font/Friz_Quadrata-Italic.ttf';
import './font/Friz_Quadrata-Regular.ttf';
import {Route, Routes,} from "react-router-dom";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/primereact.min.css";
import * as React from "react";
import useChampions from "./hooks/useChampions";
import styled from "styled-components/macro";
import PrivateRoute from "./security/PrivateRoute";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import './InitLanguages'
import Home from "./mobile/pages/Home";
import Login from "./mobile/pages/Login";
import Register from "./mobile/pages/Register";
import SummonerOverview from "./mobile/pages/SummonerOverview";
import HeadNavBar from "./mobile/components/HeadNavBar";
import OptionNavigation from "./mobile/components/OptionNavigation";
import AzDefault from "./mobile/pages/AZDefault";
import SingleCard from "./mobile/pages/SingleCard";

function MobileApp() {
    const {playable, setPlayable, reloadChamps, resetChampions} = useChampions();
    return (
        <div>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/register" element={<Register/>}/>
                <Route path="/overview" element={<SummonerOverview/>}/>
                <Route path="/" element={<PrivateRoute/>}>
                    <Route path="/home" element={
                        <>
                            <HeadNavBar changePlayableFilter={setPlayable} />
                            <Content>
                                <OptionNavigation playable={playable} changePlayableFilter={setPlayable} resetChampions={resetChampions}/>
                                <AzDefault playable={playable} reloadChamps={reloadChamps}/>
                            </Content>
                        </>
                    }/>
                </Route>
                <Route path="/" element={<PrivateRoute/>}>
                    <Route path="/singleCard" element={
                        <>
                            <HeadNavBar changePlayableFilter={setPlayable} />
                            <Content>
                                <SingleCard />
                            </Content>
                        </>
                    }/>
                </Route>

            </Routes>
            <ToastContainer
                position="top-center"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
            />
        </div>
    );
}

const Content = styled.section`
  padding-bottom: 80px;
`


export default MobileApp;
