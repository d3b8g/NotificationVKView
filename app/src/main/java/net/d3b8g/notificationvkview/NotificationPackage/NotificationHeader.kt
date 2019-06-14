package net.d3b8g.notificationvkview.NotificationPackage

import android.app.NotificationManager
import android.content.Context
import android.support.v4.app.NotificationCompat
import net.d3b8g.notificationvkview.R

class NotificationHeader(val context: Context){

    var notificationManager: NotificationManager?=null

    private fun init() {
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun startNotify(title:String,url_ava:String){
        init()

        val builder = NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(title)

        notificationManager?.notify(1364,builder.build())
    }

}