package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.MatchDetailData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API3List {

    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    fun getMatch(
        @Path("puuid") puuid: String,
        @Query("count") count: String,
        @Query("api_key") api_key: String
    ): Call<List<String>>

    @GET("/lol/match/v5/matches/{matchId}")
    fun getMatchDetail(
        @Path("matchId") matchId: String,
        @Query("api_key") api_key: String
    ): Call<MatchDetailData>

}