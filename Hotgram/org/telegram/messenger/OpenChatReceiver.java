package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.telegram.ui.LaunchActivity;

public class OpenChatReceiver extends Activity {
    public OpenChatReceiver() {
        super();
    }

    protected void onCreate(Bundle arg3) {
        super.onCreate(arg3);
        Intent v3 = this.getIntent();
        if(v3 == null) {
            this.finish();
        }

        if(v3.getAction() != null) {
            if(!v3.getAction().startsWith("com.tmessages.openchat")) {
            }
            else {
                Intent v0 = new Intent(((Context)this), LaunchActivity.class);
                v0.setAction(v3.getAction());
                v0.putExtras(v3);
                this.startActivity(v0);
                this.finish();
                return;
            }
        }

        this.finish();
    }
}

