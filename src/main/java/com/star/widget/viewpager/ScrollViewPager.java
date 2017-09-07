package com.star.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollViewPager extends ViewPager {

	private boolean isScrollEnable = true;

	public ScrollViewPager(Context context) {
		super(context);
	}

	public ScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setScrollEnable(boolean isScrollEnable) {
		this.isScrollEnable = isScrollEnable;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

	@Override
	public void scrollTo(int x, int y) {
		if (isScrollEnable) {
			super.scrollTo(x, y);
		}
	}
}
