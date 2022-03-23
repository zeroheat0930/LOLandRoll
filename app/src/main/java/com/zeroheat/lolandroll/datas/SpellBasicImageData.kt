package com.zeroheat.lolandroll.datas

import java.io.Serializable

class SpellBasicImageData(
var full: String,
var Sprite: String,
var x : Int,
var y : Int,
var w : Int,
var h : Int,
):Serializable {

    fun getHashMap(): HashMap<String,Any>{
        val hashMap = hashMapOf<String, Any>(
            "full" to this.full,
            "x" to this.x,
        )


    return hashMap

    }
}