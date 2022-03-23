package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIList {

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    fun getsummoner(
        @Path("summonerName") summonerName : String,
        @Query("api_key") api_key : String
    ): Call<SummonerResponse>

    @GET("league/v4/entries/by-summoner/{encryptedSummonerId}")
    fun getLeague(
        @Path("encryptedSummonerId") encryptedSummonerId : String,
        @Query("api_key") api_key: String
    ): Call<List<LeagueResponse>>

    @GET("spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    suspend fun getspectator(
        @Path("encryptedSummonerId") encryptedSummonerId : String,
        @Query("api_key") api_key : String
    ): Call<SpectatorResponse>

    @GET("/lol/champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}")
    fun getChampionMastery(
        @Path("encryptedSummonerId") encryptedSummonerId: String,
        @Query("api_key") api_key: String
    ): Call<List<ChampionMasteryResponse>>

}