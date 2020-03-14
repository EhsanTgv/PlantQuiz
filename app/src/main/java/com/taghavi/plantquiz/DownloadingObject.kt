package com.taghavi.plantquiz

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadingObject {

    @Throws(IOException::class)
    fun downloadJSONDataFrom(link: String): String {
        val stringBuilder = StringBuilder()
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection
        try {
            val bufferedInputString = BufferedInputStream(urlConnection.inputStream)
            val bufferedReader = BufferedReader(InputStreamReader(bufferedInputString))

            var inputLine: String?
            inputLine = bufferedReader.readLine()
            while (inputLine != null) {
                stringBuilder.append(inputLine)
                inputLine = bufferedReader.readLine()
            }
        } finally {
            urlConnection.disconnect()
        }

        return stringBuilder.toString()
    }
}