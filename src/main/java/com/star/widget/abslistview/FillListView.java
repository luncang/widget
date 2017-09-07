package com.star.widget.abslistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 解决嵌套显示不全
 */

public class FillListView extends ListView {

    public FillListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FillListView(Context context) {
        this(context, null);
    }

    public FillListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}