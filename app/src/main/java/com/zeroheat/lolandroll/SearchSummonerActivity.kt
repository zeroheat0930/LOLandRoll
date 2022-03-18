package com.zeroheat.lolandroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroheat.lolandroll.adapters.SearchSummonerRecyclerAdapter
import com.zeroheat.lolandroll.databinding.ActivitySearchSummonerBinding
import com.zeroheat.lolandroll.datas.BasicResponse
import com.zeroheat.lolandroll.datas.Summoner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSummonerActivity : BaseActivity() {

    lateinit var binding : ActivitySearchSummonerBinding

    val mSummonerList = ArrayList<Summoner>()

    lateinit var mSummonerAdapter : SearchSummonerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_summoner)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mSummonerAdapter = SearchSummonerRecyclerAdapter(mContext, mSummonerList)
        binding.mySummonerRecyclerView.adapter = mSummonerAdapter
        binding.mySummonerRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

    override fun onResume() {
        super.onResume()
        getMyplacesFromServer()
    }

    fun getMyplacesFromServer(){

        apiList.getsummoner().enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })
    }
}