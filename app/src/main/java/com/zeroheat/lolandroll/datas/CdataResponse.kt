package com.zeroheat.lolandroll.datas

import java.io.Serializable

class CdataResponse(

    var type : String,
    var format : String,
    var version : String,
    var data : ChampionData

) : Serializable{
}