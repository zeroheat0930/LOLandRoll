package com.zeroheat.lolandroll

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase
import com.zeroheat.lolandroll.api.*

abstract class BaseActivity : AppCompatActivity(){

    lateinit var mContext: Context
    lateinit var apiList: APIList
    lateinit var apiList2: API2List
    lateinit var apiList3: API3List
    lateinit var apiListTFT: APIListTFT

    lateinit var txtTitle: TextView
    lateinit var btnAdd: ImageView

//    멤버변수로 RealtimeDb에 연결
    val realtimeDB = FirebaseDatabase.getInstance("https://lolandroll-543b4-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this

        val retrofit = ServerAPI1.getRetrofit(mContext)
        apiList = retrofit.create(APIList::class.java)

        val retrofit1 = ServerAPI2.getRetrofit(mContext)
        apiList2 = retrofit1.create(API2List::class.java)

        val retrofit2 = AsiaServerAPI.getRetrofit(mContext)
        apiList3 = retrofit2.create(API3List::class.java)

        val retrofit3 = AsiaServerAPI.getRetrofit(mContext)
        apiListTFT = retrofit3.create(APIListTFT::class.java)

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

