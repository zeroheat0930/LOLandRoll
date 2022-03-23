package com.zeroheat.lolandroll.datas

import java.io.Serializable

class MatchDetailData (
    var metadata : matchMetaData,
    var Info : matchInfoData


     ):Serializable{

    fun getMatchDetailHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(

            "metadata" to  this.metadata.getmatchMetaHashMap(),
            "matchInfo" to this.Info.getInfoHashMap()

        )
        return hashMap
    }
}