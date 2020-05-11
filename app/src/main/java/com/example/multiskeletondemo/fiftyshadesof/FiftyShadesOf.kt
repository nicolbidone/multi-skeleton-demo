package com.example.multiskeletondemo.fiftyshadesof

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.multiskeletondemo.fiftyshadesof.viewstate.ImageViewState
import com.example.multiskeletondemo.fiftyshadesof.viewstate.TextViewState
import com.example.multiskeletondemo.fiftyshadesof.viewstate.ViewState
import java.util.*

class FiftyShadesOf(private val context: Context?) {
    private val viewsState: HashMap<View, ViewState<*>>
    private var fadein = true
    private var radius = 0
    private var started = false
    fun on(vararg viewsId: Int): FiftyShadesOf {
        if (context is Activity) {
            for (view in viewsId) {
                add(context.findViewById(view))
            }
        }
        return this
    }

    fun fadeIn(fadein: Boolean): FiftyShadesOf {
        this.fadein = fadein
        return this
    }

    fun setRadius(radius: Int): FiftyShadesOf {
        this.radius = radius
        return this
    }

    private fun add(view: View) {
        if (view is TextView) {
            viewsState[view] = TextViewState(view)
        } else if (view is ImageView) {
            viewsState[view] = ImageViewState(view)
        } else if (view is ViewGroup) {
            val viewGroup = view
            val count = viewGroup.childCount
            for (i in 0 until count) {
                val child = viewGroup.getChildAt(i)
                add(child)
            }
        }
    }

    fun on(vararg views: View): FiftyShadesOf {
        for (view in views) add(view)
        return this
    }

    fun except(vararg views: View?): FiftyShadesOf {
        for (view in views) {
            viewsState.remove(view)
        }
        return this
    }

    fun start(): FiftyShadesOf {
        if (!started) { //prepare for starting
            for (viewState in viewsState.values) {
                viewState.beforeStart()
            }
            started = true
            //start
            for (viewState in viewsState.values) {
                viewState.start(fadein, radius)
            }
        }
        return this
    }

    fun stop(): FiftyShadesOf {
        if (started) {
            for (viewState in viewsState.values) {
                viewState.stop()
            }
            started = false
        }
        return this
    }

    companion object {
        fun with(context: Context?): FiftyShadesOf {
            return FiftyShadesOf(context)
        }
    }

    init {
        viewsState = HashMap()
    }
}
