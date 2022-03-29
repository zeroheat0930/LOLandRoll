package com.zeroheat.lolandroll.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ServerAPI1 {


    companion object {

        private var retrofit: Retrofit? = null
        private var BASE_URL = "https://kr.api.riotgames.com/lol/"



        fun getRetrofit(context: Context): Retrofit {



//               Date 자료형으로 파싱 => String을 yyyy-MM-dd HH:mm:ss 으로 파싱해서 저장해야함. (고정된 양식으로 내려줌)

            val gson = GsonBuilder()
                .setDateFormat("M월 d일")  // 서버가 이런 양식으로 보내주는 String을
                .registerTypeAdapter(
                    Date::class.java,
                    DateDeserializer()
                ) // 어떤 형태의 자료형에 적용시킬지?  Date클래스로 파싱.
                .create()

            val myClient = OkHttpClient.Builder()
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL) // 어느 서버를 기반으로 움직일건지.
                .addConverterFactory(GsonConverterFactory.create(gson)) // gson 라이브러리와 결합 + date 파싱 요령 첨부.
                .client(myClient) // 인터셉터를 부착해둔 클라이언트로 통신하도록
                .build()


            return retrofit!!

        }

    }
}