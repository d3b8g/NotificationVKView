package net.d3b8g.notificationvkview.FramePosition

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import net.d3b8g.notificationvkview.Helpers.setFont
import net.d3b8g.notificationvkview.MainActivity
import net.d3b8g.notificationvkview.R

class NullAccount:Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.f_null_account,container,false)
        val title = inflater.findViewById<TextView>(R.id.t_not_auth)
        val btn_auth = inflater.findViewById<Button>(R.id.b_auth)
        title.typeface = setFont(inflater.context)
        btn_auth.typeface = setFont(inflater.context)

        btn_auth.setOnClickListener {
            VK.login((inflater.context as Activity), arrayListOf(VKScope.MESSAGES,VKScope.GROUPS))
        }

        return inflater
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object:VKAuthCallback{
            override fun onLogin(token: VKAccessToken) {
                MainActivity.LoggedAccess(layoutInflater.context)
                Log.e("RRR","RRRRRRR")
            }

            override fun onLoginFailed(errorCode: Int) {
                Log.e("RRR","$errorCode")
            }
        }
        if(data==null||!VK.onActivityResult(requestCode, resultCode, data,callback)){
            Log.e("RRR","smth wrong")
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}