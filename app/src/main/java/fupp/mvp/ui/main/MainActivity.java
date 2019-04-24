package fupp.mvp.ui.main;

import android.util.Log;
import android.widget.TextView;

import fupp.mvp.R;
import fupp.mvp.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements IMainConstruct.IMainView {


    private TextView mTextView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mTextView = findViewById(R.id.textView);
    }

    @Override
    public void showLoading() {
        Log.e("Tag", "showLoading: ");
    }

    @Override
    public void hideLoading() {
        Log.e("Tag", "hideLoading: ");
    }

    @Override
    public void getDataSuccess(String result) {
        mTextView.setText(result);
        Log.e("Tag", "getDataSuccess: " + result);
    }

    @Override
    public void getDataFail(Throwable throwable) {
        Log.e("Tag", "getDataFail: " + throwable.getMessage());
    }
}
