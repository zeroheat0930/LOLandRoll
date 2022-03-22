package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.ItemResponse
import com.zeroheat.lolandroll.datas.SData
import retrofit2.Call
import retrofit2.http.GET

interface API2List {

    @GET("12.5.1/data/en_US/champion.json")
    fun champion() : Call<ItemResponse>

    @GET("12.5.1/data/en_US/summoner.json")
    fun summoner() : Call<SData>

    @GET("12.5.1/data/en_US/item.json")
    fun item() : Call<ItemResponse>
}