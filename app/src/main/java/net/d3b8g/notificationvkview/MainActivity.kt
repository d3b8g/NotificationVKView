package net.d3b8g.notificationvkview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.FrameLayout
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*
import net.d3b8g.notificationvkview.FramePosition.Content
import net.d3b8g.notificationvkview.FramePosition.NullAccount
import net.d3b8g.notificationvkview.Helpers.replaceFragment
import net.d3b8g.notificationvkview.Intefaces.LoginWell
import net.d3b8g.notificationvkview.NotificationPackage.NotificationHeader

class MainActivity : AppCompatActivity(),LoginWell {

    private val GLOBAL_TAG_CONTENT = "content_tag_activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<FrameLayout>(R.id.user_indent)
        val content = findViewById<FrameLayout>(R.id.content_frame)

        if(VK.isLoggedIn())replaceFragment(Content(),content.id,GLOBAL_TAG_CONTENT)
        else replaceFragment(NullAccount(),content.id,GLOBAL_TAG_CONTENT)

        NotificationHeader(this).startNotify("аудиосообщение","https://pp.userapi.com/c848416/v848416539/14b3f/zoiCcgQNuMA.jpg")
    }

    fun LoggedAccess(){
        replaceFragment(Content(),content_frame.id,GLOBAL_TAG_CONTENT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                LoggedAccess()
            }

            override fun onLoginFailed(errorCode: Int) {
                Log.e("RRR", "$errorCode")
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        fun btn_login(context: Context){
            VK.login(context as MainActivity, arrayListOf(VKScope.NOTIFY,VKScope.MESSAGES))
        }
    }

}
