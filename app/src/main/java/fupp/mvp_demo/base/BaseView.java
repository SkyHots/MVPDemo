package fupp.mvp_demo.base;

/**
 * Created by fupp on 16/4/22.
 */
public interface  BaseView {

    void showLoading(int requestCode);

    void hideLoading(int requestCode);

    void onError(int requestCode, Throwable throwable);

}
