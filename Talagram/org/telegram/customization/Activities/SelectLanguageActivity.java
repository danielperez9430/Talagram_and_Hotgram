package org.telegram.customization.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.a;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.TextView;
import org.telegram.messenger.LocaleController$LocaleInfo;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.LaunchActivity;

public class SelectLanguageActivity extends Activity implements View$OnClickListener {
    TextView a;
    TextView b;
    TextView c;
    LocaleInfo d;

    public SelectLanguageActivity() {
        super();
        this.d = null;
    }

    public void onClick(View arg6) {
        int v0 = 2131231527;
        int v1 = 2131099734;
        int v2 = 2131100056;
        int v3 = 2131231529;
        switch(arg6.getId()) {
            case 2131296996: {
                goto label_76;
            }
            case 2131296997: {
                goto label_43;
            }
            case 2131296999: {
                goto label_8;
            }
        }

        return;
    label_8:
        if(this.d == null) {
            this.d = new LocaleInfo();
            this.b.setBackgroundResource(v3);
            this.b.setTextColor(a.c(this.getApplicationContext(), v2));
            this.a.setTextColor(a.c(this.getApplicationContext(), v1));
            this.a.setBackgroundResource(v0);
            this.d.name = "English";
            this.d.shortName = "en";
            this.d.nameEnglish = "English";
        }

        LocaleController.getInstance().applyLanguage(this.d, true, true, UserConfig.selectedAccount);
        Intent v6 = new Intent(this.getApplicationContext(), LaunchActivity.class);
        goto label_108;
    label_43:
        this.a.setBackgroundResource(v3);
        this.a.setTextColor(a.c(this.getApplicationContext(), v2));
        this.b.setTextColor(a.c(this.getApplicationContext(), v1));
        this.b.setBackgroundResource(v0);
        this.d = new LocaleInfo();
        this.d.name = "پارسی";
        this.d.shortName = "fa";
        this.d.nameEnglish = "Parsi";
        LocaleController.getInstance().applyLanguage(this.d, true, true, UserConfig.selectedAccount);
        v6 = new Intent(this.getApplicationContext(), LaunchActivity.class);
        goto label_108;
    label_76:
        this.b.setBackgroundResource(v3);
        this.b.setTextColor(a.c(this.getApplicationContext(), v2));
        this.a.setTextColor(a.c(this.getApplicationContext(), v1));
        this.a.setBackgroundResource(v0);
        this.d = new LocaleInfo();
        this.d.name = "English";
        this.d.shortName = "en";
        this.d.nameEnglish = "English";
        LocaleController.getInstance().applyLanguage(this.d, true, true, UserConfig.selectedAccount);
        v6 = new Intent(this.getApplicationContext(), LaunchActivity.class);
    label_108:
        v6.putExtra("fromIntro", true);
        this.startActivity(v6);
        this.finish();
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        this.setContentView(2131492911);
        this.b = this.findViewById(2131296996);
        this.a = this.findViewById(2131296997);
        this.c = this.findViewById(2131296999);
        this.b.setOnClickListener(((View$OnClickListener)this));
        this.a.setOnClickListener(((View$OnClickListener)this));
        this.c.setOnClickListener(((View$OnClickListener)this));
    }
}

