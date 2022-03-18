package com.zeroheat.lolandroll

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.zeroheat.lolandroll.api.APIList

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mContext: Context
    lateinit var apiList: APIList

    lateinit var txtTitle: TextView
    lateinit var btnAdd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        supportActionBar?.let {
            setCustomActionBar()
        }


    }
    abstract fun setupEvents()
    abstract fun setValues()

    fun setCustomActionBar(){

        val defaultActionBar = supportActionBar!!

        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        defaultActionBar.setDisplayShowCustomEnabled(true) 위랑 같은코드
        defaultActionBar.setCustomView(R.layout.custom_actionbar)

        val toolbar = defaultActionBar.customView.parent as Toolbar
        toolbar.setContentInsetsAbsolute(0,0)

//        UI 요소들 실제 값 대입
        txtTitle = defaultActionBar.customView.findViewById(R.id.txtCumu)
        btnAdd = defaultActionBar.customView.findViewById(R.id.btnAdd)
    }
}

