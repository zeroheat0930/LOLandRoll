package com.zeroheat.lolandroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.databinding.ActivitySearchSummonerBinding
import com.zeroheat.lolandroll.datas.SummonerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSummonerActivity : BaseActivity() {

    lateinit var binding : ActivitySearchSummonerBinding

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
            apiList.getsummoner(
                inputSummonerName,
                "RGAPI-c4fcd9c0-a53d-4e2c-a5a7-b292e56c5f7e").enqueue(object :Callback<SummonerResponse>{
                override fun onResponse(
                    call: Call<SummonerResponse>,
                    response: Response<SummonerResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!
                        Log.d("성공", br.toString())



//                        성공하면 화면넘어감
                        val myIntent = Intent(mContext, ParcticActivity::class.java)
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


