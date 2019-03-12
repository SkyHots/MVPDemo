package fupp.mvp_demo.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.OnClick;
import fupp.mvp_demo.R;

public class SplashActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {//解决安装器安装app多次启动bug
            finish();
            return;
        }
        setContentView(R.layout.activity_flash);
        initView();
    }

    @OnClick(R.id.btn_skip_splash)
    public void onClick(View view) {
        startLogin();
    }

    private void startLogin() {
        /*if (SpUtil.prefs.getBoolean("guide", true)) {//安装之后第一次打开App
            SpUtil.prefs.edit().putBoolean("guide", false).apply();
            startActivity(new Intent(mContext, GuideActivity.class));
        } else {//不是第一次打开
            startActivity(new Intent(mContext, LoginActivity.class));
        }*/
        //        startActivity(new Intent(mContext, LoginActivity.class));
        //        finish();
    }

    public void initView() {
        ImageView splash = findViewById(R.id.splash);
        splash.setBackgroundResource(R.mipmap.splash);
        startLogin();
    }

}
