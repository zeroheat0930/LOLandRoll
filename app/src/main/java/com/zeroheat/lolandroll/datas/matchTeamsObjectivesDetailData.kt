package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchTeamsObjectivesDetailData(
    var first : Boolean,
    var kills : Int
):Serializable {

    fun getHashMap(): HashMap<String,Any>{
        val hashMap = hashMapOf<String, Any>(
            "first" to this.first.toString(),
            "kills" to this.kills.toString(),

        )


        return hashMap

    }
}