package com.zucc.smartfurniture.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/25$ 16:00$
 * Description:不允许短时间内双击响应
 * <p/>
 */
public class NoDoubleClickButton extends Button{
    private long previousTime;
    public NoDoubleClickButton(Context context) {
        super(context,null);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public NoDoubleClickButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /***
     *
     * @param event
     * @return true - 消耗点击事件
     * fasle 不消耗点击事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                long currentTime = System.currentTimeMillis();
                if (currentTime - previousTime < 500) {
                    return true;
                }
                previousTime = currentTime;
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
