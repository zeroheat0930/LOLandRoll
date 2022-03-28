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
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.recyclerview.FirstData
import com.zeroheat.lolandroll.recyclerview.ThirdData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding

    lateinit var mAdapter: SearchUserrRecyclerAdapter


    val mFirstList = ArrayList<FirstData>()
    val mThisSeasonRankList = ArrayList<LeagueResponse>()
    val mThirdList = ArrayList<ThirdData>()


    lateinit var summonerInfo : SummonerResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_parctic)
        summonerInfo = intent.getSerializableExtra("summoner") as SummonerResponse
        setupEvents()
        setValues()
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {

        mAdapter = SearchUserrRecyclerAdapter(mContext, mFirstList, mThisSeasonRankList, mThirdList)
        binding.threeRecycle.adapter = mAdapter
        binding.threeRecycle.layoutManager = LinearLayoutManager(mContext)




//                  성공하면 소환사 랭크 리그 정보를 넣어줌.
//        apiList.getLeague(
//            summonerInfo.id,
//            "RGAPI-25102a0e-d805-443c-8449-e51b6a10f4c3"
//        ).enqueue(object: Callback<List<LeagueResponse>> {
//            override fun onResponse(
//                call: Call<List<LeagueResponse>>,
//                response: Response<List<LeagueResponse>>
//            ) {
//                val list = response.body()!!
////                                파이어베이스 데이터 넣기
//                if(list.size != 0 ) {
//                    realtimeDB.getReference("League").child(summonerInfo.name)
//                        .setValue(list[0])
//
//                    mMatchIdList.add(list[0].leagueId)
//                }
//
////                                데이터베이스에 puuid를 꺼내옴.
//                realtimeDB.getReference("Summoner").addValueEventListener(object :
//                    ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        val value = snapshot.children.last().child(summonerInfo.puuid).key.toString()
//
//                        Log.d("value", value)
////                                       puuid넣은 최근 30게임 전적이름 가져와서 데이터베이스 넣어줌.
//                        apiList3.getMatch(
//                            value,
//                            "20",
//                            "RGAPI-25102a0e-d805-443c-8449-e51b6a10f4c3")
//                            .enqueue(object : Callback<List<String>> {
//                                override fun onResponse(
//                                    call: Call<List<String>>,
//                                    response: Response<List<String>>
//                                ) {
//                                    val a = response.body()!!
//                                    Log.d("로그a", a.toString())
////                                    소환사 매치 목록 20개를 match데이터 베이스에 소환사 이름별대로 저장을함.
//                                    realtimeDB.getReference("Match").child(summonerInfo.name).setValue(a)
////                                      매치에 등록된 데이터를 꺼내오는 코드 시작
//                                    realtimeDB.getReference("Match").addValueEventListener(object : ValueEventListener{
//                                        override fun onDataChange(snapshot: DataSnapshot) {
////                                              스냅샷이 소환사이름데이터일때 데이터를 mMatchIdList에 넣어줌.
//                                            for (matchItem in snapshot.child(summonerInfo.name).children) {
//
////                                                Log.d("매치id목록", matchItem.value.toString())
//
//                                                apiList3.getMatchDetail(
//                                                    matchItem.value.toString(),
//                                                    "RGAPI-25102a0e-d805-443c-8449-e51b6a10f4c3").enqueue(object :
//                                                    Callback<MatchDetailData> {
//                                                    override fun onResponse(
//                                                        call: Call<MatchDetailData>,
//                                                        response: Response<MatchDetailData>
//                                                    ) {
//                                                        val b = response.body()!!
//
////                                                        Log.d("b", b.toString())
//                                                        realtimeDB.getReference("MatchDetail").child(summonerInfo.name).setValue(b)
//
//                                                        realtimeDB.getReference("MatchDetail").addValueEventListener(object : ValueEventListener{
//                                                            override fun onDataChange(snapshot: DataSnapshot) {
//                                                                val abab = snapshot.child(summonerInfo.name).child("info").child("participants")
//
//                                                                for (item in abab.children) {
//                                                                    val puuId = item.child("puuid").value.toString()
//                                                                    if (puuId == summonerInfo.puuid) {
//
//                                                                        Log.d("abab내부item", item.toString())
//                                                                        Log.d("puuid값", puuId)
//                                                                    }
//                                                                }
//
//
//
//
//
//                                                            }
//
//                                                            override fun onCancelled(error: DatabaseError) {
//
//                                                            }
//                                                        })
//
//                                                    }
//
//                                                    override fun onFailure(
//                                                        call: Call<MatchDetailData>,
//                                                        t: Throwable
//                                                    ) {
//
//                                                    }
//                                                })
//
//
////                                                mMatchIdList.clear()
//                                                mMatchIdList.add(matchItem.value.toString())
//                                                mAdapter.notifyDataSetChanged()
//
//                                            }
//
//
//
//
//
//
//
//
//                                        }
//
//                                        override fun onCancelled(error: DatabaseError) {
//
//                                        }
//                                    })
//
//                                }
//
//                                override fun onFailure(
//                                    call: Call<List<String>>,
//                                    t: Throwable
//                                ) {
//
//                                }
//
//                            })
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//
//                    }
//                })
//
//
//            }
//
//            override fun onFailure(call: Call<List<LeagueResponse>>, t: Throwable) {
//
//            }
//        })

    }


    override fun setValues() {


//        제일 위에 프사쪽 UI 매칭
        realtimeDB.getReference("Summoner").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.children.last().child(summonerInfo.profileIconId.toString()).key.toString()
                val value1 = snapshot.children.last().child(summonerInfo.name).key.toString()
                val value2 = snapshot.children.last().child(summonerInfo.summonerLevel.toString()).key.toString()

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

        getMyLeagueInfoLisk()

    }

    fun getMyLeagueInfoLisk() {


//        json파싱만 사용해서 데이터 테이블에 넣어보자
        apiList.getLeague(
            summonerInfo.id,
            "RGAPI-25102a0e-d805-443c-8449-e51b6a10f4c3"
        ).enqueue(object : Callback<List<LeagueResponse>>{
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
}