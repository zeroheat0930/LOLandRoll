package com.zeroheat.lolandroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeroheat.lolandroll.adapters.MyAdapter
import com.zeroheat.lolandroll.databinding.ActivityPractice2Binding
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code
import java.util.ArrayList

class Practice2Activity : AppCompatActivity() {
    lateinit var binding : ActivityPractice2Binding
    private var dataList: ArrayList<DataItem>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_practice2)
        initializeData()
        val recyclerView = findViewById<RecyclerView>(R.id.rc)
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager // LayoutManager 등록
        recyclerView.adapter = MyAdapter(dataList) // Adapter 등록

    }
    fun initializeData() {
        dataList = ArrayList()
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