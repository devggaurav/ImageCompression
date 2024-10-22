package com.gc.imagecompression

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


//
// Created by Code For Android on 22/10/24.
// Copyright (c) 2024 CFA. All rights reserved.
//

class ImageCompressor(
    private val context : Context
) {

    suspend fun compressImage(
        contentUri : Uri,
        compressionThreshold : Long,
    ) : ByteArray? {

        return withContext(Dispatchers.IO){
            val mimeType = context.contentResolver.getType(contentUri)
             val inputBytes = context.contentResolver.openInputStream(contentUri)
                 ?.use { it.readBytes() } ?: return@withContext null

            val bitmap = BitmapFactory.decodeByteArray(inputBytes,0,inputBytes.size)

            val comporessFormat = when(mimeType){
                "image/jpeg" -> Bitmap.CompressFormat.JPEG
                "image/png" -> Bitmap.CompressFormat.PNG
                "image/webp" -> if (Build.VERSION.SDK_INT >= 30) {
                       Bitmap.CompressFormat.WEBP_LOSSLESS
                }else {
                    Bitmap.CompressFormat.WEBP
                }
            else -> Bitmap.CompressFormat.JPEG

            }


            do {

            }while ()


        }


    }



}