package com.zeroheat.lolandroll.datas

import java.io.Serializable

class ChampionBasicData(
    var version : String,
    var id : String,
    var key : String,
    var name : String,
    var title : String,
    var blurub : String,
//    var info : ChampionInfoData
    var image : ChampionImageData,
    var tags : List<String>
//    var stats : ChampionStatsData

) : Serializable {


}