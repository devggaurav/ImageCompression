package com.gc.imagecompression

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


//
// Created by Code For Android on 22/10/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class FileManager(
    private val context: Context
) {

    suspend fun saveImage(
        contentUri: Uri,
        fileName: String,
    ) {
        withContext(Dispatchers.IO) {
            context.contentResolver.openInputStream(contentUri)?.use { inputStream ->

                context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                         inputStream.copyTo(outputStream)

                }


            }


        }


    }






}