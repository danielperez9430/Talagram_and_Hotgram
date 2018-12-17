package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ac;

public class AutoMessageReplyReceiver extends BroadcastReceiver {
    public AutoMessageReplyReceiver() {
        super();
    }

    public void onReceive(Context arg18, Intent arg19) {
        Intent v0 = arg19;
        ApplicationLoader.postInitApplication();
        Bundle v1 = ac.a(arg19);
        if(v1 == null) {
            return;
        }

        CharSequence v1_1 = v1.getCharSequence("extra_voice_reply");
        if(v1_1 != null) {
            if(v1_1.length() == 0) {
            }
            else {
                long v15 = v0.getLongExtra("dialog_id", 0);
                int v2 = v0.getIntExtra("max_id", 0);
                int v0_1 = v0.getIntExtra("currentAccount", 0);
                if(v15 != 0) {
                    if(v2 == 0) {
                    }
                    else {
                        SendMessagesHelper.getInstance(v0_1).sendMessage(v1_1.toString(), v15, null, null, true, null, null, null);
                        MessagesController.getInstance(v0_1).markDialogAsRead(v15, v2, v2, 0, false, 0, true);
                    }
                }
            }
        }
    }
}

