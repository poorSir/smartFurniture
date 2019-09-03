package com.zucc.smartfurniture.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.net.Uri;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/31$ 14:35$
 * <p/>
 */
public class ConvertUtil {
    /**图片资源id转为uri*/
    public static Uri idToUri(Activity activity,int imageId){
        Uri  uri    = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + activity.getResources().getResourcePackageName(imageId) + "/"
                + activity.getResources().getResourceTypeName(imageId) + "/"
                + activity.getResources().getResourceEntryName(imageId));
        return uri;
    }
}
