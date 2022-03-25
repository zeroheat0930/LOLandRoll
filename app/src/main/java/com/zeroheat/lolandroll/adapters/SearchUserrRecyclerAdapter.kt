package com.zeroheat.lolandroll.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.api.API3List
import com.zeroheat.lolandroll.api.AsiaServerAPI
import com.zeroheat.lolandroll.api.ServerAPI1
import com.zeroheat.lolandroll.api.ServerAPI2
import com.zeroheat.lolandroll.datas.ChampionBasicData
import com.zeroheat.lolandroll.datas.MatchDetailData
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserrRecyclerAdapter (
    val mContext : Context,
    val mMatchIdList: List<String>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class FirstViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView

        init {
            content = itemView.findViewById(R.id.btnRank)
        }

    }
    inner class SecondViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView
        var image : ImageView

        init {
            content = itemView.findViewById(R.id.btnRankValue)
            content = itemView.findViewById(R.id.txtRankNow)
            content = itemView.findViewById(R.id.txtLp)
            content = itemView.findViewById(R.id.txtWinLoss)
            image = itemView.findViewById(R.id.imgRank)
        }

        fun bind(data: DataItem) {

        }

    }
    inner class ThirdViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView
        var image: ImageView

        init {
            content = itemView.findViewById(R.id.txtWin)
            content = itemView.findViewById(R.id.txtMatchTime)
            content = itemView.findViewById(R.id.txtKda)
            content = itemView.findViewById(R.id.txtKillPer)
            content = itemView.findViewById(R.id.txtGameMode)
            content = itemView.findViewById(R.id.txtGameDate)
            image = itemView.findViewById(R.id.imgUsedChamp)
            image = itemView.findViewById(R.id.imgD)
            image = itemView.findViewById(R.id.imgF)
            image = itemView.findViewById(R.id.imgUsedRun1)
            image = itemView.findViewById(R.id.imgUsedRun2)
            image = itemView.findViewById(R.id.imgItem1)
            image = itemView.findViewById(R.id.imgItem2)
            image = itemView.findViewById(R.id.imgItem3)
            image = itemView.findViewById(R.id.imgItem4)
            image = itemView.findViewById(R.id.imgItem5)
            image = itemView.findViewById(R.id.imgItem6)
            image = itemView.findViewById(R.id.imgItemWard)
        }
//아침커밋
    }
        override fun getItemViewType(position: Int): Int {

//            홀수줄 : 데이터목록
//            짝수줄 : 챔프목록
            return when(position) {
                0 -> code.ViewType.multi_type1
                1 -> code.ViewType.multi_type2
                else -> code.ViewType.multi_type3
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val context = mContext
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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


    override fun getItemCount() = mMatchIdList.size + 2 // 앞의 두개는 매치 목록이 아님

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

//        뷰타입이 3번일때는

        if (holder.itemViewType == code.ViewType.multi_type3) {
            val data = mMatchIdList[position-2] // 0,1번째는 다른거니까

            val retrofit = AsiaServerAPI.getRetrofit(mContext)
            val apiList3 = retrofit.create(API3List::class.java)
            apiList3.getMatchDetail(
                data,
                "RGAPI-8e6f79bb-ff54-4bde-a519-d7f8f85f7460").enqueue(object :
                Callback<MatchDetailData> {
                override fun onResponse(
                    call: Call<MatchDetailData>,
                    response: Response<MatchDetailData>
                ) {
                    val b = response.body()!!
                    Log.d("왜안되는건데", b.toString())
                }

                override fun onFailure(
                    call: Call<MatchDetailData>,
                    t: Throwable
                ) {

                }
            })
        }

    }

}

