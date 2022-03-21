package com.zeroheat.lolandroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zeroheat.lolandroll.databinding.ActivityParcticBinding

class ParcticActivity : BaseActivity() {
    lateinit var binding : ActivityParcticBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_parctic)
        getSupportActionBar()?.hide()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}