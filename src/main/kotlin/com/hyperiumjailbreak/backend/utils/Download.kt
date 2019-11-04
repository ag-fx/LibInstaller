package com.hyperiumjailbreak.backend.utils

import java.net.URL
import java.io.File
import org.apache.commons.io.FileUtils

@Throws(Exception::class)
fun download(dlUrl: String, outDir: File) {
    FileUtils.copyURLToFile(URL(dlUrl), outDir)
}
