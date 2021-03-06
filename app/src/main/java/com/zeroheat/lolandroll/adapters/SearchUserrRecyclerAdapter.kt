package com.zeroheat.lolandroll.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.api.ResourceProto.resource
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.api.API2List
import com.zeroheat.lolandroll.api.AsiaServerAPI
import com.zeroheat.lolandroll.datas.LeagueResponse
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.recyclerview.code
import com.zeroheat.lolandroll.utils.ChampionJsonDataUtil
import com.zeroheat.lolandroll.utils.RuneJsonData
import com.zeroheat.lolandroll.utils.SpellDataUtil
import java.text.SimpleDateFormat
import java.util.*


//lateinit var SpellBasic: SpellBasicData
//lateinit var SpellBasicImage: SpellBasicImageData
class SearchUserrRecyclerAdapter(
    val mContext: Context,
    val myPuuId: String,
    val mSummonerData: SummonerResponse,
    val mThisSeasonRankList: ArrayList<LeagueResponse>,
    val mMatchDetailList: ArrayList<MatchDetailData>,


    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class FirstViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val txtUserName = itemView.findViewById<TextView>(R.id.txtUserName)
        val txtUserLevel = itemView.findViewById<TextView>(R.id.txtUserLevel)
        val imgChamp = itemView.findViewById<ImageView>(R.id.imgChamp)
        val mainImage = itemView.findViewById<ImageView>(R.id.mainImage)


        fun bind(data: SummonerResponse) {

            txtUserName.text = "${data.name}"
            txtUserLevel.text = "${data.summonerLevel}"

            Glide.with(mContext)
                .load("https://ddragon.leagueoflegends.com/cdn/12.5.1/img/profileicon/${data.profileIconId}.png")
                .into(imgChamp)

            val champListJsonObj = ChampionJsonDataUtil.getChampJsonObject(mContext)
            val dataObj = champListJsonObj.getJSONObject("data")

            val myRandomNum = (1 until dataObj.names()!!.length()).random()
            Log.d("???????????????? - ??????", dataObj.names()!!.getString(myRandomNum))


            val random = Random()
            val num = random.nextInt(3)

            Glide.with(mContext)
                .load("https://ddragon.leagueoflegends.com/cdn/img/champion/splash/${
                    dataObj.names()!!.getString(myRandomNum)
                }_${num}.jpg")
                .into(mainImage);


        }
        }

        inner class SecondViewHolder internal constructor(itemView: View) :
            RecyclerView.ViewHolder(itemView) {

            fun bind(rankList: List<LeagueResponse>) {

                if (rankList.isNotEmpty()) {

                    for (thisSeasonData in mThisSeasonRankList) {
                        val thisSeasonData = rankList[0]

                        val leagueView =
                            LayoutInflater.from(mContext).inflate(R.layout.league_list_item, null)


                        val btnRankValue = leagueView.findViewById<TextView>(R.id.btnRankValue)
                        val txtRankNow = leagueView.findViewById<TextView>(R.id.txtRankNow)
                        val txtLp = leagueView.findViewById<TextView>(R.id.txtLp)
                        val txtWinLoss = leagueView.findViewById<TextView>(R.id.txtWinLoss)

                        val imgRank = leagueView.findViewById<ImageView>(R.id.imgRank)



                        if (thisSeasonData.queueType == "RANKED_SOLO_5x5") {
                            btnRankValue.text = "??????/2??? ??????"
                        }
                        if (thisSeasonData.tier == "CHALLENGER") {
                            Glide.with(mContext).load(R.drawable.emblem_challenger).into(imgRank)
                        } else if (thisSeasonData.tier == "GRANDMASTER") {
                            Glide.with(mContext).load(R.drawable.emblem_grandmaster).into(imgRank)
                        } else if (thisSeasonData.tier == "MASTER") {
                            Glide.with(mContext).load(R.drawable.emblem_master).into(imgRank)
                        } else if (thisSeasonData.tier == "DIAMOND") {
                            Glide.with(mContext).load(R.drawable.emblem_diamond).into(imgRank)
                        } else if (thisSeasonData.tier == "PLATINUM") {
                            Glide.with(mContext).load(R.drawable.emblem_platinum).into(imgRank)
                        } else if (thisSeasonData.tier == "GOLD") {
                            Glide.with(mContext).load(R.drawable.emblem_gold).into(imgRank)
                        } else if (thisSeasonData.tier == "SILVER") {
                            Glide.with(mContext).load(R.drawable.emblem_silver).into(imgRank)
                        } else if (thisSeasonData.tier == "BRONZE") {
                            Glide.with(mContext).load(R.drawable.emblem_bronze).into(imgRank)
                        } else if (thisSeasonData.tier == "iron") {
                            Glide.with(mContext).load(R.drawable.emblem_iron).into(imgRank)
                        } else {
                            Glide.with(mContext).load(R.drawable.emblem_unrank).into(imgRank)
                        }



                        txtRankNow.text = "${thisSeasonData.tier} ${thisSeasonData.rank}"

                        txtLp.text = "${thisSeasonData.leaguePoints} LP"

                        val x = thisSeasonData.wins
                        val y = thisSeasonData.losses
                        val z = ((x.toDouble() / (x.toDouble() + y.toDouble())) * 100).toInt()
                        txtWinLoss.text = "${x}??? ${y}??? (${z}%)"


                    }


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
            val linearColor = itemView.findViewById<LinearLayout>(R.id.linearColor)


            @SuppressLint("ResourceAsColor")
            fun bind(data: MatchDetailData) {

//                ?????? ??????
                if (data.info.gameMode == "ARAM") {
                    txtGameMode.text = "????????? ?????????"
                } else if (data.info.gameMode == "CLASSIC") {

                    if (data.info.gameType == "CUSTOM_GAME") {
                        txtGameMode.text = "????????? ?????? ??????"
                    } else if (data.info.gameType == "TUTORIAL_GAME") {
                        txtGameMode.text = "???????????? ??????"
                    } else {
                        if (data.info.queueId == 420) {
                            txtGameMode.text = "??????/??????"
                        } else if (data.info.queueId == 430) {
                            txtGameMode.text = "????????????"
                        } else if (data.info.queueId == 440) {
                            txtGameMode.text = "????????????"
                        } else if (data.info.queueId == 700) {
                            txtGameMode.text = "??????"
                        } else {
                            txtGameMode.text = data.info.gameMode
                        }
                    }

                } else if (data.info.gameMode == "PRACTICETOOL") {
                    txtGameMode.text = "????????????"
                } else {
                    txtGameMode.text = data.info.gameMode
                    // URF, ???????????? ???????????? ?????? gameMode??? ????????? ??????.
                }

//              ???????????? textview
                var mini = data.info.gameDuration / 60
                Log.d("???", mini.toString())
                var seco = data.info.gameDuration - (60 * mini)
                Log.d("???", seco.toString())
//                ???????????? 0????????????
                txtMatchTime.text = "${String.format("%02d", mini)}:${String.format("%02d", seco)}"


//              ???????????? textview

                val now = Calendar.getInstance()
                val timeAgo = now.timeInMillis - data.info.gameEndTimestamp

                if (timeAgo < 24 * 60 * 60 * 1000) {

//                    24?????? ?????? : ? ?????? ???
                    val diff = timeAgo / 1000 / 60 / 60
                    txtGameDate.text = "${diff}?????? ???"
                } else if (timeAgo < 10 * 24 * 60 * 60 * 1000) {
//            10??? ?????? : ???? ???
                    val diffDay = timeAgo / 1000 / 60 / 60 / 24
                    txtGameDate.text = "${diffDay}??? ???"
                } else {
                    val sdf = SimpleDateFormat("M??? d???")
//            10??? ?????? : sdf??? ???????????? ??????.
                    txtGameDate.text = sdf.format(data.info.gameStartTimestamp)
                }


                for (gamer in data.info.participants) {
//                    Log.d("??? ??? ???????????????", gamer.summonerName)

                    if (gamer.puuid == myPuuId) {
//                        Log.d("??? ???????", gamer.role)

//                      ???/??? ???????????? textview
                        if (gamer.win == true) {
                            txtWin.text = "???"
                            linearColor.setBackgroundColor(Color.parseColor("#3A6BF6"))
                        } else {
                            txtWin.text = "???"
                            linearColor.setBackgroundColor(Color.parseColor("#E24C4D"))
                        }


//                        ????????? ????????? ?????????
                        Glide.with(mContext)
                            .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/champion/${gamer.championName}.png")
                            .into(imgUsedChamp)

                        val imgDUrl =
                            "http://ddragon.leagueoflegends.com/cdn/12.5.1/img/spell/${SpellDataUtil.spellHashMap[gamer.summoner1Id]}.png"
//                        Log.d("D??????", imgDUrl)
//                        Log.d("summonerId", gamer.summoner1Id.toString())
                        val imgFUrl =
                            "http://ddragon.leagueoflegends.com/cdn/12.5.1/img/spell/${SpellDataUtil.spellHashMap[gamer.summoner2Id]}.png"
//                        Log.d("F??????", imgFUrl)
//                        Log.d("summonerId", gamer.summoner2Id.toString())
                        Glide.with(mContext).load(imgDUrl).into(imgD)
                        Glide.with(mContext).load(imgFUrl).into(imgF)


//                        ????????? ??? ?????????
                        val imgRunUrl =
                            "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/${gamer.perks.styles[0].style}_Domination.png"
                        Log.d("item0", gamer.perks.styles[0].style.toString())
                        Log.d("item0", gamer.perks.styles[0].selections[0].perk.toString())
                        Log.d("item0", gamer.perks.styles[1].style.toString())


                        val runeJsonArr = RuneJsonData.getRunesJsonArray(mContext)

                        for (i in 0 until runeJsonArr.length()) {

                            val runeInfoObj = runeJsonArr.getJSONObject(i)
                            Log.d("?????????", runeInfoObj.toString())

                            val runeId = runeInfoObj.getInt("id")

                            if (gamer.perks.styles[0].style == runeId) {
//                                ???????????? ?????? ????????? ??????.
//                                ??? ????????? ?????? ?????? ?????????, ?????? ??? ?????? ?????? ????????????.

                                val slotsArr = runeInfoObj.getJSONArray("slots")

//                                ??????/????????? ??? ?????? ??? ????????? ?????? ??????
                                val mainSlotObj = slotsArr.getJSONObject(0)

                                val runesArr = mainSlotObj.getJSONArray("runes")

                                for (j in 0 until runesArr.length()) {

                                    val runeObj = runesArr.getJSONObject(j)
                                    val mainRuneId = runeObj.getInt("id")

                                    if (gamer.perks.styles[0].selections[0].perk == mainRuneId) {
                                        val mainRuneIcon = runeObj.getString("icon")
                                        val mainRuneUrl =
                                            "https://ddragon.leagueoflegends.com/cdn/img/${mainRuneIcon}"

                                        Glide.with(mContext).load(mainRuneUrl).into(imgUsedRun1)
                                    }

                                }


                            }

                            if (gamer.perks.styles[1].style == runeId) {

//                                ???????????? ?????? ????????? ??????
                                val subRuneIcon = runeInfoObj.getString("icon")
                                val subRuneURL =
                                    "https://ddragon.leagueoflegends.com/cdn/img/${subRuneIcon}"

                                Glide.with(mContext).load(subRuneURL).into(imgUsedRun2)

                            }

                        }


//                        ????????? ???1 ?????????
                        if (gamer.item0 != 0) {
//                            ??????????????????
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item0}.png")
                                .into(imgItem1)
                        } else {
//                            ???????????????
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem1)
                        }
//                        ????????? ???2 ?????????
                        if (gamer.item1 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item1}.png")
                                .into(imgItem2)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem2)
                        }
//                        ????????? ???3 ?????????
                        if (gamer.item2 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item2}.png")
                                .into(imgItem3)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem3)
                        }
//                        ????????? ???4 ?????????
                        if (gamer.item3 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item3}.png")
                                .into(imgItem4)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem4)
                        }
//                        ????????? ???5 ?????????
                        if (gamer.item4 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item4}.png")
                                .into(imgItem5)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem5)
                        }
//                        ????????? ???6 ?????????
                        if (gamer.item5 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item5}.png")
                                .into(imgItem6)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItem6)
                        }
//                        ????????? ?????? ?????????
                        if (gamer.item6 != 0) {
                            Glide.with(mContext)
                                .load("http://ddragon.leagueoflegends.com/cdn/12.5.1/img/item/${gamer.item6}.png")
                                .into(imgItemWard)
                        } else {
                            Glide.with(mContext).load(R.drawable.opggitem).into(imgItemWard)
                        }


//                      ??? ?????? ???????????? textview
                        txtKda.text = "${gamer.kills} / ${gamer.deaths} / ${gamer.assists}"

//                      KDA??????

                        if (gamer.deaths == 0) {
                            txtKillPer.text = "Perfact"
                        } else {
                            var kda = (gamer.kills + gamer.assists) / gamer.deaths
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

        //    ?????? xml??? inflate?????? ???????????????
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

        //  ????????? ????????????
        override fun getItemCount() =
            mMatchDetailList.size + 2 // ?????? ????????? ?????? ????????? ??????


        //    ????????? ?????? ????????? ??????
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


//        ???????????? 1????????????
            if (holder.itemViewType == code.ViewType.multi_type1) {
                (holder as FirstViewHolder).bind(mSummonerData)

            }
//        ???????????? 2????????????
            if (holder.itemViewType == code.ViewType.multi_type2) {
//            val data2 = mLeagueList[position]
//            Log.d("data2", data2.toString())

                (holder as SecondViewHolder).bind(mThisSeasonRankList)
            }
//        ???????????? 3????????????

            if (holder.itemViewType == code.ViewType.multi_type3) {
                val data = mMatchDetailList[position - 2] // 0,1????????? ???????????????

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










