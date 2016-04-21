package com.dmin.android.recordreadbook.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dmin.android.recordreadbook.R;

/**
 * Created by dmin on 2016/4/21.
 */
public class CustomDialog extends Dialog {
    private EditText pageNumber;
    private ImageButton positiveButton;

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_custom,null);
        super.setContentView(mView);

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = 800;
        lp.height = 600;

        pageNumber = (EditText) findViewById(R.id.page_num_edit_text);
        positiveButton = (ImageButton) findViewById(R.id.custom_positiveButton);
    }

    public  View getEditText(){
        return pageNumber;
    }

    public View getPosBtn(){
        return positiveButton;
    }
}
