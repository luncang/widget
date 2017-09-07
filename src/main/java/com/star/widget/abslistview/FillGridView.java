package com.star.widget.abslistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决嵌套显示不全
 */
public class FillGridView extends GridView {

	public FillGridView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FillGridView(Context context) {
		this(context, null);
	}

	public FillGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
