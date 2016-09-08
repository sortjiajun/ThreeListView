package jiajun.threelevelistview.util;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;

import jiajun.threelevelistview.gson.reserilaize.DateDeserializer;
import jiajun.threelevelistview.gson.serialize.DateSerializer;


/**
 * json工具类
 *
 * @author xiaomo
 * @version $Id: JSONUtils.java, v 0.1 2014年6月11日 下午11:14:27 xiaomo Exp $
 */
public class JSONUtil {
    private static final String SUCCESS = "success";
    private static final String VERSION_CODE = "versionCode";
    private static final String ERROR_MSG = "errorMsg";
    private static Gson gson;

    static {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new DateSerializer()).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new DateDeserializer()).setDateFormat(DateFormat.LONG);
        gson = gb.create();
    }

    /**
     * 请求是否成功
     *
     * @param jsonResult json数据
     */
    public static boolean isRequestSuccess(String jsonResult) {
        try {
            return new JSONObject(jsonResult).getBoolean(SUCCESS);
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

    }

    public static int getListDateVersion(String jsonResult) {
        try {
            return new JSONObject(jsonResult).getInt(VERSION_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 解析对象
     *
     * @param jsonResult  json数据
     * @param objectClass 要封装的对象类
     * @return 解析对象
     */
    public static <T> T getObject(String jsonResult, Class<T> objectClass) {
        try {
            return gson.fromJson(jsonResult, objectClass);
        } catch (Exception e) {
            Log.v("家俊","Gson解析异常"+e.getMessage()+"------->"+e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public static <T> ArrayList<T> getList(String jsonResult, Class<T> objectClass) {
        return gson.fromJson(jsonResult, new TypeToken<ArrayList<T>>(){}.getType());
    }

    public static String getString(String jsonResult, String key) {
        try {
            return new JSONObject(jsonResult).getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getErrorInfo(String jsonResult) {
        try {
            return new JSONObject(jsonResult).getString(ERROR_MSG);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object src) {
        return gson.toJson(src);
    }
}
