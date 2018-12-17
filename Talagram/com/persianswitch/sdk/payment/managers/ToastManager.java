package com.persianswitch.sdk.payment.managers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.persianswitch.sdk.R$id;
import com.persianswitch.sdk.R$layout;
import com.persianswitch.sdk.base.manager.FontManager;

public class ToastManager {
    static ToastManager a;
    private Context b;
    private Toast c;
    private TextView d;

    private ToastManager(Context arg1) {
        super();
        this.b = arg1;
    }

    private static ToastManager a(Context arg1) {
        if(ToastManager.a == null) {
            ToastManager.a = new ToastManager(arg1);
        }

        return ToastManager.a;
    }

    public static void a(Context arg0, String arg1) {
        ToastManager.a(arg0).a(arg1);
    }

    private void a(String arg4) {
        if(this.c == null) {
            this.c = new Toast(this.b);
            this.c.setDuration(0);
            View v0 = LayoutInflater.from(this.b).inflate(layout.asanpardakht_lyt_toast, null);
            this.d = v0.findViewById(id.txt_toast_message);
            FontManager.a(this.d);
            this.c.setView(v0);
        }

        this.d.setText(((CharSequence)arg4));
        this.c.show();
    }
}

