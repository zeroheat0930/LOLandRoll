package com.zeroheat.lolandroll.datas

import java.io.Serializable

class ItemDetailData(

    var name : String,
    var description : String,
    var colloq : String,
    var plaintext : String,
    var into : List<String>,
    var image : ItemDetailImageData,
//    var gold : ItemDetailGoldData,
    var tags : List<String>,
//    var maps : ItemDetailMapsData,
//    var stats : ItemDetailStatsData,
) :Serializable {
}