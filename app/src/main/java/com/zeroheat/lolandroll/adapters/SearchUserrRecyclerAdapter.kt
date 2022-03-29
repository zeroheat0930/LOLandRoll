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
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.api.API2List
import com.zeroheat.lolandroll.api.AsiaServerAPI
import com.zeroheat.lolandroll.datas.*
import com.zeroheat.lolandroll.recyclerview.*
import com.zeroheat.lolandroll.utils.SpellDataUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

//lateinit var SpellBasic: SpellBasicData
//lateinit var SpellBasicImage: SpellBasicImageData
class SearchUserrRecyclerAdapter(
    val mContext: Context,
    val myPuuId: String,
    val mSummonerData : SummonerResponse,
    val mThisSeasonRankList : ArrayList<LeagueResponse>,
    val mMatchDetailList : ArrayList<MatchDetailData>,



    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class FirstViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtUserName = itemView.findViewById<TextView>(R.id.txtUserName)
        val txtUserLevel = itemView.findViewById<TextView>(R.id.txtUserLevel)
        val imgChamp = itemView.findViewById<ImageView>(R.id.imgChamp)


        fun bind(data: SummonerResponse) {

        txtUserName.text = "${data.name}"
        txtUserLevel.text = "${data.summonerLevel}"

        Glide.with(mContext).load("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/profileicon/${data.profileIconId}.png").into(imgChamp)


        }


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
            val imgD = itemView.findViewById<ImageView>(R.id.imgD)
            val imgF = itemView.findViewById<ImageView>(R.id.imgF)
            val imgUsedRun1 = itemView.findViewById<ImageView>(R.id.imgUsedRun1)
            val imgUsedRun2 = itemView.findViewById<ImageView>(R.id.imgUsedRun2)
            val imgItem1 = itemView.findViewById<ImageView>(R.id.imgItem1)
            val imgItem2 = itemView.findViewById<ImageView>(R.id.imgItem2)
            val imgItem3 = itemView.findViewById<ImageView>(R.id.imgItem3)
            val imgItem4 = itemView.findViewById<ImageView>(R.id.imgItem4)
            val imgItem5 = itemView.findViewById<ImageView>(R.id.imgItem5)
            val imgItem6 = itemView.findViewById<ImageView>(R.id.imgItem6)
            val imgItemWard = itemView.findViewById<ImageView>(R.id.imgItemWard)


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
                        if(data.info.queueId == 420){
                            txtGameMode.text = "개인/듀오"
                        }else if(data.info.queueId == 430){
                            txtGameMode.text = "일반게임"
                        }else if(data.info.queueId == 440){
                            txtGameMode.text = "자유랭크"
                        }else if(data.info.queueId == 700){
                            txtGameMode.text = "격전"
                        }else{
                            txtGameMode.text = data.info.gameMode
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
                txtMatchTime.text = "${String.format("%02d", mini)}:${ String.format("%02d", seco)}"


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
                    val sdf = SimpleDateFormat("M월 d일")
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

                        val imgDUrl = "http://ddragon.leagueoflegends.com/cdn/12.5.1/img/spell/${SpellDataUtil.spellHashMap[gamer.summoner1Id]}.png"
//                        Log.d("D스펠", imgDUrl)
//                        Log.d("summonerId", gamer.summoner1Id.toString())
                        val imgFUrl = "http://ddragon.leagueoflegends.com/cdn/12.5.1/img/spell/${SpellDataUtil.spellHashMap[gamer.summoner2Id]}.png"
//                        Log.d("F스펠", imgFUrl)
//                        Log.d("summonerId", gamer.summoner2Id.toString())
                        Glide.with(mContext).load(imgDUrl).into(imgD)
                        Glide.with(mContext).load(imgFUrl).into(imgF)



//                        사용한 룬 이미지
                        Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item0}.png").into(imgUsedRun1)
                        Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item0}.png").into(imgUsedRun2)



//                        사용한 템1 이미지
                        if (gamer.item0 != 0) {
//                            아이템이미지
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item0}.png").into(imgItem1)
                        }
                        else {
//                            기본이미지
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem1)
                        }
//                        사용한 템2 이미지
                        if (gamer.item1 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item1}.png").into(imgItem2)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem2)
                        }
//                        사용한 템3 이미지
                        if (gamer.item2 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item2}.png").into(imgItem3)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem3)
                        }
//                        사용한 템4 이미지
                        if (gamer.item3 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item3}.png").into(imgItem4)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem4)
                        }
//                        사용한 템5 이미지
                        if (gamer.item4 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item4}.png").into(imgItem5)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem5)
                        }
//                        사용한 템6 이미지
                        if (gamer.item5 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item5}.png").into(imgItem6)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItem6)
                        }
//                        사용한 와드 이미지
                        if (gamer.item6 != 0) {
                            Glide.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item6}.png").into(imgItemWard)
                        }
                        else {
                            Glide.with(mContext).load(R.mipmap.ic_launcher).into(imgItemWard)
                        }





//                      킬 데스 어시스트 textview
                        txtKda.text = "${gamer.kills} / ${gamer.deaths} / ${gamer.assists}"

//                      KDA계산

                        if(gamer.deaths == 0){
                            txtKillPer.text = "Perfact"
                        }
                        else {
                            var kda = (gamer.kills + gamer.assists) /gamer.deaths
                            txtKillPer.text = "KDA ${kda}"

                        }
                    }

                }
            }
        }


    override fun getItemViewType(position: Int): Int {


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
                    (holder as FirstViewHolder).bind(mSummonerData)

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
                    val apiList2 = retrofit.create(API2List::class.java)

//                    apiList2.summoner().enqueue(object :Callback<SdataResponse>{
//                        override fun onResponse(call: Call<SdataResponse>, response: Response<SdataResponse>) {
//                            val spell = response.body()!!
//
//                        }
//
//                        override fun onFailure(call: Call<SdataResponse>, t: Throwable) {
//
//                        }
//                    })


                }

        }
    }








