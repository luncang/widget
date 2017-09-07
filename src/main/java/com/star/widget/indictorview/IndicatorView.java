package com.star.widget.indictorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.star.widget.R;


/**
 * @author hao.zhong
 */
public class IndicatorView extends LinearLayout {

    private final Context mContext;

    private ImageView[] adIndicatorIVs;

    private int checkedResid;
    private int uncheckedResid;

    private int spacePinding = 14;

    public IndicatorView(Context context) {
        this(context, null);
    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        checkedResid = R.drawable.indicator_point_checked;
        uncheckedResid = R.drawable.indicator_point_unchecked;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IndicatorView, 0, 0);
            spacePinding = a.getDimensionPixelOffset(R.styleable.IndicatorView_space_pinding, spacePinding);
        }
    }

    public void initView(int size) {
        removeAllViews();

        //// TODO: 2016/2/19 增加判断
        if (size <= 1) {
            Log.e("dade", "隐藏vp底部圆点");
            return;
        }

        // 添加底部小圆点
        adIndicatorIVs = new ImageView[size];
        for (int i = 0; i < size; i++) {
            // 有多少张图片就new多少次
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            // 设置每个小圆点距离左边的中间Lin
            if (i > 0) {
                layoutParams.setMargins(spacePinding, 0, 0, 0);
            } else {
                layoutParams.setMargins(0, 0, 0, 0);
            }
            ImageView imageView = new ImageView(mContext);
            // 设置小圆点的宽高
            imageView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            adIndicatorIVs[i] = imageView;
            if (i == 0) {
                // 默认第一张图片
                adIndicatorIVs[i].setBackgroundResource(checkedResid);
            } else {
                // 其他图片都未选中状态
                adIndicatorIVs[i].setBackgroundResource(uncheckedResid);
            }
            addView(adIndicatorIVs[i], layoutParams);
        }
    }

    public void updatePosition(int position) {
        if (null != adIndicatorIVs) {
            for (int i = 0; i < adIndicatorIVs.length; i++) {
                if (i == position) {
                    // 默认第一张图片
                    adIndicatorIVs[i].setBackgroundResource(checkedResid);
                } else {
                    // 其他图片都未选中状态
                    adIndicatorIVs[i].setBackgroundResource(uncheckedResid);
                }
            }
        }
    }

    /**
     * 设置小圆点样式
     *
     * @param checkedResid
     * @param uncheckedResid
     */
    public void setIndicatorStyle(int checkedResid, int uncheckedResid) {
        this.checkedResid = checkedResid;
        this.uncheckedResid = uncheckedResid;
    }
}
