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
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding

    lateinit var mAdapter: SearchUserrRecyclerAdapter


    val mMatchIdList = ArrayList<String>()


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

        mAdapter = SearchUserrRecyclerAdapter(mContext, mMatchIdList)
        binding.threeRecycle.adapter = mAdapter
        binding.threeRecycle.layoutManager = LinearLayoutManager(mContext)


//                  성공하면 소환사 랭크 리그 정보를 넣어줌.
        apiList.getLeague(
            summonerInfo.id,
            "RGAPI-bb301326-0c05-4b58-a7c1-80fe997968aa"
        ).enqueue(object: Callback<List<LeagueResponse>> {
            override fun onResponse(
                call: Call<List<LeagueResponse>>,
                response: Response<List<LeagueResponse>>
            ) {
                val list = response.body()!!
//                                파이어베이스 데이터 넣기
                if(list.size != 0 ) {
                    realtimeDB.getReference("League").child(summonerInfo.name)
                        .setValue(list[0])
                }
//                                데이터베이스에 puuid를 꺼내옴.
                realtimeDB.getReference("Summoner").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val value = snapshot.children.last().child("puuid").value.toString()
//                                       puuid넣은 최근 30게임 전적이름 가져와서 데이터베이스 넣어줌.
                        apiList3.getMatch(
                            value,
                            "30",
                            "RGAPI-bb301326-0c05-4b58-a7c1-80fe997968aa")
                            .enqueue(object : Callback<List<String>> {
                                override fun onResponse(
                                    call: Call<List<String>>,
                                    response: Response<List<String>>
                                ) {
                                    val a = response.body()!!
                                    realtimeDB.getReference("Match").child(summonerInfo.name).setValue(a)

                                    realtimeDB.getReference("Match").addValueEventListener(object : ValueEventListener{
                                        override fun onDataChange(snapshot: DataSnapshot) {

                                            for (matchItem in snapshot.child(summonerInfo.name).children) {
                                                Log.d("매치id목록", matchItem.value.toString())
                                                mMatchIdList.add(matchItem.value.toString())
                                            }

                                            mAdapter.notifyDataSetChanged()


                                            // 이거는 좀 보류 해놧다가 물어봐야됨 내일.

                                        }

                                        override fun onCancelled(error: DatabaseError) {

                                        }
                                    })

                                }

                                override fun onFailure(
                                    call: Call<List<String>>,
                                    t: Throwable
                                ) {

                                }

                            })
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })

            }

            override fun onFailure(call: Call<List<LeagueResponse>>, t: Throwable) {

            }
        })


//        val recyclerView = findViewById<RecyclerView>(R.id.threeRecycle)
//        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        recyclerView.layoutManager = manager
//        recyclerView.adapter = SearchUserrRecyclerAdapter(dataList)

    }


    override fun setValues() {
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



    }
}