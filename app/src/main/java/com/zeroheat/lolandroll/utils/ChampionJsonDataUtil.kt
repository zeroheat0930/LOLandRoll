package com.zeroheat.lolandroll.utils

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class ChampionJsonDataUtil {

    companion object {

        fun getChampJsonObject(context: Context) : JSONObject {
            val chamJsonStr = context.assets.open("champion.json").reader().readText()
            Log.d("챔피언JSON", chamJsonStr)

            return JSONObject(chamJsonStr)

        }

    }
}