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

class SearchUserrRecyclerAdapter (
    val mContext : Context,
    val mList: List<SummonerResponse>
) : RecyclerView.Adapter<SearchUserrRecyclerAdapter.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

//        val txtStartPlaceName = view.findViewById<TextView>(R.id.txtStartPlaceName)
//        val imgViewMap = view.findViewById<ImageView>(R.id.imgViewMap)

        fun bind(data:SummonerResponse){

//            txtStartPlaceName.text = data.name

            }

        }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val view = LayoutInflater.from(mContext).inflate(R.layout.my_place_list_item,parent,false)
//        return MyViewHolder(view)
//    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount() = mList.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

}

