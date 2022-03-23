package com.zeroheat.lolandroll

import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.zeroheat.lolandroll.adapters.MyAdapter
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code

class SearchResultActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding
    private var dataList: ArrayList<DataItem>? = null
    var mChatList = ArrayList<SummonerResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_parctic)
        setupEvents()
        setValues()
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {
        initializeData()
        val recyclerView = findViewById<RecyclerView>(R.id.threeRecycle)
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager // LayoutManager 등록
//        recyclerView.adapter = MyAdapter(dataList) // Adapter 등록

    }


    override fun setValues() {
        realtimeDB.getReference("Summoner").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.children.last().child("profileIconId").value.toString()
                val value1 = snapshot.children.last().child("name").value.toString()
                val value2 = snapshot.children.last().child("summonerLevel").value.toString()

                Log.d("값" , value)
                Log.d("값", "Value1 is" + value1)

                val ImageView: ImageView = findViewById(R.id.imgChamp)
                Glide.with(mContext).load("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/profileicon/${value}.png")
                    .into(ImageView)

                val TitleText : TextView = findViewById(R.id.txtUserName)
                TitleText.setText(value1)

                val Level : TextView = findViewById(R.id.txtUserLevel)
                Level.setText(value2)

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })



    }
    fun initializeData() {
        dataList = java.util.ArrayList()
        dataList!!.add(DataItem("사용자1님이 입장하셨습니다.", null, code.ViewType.CENTER_CONTENT))
        dataList!!.add(DataItem("사용자2님이 입장하셨습니다.", null, code.ViewType.CENTER_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.LEFT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
        dataList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.RIGHT_CONTENT))
    }
}