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

    fun getSpellHashMap() : HashMap<String,Any>{
        val hashMap = hashMapOf<String,Any>(
            "SummonerBarrier" to this.SummonerBarrier.getHashMap(),
            "SummonerBoost" to this.SummonerBoost.getHashMap(),
            "SummonerDot" to this.SummonerDot.getHashMap(),
            "SummonerExhaust" to this.SummonerExhaust.getHashMap(),
            "SummonerFlash" to this.SummonerFlash.getHashMap(),
            "SummonerHaste" to this.SummonerHaste.getHashMap(),
            "SummonerHeal" to this.SummonerHeal.getHashMap(),
            "SummonerMana" to this.SummonerMana.getHashMap(),
            "SummonerPoroRecall" to this.SummonerPoroRecall.getHashMap()

        )
        return hashMap
    }
}