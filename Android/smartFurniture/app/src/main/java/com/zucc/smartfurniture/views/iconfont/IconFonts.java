package com.zucc.smartfurniture.views.iconfont;

import com.joanzapata.iconify.Icon;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/20$ 9:44$
 * <p/>
 */
public enum IconFonts implements Icon{
    icon_home('\ue635'),
    icon_control('\ue60f'),
    icon_record('\ue62b'),
    icon_mine('\ue62a');

    private char character;
    IconFonts(char character){
        this.character=character;
    }
    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
