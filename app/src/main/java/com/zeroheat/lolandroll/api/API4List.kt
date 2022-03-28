package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.PerksData
import retrofit2.Call
import retrofit2.http.GET

interface API4List {
    @GET("/v1/perks.json")
    fun getPerks(): Call<PerksData>
}