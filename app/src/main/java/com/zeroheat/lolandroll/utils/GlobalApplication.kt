package com.zeroheat.lolandroll.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"4b815a9d074d2ea9015de57ea89fe165")
    }
}