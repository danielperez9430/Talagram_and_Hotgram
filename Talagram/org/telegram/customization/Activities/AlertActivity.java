package org.telegram.customization.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.TextView;

public class AlertActivity extends Activity {
    TextView a;
    TextView b;

    public AlertActivity() {
        super();
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.setContentView(2131492895);
        this.a = this.findViewById(2131296345);
        this.b = this.findViewById(2131296516);
        String v2 = this.getIntent().getStringExtra("EXTRA_UPDATE_MODEL");
        if(!TextUtils.isEmpty(((CharSequence)v2))) {
            this.b.setText(((CharSequence)v2));
        }

        this.a.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg1) {
                System.exit(0);
            }
        });
    }
}

