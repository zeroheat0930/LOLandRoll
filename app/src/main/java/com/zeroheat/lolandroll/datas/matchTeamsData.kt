package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchTeamsData(

    var bans : List<BanResponse>,
    var objectives : matchTeamsObjectivesData,
    var teamId : Int,
    var win : Boolean


):Serializable {
    fun getHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "bans" to this.bans[0].getListHashMap(),
            "objectives" to this.objectives.getHashMap(),
            "teamId" to this.teamId.toString(),
            "win" to this.win.toString()

        )
        return hashMap
    }

}