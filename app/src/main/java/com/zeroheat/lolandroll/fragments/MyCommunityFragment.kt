package com.zeroheat.lolandroll.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.R
import com.zeroheat.lolandroll.SearchSummonerActivity
import com.zeroheat.lolandroll.databinding.FragmentMyHomeBinding
import com.zeroheat.lolandroll.databinding.FragmentMyTftBinding

class MyCommunityFragment : BaseFragment() {

    lateinit var binding : FragmentMyTftBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {binding = DataBindingUtil.inflate( inflater, R.layout.fragment_my_tft, container, false )
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }


    override fun setupEvents() {

        binding.btnSearch1.setOnClickListener {
            val myIntent = Intent(mContext, SearchSummonerActivity::class.java )
            mContext.startActivity(myIntent)
        }
        binding.edtTp1.setOnClickListener {
            val myIntent = Intent(mContext, SearchSummonerActivity::class.java )
            mContext.startActivity(myIntent)
        }

    }

    override fun setValues() {

    }


}