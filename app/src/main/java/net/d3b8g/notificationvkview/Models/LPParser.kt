package net.d3b8g.notificationvkview.Models

import android.os.Parcel
import android.os.Parcelable
import android.util.ArrayMap
import com.google.gson.JsonArray
import net.d3b8g.notificationvkview.FramePosition.Content

class LPParser (
    val msg_list:Int = 0,
    val user_id:Long = 0,
    val chat_id:Long?=0,
    val message_id:Int,
    val attachments:ArrayMap<String,String>,
    val message_time:Long):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readLong(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readInt(),
        parcel.readValue(ArrayMap::class.java.classLoader) as ArrayMap<String,String>,
        parcel.readLong()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(msg_list)
        dest.writeLong(user_id)
        dest.writeValue(chat_id) as? Long
        dest.writeValue(message_id)
        dest.writeValue(attachments)
        dest.writeLong(message_time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LPParser> {
        override fun createFromParcel(parcel: Parcel): LPParser {
            return LPParser(parcel)
        }

        override fun newArray(size: Int): Array<LPParser?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JsonArray): LPParser {
            val response_code_lp = json.get(0).asJsonArray.get(0).asInt
            val chat_id: Long? = 0
            val msg_list:Int = json.size()
            var arrayMap: ArrayMap<String, String> = ArrayMap()
            Content().requestLP()
            for(i in 0..json.size()){
                if (response_code_lp == 4 && json.get(i).asJsonArray.get(7).toString().contains("attach1_type")) {

                }
            }
            return LPParser(0,0, 0, 0, arrayMap, 0)
        }
    }
}