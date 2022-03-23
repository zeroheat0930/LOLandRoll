package com.zeroheat.lolandroll.api

import com.zeroheat.lolandroll.datas.CdataResponse
import com.zeroheat.lolandroll.datas.ItemResponse
import com.zeroheat.lolandroll.datas.PdataResponse
import com.zeroheat.lolandroll.datas.SdataResponse
import retrofit2.Call
import retrofit2.http.GET

interface API2List {

    @GET("12.5.1/data/ko_KR/champion.json")
    fun champion() : Call<CdataResponse>

    @GET("12.5.1/data/ko_KR/summoner.json")
    fun summoner() : Call<SdataResponse>

    @GET("12.5.1/data/ko_KR/item.json")
    fun item() : Call<ItemResponse>

    @GET("12.5.1/data/ko_KR/profileicon.json")
    fun profileIcon() : Call<PdataResponse>
}