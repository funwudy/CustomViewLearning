package com.example.fun.mytopbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 31798 on 2016/8/24.
 */
public class TopBar extends RelativeLayout {

    private Button leftButton, rightButton;
    private TextView tvTitle;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftText;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightText;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams, rightParams, titleParams;

    private OnTopBarClickListener mListener;

    public interface OnTopBarClickListener {
        void onLeftClick();
        void onRightClick();
    }

    public void setOnTopBarClickListener(OnTopBarClickListener listener) {
        mListener = listener;
    }

    public TopBar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        leftTextColor = tArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        leftBackground = tArray.getDrawable(R.styleable.TopBar_leftBackground);
        leftText = tArray.getString(R.styleable.TopBar_leftText);

        rightTextColor = tArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        rightBackground = tArray.getDrawable(R.styleable.TopBar_rightBackground);
        rightText = tArray.getString(R.styleable.TopBar_rightText);

        titleTextSize = tArray.getDimensionPixelSize(R.styleable.TopBar_titleTextSize, 0);
        titleTextColor = tArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        title = tArray.getString(R.styleable.TopBar_title);

        tArray.recycle();

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftText);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightText);

        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xFFF59563);

        leftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);

        addView(leftButton, leftParams);

        rightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);

        addView(rightButton, rightParams);

        titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);

        addView(tvTitle, titleParams);

        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onLeftClick();
            }
        });

        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onRightClick();
            }
        });

    }
}
