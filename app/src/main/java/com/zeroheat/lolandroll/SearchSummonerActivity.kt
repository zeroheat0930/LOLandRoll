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
import org.json.JSONObject
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

            //          소환사 이름대면 검색결과가 나옴.
            apiList.getsummoner(
                inputSummonerName,
                "RGAPI-58926f96-1fdf-430d-9f3b-b47b717bccfe").enqueue(object :Callback<SummonerResponse>{
                override fun onResponse(
                    call: Call<SummonerResponse>,
                    response: Response<SummonerResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!
                        Log.d("성공", br.toString())
                        //                        성공하면 화면넘어감
                        val myIntent = Intent(mContext, SearchResultActivity::class.java)
                        myIntent.putExtra("summoner", br)
                        startActivity(myIntent)


                    }
                    else{

                        val jsonObj = JSONObject(response.errorBody()!!.string())
                        Log.d("검색실패", jsonObj.toString())
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


