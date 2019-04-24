package fupp.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import fupp.mvp.base.util.DisplayUtil;


public class MySpinnerAdapter extends ArrayAdapter<String> {

    private AppCompatActivity mContext;
    private String[] mStrings;

    public MySpinnerAdapter(Context context, String[] strings) {
        super(context, android.R.layout.simple_spinner_item, strings);
        mContext = (AppCompatActivity) context;
        mStrings = strings;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        //修改Spinner展开后的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setEllipsize(null);
        tv.setPadding(DisplayUtil.dip2px(mContext, 10), 0, DisplayUtil.dip2px(mContext, 10), 0);
        tv.setText(mStrings[position]);
        tv.setTextSize(15f);
        tv.setTextColor(Color.parseColor("#8F000000"));
        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // 修改Spinner选择后结果的字体颜色
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        //此处text1是Spinner默认的用来显示文字的TextView
        TextView tv = convertView.findViewById(android.R.id.text1);
        tv.setText(mStrings[position]);
        tv.setTextSize(13f);
        tv.setTextColor(Color.WHITE);
        return convertView;
    }

}