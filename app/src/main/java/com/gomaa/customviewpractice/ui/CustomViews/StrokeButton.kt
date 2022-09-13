package com.gomaa.customviewpractice.ui.CustomViews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes
import com.gomaa.customviewpractice.R


class StrokeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var strokColor: Int = 0
    private var backColor: Int = 0
    private var CornerRedius: Float = 10f
    private var text: String = "Button"

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.StrokedButton) {
            strokColor = getColor(R.styleable.StrokedButton_FirstColor, 0)
            backColor = getColor(R.styleable.StrokedButton_SecondColor, 0)
            CornerRedius = getDimension(R.styleable.StrokedButton_CornerRedius, 0f).toFloat()
            text = getNonResourceString(R.styleable.StrokedButton_Buttontext)
        }
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 45.0f
        typeface = Typeface.create("", Typeface.BOLD)
        color = strokColor
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth = 12f // default: Hairline-widt
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        paint.color = strokColor
        //Draw rect
        val outerRect = RectF(
            left.toFloat() + 32f,
            (height / 2).toFloat() - top,
            width.toFloat() - 32f,
            (height / 2).toFloat() + top
        )
        Log.d("TAG", "onDraw Text: left= $left , top= $top")

        canvas.drawRect(outerRect, paint)

        paint.color = Color.BLACK
        val x = width/2f
        val y = height/2f + 10f

        Log.d("TAG", "onDraw outer: x= ${outerRect.width()} , y= ${outerRect.height()}")
        Log.d("TAG", "onDraw Text: x= $x , y= $y")
        canvas.drawText(text, x, y, paint)

    }
}

