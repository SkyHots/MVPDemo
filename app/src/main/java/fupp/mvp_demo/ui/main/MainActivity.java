package fupp.mvp_demo.ui.main;

import fupp.mvp_demo.R;
import fupp.mvp_demo.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements IMainConstruct.IMainView {


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void showLoading(int requestCode) {

    }

    @Override
    public void hideLoading(int requestCode) {

    }

    @Override
    public void onError(int requestCode, Throwable throwable) {

    }
}
