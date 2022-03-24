package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchTeamsData(

    var bans : List<BanResponse>,
    var objectives : matchTeamsObjectivesData,
    var teamId : Int,
    var win : Boolean


):Serializable {


}