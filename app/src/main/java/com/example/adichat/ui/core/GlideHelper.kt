package com.example.adichat.ui.core

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.adichat.R
import com.example.adichat.remote.service.ServiceFactory

object GlideHelper  {
    const val TAG = "GlideHelper"

    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Drawable? = iv.drawable) {
        val imgPath = ServiceFactory.SERVER_URL + path?.replace("..", "")

        Glide.with(context)
            .load(imgPath)
            .placeholder(placeholder)
            .error(placeholder)
            .into(iv)
        Log.d(TAG, "imgPath = " + imgPath + " ServiceFactory.SERVER_URL=  " + ServiceFactory.SERVER_URL + " path = " + path)
    }

    fun loadImage(context: Context, path: String?, iv: ImageView, placeholder: Int) {
        loadImage(context, path, iv, context.resources.getDrawable(placeholder))
    }

    @JvmStatic
    @BindingAdapter("profileImage")
    fun ImageView.loadImage(image: String?) {
        loadImage(this.context, image, this, R.drawable.ic_account_circle)
    }

    @JvmStatic
    @BindingAdapter("messageImage")
    fun ImageView.loadMessageImage(message: String?) {
        if (message.isNullOrBlank()) return
        loadImage(this.context, message, this, R.drawable.placeholder)
        Log.d(TAG, "messageImage = " + message)
    }
}