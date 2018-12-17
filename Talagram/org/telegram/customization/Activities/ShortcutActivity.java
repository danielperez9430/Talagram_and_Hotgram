package org.telegram.customization.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.telegram.messenger.ApplicationLoader;

public class ShortcutActivity extends Activity {
    public ShortcutActivity() {
        super();
    }

    protected void onCreate(Bundle arg3) {
        this.requestWindowFeature(1);
        this.setTheme(2131689853);
        this.getWindow().setBackgroundDrawableResource(2131231681);
        super.onCreate(arg3);
        Intent v3 = this.getIntent();
        v3.setClassName(ApplicationLoader.applicationContext.getPackageName(), "org.telegram.ui.LaunchActivity");
        this.startActivity(v3);
        this.finish();
    }
}

