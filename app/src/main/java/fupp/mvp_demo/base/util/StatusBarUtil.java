package fupp.mvp_demo.base.util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by fupp on 2017/6/27 0027.
 */

public class StatusBarUtil {

    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //顶部状态栏背景色
            window.setStatusBarColor(activity.getResources().getColor(colorResId));
            //底部导航栏背景色
            window.setNavigationBarColor(activity.getResources().getColor(colorResId));
        }
    }

    public static void setWindowStatusBarColor(Dialog dialog, int colorResId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = dialog.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(dialog.getContext().getResources().getColor(colorResId));

            //底部导航栏
            //                window.setNavigationBarColor(activity.getResources().getColor(colorResId));
        }
    }
}
