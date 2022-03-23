package com.zeroheat.lolandroll.datas

import java.io.Serializable

class matchInfoData(

    var gameCreation : Long,
    var gameDuration : Long,
    var gameEndTimestamp : Long,
    var gameStartTimestamp : Long,
    var gameId : Long,
    var gameMode : String,
//    var gameName : String,
//    var gameType : String,
//    var gameVersion : String,
    var mapId : Int,
    var participants : List<matchPartyPeople>,
//    var platformId : String,
    var queueId : Int,
    var teams : List<matchTeamsData>,
//    var tournamentCode : String



) : Serializable{

    fun getInfoHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(

            "gameCreation" to this.gameCreation.toString(),
            "gameDuration" to this.gameDuration.toString(),
            "gameEndTimestamp" to this.gameEndTimestamp.toString(),
            "gameStartTimestamp" to this.gameStartTimestamp.toString(),
            "gameId" to this.gameId.toString(),
            "gameMode" to this.gameMode,
            "mapId" to this.mapId.toString(),
            "participants" to this.participants.list.getLongHashMap(),
            "queueId" to this.queueId.toString(),
            "teams" to this.teams.getHashMap()



        )
        return hashMap
    }

}