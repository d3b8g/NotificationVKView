package net.d3b8g.notificationvkview.Helpers

import android.app.Application
import android.util.Log
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        VK.addTokenExpiredHandler(tokenTracker)
    }

    private val tokenTracker = object : VKTokenExpiredHandler{
        override fun onTokenExpired() {
            Log.e("RRR","fff")
        }
    }
}