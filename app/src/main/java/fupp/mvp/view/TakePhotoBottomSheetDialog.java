package fupp.mvp.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fupp.mvp.R;

public class TakePhotoBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private BottomSheetDialog dialog;
    private ClickMyDialogListener listener;

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public interface ClickMyDialogListener {
        void onClick(View view);
    }

    public TakePhotoBottomSheetDialog() {
        super();
    }

    @SuppressLint("ValidFragment")
    public TakePhotoBottomSheetDialog(ClickMyDialogListener listener) {
        super();
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new BottomSheetDialog(getActivity(), getTheme());
        dialog.setContentView(R.layout.dialog_bottom_take_photo);
        TextView tvSelectPhoto = dialog.findViewById(R.id.dialog_bottom_take_photo_tv_select_photo);
        TextView tvTakePhoto = dialog.findViewById(R.id.dialog_bottom_take_photo_tv_take_photo);
        Button btnCanle = dialog.findViewById(R.id.dialog_bottom_take_photo_btn_canle);
        tvSelectPhoto.setOnClickListener(this);
        tvTakePhoto.setOnClickListener(this);
        btnCanle.setOnClickListener(this);
        dialog.getWindow().findViewById(R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);
        return dialog;
    }

}
