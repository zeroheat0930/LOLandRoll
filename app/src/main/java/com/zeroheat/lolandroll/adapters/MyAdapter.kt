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

class MyAdapter internal constructor(
    val mContext : Context,
    var myDataList: ArrayList<DataItem>



) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val context = parent.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return if (viewType == code.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.first_content, parent, false)
            CenterViewHolder(view)
        } else if (viewType == code.ViewType.LEFT_CONTENT) {
            view = inflater.inflate(R.layout.second_content, parent, false)
            LeftViewHolder(view)
        } else {
            view = inflater.inflate(R.layout.third_content, parent, false)
            RightViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is CenterViewHolder) {
            viewHolder.content.text = myDataList!![position].content
        } else if (viewHolder is LeftViewHolder) {
            viewHolder.name.text = myDataList!![position].name
            viewHolder.content.text = myDataList!![position].content
        } else {
            (viewHolder as RightViewHolder).name.text = myDataList!![position].name
            viewHolder.content.text = myDataList!![position].content
        }
    }

    override fun getItemCount(): Int {
        return myDataList!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return myDataList!![position].viewType
    }

    inner class CenterViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView

        init {
            content = itemView.findViewById(R.id.content)
        }
    }

    inner class LeftViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView
        var name: TextView
        var image: ImageView

        init {
            content = itemView.findViewById(R.id.content)
            name = itemView.findViewById(R.id.name)
            image = itemView.findViewById(R.id.imageView)
        }
    }

    inner class RightViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var content: TextView
        var name: TextView
        var image: ImageView

        init {
            content = itemView.findViewById(R.id.content)
            name = itemView.findViewById(R.id.name)
            image = itemView.findViewById(R.id.imageView)
        }
    }

    init {
        myDataList = dataList
    }
}