import * as React from 'react';
import {useEffect, useState} from 'react';
import styled from "styled-components/macro";
import {StatisticService} from "../../service/StatisticService";
import background from "../../video/background4.mp4";
import SummonerOverviewTable from "../components/SummonerOverviewTable";

export default function SummonerOverview() {
    const [statistics, setStatistics] = useState([]);

    const statisticService = new StatisticService();
    useEffect(() => {
        statisticService.getSummonerStatistic().then((res) => {
            setStatistics(res.summoners)
        })
    }, [])

    return (
        <StyledSummonerOverviewContainer>
            <StyledBackgroundVideo autoPlay loop muted>
                <source src={background} type="video/mp4"/>
            </StyledBackgroundVideo>
            <StyledHeadline>Summoner Overview</StyledHeadline>
            <SummonerOverviewTable statistics={statistics}/>
        </StyledSummonerOverviewContainer>

    )
}
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

const StyledHeadline = styled.h2`
  color: white;
`

const StyledSummonerOverviewContainer = styled.section`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`
