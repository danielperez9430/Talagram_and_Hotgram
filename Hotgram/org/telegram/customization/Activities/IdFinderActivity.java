package org.telegram.customization.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView$OnEditorActionListener;
import android.widget.TextView;
import android.widget.Toast;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.LaunchActivity;

public class IdFinderActivity extends Activity implements View$OnClickListener {
    View a;
    View b;
    View c;
    EditText d;

    public IdFinderActivity() {
        super();
    }

    private void a() {
        this.a = this.findViewById(2131296392);
        this.b = this.findViewById(2131296449);
        this.c = this.findViewById(2131296604);
        this.d = this.findViewById(2131296469);
        this.a.setOnClickListener(((View$OnClickListener)this));
        this.b.setOnClickListener(((View$OnClickListener)this));
        this.c.setOnClickListener(((View$OnClickListener)this));
        this.d.setOnEditorActionListener(new TextView$OnEditorActionListener() {
            public boolean onEditorAction(TextView arg1, int arg2, KeyEvent arg3) {
                if(arg2 == 3) {
                    IdFinderActivity.a(this.a);
                    return 1;
                }

                return 0;
            }
        });
    }

    static void a(IdFinderActivity arg0) {
        arg0.b();
    }

    private void b() {
        if(TextUtils.isEmpty(this.d.getText().toString())) {
            Toast.makeText(this.getApplicationContext(), this.getString(2131626761), 0).show();
        }
        else {
            this.finish();
            MessagesController.getInstance(UserConfig.selectedAccount).openByUserName(this.d.getText().toString(), LaunchActivity.mainFragmentsStack.get(LaunchActivity.mainFragmentsStack.size() - 1), 1);
        }
    }

    public void onClick(View arg2) {
        int v2 = arg2.getId();
        if(v2 == 2131296392) {
        label_10:
            this.finish();
        }
        else if(v2 == 2131296449) {
            this.b();
        }
        else if(v2 != 2131296604) {
        }
        else {
            goto label_10;
        }
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        this.setContentView(2131492901);
        this.a();
    }
}

