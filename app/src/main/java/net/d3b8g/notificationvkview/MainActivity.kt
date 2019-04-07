package net.d3b8g.notificationvkview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.vk.api.sdk.VK
import net.d3b8g.notificationvkview.FramePosition.Content
import net.d3b8g.notificationvkview.FramePosition.NullAccount
import net.d3b8g.notificationvkview.Helpers.replaceFragment
import net.d3b8g.notificationvkview.Intefaces.LoginWell

class MainActivity : AppCompatActivity(),LoginWell {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<FrameLayout>(R.id.user_indent)
        val content = findViewById<FrameLayout>(R.id.content_frame)

        if(VK.isLoggedIn())replaceFragment(NullAccount(),content.id)
        else replaceFragment(Content(),content.id)
    }
}
