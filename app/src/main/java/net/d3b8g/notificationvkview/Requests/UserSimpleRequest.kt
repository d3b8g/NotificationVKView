package net.d3b8g.notificationvkview.Requests

import android.util.Log
import com.google.gson.JsonParser
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import net.d3b8g.notificationvkview.Models.UserSimpleModels
import org.json.JSONException

class UserSimpleRequest : ApiCommand<UserSimpleModels>() {
    override fun onExecute(manager: VKApiManager): UserSimpleModels {
        val call = VKMethodCall.Builder()
                .method("users.get")
                .args("fields","photo_200_orig")
                .version("5.92")
                .build()
        return manager.execute(call,parseUserData())
    }

    class parseUserData : VKApiResponseParser<UserSimpleModels> {
        override fun parse(response: String): UserSimpleModels {
            try{
                val data = JsonParser().parse(response).asJsonObject.get("response").asJsonArray.get(0)
                Log.e("RRR","$data")
                return UserSimpleModels(
                    id = data.asJsonObject.get("id").asInt,
                    username = "${data.asJsonObject.get("first_name").asString} ${data.asJsonObject.get("last_name").asString}",
                    avatar = data.asJsonObject.get("photo_200_orig").asString
                )
            }catch (ex:JSONException){
                throw VKApiIllegalResponseException(ex)
            }
        }
    }
}