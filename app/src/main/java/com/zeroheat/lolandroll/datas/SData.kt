package com.zeroheat.lolandroll.datas

import java.io.Serializable

class SData(
    var type : String,
    var version : String,
    var data : SpellData
) : Serializable{
}