package com.zeroheat.lolandroll.datas

import java.io.Serializable

class SpellData(
    var SummonerBarrier : SpellBasicData,
    var SummonerBoost : SpellBasicData,
    var SummonerDot : SpellBasicData,
    var SummonerExhaust : SpellBasicData,
    var SummonerFlash : SpellBasicData,
    var SummonerHaste : SpellBasicData,
    var SummonerHeal : SpellBasicData,
    var SummonerMana : SpellBasicData,
    var SummonerPoroRecall : SpellBasicData,
    var SummonerPoroThrow : SpellBasicData,
    var SummonerSmite : SpellBasicData,
    var SummonerSnowURFSnowball_Mark : SpellBasicData,
    var SummonerSnowball : SpellBasicData,
    var SummonerTeleport : SpellBasicData,
    var Summoner_UltBookPlaceholder : SpellBasicData,
    var Summoner_UltBookSmitePlaceholder : SpellBasicData,

) : Serializable{

//    fun getSpellHashMap() : HashMap<String,String>{
//        return hashMapOf(
//            "SummonerBarrier" to this.SummonerBarrier.toString(),
//            "SummonerBoost" to this.SummonerBoost.toString(),
//            "SummonerDot" to this.SummonerDot.toString(),
//            "SummonerExhaust" to this.SummonerExhaust.toString(),
//            "SummonerFlash" to this.SummonerFlash.toString(),
//            "SummonerHaste" to this.SummonerHaste.toString(),
//            "SummonerHeal" to this.SummonerHeal.toString(),
//            "SummonerMana" to this.SummonerMana.toString(),
//            "SummonerPoroRecall" to this.SummonerPoroRecall.toString()
//        )
//    }
}