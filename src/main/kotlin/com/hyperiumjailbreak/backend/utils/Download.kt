package com.hyperiumjailbreak.backend.utils

import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.File
import java.nio.file.Files

@Throws(Exception::class)
fun download(dlUrl: String, outName: String, outDir: File) {
    val connection = URL(dlUrl).openConnection() as HttpURLConnection
    connection.requestMethod = "GET"

    val status = connection.responseCode
    println("Got response code $status")

    if(status in 400..599) throw Exception("Got response code $status")

    val file = File(outDir, outName)
    file.mkdirs()
    val fpath = file.toPath()

    BufferedReader(
            InputStreamReader(connection.inputStream)
    ).use { inputThing ->
        val line: String? = inputThing.readLine()
        while (line != null) {
            Files.write(fpath, line.toByteArray())
        }
    }
}
