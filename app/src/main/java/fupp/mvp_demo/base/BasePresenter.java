package fupp.mvp_demo.base;

import android.content.Context;

/**
 * @Created by    fupp
 * @CreateTime 2019/4/2 17:30
 */

public abstract class BasePresenter<M, V> {

    public Context context;
    public M mModel;
    public V mView;

    void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onDestroy() {
        mModel = null;
        mView = null;
    }

    public abstract void onStart();

}
