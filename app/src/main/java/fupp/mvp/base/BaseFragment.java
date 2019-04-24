package fupp.mvp.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fupp.mvp.R;

/**
 * Created by fupp on 2017/7/20 0020.
 *
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment {

    public BaseActivity mActivity;
    public Unbinder mBind;

    public T mPresenter;
    public E mModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(this.getLayoutId(), container, false);
        mBind = ButterKnife.bind(this, view);

        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);

        if (this instanceof BaseView) {
            mPresenter.setVM(this, mModel);
        }

        initView(view, savedInstanceState);
        initData();
        setListener();
        setAdapter();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    protected void initData() {
    }

    protected void setListener() {
    }

    protected void setAdapter() {
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_in_enter, R.anim.activity_in_exit);
    }

    public void startActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(mActivity, tarActivity);
        startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_in_enter, R.anim
                .activity_in_exit);
    }

    /**
     * startActivityForResult
     *
     * @param tarActivity 目标界面
     * @param requestCode 请求码
     */
    public void startActivity(Class<? extends Activity> tarActivity, int requestCode) {
        Intent intent = new Intent(mActivity, tarActivity);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
     *
     * @param msg Toast内容
     */
    public void showToast(String msg) {
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }
}
