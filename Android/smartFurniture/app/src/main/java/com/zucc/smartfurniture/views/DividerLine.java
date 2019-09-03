package com.zucc.smartfurniture.views;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/11/2$ 16:00$
 * Description:绘制分割线
 * <p/>
 */
public class DividerLine extends RecyclerView.ItemDecoration{
    /** 默认(不显示分割线) - -1 */
    public static final int DEFAULT    = -1;
    /** 水平方向 - 0 */
    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    /** 垂直方向 - 1 */
    public static final int VERTICAL   = LinearLayoutManager.VERTICAL;
    /** 全方向 - 9 */
    public static final int CROSS      = 9;
    private Drawable mDivider;
    // 布局方向
    private int      orientation;
    // 起始点间距
    private int marginStart = 0;
    // 结束点间距
    private int marginEnd   = 0;

    public DividerLine() {
        this(DEFAULT);
    }
    public DividerLine(int orientation) {
        this.orientation = orientation;
    }
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(orientation == VERTICAL){
            drawVertical(c,parent);
        }else if(orientation == HORIZONTAL){
            drawHorizontal(c,parent);
        }else if(orientation == CROSS){
            drawHorizontal(c,parent);
            drawVertical(c,parent);
        }
    }
    /**设置分割线起始点的间距*/
    public void setMarginStart(int marginStart) {
        this.marginStart = marginStart;
    }

    /** 设置分割线结束点的间距*/
    public void setMarginEnd(int marginEnd) {
        this.marginEnd = marginEnd;
    }
    /**画水平分割线*/
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        // 我们通过获取系统属性中的 dividerHorizontal 来添加，在系统中的AppTheme中设置
        final TypedArray ta = parent.getContext().obtainStyledAttributes(new int[]{android.R.attr.dividerHorizontal});
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for(int i=0;i<childCount -1;i++){
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top =child.getBottom()+params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left+marginStart,top,right-marginEnd,bottom);
            mDivider.draw(c);
        }

    }

    /**画垂直分割线*/
    private void drawVertical(Canvas c, RecyclerView parent) {
        // 我们通过获取系统属性中的 dividerVertical 来添加，在系统中的AppTheme中设置
        final TypedArray ta = parent.getContext().obtainStyledAttributes(new int[]{android.R.attr.dividerVertical});
        this.mDivider = ta.getDrawable(0);
        ta.recycle();

        final int top    = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            // 获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int                       left   = child.getRight() + params.rightMargin;
            final int                       right  = left + mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top + marginStart, right, bottom - marginEnd);
            mDivider.draw(c);
        }
    }
}
