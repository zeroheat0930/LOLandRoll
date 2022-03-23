package com.zeroheat.lolandroll.datas

import org.json.JSONObject
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

    fun getAsHashMap() : HashMap<String,String>{
        return hashMapOf(
        "accountId" to this.accountId,
        "profileIconId" to this.profileIconId.toString(),
        "revisionDate" to this.revisionDate.toString(),
            "name" to this.name,
            "puuid" to this.puuid,
            "id" to this.id,
            "summonerLevel" to this.summonerLevel.toString()
        )
    }

}