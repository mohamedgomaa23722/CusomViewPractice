package com.gomaa.customviewpractice.ui.CustomViews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.withStyledAttributes
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

    private var strokeColor = resources.getColor(R.color.purple_700)
    private var textColor = resources.getColor(R.color.purple_700)
    private var text: String = ""
    private var flag = resources.getDrawable(R.drawable.egypt)

    init {
        context.withStyledAttributes(attrs, R.styleable.CountryView) {
            strokeColor = getColor(
                R.styleable.CountryView_strokeColor,
                resources.getColor(R.color.purple_700)
            )
            textColor = getColor(
                R.styleable.CountryView_textColor,
                resources.getColor(R.color.purple_700)
            )
            text = getString(R.styleable.CountryView_text).toString()
            flag = getDrawable(R.styleable.CountryView_flag)
        }
    }


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
        paint.color = strokeColor
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canvas.clipOutPath(path)
        }
        drawCircle(canvas)
    }

    override fun clipRect(canvas: Canvas) {
    }


    override fun drawText(canvas: Canvas) {
        paint.color = textColor
        paint.textSize = 45f
        paint.typeface = Typeface.DEFAULT_BOLD
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(
            text,
            width.toFloat()/2f - paint.textSize,
            80f + paint.textSize - 30f,
            paint
        )

        val drawable = flag
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

    override fun setText(countryName: String) {
        text = countryName
    }

    override fun setStrokeColor(strokeColor: Int) {
        this.strokeColor = resources.getColor(strokeColor)
    }

    override fun setTextColor(textColor: Int) {
        this.textColor = resources.getColor(textColor)
    }

    override fun setFlag(flag: Int) {
        this.flag = resources.getDrawable(flag)
    }


}