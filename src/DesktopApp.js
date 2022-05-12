import './App.css';
import './font/Friz_Quadrata-Bold.otf';
import './font/Friz_Quadrata-Italic.ttf';
import './font/Friz_Quadrata-Regular.ttf';
import {Route, Routes,} from "react-router-dom";
import "primereact/resources/themes/bootstrap4-dark-blue/theme.css";
import "primereact/resources/primereact.min.css";
import AzDefault from "./desktop/pages/AZDefault";
import HeadNavBar from "./desktop/components/HeadNavBar";
import * as React from "react";
import useChampions from "./hooks/useChampions";
import SingleCard from "./desktop/pages/SingleCard";
import styled from "styled-components/macro";
import RandomCard from "./desktop/pages/RandomCard";
import PrivateRoute from "./security/PrivateRoute";
import Login from "./desktop/pages/Login";
import Register from "./desktop/pages/Register";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import OptionNavigation from "./desktop/components/OptionNavigation";
import SummonerOverview from "./desktop/pages/SummonerOverview";
import Home from "./desktop/pages/Home";
import './InitLanguages'

function DesktopApp() {
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
  padding-top: 80px;
`


export default DesktopApp;
