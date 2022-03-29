package com.zeroheat.lolandroll.utils

import android.content.Context
import android.util.Log
import org.json.JSONArray

class ChampionJsonDataUtil {

    companion object {

        fun getChampJsonArray(context: Context) : JSONArray {
            val chamJsonStr = context.assets.open("champion.json").reader().readText()
            Log.d("챔피언JSON", chamJsonStr)

            return JSONArray(chamJsonStr)

        }

    }
}