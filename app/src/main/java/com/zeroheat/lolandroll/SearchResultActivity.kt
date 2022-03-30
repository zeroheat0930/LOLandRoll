package com.zeroheat.lolandroll

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeroheat.lolandroll.adapters.SearchUserrRecyclerAdapter
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.datas.SummonerResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : BaseActivity() {
    lateinit var binding: ActivityParcticBinding

    lateinit var mAdapter: SearchUserrRecyclerAdapter


    val mThisSeasonRankList = ArrayList<LeagueResponse>()
    val mMatchDetailList = ArrayList<MatchDetailData>()

    lateinit var summonerInfo : SummonerResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_parctic)
        summonerInfo = intent.getSerializableExtra("summoner") as SummonerResponse
        setupEvents()
        setValues()
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {

        mAdapter = SearchUserrRecyclerAdapter(mContext,summonerInfo.puuid, summonerInfo, mThisSeasonRankList, mMatchDetailList)
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

    }

    fun getMyLeagueInfoList() {


//        json파싱만 사용해서 데이터 테이블에 넣어보자
        apiList.getLeague(
            summonerInfo.id,
            "RGAPI-8ffec796-fc80-4c91-875a-491d53aeb79a"
        ).enqueue(object : Callback<List<LeagueResponse>> {
            override fun onResponse(
                call: Call<List<LeagueResponse>>,
                response: Response<List<LeagueResponse>>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()!!

                    mThisSeasonRankList.clear()
                    mThisSeasonRankList.addAll(list)

                    mAdapter.notifyDataSetChanged()
                }
                else {
                    val jsonObj = JSONObject(response.errorBody()!!.string())

                    Log.d("응답실패", jsonObj.toString())
                }

            }

            override fun onFailure(call: Call<List<LeagueResponse>>, t: Throwable) {
                Toast.makeText(mContext,"전적 검색 API서버 점검중입니다.", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun getMy30MatchList() {

        apiList3.getMatch(
            summonerInfo.puuid,
            "30",
            "RGAPI-8ffec796-fc80-4c91-875a-491d53aeb79a"
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
                            "RGAPI-8ffec796-fc80-4c91-875a-491d53aeb79a"
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
                                Toast.makeText(mContext,"전적 검색 API서버 점검중입니다.", Toast.LENGTH_LONG).show()
                            }
                        })
                    }

                }

                override fun onFailure(
                    call: Call<List<String>>,
                    t: Throwable
                ) {
                    Toast.makeText(mContext,"전적 검색 API서버 점검중입니다.", Toast.LENGTH_LONG).show()
                }

            })

    }
}