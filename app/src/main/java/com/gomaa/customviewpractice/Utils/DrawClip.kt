package com.gomaa.customviewpractice.Utils

import android.graphics.Canvas

interface drawClip {
    fun drawClippedRectangle(canvas: Canvas)
    fun drawBackAndUnclippedRectangle(canvas:Canvas)
    fun drawDifferenceClippingExample(canvas:Canvas)
    fun drawCircularClippingExample(canvas:Canvas)
    fun drawIntersectionClippingExample(canvas:Canvas)
    fun drawCombinedClippingExample(canvas:Canvas)
    fun drawRoundedRectangleClippingExample(canvas:Canvas)
    fun drawOutsideClippingExample(canvas:Canvas)
    fun drawSkewedTextExample(canvas:Canvas)
    fun  drawTranslatedTextExample(canvas:Canvas)
}