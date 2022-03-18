package com.zeroheat.lolandroll.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.SearchSummonerActivity
import com.zeroheat.lolandroll.databinding.FragmentMyHomeBinding
import com.zeroheat.lolandroll.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyHomeFragment : BaseFragment() {

    lateinit var binding : FragmentMyHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {binding = DataBindingUtil.inflate( inflater, R.layout.fragment_my_home, container, false )
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        binding.btnSearch.setOnClickListener {
             val myIntent = Intent(mContext,SearchSummonerActivity::class.java )
            mContext.startActivity(myIntent)
        }
        binding.edtTp.setOnClickListener {
            val myIntent = Intent(mContext,SearchSummonerActivity::class.java )
            mContext.startActivity(myIntent)
        }

    }

    override fun setValues() {

    }


}