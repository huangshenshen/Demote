package com.project.moli.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/2/20.
 */

public class RoundCenterTxt extends View {
    private Paint mPaint;
    //圆的半径
    private float mRadius;
    //总体大小
    private int mHeight;
    private int mWidth;
    //文字
    private String  mtext;

    //圆心坐标
    private float x;
    private float y;
    //圆的颜色
    private int mCircleColor;
    //字体大小
    private float mCenterTextSize;


    public RoundCenterTxt(Context context) {
        super(context);
    }

    public RoundCenterTxt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public RoundCenterTxt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.RoundCenterTxt,defStyleAttr,0);
        mCircleColor = typedArray.getColor(R.styleable.RoundCenterTxt_circleColor,Color.parseColor("#62A788"));
        mCenterTextSize = typedArray.getDimensionPixelSize(R.styleable.RoundCenterTxt_centerTextSize,PxUtils.spToPx(40,context));
        mRadius = typedArray.getDimensionPixelSize(R.styleable.RoundCenterTxt_radius,PxUtils.dpToPx(100,context));
        mtext = typedArray.getString(R.styleable.RoundCenterTxt_mtext);
        init();


    }
    //初始化画笔
    public void init(){
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取测量大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            mRadius = widthSize / 2;
            x = widthSize / 2;
            y = heightSize / 2;
            mWidth = widthSize;
            mHeight = heightSize;
        }

        if(widthMode == MeasureSpec.AT_MOST&&heightMode ==MeasureSpec.AT_MOST){
            mWidth = (int) (mRadius*2);
            mHeight = (int) (mRadius*2);
            x = mRadius;
            y = mRadius;

        }

        setMeasuredDimension(mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建一个RectF，用来限定绘制圆弧的范围
        RectF rectF = new RectF();
        mPaint.setColor(mCircleColor);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x,y,mRadius,mPaint );
        //绘制文本
        Paint textPaint = new Paint();
        String text = mtext;
        textPaint.setTextSize(mCenterTextSize);
        float textLength = textPaint.measureText(text);
        textPaint.setColor(Color.WHITE);
        canvas.drawText(text, x - textLength/2, y, textPaint);





    }
}
