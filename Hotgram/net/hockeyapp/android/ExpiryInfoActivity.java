package net.hockeyapp.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import net.hockeyapp.android.e.k;

public class ExpiryInfoActivity extends Activity {
    public ExpiryInfoActivity() {
        super();
    }

    public void onCreate(Bundle arg4) {
        super.onCreate(arg4);
        this.setTitle(d.hockeyapp_expiry_info_title);
        this.setContentView(c.hockeyapp_activity_expiry_info);
        this.findViewById(b.label_message).setText(this.getString(d.hockeyapp_expiry_info_text, new Object[]{k.b(((Context)this))}));
    }
}

