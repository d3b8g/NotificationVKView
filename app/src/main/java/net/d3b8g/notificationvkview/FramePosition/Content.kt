package net.d3b8g.notificationvkview.FramePosition

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import net.d3b8g.notificationvkview.Models.LPModels
import net.d3b8g.notificationvkview.R
import net.d3b8g.notificationvkview.Requests.LongPollLink
import net.d3b8g.notificationvkview.Requests.LongPollRequest

class Content:Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.f_content,container,false)

        VK.execute(LongPollRequest(),object: VKApiCallback<LPModels>{
            override fun fail(error: VKApiExecutionException) {
                Handler().postDelayed({
                    TODO("все по новой ")
                },1000)
            }

            override fun success(result: LPModels) {
                Log.e("RRR","${result.key}")
                LongPollLink().execute("https://${result.server}?act=a_check&key=${result.key}&ts=${result.ts}&wait=25&mode=202&version=6")
            }
        })

        return  inflate
    }
}