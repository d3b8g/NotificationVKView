package net.d3b8g.notificationvkview.Requests

import android.util.Log
import com.google.gson.JsonParser
import com.vk.api.sdk.VKApiManager
import com.vk.api.sdk.VKApiResponseParser
import com.vk.api.sdk.VKMethodCall
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException
import com.vk.api.sdk.internal.ApiCommand
import net.d3b8g.notificationvkview.Models.LPModels
import org.json.JSONException

class LongPollRequest(val xxx: String = "") : ApiCommand<LPModels>(){
    override fun onExecute(manager: VKApiManager): LPModels {
        val call = VKMethodCall.Builder()
                .method("messages.getLongPollServer")
                .args("Ip_version",3)
                .version("5.92")
                .build()

        return manager.execute(call,parseResponse())
    }

    class parseResponse:VKApiResponseParser<LPModels>{
        override fun parse(response: String?): LPModels {
            try {
                Log.e("RRR","$response")
                val data = JsonParser().parse(response!!).asJsonObject.get("response")
                return LPModels(key = data.asJsonObject.get("key").asString,
                server =  data.asJsonObject.get("server").asString,
                ts = data.asJsonObject.get("ts").asLong)
            }catch (ex:JSONException){
                throw VKApiIllegalResponseException(ex)
            }
        }
    }

}