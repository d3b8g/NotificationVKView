package net.d3b8g.notificationvkview.FramePosition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.ArrayMap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import de.hdodenhof.circleimageview.CircleImageView
import net.d3b8g.notificationvkview.Constants.VK_Constants.Companion.client_id
import net.d3b8g.notificationvkview.Models.LPModels
import net.d3b8g.notificationvkview.Models.LPParser
import net.d3b8g.notificationvkview.Models.UserSimpleModels
import net.d3b8g.notificationvkview.R
import net.d3b8g.notificationvkview.Requests.LongPollLink
import net.d3b8g.notificationvkview.Requests.LongPollRequest
import net.d3b8g.notificationvkview.Requests.UserSimpleRequest

class Content:Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater.inflate(R.layout.f_my_account,container,false)

        val avatar = inflate.findViewById<CircleImageView>(R.id.content_avatar)
        val name = inflate.findViewById<TextView>(R.id.content_username)

        VK.execute(UserSimpleRequest(),object : VKApiCallback<UserSimpleModels>{
            override fun fail(error: VKApiExecutionException) {
                Log.d("RRR","smth wrong $error)")
            }

            override fun success(result: UserSimpleModels) {
                Picasso.get().load(result.avatar).into(avatar)
                name.text = result.username
                client_id = result.id
                Log.e("RRR","все ок")
            }
        })

        VK.execute(LongPollRequest(),object: VKApiCallback<LPModels>{
            override fun fail(error: VKApiExecutionException) {
                Log.d("RRR","smth wrong $error)")
            }

            override fun success(result: LPModels) {
                LongPollLink().execute("https://${result.server}?act=a_check&key=${result.key}&ts=${result.ts}&wait=25&mode=202&version=6")
                global_ts = result.ts
                server = result.server
                key = result.key
            }
        })

        return inflate
    }

    private fun simulateLP() {
        var arrayMap: ArrayMap<String, String> = ArrayMap()
        arrayMap["Videos"] = "https://link_to_video"
        arrayMap["Sticker"] = "sticker_link"
    }

    fun editFrame(model:LPParser){
        Log.e("RRR","${model.user_id}")
    }

    fun requestLP(){
        LongPollLink().execute("https://$server?act=a_check&key=$key&ts=$global_ts&wait=25&mode=202&version=6")
    }

    companion object {
        var global_ts:Long = 0
        var server:String = ""
        var key:String = ""
    }
}