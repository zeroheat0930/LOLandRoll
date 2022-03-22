package com.zeroheat.lolandroll.datas

import java.io.Serializable

class MiniseriesResponse(
    var losses: Int,
    var progress: String,
    var target: Int,
    var wins: Int,

) :Serializable{
}