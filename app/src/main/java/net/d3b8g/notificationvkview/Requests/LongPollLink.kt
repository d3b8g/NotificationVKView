package net.d3b8g.notificationvkview.Requests

import android.os.AsyncTask
import java.net.HttpURLConnection
import java.net.URL

class LongPollLink:AsyncTask<String,String,String>(){
    override fun doInBackground(vararg url: String?): String {
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

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

    }
}