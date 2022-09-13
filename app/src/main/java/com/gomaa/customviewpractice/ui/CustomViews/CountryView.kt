package com.gomaa.customviewpractice.ui.CustomViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.gomaa.customviewpractice.Utils.CustomView
import com.gomaa.customviewpractice.R


/**
 * draw rectangle
 * draw circle
 * clip this rectangle
 * clip this circle
 */
class CountryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), CustomView {

    private val CircelRedius = 80f


    private var rectRight: Float = 0f
    private val rectLeft: Float = 0f
    private val rectTop: Float = CircelRedius - 50f  // 100
    private val rectBottom: Float = (CircelRedius) + 50f //140

    // 80-50 = 30   , 80+50 = 130        100
    private
    val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        strokeWidth = resources.getDimension(R.dimen.strokeWidth)
        textSize = resources.getDimension(R.dimen.textSize)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (widthMeasureSpec < 350)
            setMeasuredDimension(350, 170)
        else {
            setMeasuredDimension(widthMeasureSpec, 170)
        }
    }

    private val path = Path()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(
            "TAG",
            "onDraw: left = $rectLeft , right = $rectRight , top = $rectTop , bottom = $rectBottom"
        )

        canvas.drawColor(Color.TRANSPARENT)
        paint.color = Color.TRANSPARENT
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        drawText(canvas)
        clipCircle(canvas)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun drawCircle(canvas: Canvas) {
        paint.color = resources.getColor(R.color.purple_700)
        canvas.drawRoundRect(
            rectLeft,
            rectTop,
            width - (CircelRedius * 2) + 30f,
            rectBottom,
            10f,
            10f,
            paint
        )
        canvas.drawCircle(width.toFloat() - (CircelRedius), CircelRedius, CircelRedius, paint)
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun clipCircle(canvas: Canvas) {
        paint.color = Color.TRANSPARENT
        path.addCircle(
            width.toFloat() - (CircelRedius),
            CircelRedius,
            CircelRedius - 5f,
            Path.Direction.CCW
        )
        path.addRoundRect(
            rectLeft + 5f,
            rectTop + 5f,
            width - (CircelRedius * 2) + 30f,
            rectBottom - 5f,
            10f,
            10f,
            Path.Direction.CCW
        )

        canvas.clipOutPath(path)
        drawCircle(canvas)
    }

    override fun clipRect(canvas: Canvas) {
    }


    override fun drawText(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.textSize = 45f
        paint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText(
            "جمهورية مصر العربية",
            (CircelRedius * 4),
            80f + paint.textSize - 30f,
            paint
        )

        val drawable = resources.getDrawable(R.drawable.egypt)
        val bitmap = (drawable as BitmapDrawable).bitmap

        val rect = RectF(
            width.toFloat() - (CircelRedius) - 40,
            50f,
            width.toFloat() - (CircelRedius) + 40,
            100f
        )
        canvas.drawBitmap(
            bitmap, null,
            rect, null
        )

    }


}