package fupp.mvp_demo.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import fupp.mvp_demo.R;
import fupp.mvp_demo.base.RxSchedulers;
import fupp.mvp_demo.ui.main.MainActivity;
import rx.Observable;

public class SplashActivity extends AppCompatActivity {

    private boolean skip;

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

    public void skip(View view) {
        skip = true;
        startLogin();
    }

    private void startLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void initView() {
        Observable.timer(3500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.io_main())
                .subscribe(aLong -> {
                    if (!skip) {
                        startLogin();
                    } else {
                        Log.e("Tag", "Skipped");
                    }
                }, throwable -> Log.e("Tag", "" + throwable.getMessage()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Tag", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Tag", "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Tag", "onDestroy: ");
    }
}
