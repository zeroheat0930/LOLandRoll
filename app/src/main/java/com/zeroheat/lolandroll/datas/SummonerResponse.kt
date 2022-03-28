package com.zeroheat.lolandroll.datas

import java.io.Serializable

class SummonerResponse (
    var accountId: String,
    var profileIconId: Int,
    var revisionDate: Long,
    var name: String,
    var puuid: String,
    var id: String,
    var summonerLevel: Long
):Serializable{



}