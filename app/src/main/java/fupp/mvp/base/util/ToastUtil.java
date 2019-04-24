package fupp.mvp.base.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fupp.mvp.R;

public class ToastUtil {

    private static Toast mToast;

    /**
     * 非阻塞试显示Toast,防止出现连续点击Toast时的显示问题
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.custom_toast, null);
        TextView textToast = ((TextView) view.findViewById(R.id.text_toast));
        textToast.setText(text);
        if (mToast == null) {
            mToast = new Toast(context.getApplicationContext());
        }
        mToast.setView(view);
        mToast.setDuration(duration);
        mToast.show();
    }

    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

}