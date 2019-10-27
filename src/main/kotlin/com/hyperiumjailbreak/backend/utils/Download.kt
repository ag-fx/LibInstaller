package com.hyperiumjailbreak.backend.utils

import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.File
import java.nio.file.Files

@Throws(Exception::class)
fun download(dlUrl: String, outName: String, outDir: File) {
    val url = URL(dlUrl)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    println("Got response code ${connection.responseCode}")

    val response = StringBuilder()
    BufferedReader(InputStreamReader(connection.inputStream)).use { inputThing ->
        val line: String? = inputThing.readLine()
        while (line != null) {
            response.append(line)
        }
    }
    val file = File(outDir, outName)
    file.mkdirs()
    Files.write(file.toPath(), response.toString().toByteArray())
}
