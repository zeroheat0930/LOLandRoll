package com.zeroheat.lolandroll.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.api.API3List
import com.zeroheat.lolandroll.api.AsiaServerAPI
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.recyclerview.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserrRecyclerAdapter(
    val mContext: Context,

    val myPuuId: String,
    val mFirstList : ArrayList<FirstData>,
    val mThisSeasonRankList : ArrayList<LeagueResponse>,
    val mMatchDetailList : ArrayList<MatchDetailData>,


    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class FirstViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

//        val btnRank = itemView.findViewById<TextView>(R.id.btnRank)
//
//        fun bind1(leagueList: List<LeagueResponse>) {
//
//            for (league in leagueList) {
////                첨부된 시즌별 랭크 하나하나를 레이아웃에 addView해준다던지
//            }
//
//
//        }


    }

    inner class SecondViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {


        val txtRankNow = itemView.findViewById<TextView>(R.id.txtRankNow)

        fun bind(rankList: List<LeagueResponse>) {

            if (rankList.isNotEmpty()) {

                val thisSeasonData = rankList[0]

                txtRankNow.text = "${thisSeasonData.tier} ${thisSeasonData.rank}"
            }


        }
    }

    inner class ThirdViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

            val txtGameMode = itemView.findViewById<TextView>(R.id.txtGameMode)
            val txtKda = itemView.findViewById<TextView>(R.id.txtKda)


            fun bind(data: MatchDetailData) {

                txtGameMode.text = data.info.gameMode
                Log.d("이 판 id", data.info.gameId.toString())

                for (gamer in data.info.participants) {
                    Log.d("이 판 참여자목록", gamer.summonerName)

                    if (gamer.puuid == myPuuId) {
                        Log.d("내 라인?", gamer.role)

                        txtKda.text = "${gamer.kills}/${gamer.deaths}/${gamer.assists}"
                    }

                }
            }
        }


    override fun getItemViewType(position: Int): Int {

//            홀수줄 : 데이터목록
//            짝수줄 : 챔프목록
                return when (position) {
                    0 -> code.ViewType.multi_type1
                    1 -> code.ViewType.multi_type2
                    else -> code.ViewType.multi_type3
                }
            }

            //    어떤 xml을 inflate해서 사용할건지
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): RecyclerView.ViewHolder {
                val view: View
                val context = mContext
                val inflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                return if (viewType == code.ViewType.multi_type1) {
                    view = inflater.inflate(R.layout.first_content, parent, false)
                    FirstViewHolder(view)
                } else if (viewType == code.ViewType.multi_type2) {
                    view = inflater.inflate(R.layout.second_content, parent, false)
                    SecondViewHolder(view)
                } else {
                    view = inflater.inflate(R.layout.third_content, parent, false)
                    ThirdViewHolder(view)
                }

            }

            //  목록이 몇개인지
            override fun getItemCount() =
                mMatchDetailList.size + 2 // 앞의 두개는 매치 목록이 아님


            //    위치에 맞는 데이터 추출
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


//        뷰타입이 1번일때는
                if (holder.itemViewType == code.ViewType.multi_type1) {
//            val data1 = mRankList[position]

                }
//        뷰타입이 2번일때는
                if (holder.itemViewType == code.ViewType.multi_type2) {
//            val data2 = mLeagueList[position]
//            Log.d("data2", data2.toString())

                    (holder as SecondViewHolder).bind(mThisSeasonRankList)
                }
//        뷰타입이 3번일때는

                if (holder.itemViewType == code.ViewType.multi_type3) {
                    val data = mMatchDetailList[position - 2] // 0,1번째는 다른거니까

                    (holder as ThirdViewHolder).bind(data)


                    val retrofit = AsiaServerAPI.getRetrofit(mContext)
                    val apiList3 = retrofit.create(API3List::class.java)


                }

        }
    }








