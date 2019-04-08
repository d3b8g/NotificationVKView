package net.d3b8g.notificationvkview.Models

class LPParser (
    val action_id:Int = 0,
    val user_id:Long = 0,
    val chat_id:Long?=0,
    val body_content: body_content
)
data class body_content(val message_id:Int?,val message_text:String,val message_time:Long)