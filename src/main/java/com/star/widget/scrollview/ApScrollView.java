package com.star.widget.scrollview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by baofei on 2016/8/9.
 */
public class ApScrollView extends ScrollView {


    private OnScrollLinstener mOnScrollLinstener;

    public interface OnScrollLinstener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    public void setOnScrollLinstener(OnScrollLinstener mOnScrollLinstener) {
        this.mOnScrollLinstener = mOnScrollLinstener;
    }

    public ApScrollView(Context context) {
        super(context);
    }

    public ApScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ApScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ApScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollLinstener != null) {
            mOnScrollLinstener.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
