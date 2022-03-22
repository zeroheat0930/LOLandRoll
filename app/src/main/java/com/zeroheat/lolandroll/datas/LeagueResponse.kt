package com.zeroheat.lolandroll.datas

import java.io.Serializable

class LeagueResponse(
    var leagueId: String,
    var summonerId: String,
    var summonerName: String,
    var queueType: String,
    var tier: String,
    var rank: String,
    var leaguePoints: Int,
    var wins: Int,
    var losses: Int,
    var hotStreak: Boolean,
    var veteran: Boolean,
    var freshBlood: Boolean,
    var inactive: Boolean,
    var miniSeries : MiniseriesResponse

) :Serializable{

    fun getBsHashMap() : HashMap<String,String>{
        return hashMapOf(
            "leagueId" to this.leagueId,
            "summonerId" to this.summonerId,
            "summonerName" to this.summonerName,
            "queueType" to this.queueType,
            "tier" to this.tier,
            "rank" to this.rank,
            "leaguePoints" to this.leaguePoints.toString(),
            "wins" to this.wins.toString(),
            "losses" to this.losses.toString()
        )
    }
}