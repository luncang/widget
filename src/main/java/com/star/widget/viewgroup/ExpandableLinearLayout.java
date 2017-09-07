package com.star.widget.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

import com.star.widget.R;


/**
 * Created by Luncang on 2017/8/31.
 * Description:带动画的linearlayout
 */

public class ExpandableLinearLayout extends LinearLayout {

    private final int DEFAULT_DURATION = 350;
    private final boolean DEFAULT_ISEXPANDABLE = true;
    private int duration;
    private Context mContext;
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;
    int measureHeight, measureWidth;
    boolean isExpandable;
    GoneRunnable goneRunnable;
    MarginLayoutParams marginLayoutParams;

    public ExpandableLinearLayout(Context context) {
        this(context, null);
    }

    public ExpandableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpandableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLinearLayout, defStyleAttr, 0);
        duration = a.getInteger(R.styleable.ExpandableLinearLayout_duration, DEFAULT_DURATION);
        isExpandable = a.getBoolean(R.styleable.ExpandableLinearLayout_expandable, DEFAULT_ISEXPANDABLE);
        a.recycle();
        this.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    private void collapse(final View view) {
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        measureWidth = getMeasuredWidth();
        measureHeight = getMeasuredHeight();
        Log.e("mess", "collapse------------" + measureHeight + ",height:" + getHeight());
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.e("mess", "close interploated:" + interpolatedTime);
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = 0;
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = (int) ((1 - interpolatedTime) * measureHeight);
                    view.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
        isExpandable = false;
    }


    private void expand(final View view) {
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        if (measureHeight == 0) {
            measureHeight = getMeasuredHeight();
        }
        Log.e("mess", "expand------------" + measureHeight + ",height:" + getHeight());
        Animation animation = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                Log.e("mess", "open interploated:" + interpolatedTime);
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = measureHeight;
                } else {
                    view.getLayoutParams().height = (int) (measureHeight * interpolatedTime);
                    view.setVisibility(View.VISIBLE);
                }
                view.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        animation.setDuration(duration);
        view.startAnimation(animation);
        isExpandable = true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }


    public void toggle() {
        if (isExpandable) {
            collapse(this);
        } else {
            expand(this);
        }
    }

    public void setExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }

    class GoneRunnable implements Runnable {
        private final View view;

        public GoneRunnable(View view) {
            this.view = view;
        }

        @Override
        public void run() {
            view.setVisibility(GONE);
        }
    }


}
