package com.zucc.smartfurniture.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zucc.smartfurniture.R;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/24$ 15:41$
 * <p/>
 */
public class ClearEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    /**一键删除的按钮*/
    private Drawable mClearDrawable;
    /** 控件是否有焦点*/
    private boolean hasFocus;
    public ClearEditText(Context context) {
        super(context,null);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.editTextStyle);
        init(context,attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ClearEditText);
        int clearDrawableId = typedArray.getResourceId(R.styleable.ClearEditText_delete_image,R.drawable.icon_clear);
        mClearDrawable=typedArray.getResources().getDrawable(clearDrawableId);
        mClearDrawable.setBounds(0, 0, (int) getTextSize(), (int) getTextSize());//设置Drawable的宽高和TextSize的大小一致
        typedArray.recycle();
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }
    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mClearDrawable !=null && event.getAction() == MotionEvent.ACTION_UP){
            int x = (int)event.getX();
            /*判断触摸点是否在水平范围内
            getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离
           getPaddingRight() 获取删除图标右边缘到控件右边缘的距离*/
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight()))
                    && (x < (getWidth() - getPaddingRight()));
            // 获取删除图标的边界，返回一个Rect对象
            Rect rect = mClearDrawable.getBounds();
            // 获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            // 计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            // 判断触摸点是否在竖直范围内(可能会有点误差)
            // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerHeight && isInnerWidth) {
                this.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFocus=hasFocus;
        if(hasFocus){
            setClearIconVisible(getText().length()>0);
        }else{
            setClearIconVisible(false);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    /**
     * 当输入框里面内容发生变化的时候回调的方法
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(hasFocus){
            setClearIconVisible(s.length()>0);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
