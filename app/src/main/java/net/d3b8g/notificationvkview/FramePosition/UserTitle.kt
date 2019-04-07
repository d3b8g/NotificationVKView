package net.d3b8g.notificationvkview.FramePosition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.d3b8g.notificationvkview.R

class UserTitle:Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflater = inflater.inflate(R.layout.f_user_title,container,false)

        return  inflater
    }
}