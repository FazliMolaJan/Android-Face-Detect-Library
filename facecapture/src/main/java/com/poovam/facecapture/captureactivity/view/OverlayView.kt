package com.poovam.facecapture.captureactivity.view

import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import com.poovam.facecapture.R
import com.poovam.facecapture.framework.camera.CaptureRegion


/**
 * Created by poovam-5255 on 9/29/2018.
 */
internal class OverlayView : View {

    private val mTransparentPaint = Paint()

    private val mBorderColor = Paint()

    private val mPath = Path()

    private var rect: RectF? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attr: AttributeSet) : super(context,attr)

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context,attr,defStyle)

    init{
        initPaints()
    }

    private fun initPaints() {
        mTransparentPaint.color = Color.TRANSPARENT
        mTransparentPaint.strokeWidth = 10f

        mBorderColor.color = ContextCompat.getColor(context,R.color.face_capture_sdk_theme_white)
        mBorderColor.strokeWidth = 10f
        mBorderColor.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if(rect == null){
            rect = RectF(CaptureRegion.getCaptureRegionForScreen(canvas.width ,canvas.height))
        }

        mPath.reset()

        mPath.addOval(rect, Path.Direction.CW)
        mPath.fillType = Path.FillType.INVERSE_EVEN_ODD

        canvas.drawOval(rect,mTransparentPaint)
        canvas.drawOval(rect,mBorderColor)

        canvas.drawPath(mPath, mTransparentPaint)
        canvas.clipPath(mPath)
        canvas.drawColor(ContextCompat.getColor(context,R.color.face_capture_sdk_transparent_overlay_color))
    }
}