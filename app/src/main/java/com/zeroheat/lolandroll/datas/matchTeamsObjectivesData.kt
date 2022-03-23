package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchTeamsObjectivesData(
    var baron : matchTeamsObjectivesDetailData,
    var champion : matchTeamsObjectivesDetailData,
    var dragon : matchTeamsObjectivesDetailData,
    var inhibitor : matchTeamsObjectivesDetailData,
    var riftHerald: matchTeamsObjectivesDetailData,
    var tower : matchTeamsObjectivesDetailData
):Serializable {

    fun getHashMap() : HashMap<String,Any> {
        val hashMap = hashMapOf<String, Any>(
            "baron" to this.getHashMap(),
            "champion" to this.getHashMap(),
            "dragon" to this.dragon.getHashMap(),
            "inhibitor" to this.inhibitor.getHashMap(),
            "riftHerald" to this.riftHerald.getHashMap(),
            "tower" to this.tower.getHashMap()
        )
        return hashMap
    }


}