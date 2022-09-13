package com.gomaa.customviewpractice.Utils

import android.graphics.Canvas

interface CustomView {

    fun drawCircle(canvas: Canvas)
    fun clipCircle(canvas: Canvas)
    fun clipRect(canvas: Canvas)
    fun drawText(canvas: Canvas)

    //Setter operations
    fun setText(countryName:String)
    fun setStrokeColor(strokeColor:Int)
    fun setTextColor(textColor:Int)
    fun setFlag(flag:Int)

}