package com.example.multiskeletondemo.fiftyshadesof.viewstate

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.TextView

class TextViewState(textView: TextView) :
    ViewState<TextView>(textView) {
    var textColor: ColorStateList? = null
    override fun beforeStart() {
        super.beforeStart()
        textColor = view.textColors
        darker = view.typeface != null && view.typeface.isBold
    }

    override fun restore() {
        view.setTextColor(textColor)
    }

    override fun start(fadein: Boolean, radius: Int) {
        super.start(fadein, radius)
        view.setTextColor(Color.TRANSPARENT)
    }
}
