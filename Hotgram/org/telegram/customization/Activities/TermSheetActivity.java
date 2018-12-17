package org.telegram.customization.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.TextView;
import com.crashlytics.android.a.b;
import com.crashlytics.android.a.m;
import org.telegram.messenger.LocaleController;
import utils.view.FarsiCheckBox;
import utils.view.ToastUtil;

public class TermSheetActivity extends Activity {
    TextView a;
    TextView b;
    TextView c;
    FarsiCheckBox d;

    public TermSheetActivity() {
        super();
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        this.setContentView(2131492917);
        this.a = this.findViewById(2131296338);
        this.b = this.findViewById(2131296353);
        this.d = this.findViewById(2131296395);
        this.b.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg4) {
                b.c().a(new m("TRACKER_VIEW_ACTION").a("RULES", "IGNORED"));
                System.exit(0);
            }
        });
        this.a.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg4) {
                if(this.a.d.a()) {
                    b.c().a(new m("TRACKER_VIEW_ACTION").a("RULES", "ACCEPTED"));
                    utils.a.b.i(true);
                    this.a.setResult(-1);
                    this.a.finish();
                }
                else {
                    ToastUtil.a(this.a.getApplicationContext(), LocaleController.getString("PleaseAgree", 2131625774)).show();
                }
            }
        });
        this.c = this.findViewById(2131296532);
        this.c.setPaintFlags(this.c.getPaintFlags() | 8);
        this.c.setOnClickListener(new View$OnClickListener() {
            public void onClick(View arg3) {
                String v3 = utils.a.b.n();
                Intent v0 = new Intent("android.intent.action.VIEW");
                v0.setData(Uri.parse(v3));
                this.a.startActivity(v0);
            }
        });
    }
}

