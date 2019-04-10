package net.d3b8g.notificationvkview.Models

import android.os.Parcel
import android.os.Parcelable
import android.util.ArrayMap
import android.util.Log
import com.google.gson.JsonArray

class LPParser (
    val user_id:Long = 0,
    val chat_id:Long?=0,
    val message_id:Int,
    val attachments:ArrayMap<String,String>,
    val message_time:Long):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readInt(),
        parcel.readValue(ArrayMap::class.java.classLoader) as ArrayMap<String,String>,
        parcel.readLong()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(user_id)
        dest.writeValue(chat_id) as? Long
        dest.writeValue(message_id)
        dest.writeValue(attachments)
        dest.writeLong(message_time)
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<LPParser> {
        override fun createFromParcel(parcel: Parcel): LPParser {
            return LPParser(parcel)
        }

        override fun newArray(size: Int): Array<LPParser?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JsonArray): LPParser {
            val response_code_lp = json.get(0).asInt
            val chat_id: Long? = 0
            var arrayMap: ArrayMap<String, String> = ArrayMap()
            return when (response_code_lp) {
                2 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                3 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                4 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                5 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                6 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                7 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                8 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                9 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                10 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                11 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                12 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                13 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                14 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                51 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                52 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                63 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                64 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                70 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                80 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                112 -> {
                    LPParser(0, 0, 0, arrayMap, 0)
                }
                else -> {
                    Log.e("RRR", "Замечен новый ОТВЕТ\n$json")
                    LPParser(0, 0, 0, arrayMap, 0)
                }

            }
        }
    }
}