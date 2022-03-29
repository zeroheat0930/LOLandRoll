package com.zeroheat.lolandroll.utils

import android.content.Context
import android.util.Log
import org.json.JSONArray

class RuneJsonData {

    companion object {

        fun getRunesJsonArray(context: Context) : JSONArray {

            val runeJsonStr = context.assets.open("runes_reforged.json").reader().readText()
            Log.d("룬JSON", runeJsonStr)

            return JSONArray(runeJsonStr)

        }

    }

}