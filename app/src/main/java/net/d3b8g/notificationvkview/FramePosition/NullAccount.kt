package net.d3b8g.notificationvkview.FramePosition

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
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
            MainActivity.btn_login(activity as Context)
        }

        return inflater
    }

}