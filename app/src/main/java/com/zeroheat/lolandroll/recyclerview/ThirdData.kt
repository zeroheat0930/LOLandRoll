package com.zeroheat.lolandroll.recyclerview

import com.zeroheat.lolandroll.datas.matchInfoData
import com.zeroheat.lolandroll.datas.matchPartyPeople
import java.io.Serializable

class ThirdData (


        var gameCreation : matchInfoData,
        var gameDuration : matchInfoData,
        var gameEndTimestamp : matchInfoData,
        var gameStartTimestamp : matchInfoData,

        var  kill : matchPartyPeople,
        var  assists	:matchPartyPeople,
        var  deaths	: matchPartyPeople,


        ):Serializable{
}