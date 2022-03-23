package com.zeroheat.lolandroll

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zeroheat.lolandroll.adapters.MyAdapter
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code

class ParcticActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding
    private var dataList: ArrayList<DataItem>? = null
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
        val ImageView: ImageView = findViewById(R.id.imgChamp)
        Glide.with(mContext).load("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/profileicon/5083.png")
            .into(ImageView)

        val TitleText: TextView = findViewById(R.id.txtUserName)



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