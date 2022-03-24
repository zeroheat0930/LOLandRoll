package com.zeroheat.lolandroll.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.recyclerview.DataItem
import com.zeroheat.lolandroll.recyclerview.code

class SearchUserrRecyclerAdapter (
    val mContext : Context,
    val mList: List<DataItem>
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

    }
        override fun getItemViewType(position: Int): Int {
            return when (position) {
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


    override fun getItemCount() = mList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mList[position]

    }

}

