package kr.ac.kpu.ce2019152012.hair_you

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    /*companion object{
        var appContext : Context? = null
    }*/

    override fun onCreate() {
        super.onCreate()
        /*appContext = this*/
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "7d73ad51a6a4a5808b30eb48286e214c")
    }
}