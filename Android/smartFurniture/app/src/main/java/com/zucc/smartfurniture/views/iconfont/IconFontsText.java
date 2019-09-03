package com.zucc.smartfurniture.views.iconfont;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zucc.smartfurniture.MyApplication;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/24$ 16:40$
 * Description:自定义textview使用string中的iconfont
 * <p/>
 */

public class IconFontsText extends TextView{
    public IconFontsText(Context context) {
        super(context,null);
    }

    public IconFontsText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public IconFontsText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        this.setTypeface(MyApplication.getInstance().getIconTypeFace());
    }
}
