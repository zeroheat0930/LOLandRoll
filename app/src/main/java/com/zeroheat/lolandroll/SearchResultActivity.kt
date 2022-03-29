package com.zeroheat.lolandroll

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.zeroheat.lolandroll.adapters.SearchUserrRecyclerAdapter
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.datas.SummonerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : BaseActivity() {
    lateinit var binding: ActivityParcticBinding

    lateinit var mAdapter: SearchUserrRecyclerAdapter


    val mMainList = ArrayList<SummonerResponse>()
    val mThisSeasonRankList = ArrayList<LeagueResponse>()
    val mMatchDetailList = ArrayList<MatchDetailData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parctic)
//        summonerInfo = intent.getSerializableExtra("summoner") as SummonerResponse
        setupEvents()
        setValues()
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {

        mAdapter = SearchUserrRecyclerAdapter(mContext,mMainList, mThisSeasonRankList, mMatchDetailList,mMainList[0].puuid )
        binding.threeRecycle.adapter = mAdapter
        binding.threeRecycle.layoutManager = LinearLayoutManager(mContext)


    }


    override fun setValues() {

        getMyMainInfoList()

        getMyLeagueInfoList()

        getMy30MatchList()


    }

    fun getMyMainInfoList(){
        val a = intent.getStringExtra("Name")
        //          소환사 이름대면 검색결과가 나옴.
        apiList.getsummoner(
            a!!,
            "RGAPI-9bf477d4-f348-4c9f-86ad-509cc1f62d76").enqueue(object :Callback<SummonerResponse>{
            override fun onResponse(
                call: Call<SummonerResponse>,
                response: Response<SummonerResponse>
            ) {
                if (response.isSuccessful){
                    val br = response.body()!!
                    Log.d("성공", br.toString())
                    mMainList.clear()
                    mMainList.addAll(listOf(br))

                    mAdapter.notifyDataSetChanged()

                }
                else{
                    Toast.makeText(mContext,"등록되지 않은 소환사 입니다.", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<SummonerResponse>, t: Throwable) {

            }
        })
    }

    fun getMyLeagueInfoList() {


//        json파싱만 사용해서 데이터 테이블에 넣어보자
        apiList.getLeague(
            mMainList[0].id,
            "RGAPI-9bf477d4-f348-4c9f-86ad-509cc1f62d76"
        ).enqueue(object : Callback<List<LeagueResponse>> {
            override fun onResponse(
                call: Call<List<LeagueResponse>>,
                response: Response<List<LeagueResponse>>
            ) {
                val list = response.body()!!

                mThisSeasonRankList.clear()
                mThisSeasonRankList.addAll(list)

                mAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<LeagueResponse>>, t: Throwable) {

            }
        })

    }

    fun getMy30MatchList() {

        apiList3.getMatch(
            mMainList[0].puuid,
            "30",
            "RGAPI-9bf477d4-f348-4c9f-86ad-509cc1f62d76"
        )
            .enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>,
                    response: Response<List<String>>
                ) {
                    val matchIdList = response.body()!!
                    Log.d("매치id목록", matchIdList.toString())

                    for (matchId in matchIdList) {

                        apiList3.getMatchDetail(
                            matchId,
                            "RGAPI-9bf477d4-f348-4c9f-86ad-509cc1f62d76"
                        ).enqueue(object :
                            Callback<MatchDetailData> {
                            override fun onResponse(
                                call: Call<MatchDetailData>,
                                response: Response<MatchDetailData>
                            ) {

                                response.body()?.let{
                                    val matchDetail = response.body()!!

                                    Log.d("matchDetail", matchDetail.toString())

                                    mMatchDetailList.add(matchDetail)

                                    mMatchDetailList.sortByDescending {
                                        it.info.gameStartTimestamp
                                    }


                                    mAdapter.notifyDataSetChanged()
                                }

                            }

                            override fun onFailure(
                                call: Call<MatchDetailData>,
                                t: Throwable
                            ) {

                            }
                        })
                    }

                }

                override fun onFailure(
                    call: Call<List<String>>,
                    t: Throwable
                ) {

                }

            })

    }
}