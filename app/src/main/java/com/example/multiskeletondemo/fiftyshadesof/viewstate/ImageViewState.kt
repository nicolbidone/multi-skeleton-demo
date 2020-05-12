package com.example.multiskeletondemo.fiftyshadesof.viewstate

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

class ImageViewState(imageView: ImageView) :
    ViewState<ImageView>(imageView) {
    var source: Drawable? = null
    override fun beforeStart() {
        super.beforeStart()
        source = view.drawable
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun restore() {
        view.setImageDrawable(source)
    }
}
