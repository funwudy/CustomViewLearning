package com.example.fun.customviewlearning.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.fun.customviewlearning.R;

import java.util.Random;

/**
 * Created by 31798 on 2016/8/23.
 */
public class RainbowProgressBar extends View {

    private int mTopMargin = 0;
    private int mBarWidth;
    private int mBarHeight;
    private int mBarInterval;
    private int mTotalWidth;
    private int mMoveStep = 10;
    private int mScreenWidth;

    private boolean mIsFirst = true;

    private Bar[] mBars;
    private int mBarNum;
    private Random mRandomColor = new Random();
    private Paint mPaint = new Paint();

    public RainbowProgressBar(Context context) {
        super(context);
    }

    public RainbowProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainbowProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.rainbowprogressbar);
        mBarWidth = typedArray.getDimensionPixelSize(R.styleable.rainbowprogressbar_bar_width, 80);
        mBarHeight = typedArray.getDimensionPixelSize(
                R.styleable.rainbowprogressbar_bar_height, 10);
        mBarInterval = typedArray.getDimensionPixelSize(
                R.styleable.rainbowprogressbar_bar_interval, 20);
        mTotalWidth = mBarWidth + mBarInterval;
        mPaint.setStrokeWidth(mBarHeight);
        //mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsFirst) {
            mIsFirst = false;
            mScreenWidth = getMeasuredWidth();
            mTotalWidth = mBarWidth + mBarInterval;
            mBarNum = mScreenWidth / mTotalWidth + 2;
            mBars = new Bar[mBarNum];
            for (int i = 0; i < mBarNum; i++) {
                mBars[i] = new Bar();
                mBars[i].color = mRandomColor.nextInt();
                mBars[i].leftPos = i * mTotalWidth;
            }
        }
        for (int i = 0; i < mBarNum; i++) {
            mBars[i].leftPos += mMoveStep;
        }
        for (int i = mBarNum - 1; i >= 0; i--) {
            if (mBars[i].leftPos > mScreenWidth) {
                mBars[i].color = mRandomColor.nextInt();
                mBars[i].leftPos = mBars[(i + 1) % mBarNum].leftPos - mTotalWidth;
            }
            mPaint.setColor(mBars[i].color);
            canvas.drawLine(mBars[i].leftPos, mTopMargin,
                    mBars[i].leftPos + mBarWidth, mTopMargin, mPaint);
        }
        invalidate();
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Bar {
        int color;
        int leftPos;
    }
}
