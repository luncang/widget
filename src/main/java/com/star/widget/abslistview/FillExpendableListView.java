package com.star.widget.abslistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * 解决嵌套显示不全
 */
public class FillExpendableListView extends ExpandableListView{
    public FillExpendableListView(Context context) {
        super(context);
    }

    public FillExpendableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillExpendableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
