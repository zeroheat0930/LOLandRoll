package com.zeroheat.lolandroll.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchUserrRecyclerAdapter(
    val mContext: Context,

    val myPuuId: String,
    val mThisSeansonList : ArrayList<LeagueResponse>,
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
            val txtKillPer = itemView.findViewById<TextView>(R.id.txtKillPer)
            val txtWin = itemView.findViewById<TextView>(R.id.txtWin)
            val txtMatchTime = itemView.findViewById<TextView>(R.id.txtMatchTime)
            val txtGameDate = itemView.findViewById<TextView>(R.id.txtGameDate)
            val imgUsedChamp = itemView.findViewById<ImageView>(R.id.imgUsedChamp)


            fun bind(data: MatchDetailData) {

//                게임 모드
                if (data.info.gameMode == "ARAM"){
                    txtGameMode.text = "무작위 총력전"
                }
                else if(data.info.gameMode == "CLASSIC"){
                    if(data.info.gameType == "CUSTOM_GAME"){
                        txtGameMode.text = "사용자 설정 게임"
                    }
                    else if(data.info.gameType == "TUTORIAL_GAME"){
                        txtGameMode.text = "튜토리얼 게임"
                    }else{
                        if(data.info.queueId == 400){
                            txtGameMode.text = "5vs5 솔로랭크"
                    }else if(data.info.queueId == 440){
                            txtGameMode.text = "5vs5 자유랭크"
                        }else if(data.info.queueId == 700){
                            txtGameMode.text = "격전"
                        }
                    }

                }
                else if(data.info.gameMode == "PRACTICETOOL"){
                    txtGameMode.text = "연습모드"
                }
                else {
                    txtGameMode.text = data.info.gameMode
                    // URF, 이벤트맵 같은거는 그냥 gameMode로 나오게 설정.
                }

//              게임시간 textview
                var mini = data.info.gameDuration/60
                Log.d("분", mini.toString())
                var seco = data.info.gameDuration - (60*mini)
                Log.d("초", seco.toString())
//                남는부분 0채우는법
                txtMatchTime.text = "${mini}분 ${seco}초"


//              게임날자 textview

                val now = Calendar.getInstance()
                val timeAgo = now.timeInMillis - data.info.gameEndTimestamp

                if (timeAgo < 24 * 60 * 60 * 1000){

//                    24시간 이내 : ? 시간 전
                    val diff = timeAgo / 1000 / 60 / 60
                    txtGameDate.text = "${diff}시간 전"
                }
                else if( timeAgo < 10 * 24 * 60 * 60 * 1000 ) {
//            10일 이내 : ?일 전
                    val diffDay = timeAgo / 1000 / 60 / 60 / 24
                    txtGameDate.text = "${diffDay}일 전"
                }
                else {
                    val sdf = SimpleDateFormat("M월 d일 a h시 m분")
//            10일 이상 : sdf로 가공해서 리턴.
                    txtGameDate.text = sdf.format(data.info.gameStartTimestamp)
                }


                for (gamer in data.info.participants) {
//                    Log.d("이 판 참여자목록", gamer.summonerName)

                    if (gamer.puuid == myPuuId) {
//                        Log.d("내 라인?", gamer.role)

//                      승/패 적어주는 textview
                        if(gamer.win == true){
                            txtWin.text = "승"
                        }else{
                            txtWin.text = "패"}

//                        사용한 챔피언 이미지
                        Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/champion/${gamer.championName}.png").into(imgUsedChamp)


//                      킬 데스 어시스트 textview
                        txtKda.text = "${gamer.kills}/${gamer.deaths}/${gamer.assists}"

//                      KDA계산
//                        var kda = (gamer.kills + gamer.assists) /gamer.deaths
//
//                        if(gamer.deaths == 0){
//                            txtKillPer.text = "Perfact"
//                        }
//                        else {
//                            txtKillPer.text = "KDA ${kda}"
//
//                        }
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








