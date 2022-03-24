package com.zeroheat.lolandroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.zeroheat.lolandroll.databinding.ActivitySearchSummonerBinding
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.datas.SdataResponse
import com.zeroheat.lolandroll.datas.SummonerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSummonerActivity : BaseActivity() {

    lateinit var binding : ActivitySearchSummonerBinding
    var messageCount = 0L //DB에 저장된 채팅 갯수를 담을 변수. Long타입으로 저장.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_summoner)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.edtSearch.setOnEditorActionListener { textView, action, event ->
            var handled = false
            if (action == EditorInfo.IME_ACTION_DONE) {

                handled = true
            }


            var inputSummonerName = binding.edtSearch.text.toString()


//          스펠정보 파이어베이스에 넣음.
            apiList2.summoner().enqueue(object :Callback<SdataResponse>{
                override fun onResponse(call: Call<SdataResponse>, response: Response<SdataResponse>) {
                    if(response.isSuccessful){
                        val vr = response.body()!!
                        Log.d("왜안나옴", vr.toString())
//                  파이어베이스 데이터 넣기
//                        realtimeDB.getReference("Spell").child(messageCount.toString()).setValue(vr.data.getSpellHashMap())

                    }
                }

                override fun onFailure(call: Call<SdataResponse>, t: Throwable) {

                }
            })
//          소환사 이름대면 검색결과가 나옴.
            apiList.getsummoner(
                inputSummonerName,
                "RGAPI-2eeee2b7-fd7f-447e-b5a4-90e34316dd63").enqueue(object :Callback<SummonerResponse>{
                override fun onResponse(
                    call: Call<SummonerResponse>,
                    response: Response<SummonerResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!
                        Log.d("성공", br.toString())
//                  파이어베이스 데이터 넣기
                        realtimeDB.getReference("Summoner").child(messageCount.toString()).setValue(br)
//                  성공하면 소환사 랭크 리그 정보를 넣어줌.
                        apiList.getLeague(
                            br.id,
                            "RGAPI-2eeee2b7-fd7f-447e-b5a4-90e34316dd63"
                        ).enqueue(object:Callback<List<LeagueResponse>>{
                            override fun onResponse(
                                call: Call<List<LeagueResponse>>,
                                response: Response<List<LeagueResponse>>
                            ) {
                                val list = response.body()!!
//                                파이어베이스 데이터 넣기
                                if(list.size != 0 ) {
                                    realtimeDB.getReference("League").child(messageCount.toString())
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
                                        "RGAPI-2eeee2b7-fd7f-447e-b5a4-90e34316dd63")
                                            .enqueue(object :Callback<List<String>>{
                                                override fun onResponse(
                                                    call: Call<List<String>>,
                                                    response: Response<List<String>>
                                                ) {
                                                    val a = response.body()!!
                                                    realtimeDB.getReference("Match").child(messageCount.toString()).setValue(a)

                                                    realtimeDB.getReference("Match").addValueEventListener(object : ValueEventListener{
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            val value0 = snapshot.children.last().child("0").value.toString()
                                                            val value1 = snapshot.children.last().child("1").value.toString()
                                                            val value2 = snapshot.children.last().child("2").value.toString()
                                                            val value3 = snapshot.children.last().child("3").value.toString()
                                                            val value4 = snapshot.children.last().child("4").value.toString()
                                                            val value5 = snapshot.children.last().child("5").value.toString()
                                                            val value6 = snapshot.children.last().child("6").value.toString()
                                                            val value7 = snapshot.children.last().child("7").value.toString()
                                                            val value8 = snapshot.children.last().child("8").value.toString()
                                                            val value9 = snapshot.children.last().child("9").value.toString()
                                                            val value10 = snapshot.children.last().child("10").value.toString()
                                                            // 이거는 좀 보류 해놧다가 물어봐야됨 내일.
                                                            apiList3.getMatchDetail(
                                                                value0,
                                                                "RGAPI-2eeee2b7-fd7f-447e-b5a4-90e34316dd63").enqueue(object :Callback<MatchDetailData>{
                                                                override fun onResponse(
                                                                    call: Call<MatchDetailData>,
                                                                    response: Response<MatchDetailData>
                                                                ) {
                                                                    val b = response.body()!!
                                                                    Log.d("왜안되는건데", b.toString())
//                                                                    디테일 데이터 파이어베이스에 등록
                                                                    realtimeDB.getReference("MatchDetail").child(messageCount.toString()).setValue(b)
                                                                }

                                                                override fun onFailure(
                                                                    call: Call<MatchDetailData>,
                                                                    t: Throwable
                                                                ) {

                                                                }
                                                            })
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






//                        성공하면 화면넘어감
                        val myIntent = Intent(mContext, SearchResultActivity::class.java)
                        startActivity(myIntent)
                    }
                    else{
                        Toast.makeText(mContext,"등록되지 않은 소환사 입니다.", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<SummonerResponse>, t: Throwable) {

                }
            })


            handled
        }
    }

    override fun setValues() {




    }

}


