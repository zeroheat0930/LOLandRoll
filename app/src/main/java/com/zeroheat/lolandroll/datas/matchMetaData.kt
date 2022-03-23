package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchMetaData(

    var dataVersion : String,
    var matchId: String,
    var participants: List<String>

) :Serializable{
    fun getmatchMetaHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "dataVersion" to this.dataVersion,
            "matchId" to this.matchId,
            "participants" to this.participants[0]

        )
        return hashMap
    }

}