package org.telegram.customization.e;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class a extends Fragment implements View$OnClickListener {
    RelativeLayout a;
    RelativeLayout b;
    ImageView c;
    ImageView d;
    boolean e;

    public a() {
        super();
    }

    public void a(boolean arg1) {
        this.e = arg1;
    }

    public boolean a() {
        return this.e;
    }

    public void onClick(View arg4) {
        boolean v4_1;
        int v4 = arg4.getId();
        int v1 = 2131230986;
        int v2 = 2131230985;
        if(v4 == 2131296847) {
            this.c.setImageResource(v2);
            this.d.setImageResource(v1);
            v4_1 = true;
        label_19:
            this.a(v4_1);
        }
        else if(v4 != 2131296853) {
        }
        else {
            this.c.setImageResource(v1);
            this.d.setImageResource(v2);
            v4_1 = false;
            goto label_19;
        }
    }

    public View onCreateView(LayoutInflater arg2, ViewGroup arg3, Bundle arg4) {
        View v2 = arg2.inflate(2131492990, arg3, false);
        this.a = v2.findViewById(2131296853);
        this.b = v2.findViewById(2131296847);
        this.c = v2.findViewById(2131296602);
        this.d = v2.findViewById(2131296603);
        this.a.setOnClickListener(((View$OnClickListener)this));
        this.b.setOnClickListener(((View$OnClickListener)this));
        return v2;
    }
}

