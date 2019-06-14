package net.d3b8g.notificationvkview.Requests

import android.os.AsyncTask
import android.os.Handler
import android.util.Log
import com.google.gson.JsonParser
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import net.d3b8g.notificationvkview.FramePosition.Content
import net.d3b8g.notificationvkview.Models.LPModels
import net.d3b8g.notificationvkview.Models.LPParser
import java.net.HttpURLConnection
import java.net.URL

class LongPollLink :AsyncTask<String,String,String>(){
    override fun doInBackground(vararg url: String?): String {
        Log.d("RRR","запрос в ЛПЛинк")
        var text:String = ""
        var connection = URL(url[0]).openConnection() as HttpURLConnection
        try{
            connection.connect()
            text = connection.inputStream.use { it.reader().use { reader-> reader.readText()} }
        }finally {
            connection.disconnect()
        }
        return text
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        Log.d("RRR","пришел ответ ")
        val json = JsonParser().parse(result).asJsonObject
        Content.global_ts = if(json?.get("ts")?.asLong == null) {
            var new_ts:Long = 0
            Handler().postDelayed({
                VK.execute(LongPollRequest(),object: VKApiCallback<LPModels> {
                    override fun fail(error: VKApiExecutionException) {
                        Log.d("RRR","smth wrong $error)")
                    }

                    override fun success(result: LPModels) {
                        Content.global_ts = result.ts
                        new_ts = result.ts
                        Content.server = result.server
                        Content.key = result.key
                    }
                })
            },2000)
            new_ts
        } else json.get("ts").asLong
        LPParser.parse(json = json.getAsJsonArray("updates"))
    }
}