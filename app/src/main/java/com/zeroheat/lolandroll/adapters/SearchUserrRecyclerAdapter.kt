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
import com.zeroheat.lolandroll.recyclerview.code

class SearchUserrRecyclerAdapter (
    val mContext : Context,
    val mList: List<SummonerResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    inner class FirstViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
//        var content: TextView

        init {
//            content = itemView.findViewById(R.id.content)
        }
    }
    inner class SecondViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
//        var content: TextView

        init {
//            content = itemView.findViewById(R.id.content)
        }
    }
    inner class ThirdViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
//        var content: TextView

        init {
//            content = itemView.findViewById(R.id.content)
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

//        val txtStartPlaceName = view.findViewById<TextView>(R.id.txtStartPlaceName)
//        val imgViewMap = view.findViewById<ImageView>(R.id.imgViewMap)

        fun bind(data:SummonerResponse){

//            txtStartPlaceName.text = data.name

            }

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val context = mContext
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return if (viewType == code.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.first_content, parent, false)
            FirstViewHolder(view)
        } else if (viewType == code.ViewType.LEFT_CONTENT) {
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

