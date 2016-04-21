package com.dmin.android.recordreadbook.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.dmin.android.recordreadbook.R;

/**
 * Created by dmin on 2016/4/21.
 */
public class AddDialog extends Dialog {

    private ImageButton addImageButton;
    
    public AddDialog(Context context, int themeResId) {
        super(context, themeResId);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add,null);
        super.setContentView(mView);

        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();

        lp.width = 800;
        lp.height = 800;

        addImageButton = (ImageButton) findViewById(R.id.positiveButton);
    }

    public View getPosBtn(){
        return addImageButton;
    }
}

