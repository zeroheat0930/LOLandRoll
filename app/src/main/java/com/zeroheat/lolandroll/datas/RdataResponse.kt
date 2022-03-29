package com.zeroheat.lolandroll.datas

import java.io.Serializable

class RdataResponse(

    val id : Int,
    val key : String,
    val icon : String,
    val name : String,
    val slots : runesData


):Serializable {
}