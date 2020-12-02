package com.saleskit.cbbank.features.appointment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.saleskit.cbbank.R;

class DialogUtil {

    private static void removeBackground(Dialog dialog) {
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    public static Dialog builder(Context context, @LayoutRes int layout) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(layout);
        removeBackground(dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        return dialog;
    }

    public static void setChangeValueRadioGroup(Dialog dialog, @IdRes int idGroup, final ChangeSelectedListener changeSelectedListener) {
        @Nullable
        RadioGroup group = dialog.findViewById(idGroup);
        if (group != null) {
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    changeSelectedListener.onSelectedInRadioGroup(i);
                }
            });
            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeSelectedListener.onSelectedInRadioGroup(v.getId());
                    }
                });
            }
        }
    }

    public interface ChangeSelectedListener {
        void onSelectedInRadioGroup(int idRadio);
    }
}
