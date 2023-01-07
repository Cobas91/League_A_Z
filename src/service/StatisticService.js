import axios from "axios";

export class StatisticService {
    getSummonerStatistic() {
        return axios.get("/statistic/overview/summoner").then(res => res.data)
    }
}