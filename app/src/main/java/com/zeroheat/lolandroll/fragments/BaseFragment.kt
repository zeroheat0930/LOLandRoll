package com.zeroheat.lolandroll.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zeroheat.lolandroll.api.APIList
import com.zeroheat.lolandroll.api.ServerAPI2

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var apiList: APIList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI2.getRetrofit(mContext)
        apiList = retrofit.create( APIList::class.java )

    }

    abstract fun setupEvents()
    abstract fun setValues()

}