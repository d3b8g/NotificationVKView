package net.d3b8g.notificationvkview.FramePosition

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import net.d3b8g.notificationvkview.Models.LPModels
import net.d3b8g.notificationvkview.Models.LPParser
import net.d3b8g.notificationvkview.R
import net.d3b8g.notificationvkview.Requests.LongPollLink
import net.d3b8g.notificationvkview.Requests.LongPollRequest

class Content:Fragment(){

    lateinit var inflate:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inflate = inflater.inflate(R.layout.f_content,container,false)

       requestLP()

        return inflate
    }

    private fun simulateLP() {
        var arrayMap: ArrayMap<String, String> = ArrayMap()
        arrayMap["Videos"] = "https://link_to_video"
        arrayMap["Sticker"] = "sticker_link"
        Content.resultLP(inflate.context,
            LPParser(
                user_id = 412904874,
                chat_id = null,
                attachments =  arrayMap,
                message_id = 1865418,
                message_time = 1554848034
            ))

    }

    fun editFrame(){

    }

    fun requestLP(){
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
    }

    companion object {
        fun resultLP(context:Context,model:LPParser){

        }
    }
}