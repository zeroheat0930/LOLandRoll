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
import com.zeroheat.lolandroll.datas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSummonerActivity : BaseActivity() {

    lateinit var binding : ActivitySearchSummonerBinding
//    var messageCount = 0L //DB에 저장된 채팅 갯수를 담을 변수. Long타입으로 저장.


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
//              정보 다 넣었으니 한동안 안쓸꺼임!!
////            아이템 정보 파이어베이스 넣기
//            apiList2.item().enqueue(object :Callback<ItemResponse>{
//                override fun onResponse(
//                    call: Call<ItemResponse>,
//                    response: Response<ItemResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val vr = response.body()!!
////                  파이어베이스 데이터 넣기
//                        realtimeDB.getReference("Item").child(messageCount.toString()).setValue(vr.data)
//                    }
//                }
//
//                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
//
//                }
//            })
//
////           챔피언 정보 파이어베이스 넣기
//            apiList2.champion().enqueue(object :Callback<CdataResponse>{
//                override fun onResponse(
//                    call: Call<CdataResponse>,
//                    response: Response<CdataResponse>
//                ) {
//                    if (response.isSuccessful){
//                        val vr = response.body()!!
////                  파이어베이스 데이터 넣기
//                        realtimeDB.getReference("Champion").child(messageCount.toString()).setValue(vr.data)
//                    }
//                }
//
//                override fun onFailure(call: Call<CdataResponse>, t: Throwable) {
//
//                }
//            })
//
////          스펠정보 파이어베이스에 넣음.
//            apiList2.summoner().enqueue(object :Callback<SdataResponse>{
//                override fun onResponse(call: Call<SdataResponse>, response: Response<SdataResponse>) {
//                    if(response.isSuccessful){
//                        val vr = response.body()!!
//                        Log.d("왜안나옴", vr.toString())
////                  파이어베이스 데이터 넣기
//                        realtimeDB.getReference("Spell").child(messageCount.toString()).setValue(vr.data)
//
//                    }
//                }
//
//                override fun onFailure(call: Call<SdataResponse>, t: Throwable) {
//
//                }
//            })




//          소환사 이름대면 검색결과가 나옴.
            apiList.getsummoner(
                inputSummonerName,
                "RGAPI-bb301326-0c05-4b58-a7c1-80fe997968aa").enqueue(object :Callback<SummonerResponse>{
                override fun onResponse(
                    call: Call<SummonerResponse>,
                    response: Response<SummonerResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!
                        Log.d("성공", br.toString())

//                  파이어베이스 데이터 넣기
                        realtimeDB.getReference("Summoner").child(inputSummonerName).setValue(br)

//                        성공하면 화면넘어감
                        val myIntent = Intent(mContext, SearchResultActivity::class.java)
                        myIntent.putExtra("summoner", br)
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


