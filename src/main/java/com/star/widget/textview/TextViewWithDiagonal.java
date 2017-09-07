package com.star.widget.textview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.star.widget.R;

/**
 * Created by charles on 2017/8/20.
 * 带斜划线的TextView
 * 颜色可以自定义
 */

public class TextViewWithDiagonal extends TextView {

    int diagonalColor;


    public TextViewWithDiagonal(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewWithDiagonal);
        diagonalColor = typedArray.getColor(R.styleable.TextViewWithDiagonal_diagonal_color, Color.BLACK);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(diagonalColor);
        paint.setStrokeWidth(dip2px(getContext(), 1));
        canvas.drawLine(0, dip2px(getContext(), 5), getMeasuredWidth(), getMeasuredHeight() - dip2px(getContext(), 5), paint);
    }

    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }
}
