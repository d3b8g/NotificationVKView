package net.d3b8g.notificationvkview.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonArray

class UserSimpleParser (
        val id:Int = 0,
        val username:String = "",
        val avatar:String? = ""):Parcelable{
    constructor(parcel:Parcel):this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(username)
        dest.writeString(avatar)
    }

    override fun describeContents(): Int {
       return 0
    }

    companion object CREATOR : Parcelable.Creator<UserSimpleParser>{
        override fun createFromParcel(source: Parcel): UserSimpleParser {
            return UserSimpleParser(source)
        }

        override fun newArray(size: Int): Array<UserSimpleParser?> {
             return arrayOfNulls(size)
        }

        fun parse(json:JsonArray):UserSimpleParser{
            val id = json.get(0).asJsonObject.get("id").asInt
            val username = "${json.get(0).asJsonObject.get("first_name").asString} ${json.get(0).asJsonObject.get("last_name").asString}"
            val avatar = json.get(0)?.asJsonObject?.get("photo_200_orig")?.asString
            return UserSimpleParser(id,username,avatar)
        }
    }
}