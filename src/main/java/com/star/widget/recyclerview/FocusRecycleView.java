package com.star.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * 焦点抢占监听
 */
public class FocusRecycleView extends RecyclerView {

    private final static String TAG = FocusRecycleView.class.getSimpleName();

    private OnMoveListener mOnMoveListener;

    public FocusRecycleView(Context context) {
        super(context);
    }

    public FocusRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float mX, mY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!isEnabled()){
            return true;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                if (null != mOnMoveListener) {
                    Log.e(TAG, "mOnMoveListener.onStart();");
                    mOnMoveListener.onStart();
                }
                mX = ev.getX();
                mY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mY - ev.getY()) > 20) {
                    if (Math.abs(mX - ev.getX()) < 10) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        if (null != mOnMoveListener) {
                            Log.e(TAG, "mOnMoveListener.onEnd();");
                            mOnMoveListener.onEnd();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (null != mOnMoveListener) {
                    Log.e(TAG, "mOnMoveListener.onEnd();");
                    mOnMoveListener.onEnd();
                }
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }


    public void setOnMoveListener(OnMoveListener OnMoveListener) {
        mOnMoveListener = OnMoveListener;
    }

    public interface OnMoveListener {
        void onStart();

        void onEnd();
    }
}
