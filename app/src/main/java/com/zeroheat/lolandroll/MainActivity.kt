package com.zeroheat.lolandroll

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.zeroheat.lolandroll.adapters.MainViewPager2Adapter
import com.zeroheat.lolandroll.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    lateinit var binding : ActivityMainBinding

    val database = Firebase.database
    val myRef = database.getReference("message")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()

    }

    override fun setupEvents() {

 //        바텀 네비게이션의 메뉴 선택 > 뷰페이져의 페이지 이동

        binding.mainBottomNav.setOnItemSelectedListener {

//            어떤 메뉴가 선택되었는지? it 변수가 알려줌.

            when (it.itemId) {
                R.id.myHome ->{
                    binding.mainViewPager2.currentItem = 0
                    btnAdd.visibility = View.GONE
                    txtTitle.visibility = View.GONE
                }
                R.id.myHero -> {
                    binding.mainViewPager2.currentItem = 1
                    btnAdd.visibility = View.GONE
                    txtTitle.visibility = View.GONE
                }
                R.id.myComu -> {
                    binding.mainViewPager2.currentItem = 2
                    btnAdd.visibility = View.VISIBLE
                    txtTitle.visibility = View.VISIBLE
                }
                R.id.myDefault -> {
                    binding.mainViewPager2.currentItem = 3
                    btnAdd.visibility = View.GONE
                    txtTitle.visibility = View.GONE
                }
            }

            return@setOnItemSelectedListener true
        }

//        뷰페이저의 페이지 이동 > 바텀 네비게이션의 메뉴 선택
        binding.mainViewPager2.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback() {

//            추상 메쏘드 X. 이벤트처리 함수를 직접 오버라이딩

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.mainBottomNav.selectedItemId = when(position) {
                    0 -> R.id.myHome
                    1 -> R.id.myHero
                    2 -> R.id.myComu
                    else -> R.id.myDefault
                }

            }

        } )


    }

    override fun setValues() {

        binding.mainViewPager2.adapter = MainViewPager2Adapter(this) // 변수 : Activity => 객체 : context로 대입 불가

    }


}