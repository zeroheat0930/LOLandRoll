package com.zeroheat.lolandroll.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zeroheat.lolandroll.api.*

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var apiList: APIList
    lateinit var apiList1: API2List
    lateinit var apiList2: API3List
    lateinit var apiList3: APIListTFT

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

        val retrofit = ServerAPI1.getRetrofit(mContext)
        apiList = retrofit.create( APIList::class.java )

        val retrofit1 = ServerAPI2.getRetrofit(mContext)
        apiList1 = retrofit1.create( API2List::class.java )

        val retrofit2 = AsiaServerAPI.getRetrofit(mContext)
        apiList2 = retrofit2.create( API3List::class.java )

        val retrofit3 = ServerTFTAPI.getRetrofit(mContext)
        apiList3 = retrofit3.create( APIListTFT::class.java )

    }

    abstract fun setupEvents()
    abstract fun setValues()

}