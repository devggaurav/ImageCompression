package com.gc.imagecompression

import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch


//
// Created by Code For Android on 22/10/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

@Composable
fun PhotoPickerScreen(
    imageCompressor: ImageCompressor,
    fileManager: FileManager,
    modifier: Modifier = Modifier
){

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val photoPicker = rememberLauncherForActivityResult(
      contract = ActivityResultContracts.PickVisualMedia(),
    ){ contentUri ->

        if (contentUri == null){
            return@rememberLauncherForActivityResult
        }

        val mimeType = context.contentResolver.getType(contentUri)
        val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
        scope.launch {

            fileManager.saveImage(
                contentUri = contentUri,
                fileName = "uncompressed.$extension"
            )


        }

        scope.launch {
         val compressedImage =   imageCompressor.compressImage(
                contentUri = contentUri,
                compressionThreshold = 200 * 1024L
            )
         fileManager.saveImage(
             bytes = compressedImage ?: return@launch,
             fileName = "compressed.$extension"
         )

        }



    }

    Box(modifier = Modifier.fillMaxSize())




}