package net.d3b8g.notificationvkview.Helpers

import android.app.Application
import com.vk.api.sdk.VK

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        VK.initialize(applicationContext)
    }
}