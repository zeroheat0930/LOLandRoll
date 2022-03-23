package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchTeamsObjectivesData(
    var baron : matchTeamsObjectivesDetailData,
    var champion : matchTeamsObjectivesDetailData,
    var dragon : matchTeamsObjectivesDetailData,
    var inhibitor : matchTeamsObjectivesDetailData,
    var riftHerald: matchTeamsObjectivesDetailData,
    var tower : matchTeamsObjectivesDetailData
):Serializable {


}