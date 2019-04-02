package fupp.mvp_demo.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fupp.mvp_demo.R;
import fupp.mvp_demo.base.util.ActivityUtil;
import fupp.mvp_demo.base.util.NetWorkUtil;
import fupp.mvp_demo.view.SwipeBackLayout;

/**
 * Created by fupp on 2017/7/14 0014.
 *
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends
        AppCompatActivity {

    public Unbinder mBinder;
    public Context mContext;

    public P mPresenter;
    public M mModel;

    private SwipeBackLayout swipeBackLayout;
    private ImageView ivShadow;
    public Dialog mDialog;

    private InputMethodManager mInputMethodManager;

    public InputFilter emojiFilter = (source, start, end, dest, dStart, dEnd) -> {
        for (int index = start; index < end; index++) {
            int type = Character.getType(source.charAt(index));
            if (type == Character.SURROGATE) {
                return "";
            }
        }
        return null;
    };

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setContentView(this.getLayoutId());
        mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mBinder = ButterKnife.bind(this);
        ActivityUtil.addActivity(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);

        if (this instanceof BaseView)
            mPresenter.setVM(this, mModel);

        initView();
    }

    @Override
    protected void onDestroy() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;

        if (mBinder != null) {
            mBinder.unbind();
        }

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        ActivityUtil.removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.activity_flash ) {
            super.setContentView(layoutResID);

        } else {
            super.setContentView(getContainer());
            View view = LayoutInflater.from(this).inflate(layoutResID, null);
            view.setBackgroundResource(R.color.colorWhite);
            swipeBackLayout.addView(view);
        }
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);

        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.colorLLGray));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        swipeBackLayout.setOnSwipeBackListener((fa, fs) -> ivShadow.setAlpha(1 - fs));
        return container;
    }

    /**
     * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
     *
     * @param msg Toast内容
     */
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


    public void startActivity(Class<? extends Activity> tarActivity) {
        Intent intent = new Intent(this, tarActivity);
        this.startActivity(intent);
    }


    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in_enter, R.anim.activity_in_exit);
    }

    /**
     * startActivityForResult
     *
     * @param tarActivity 目标界面
     * @param requestCode 请求码
     */
    public void startActivity(Class<? extends Activity> tarActivity, int requestCode) {
        Intent intent = new Intent(this, tarActivity);
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.activiyt_out_enter, R.anim.activity_out_exit);
    }

    public void popupInputMethodWindow(View view) {
        new Handler().postDelayed(() -> mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS), 0);
    }

    public void dismissPD() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void clazzForDialog(AlertDialog dialog) {
        // 在dialog执行show之后才能来设置
        TextView tvMsg = dialog.findViewById(android.R.id.message);
        tvMsg.setTextSize(16);
        tvMsg.setTextColor(Color.parseColor("#4E4E4E"));

        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextSize(16);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorLLGray));
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextSize(16);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorTheme));

        try {
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object alertController = mAlert.get(dialog);

            Field mTitleView = alertController.getClass().getDeclaredField("mTitleView");
            mTitleView.setAccessible(true);

            TextView tvTitle = (TextView) mTitleView.get(alertController);
            if (null != tvTitle) {
                tvTitle.setTextSize(16);
                tvTitle.setTextColor(Color.parseColor("#000000"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 网络连接状态
     */
    public boolean isNetConnected() {
        return NetWorkUtil.isNetConnected(this);
    }

    public abstract int getLayoutId();

    public abstract void initView();
}
