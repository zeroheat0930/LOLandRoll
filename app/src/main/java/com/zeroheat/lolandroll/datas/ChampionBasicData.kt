package com.zeroheat.lolandroll.datas

import java.io.Serializable

class ChampionBasicData(
    var version : String,
    var id : String,
    var key : String,
    var name : String,
    var title : String,
    var blurub : String,
//    var info : ChampionInfoData
    var image : ChampionImageData,
    var tags : List<String>
//    var stats : ChampionStatsData

) : Serializable {

    fun getHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "id" to this.id,
            "key" to this.key,
            "name" to this.name,
            "title" to this.title,
            "image" to this.image.getHashMap()
        )
        return hashMap
    }
}