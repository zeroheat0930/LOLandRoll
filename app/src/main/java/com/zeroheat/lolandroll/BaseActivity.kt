package com.zeroheat.lolandroll

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zeroheat.lolandroll.api.APIList

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mContext: Context
    lateinit var apiList: APIList

    lateinit var txtTitle: TextView
    lateinit var btnAdd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this


    }
    abstract fun setupEvents()
    abstract fun setValues()
}

