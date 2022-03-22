package com.zeroheat.lolandroll.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.datas.SummonerResponse
import com.zeroheat.lolandroll.datas.chatdata

class chatRecyclerViewAdapter (
    val mContext : Context,
    val mList: List<chatdata>
) : RecyclerView.Adapter<chatRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val txtContext = view.findViewById<TextView>(R.id.txtContent)
        val txtCreatedAt = view.findViewById<TextView>(R.id.txtCreatedAt)

        fun bind(data:chatdata){

            txtContext.text = data.content
            txtCreatedAt.text = data.createdAt
            }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main2_list_item, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount() = mList.size



}






