package com.zeroheat.lolandroll.datas

import java.io.Serializable

class SpellBasicData(
    var id : String,
    var name : String,
    var maxrank : Int,
    var key : String,
    var modes : List<String>,
    var image : SpellBasicImageData
) : Serializable{

    fun getHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "id" to this.id,
            "name" to this.name,
            "maxrank" to this.maxrank.toString(),
            "key" to this.key,
            "image" to this.image.getHashMap()
        )
        return hashMap
    }

}