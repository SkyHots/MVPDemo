package fupp.mvp_demo;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import fupp.mvp_demo.base.util.SpUtil;


public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        SpUtil.init(mApp);
    }

    public static Context getAppContext() {
        return mApp;
    }

    public static Resources getAppResources() {
        return mApp.getResources();
    }

}
