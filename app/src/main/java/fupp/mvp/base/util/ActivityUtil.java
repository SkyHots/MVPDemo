package fupp.mvp.base.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fupp on 2017/6/23 0023.
 * 管理Activity生命周期
 */

public class ActivityUtil {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
