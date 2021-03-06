package com.zeroheat.lolandroll.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.databinding.FragmentMyDefalutBinding

class MySettingFragment : BaseFragment() {

    lateinit var binding : FragmentMyDefalutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_my_defalut, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

    }

    override fun setValues() {

    }


}