package com.gomaa.customviewpractice.Utils

import android.graphics.Canvas

interface CustomView {

    fun drawCircle(canvas: Canvas)
    fun clipCircle(canvas: Canvas)
    fun clipRect(canvas: Canvas)
    fun drawText(canvas: Canvas)
}