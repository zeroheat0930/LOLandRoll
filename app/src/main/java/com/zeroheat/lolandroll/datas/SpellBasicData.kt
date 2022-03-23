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
            "name" to this.name,
            "image" to this.image.getHashMap()
        )
        return hashMap
    }

}