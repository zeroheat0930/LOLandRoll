package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.TFTSummonerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIListTFT {

    @GET("summoner/v1/summoners/by-name/{summonerName}")
    fun getTFTsummoner(
        @Path("summonerName") summonerName : String,
        @Query("api_key") api_key : String
    ): Call<TFTSummonerResponse>



}