package com.zeroheat.lolandroll.datas

import java.io.Serializable

class ChampionMasteryResponse (
    var championPointsUntilNextLevel: Long,
    var chestGranted: Boolean,
    var championId: Long,
    var lastPlayTime: Long,
    var championLevel: Int,
    var summonerId: String,
    var championPoints: Int,
    var championPointsSinceLastLevel: Long,
    var tokensEarned: Int,

    ) :Serializable{


    }