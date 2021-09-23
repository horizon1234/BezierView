package com.lcw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * 自定义View(波浪)
 * Create by: chenwei.li
 * Date: 2017/4/21
 * Time: 下午11:47
 * Email: lichenwei.me@foxmail.com
 */

public class BezierWaveView extends View {

    //路径和画笔
    private Path mPath;
    private Paint mPaint;

    //记录屏幕的宽高
    private int mScreenHeight;
    private int mScreenWidth;

    private int mOffset;

    private float mProgress = 0.5f;


    public BezierWaveView(Context context) {
        super(context);
        init(context);
    }


    public BezierWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BezierWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    /**
     * 进行初始化的一些操作
     */
    private void init(Context context) {
        //获取屏幕的宽高
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;

        //路径,画笔设置
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#00BFFF"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(8);

        setViewanimator();
//        setProgress();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //贝塞尔曲线
        //当mOffset = 0
        float naviProgress = 1 - mProgress;
        float startY = mScreenHeight * naviProgress;

        mPath.moveTo(-mScreenWidth + mOffset, startY);

        mPath.quadTo(-mScreenWidth * 3 / 4 + mOffset, mScreenHeight * naviProgress - 200, -mScreenWidth / 2 + mOffset, mScreenHeight * naviProgress);
        mPath.quadTo(-mScreenWidth / 4 + mOffset, mScreenHeight * naviProgress + 200, 0 + mOffset, mScreenHeight * naviProgress);
        mPath.quadTo(mScreenWidth / 4 + mOffset, mScreenHeight * naviProgress - 200, mScreenWidth / 2 + mOffset, mScreenHeight * naviProgress);
        mPath.quadTo(mScreenWidth * 3 / 4 + mOffset, mScreenHeight * naviProgress + 200, mScreenWidth + mOffset, mScreenHeight * naviProgress);

        //空白部分填充
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * 设置动画效果
     */
    private void setViewanimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, mScreenWidth);
        valueAnimator.setDuration(1200);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 设置加载进度
     * */
    private void setProgress(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 100f);
        valueAnimator.setDuration(20000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mProgress = (float) animation.getAnimatedValue() / 100f;
                invalidate();
            }
        });
        valueAnimator.start();
    }


}
