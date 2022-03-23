package com.zeroheat.lolandroll.datas

import java.io.Serializable

class BanResponse(
    var championId: Int,
    var pickTurn: Int
):Serializable {

    fun getListHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "championId" to this.championId.toString(),
            "pickTurn" to this.pickTurn.toString()
        )
        return hashMap
    }

}