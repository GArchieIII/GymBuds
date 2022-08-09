package com.example.gymbuds.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.gymbuds.R
import java.io.IOException

class GlideLoader(val context: Context) {

    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            Glide
                .with(context)
                .load(image.toString())
                .centerCrop()
                .placeholder(R.drawable.ic_user_placeholder)
                .dontAnimate()
                .into(imageView)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}