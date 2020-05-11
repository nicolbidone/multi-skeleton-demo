package com.example.multiskeletondemo.fiftyshadesof

import android.animation.*
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import java.lang.ref.WeakReference

class GreyDrawable : Drawable() {
    var isFadein = false
    var grayColor = DEFAULT_GREY
    var valueAnimator: ValueAnimator? = null
    var paint: Paint
    var cornersRadius = 0
    var viewWeakReference: WeakReference<View>? = null
    override fun draw(canvas: Canvas) {
        paint.color = grayColor
        val rectF = RectF(
            0F,  // left
            0F,  // top
            bounds.width().toFloat(),  // right
            bounds.height().toFloat() // bottom
        )
        canvas.drawRoundRect(rectF, cornersRadius.toFloat(), cornersRadius.toFloat(), paint)
    }

    override fun setAlpha(i: Int) {}
    fun setCornerRadious(cornersRadius: Int) {
        this.cornersRadius = cornersRadius
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {}
    override fun getOpacity(): Int {
        return 255
    }

    fun start(view: View, darker: Boolean) {
        viewWeakReference = WeakReference(view)
        val defaultGrey =
            if (darker) DEFAULT_GREY_BOLD else DEFAULT_GREY
        val darkerGrey =
            if (darker) DARKER_GREY_BOLD else DARKER_GREY
        valueAnimator = ValueAnimator.ofInt(defaultGrey, darkerGrey)
        valueAnimator?.setRepeatCount(ObjectAnimator.INFINITE)
        valueAnimator?.setDuration(DURATION.toLong())
        valueAnimator?.setEvaluator(ArgbEvaluator())
        valueAnimator?.setRepeatMode(ValueAnimator.REVERSE)
        valueAnimator?.setInterpolator(LinearInterpolator())
        valueAnimator?.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
            grayColor = valueAnimator?.animatedValue as Int
            val v = viewWeakReference!!.get()
            v?.invalidate()
        })
        valueAnimator?.start()
    }

    fun cancel() {
        valueAnimator!!.cancel()
    }

    fun stop(callback: Callback?) {
        valueAnimator!!.cancel()
        if (isFadein) {
            stopFadeIn(callback)
        } else {
            stopGray(callback)
        }
    }

    fun stopFadeIn(callback: Callback?) {
        if (callback != null) {
            val v = viewWeakReference!!.get()
            if (v != null) {
                val alphaAnimator =
                    ObjectAnimator.ofFloat(v, View.ALPHA, 0f).setDuration(400)
                alphaAnimator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        callback.onFadeOutStarted()
                        callback.onFadeOutFinished()
                        val v = viewWeakReference!!.get()
                        if (v != null) {
                            ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start()
                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        onAnimationEnd(animation)
                    }
                })
                alphaAnimator.start()
            }
        }
    }

    fun stopGray(callback: Callback?) {
        val emptyColor = Color.argb(
            0,
            Color.red(grayColor),
            Color.green(grayColor),
            Color.blue(grayColor)
        )
        valueAnimator = ValueAnimator.ofInt(grayColor, emptyColor)
        valueAnimator?.setDuration(STOP_DURATION.toLong())
        valueAnimator?.setEvaluator(ArgbEvaluator())
        valueAnimator?.setInterpolator(AccelerateInterpolator())
        valueAnimator?.addUpdateListener(AnimatorUpdateListener { valueAnimator ->
            grayColor = valueAnimator?.animatedValue as Int
            val v = viewWeakReference!!.get()
            v?.invalidate()
        })
        valueAnimator?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (callback != null) {
                    callback.onFadeOutFinished()
                    val v = viewWeakReference!!.get()
                    if (v != null) {
                        ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start()
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                super.onAnimationCancel(animation)
                callback?.onFadeOutFinished()
            }
        })
        valueAnimator?.setStartDelay(50)
        valueAnimator?.setDuration(400)
        valueAnimator?.start()
        callback?.onFadeOutStarted()
    }

    interface Callback {
        fun onFadeOutStarted()
        fun onFadeOutFinished()
    }

    companion object {
        val DEFAULT_GREY = Color.argb(50, 200, 200, 200)
        val DARKER_GREY = Color.argb(160, 180, 180, 180)
        val DEFAULT_GREY_BOLD = Color.argb(50, 180, 180, 180)
        val DARKER_GREY_BOLD = Color.argb(180, 150, 150, 150)
        const val DURATION = 750
        const val STOP_DURATION = 200
    }

    init {
        paint = Paint()
    }
}
