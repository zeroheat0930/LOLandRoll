package com.zeroheat.lolandroll.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.zeroheat.lolandroll.api.APIList

abstract class BaseFragment : Fragment() {

    lateinit var mContext: Context
    lateinit var apiList: APIList

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext()

    }

    abstract fun setupEvents()
    abstract fun setValues()

}