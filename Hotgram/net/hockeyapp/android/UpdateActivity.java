package net.hockeyapp.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

public class UpdateActivity extends Activity {
    public UpdateActivity() {
        super();
    }

    public void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        if(arg4 == null) {
            arg4 = this.getIntent().getExtras();
            if(arg4 == null) {
                this.finish();
                return;
            }
            else {
                this.getFragmentManager().beginTransaction().add(16908290, Fragment.instantiate(((Context)this), arg4.getString("fragmentClass", g.class.getName()), arg4), "hockey_update_dialog").commit();
            }
        }

        this.setTitle(d.hockeyapp_update_title);
    }
}

