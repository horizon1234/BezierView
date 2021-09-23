package com.lcw.view.shoppingcart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CubicView @JvmOverloads constructor(context: Context
                                          , attrs: AttributeSet? = null
                                          , defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private var mPath: Path? = null
    private var mPaint: Paint? = null

    init {
        mPath = Path()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = Color.BLUE
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = 8f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPath?.apply {
            moveTo(100f,100f)
            quadTo(150f,50f,200f,100f)
            quadTo(250f,150f,300f,100f)
            quadTo(350f,50f,400f,100f)
            quadTo(450f,150f,500f,100f)
        }
        canvas?.drawPath(mPath!!,mPaint!!)
    }
}