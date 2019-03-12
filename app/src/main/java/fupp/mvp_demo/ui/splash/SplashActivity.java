package fupp.mvp_demo.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.OnClick;
import fupp.mvp_demo.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // TODO: 2019/3/12 0012 to main activity

    }

    public void initView() {
        ImageView splash = findViewById(R.id.splash);
        splash.setBackgroundResource(R.mipmap.splash);
        startLogin();
    }

}
