package com.zeroheat.lolandroll

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.databinding.ActivitySearchTftSummonerBinding
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.datas.TFTSummonerResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchTftSummonerActivity : BaseActivity() {

    lateinit var binding : ActivitySearchTftSummonerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_tft_summoner)
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
            apiListTFT.getTFTsummoner(
                inputSummonerName,
                "RGAPI-f1f932e8-b712-42c5-a75b-69d36136a2df").enqueue(object :
                Callback<TFTSummonerResponse> {
                override fun onResponse(
                    call: Call<TFTSummonerResponse>,
                    response: Response<TFTSummonerResponse>
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

                override fun onFailure(call: Call<TFTSummonerResponse>, t: Throwable) {

                }
            })


            handled
        }
    }

    override fun setValues() {




    }

}
