package com.zeroheat.lolandroll

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.databinding.ActivitySearchSummonerBinding
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.fragments.UserFragment
import org.json.JSONObject
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
                "RGAPI-674f74e7-1c47-41ca-bdd5-6bad2a4ea7d9").enqueue(object :Callback<SummonerResponse>{
                override fun onResponse(
                    call: Call<SummonerResponse>,
                    response: Response<SummonerResponse>
                ) {
                    if (response.isSuccessful){
                        val br = response.body()!!
                        Log.d("성공", br.toString())
                    }
                    else{
                        val jsonObj = JSONObject(response.errorBody()!!.toString())
                        Log.d("에러응답",jsonObj.toString())
                    }
                }

                override fun onFailure(call: Call<SummonerResponse>, t: Throwable) {

                }
            })



            val myIntent = Intent(mContext, ParcticActivity::class.java)
            startActivity(myIntent)
            handled
        }
    }

    override fun setValues() {

    }

}


