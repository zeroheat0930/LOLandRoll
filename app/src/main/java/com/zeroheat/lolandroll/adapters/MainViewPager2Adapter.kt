package com.zeroheat.lolandroll.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zeroheat.lolandroll.fragments.MyChampionFragment
import com.zeroheat.lolandroll.fragments.MyCommunityFragment
import com.zeroheat.lolandroll.fragments.MyHomeFragment
import com.zeroheat.lolandroll.fragments.MySettingFragment

class MainViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MyHomeFragment()
            1 -> MyChampionFragment()
            2 -> MyCommunityFragment()
            else -> MySettingFragment()
        }
    }
}