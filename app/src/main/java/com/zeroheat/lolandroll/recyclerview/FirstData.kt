package com.zeroheat.lolandroll.recyclerview

import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.SummonerResponse
import java.io.Serializable

class FirstData(
    val id: LeagueResponse,
    val user_id: LeagueResponse,
    val title: LeagueResponse,
    var tier: LeagueResponse,
    var rank: LeagueResponse,
    var leaguePoints: LeagueResponse,
):Serializable {
}