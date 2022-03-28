package com.zeroheat.lolandroll.datas

import java.io.Serializable

class PerksData(

    val id : Int,
    val name : String,
    val majorChangePatchVersion : String,
    val tooltip : String,
    val shortDesc : String,
    val longDesc : String,
    val iconPath : String

):Serializable {
}