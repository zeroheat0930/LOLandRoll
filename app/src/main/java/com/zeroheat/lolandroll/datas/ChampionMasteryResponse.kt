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

        fun getDsHashMap() : HashMap<String,String>{
            return hashMapOf(
                "championPointsUntilNextLevel" to this.championPointsUntilNextLevel.toString(),
                "chestGranted" to this.summonerId,
                "championId" to this.championId.toString(),
                "lastPlayTime" to this.lastPlayTime.toString(),
                "championLevel" to this.championLevel.toString(),
                "summonerId" to this.summonerId,
                "championPoints" to this.championPoints.toString(),
                "championPointsSinceLastLevel" to this.championPointsSinceLastLevel.toString(),
                "tokensEarned" to this.tokensEarned.toString()
            )
        }
    }