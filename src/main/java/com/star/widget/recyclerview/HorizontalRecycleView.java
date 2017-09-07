package com.star.widget.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;


/**
 * 焦点抢占监听
 */
public class HorizontalRecycleView extends RecyclerView {

    private final  String TAG = HorizontalRecycleView.class.getSimpleName();

    private OnMoveListener mOnMoveListener;

    public HorizontalRecycleView(Context context) {
        super(context);
    }

    public HorizontalRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float mX, mY = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(false);
                mX = e.getX();
                mY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(mX - e.getX()) > 20 && Math.abs(mY - e.getY()) < 10) {
                    Log.i(TAG,"ACTION_MOVE===mX:" + mX + ";getX:" + e.getX() + ";mY:" + mY + ";getY:" + e.getY());
                    getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(mY - e.getY()) < 10 && Math.abs(mX - e.getX()) < 10) {
                    Log.i(TAG,"ACTION_UP===mX:" + mX + ";getX:" + e.getX() + ";mY:" + mY + ";getY:" + e.getY());
                    if (null != mOnMoveListener) {
                        mOnMoveListener.onClick();
                    }
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(e);
    }



    public void setOnMoveListener(OnMoveListener OnMoveListener) {
        mOnMoveListener = OnMoveListener;
    }

    public interface OnMoveListener {
        void onClick();
    }
}
