package com.zeroheat.lolandroll.datas

import java.io.Serializable

class ItemResponse (

    var type : String,
    var version : String,
//    var basic : ItemBasicData,
    var data : ItemData,
//    var groups : List<ItemGroupData>,
//    var tree : List<ItemTreeData>

) :Serializable{
}