package com.example.multiskeletondemo.fiftyshadesof.viewstate

import android.graphics.drawable.Drawable
import android.view.View
import com.example.multiskeletondemo.fiftyshadesof.GreyDrawable

abstract class ViewState<V : View>(var view: V) {
    var background: Drawable? = null
    @JvmField
    protected var darker = false
    protected open fun restore() {}
    protected fun restoreBackground() {
        view.setBackgroundDrawable(background)
    }

    open fun beforeStart() {
        background = view.background
    }

    open fun start(fadein: Boolean, radius: Int) {
        val greyDrawable =
            GreyDrawable()
        view.setBackgroundDrawable(greyDrawable)
        greyDrawable.isFadein = fadein
        greyDrawable.setCornerRadious(radius)
        greyDrawable.start(view, darker)
    }

    fun stop() {
        val drawable = view.background
        if (drawable is GreyDrawable) {
            drawable.stop(object : GreyDrawable.Callback {
                override fun onFadeOutStarted() {
                    restore()
                }

                override fun onFadeOutFinished() {
                    restoreBackground()
                }
            })
        } else {
            restore()
        }
    }
}
