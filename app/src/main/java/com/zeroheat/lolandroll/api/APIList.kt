package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIList {

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    fun getsummoner(
        @Path("summonerName") summonerName : String,
        @Query("api_key") api_key : String
    ): Call<BasicResponse>
}