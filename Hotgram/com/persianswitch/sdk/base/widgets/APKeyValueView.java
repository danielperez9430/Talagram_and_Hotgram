package com.persianswitch.sdk.base.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.persianswitch.sdk.R$id;
import com.persianswitch.sdk.R$layout;

public class APKeyValueView extends LinearLayout {
    private TextView a;
    private TextView b;
    private ImageView c;

    public APKeyValueView(Context arg2, CharSequence arg3, CharSequence arg4) {
        this(arg2, arg3, arg4, 0);
    }

    public APKeyValueView(Context arg1, CharSequence arg2, CharSequence arg3, int arg4) {
        super(arg1);
        this.a();
        this.setKey(arg2);
        this.setValue(arg3);
        this.setSmallIcon(arg4);
    }

    private void a() {
        LayoutInflater.from(this.getContext()).inflate(layout.asanpardakht_view_key_value, ((ViewGroup)this), true);
        this.a = this.findViewById(id.qa_view_key);
        this.b = this.findViewById(id.qa_view_value);
        this.c = this.findViewById(id.img_small_value_icon);
    }

    public void setKey(CharSequence arg2) {
        this.a.setText(arg2);
    }

    public void setSmallIcon(int arg2) {
        int v0;
        ImageView v2;
        if(arg2 > 0) {
            this.c.setImageResource(arg2);
            v2 = this.c;
            v0 = 0;
        }
        else {
            v2 = this.c;
            v0 = 8;
        }

        v2.setVisibility(v0);
    }

    public void setValue(CharSequence arg2) {
        this.b.setText(arg2);
    }
}

