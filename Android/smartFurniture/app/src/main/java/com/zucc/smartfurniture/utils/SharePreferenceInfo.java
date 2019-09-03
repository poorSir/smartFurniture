package com.zucc.smartfurniture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zucc.smartfurniture.MyApplication;
import com.zucc.smartfurniture.common.BaseParam;

import java.util.Set;

import static com.zucc.smartfurniture.utils.SharePreferenceInfo.DataType.STRING;

/**
 * Author: 沈志华
 * E-mail: shenzh@erongdu.com
 * Date: 2017/10/30$ 10:44$
 * Description:sharePreference 工具类
 * <p/>
 */
public class SharePreferenceInfo {
    private static SharePreferenceInfo instance;
    private String file_name= BaseParam.fileName;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public SharePreferenceInfo(){
        preferences = MyApplication.context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        editor=preferences.edit();
    }
    public static SharePreferenceInfo getInstance(){
        if(instance == null){
            instance = new SharePreferenceInfo();
        }
        return instance;
    }
    /**存储数据*/
    public void setValue(String key,Object obj){
        if (key != null){
            if (obj instanceof Integer){
                editor.putInt(key, (Integer)obj);
            } else if (obj instanceof Long){
                editor.putLong(key, (Long)obj);
            } else if (obj instanceof Boolean){
                editor.putBoolean(key, (Boolean)obj);
            } else if (obj instanceof Float){
                editor.putFloat(key, (Float) obj);
            } else if (obj instanceof Set){
                editor.putStringSet(key, (Set<String>) obj);
            } else if(obj instanceof String) {
                editor.putString(key, String.valueOf(obj));
            }
        }
        editor.commit();
    }
    public void putObject(Object object) {
        String key  = getKey(object.getClass());
        Gson   gson = new Gson();
        String json = gson.toJson(object);
        setValue(key,json);
    }
    public <T> T getObject(Class<T> clazz) {
        String key = getKey(clazz);
        String json = (String)getValue(key, STRING);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }
    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }
    public void remove(String key){
        editor.remove(key);
        editor.commit();
    }
    /**取出数据*/
    public Object getValue(String key,DataType type){
        switch (type) {
            case INTEGER:
                return preferences.getInt(key, -1);
            case FLOAT:
                return preferences.getFloat(key, -1f);
            case BOOLEAN:
                return preferences.getBoolean(key, false);
            case LONG:
                return preferences.getLong(key, -1L);
            case STRING:
                return preferences.getString(key, null);
            case STRING_SET:
                return preferences.getStringSet(key, null);
            default:
                return null;
        }
    }
    public enum DataType{
        INTEGER, LONG, BOOLEAN, FLOAT, STRING, STRING_SET
    }
}
