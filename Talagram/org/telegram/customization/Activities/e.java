package org.telegram.customization.Activities;

import android.app.Activity;
import android.os.Bundle;
import org.telegram.customization.i.h;
import org.telegram.customization.i.i;
import org.telegram.customization.i.j;
import org.telegram.customization.k.a;
import org.telegram.customization.util.a.g;
import org.telegram.customization.voip.LinphoneSipWrapper;
import org.telegram.messenger.ApplicationLoader;

public class e extends Activity {
    public j api;
    private i apiMapper;
    public a bus;
    public g linphonePrefs;
    public LinphoneSipWrapper sipWrapper;

    public e() {
        super();
    }

    public void attach() {
        h v0 = this.getApiCallback();
        if(v0 != null) {
            this.apiMapper = new i(v0);
            this.api.a(this.apiMapper);
        }
    }

    public void detach() {
        if(this.apiMapper != null) {
            this.api.b(this.apiMapper);
        }
    }

    protected h getApiCallback() {
        return null;
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        ApplicationLoader.getComponent().a(this);
        this.attach();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.detach();
    }

    protected void onStop() {
        super.onStop();
    }
}

