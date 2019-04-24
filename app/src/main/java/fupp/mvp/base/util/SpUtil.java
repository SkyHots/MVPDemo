package fupp.mvp.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by fupp on 2017/7/10.
 * SharedPreferences帮助类
 */

public class SpUtil {

    public static SharedPreferences prefs;

    /**
     * 初始化SharedPreferences
     *
     * @param context 上下文对象
     */
    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 保存是否是默认域名
     * @return 是
     */
    @SuppressLint("ApplySharedPref")
    public static void setIsDefaultEndpoint(boolean flag) {
        prefs.edit().putBoolean("isDefaultEndpoint", flag).commit();
    }

    /**
     * 保存的是否是默认的域名
     * @return 是
     */
    public static boolean getIsDefaultEndpoint() {
        return prefs.getBoolean("isDefaultEndpoint", true);
    }

    /**
     * 把ip地址和port端口(String ip, String port)号保存到SharedPreferences
     *
     * @param ip   ip地址
     * @param port port端口号
     */
    public static void setIp_Port(String ip, String port) {
        prefs.edit().putString("ip", ip).apply();
        prefs.edit().putString("port", port).apply();
    }


}
