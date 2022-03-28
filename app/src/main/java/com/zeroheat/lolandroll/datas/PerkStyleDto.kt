package com.zeroheat.lolandroll.datas

import java.io.Serializable

class PerkStyleDto(

    val description : String,
    val selections : List<PerkStyleSelectionDto>,
    val style : Int


):Serializable {
}