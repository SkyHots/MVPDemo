package fupp.mvp_demo.base.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.util.List;

import fupp.mvp_demo.App;


/**
 * Created by fupp on 2017/7/11 0011.
 */

public class AppUtil {
    /**
     * 获取版本号
     *
     * @return 当前应用的版本名
     */
    public static String getVersionName() {
        try {
            PackageManager manager = App.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getAppContext()
                    .getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本不详";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = App.getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getAppContext().getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取设备ID
     *
     * @return 当前设备号
     */
    public static String getDeviceId() {
        TelephonyManager manager = (TelephonyManager) App.getAppContext().getSystemService
                (Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }

    /**
     * 程序是否在前台运行
     *
     * @return boolean isForeground
     */
    public static boolean isAppOnForeground() {
        try {
            ActivityManager activityManager = (ActivityManager) App.getAppContext().getSystemService(Context
                    .ACTIVITY_SERVICE);
            String packageName = App.getAppContext().getPackageName();

            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                    .getRunningAppProcesses();
            if (appProcesses == null)
                return false;

            for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                if (appProcess.processName.equals(packageName)
                        && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
}
