package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchMetaData(

    var dataVersion : String,
    var matchId: String,
    var participants: List<String>

) :Serializable{
}