package fupp.mvp_demo.base;

import android.content.Context;

import java.lang.ref.SoftReference;

/**
 * @Created by    fupp
 * @CreateTime 2019/4/2 17:30
 */

public abstract class BasePresenter<M, V> {

    public Context context;
    public M mModel;
    public SoftReference<V> mView;

    void setVM(V v, M m) {
        this.mView = new SoftReference<>(v);
        this.mModel = m;
        this.onStart();
    }

    public void onDestroy() {
        if (mModel != null)
            mModel = null;
        if (mView != null)
            mView.clear();
    }

    public abstract void onStart();
}
