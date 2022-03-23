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
            "Sprite" to this.Sprite,
            "x" to this.x,
            "y" to this.y,
            "w" to this.w,
            "h" to this.h,
        )


    return hashMap

    }
}