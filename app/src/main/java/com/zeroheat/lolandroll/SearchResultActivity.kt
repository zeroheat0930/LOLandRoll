package com.zeroheat.lolandroll

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.zeroheat.lolandroll.adapters.SearchUserrRecyclerAdapter
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code

class SearchResultActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding
    var mList = ArrayList<DataItem>()
    lateinit var  mAdapter: SearchUserrRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_parctic)
        setupEvents()
        setValues()
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {

        mAdapter = SearchUserrRecyclerAdapter(mContext, mList)
        binding.threeRecycle.adapter = mAdapter
        binding.threeRecycle.layoutManager = LinearLayoutManager(mContext)


//        initializeData()
//        val recyclerView = findViewById<RecyclerView>(R.id.threeRecycle)
//        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.layoutManager = manager
//        recyclerView.adapter = SearchUserrRecyclerAdapter(dataList)

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
        mList = java.util.ArrayList()
        mList!!.add(DataItem(null, "2021 Master1", code.ViewType.multi_type1))
        mList!!.add(DataItem(null, "2020 Master1", code.ViewType.multi_type1))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자1", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type2))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
        mList!!.add(DataItem("안녕하세요", "사용자2", code.ViewType.multi_type3))
    }
}