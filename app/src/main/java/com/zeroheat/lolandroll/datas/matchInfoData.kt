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


}