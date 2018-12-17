package com.google.firebase.messaging;

import android.app.PendingIntent$CanceledException;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.ad;
import com.google.firebase.iid.d;
import com.google.firebase.iid.x;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FirebaseMessagingService extends ad {
    private static final Queue zzdo;

    static {
        FirebaseMessagingService.zzdo = new ArrayDeque(10);
    }

    public FirebaseMessagingService() {
        super();
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage arg1) {
    }

    public void onMessageSent(String arg1) {
    }

    public void onNewToken(String arg1) {
    }

    public void onSendError(String arg1, Exception arg2) {
    }

    protected final Intent zzb(Intent arg1) {
        return x.a().b();
    }

    public final boolean zzc(Intent arg3) {
        if("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(arg3.getAction())) {
            Parcelable v0 = arg3.getParcelableExtra("pending_intent");
            if(v0 != null) {
                try {
                    ((PendingIntent)v0).send();
                }
                catch(PendingIntent$CanceledException ) {
                    Log.e("FirebaseMessaging", "Notification pending intent canceled");
                }
            }

            if(b.e(arg3)) {
                b.b(arg3);
            }

            return 1;
        }

        return 0;
    }

    public final void zzd(Intent arg10) {
        int v0_1;
        Task v1_1;
        String v10;
        String v0 = arg10.getAction();
        if(!"com.google.android.c2dm.intent.RECEIVE".equals(v0)) {
            if("com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(v0)) {
            }
            else {
                if("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(v0)) {
                    if(b.e(arg10)) {
                        b.c(arg10);
                        return;
                    }
                }
                else if("com.google.firebase.messaging.NEW_TOKEN".equals(v0)) {
                    this.onNewToken(arg10.getStringExtra("token"));
                    return;
                }
                else {
                    v0 = "FirebaseMessaging";
                    String v1 = "Unknown intent action: ";
                    v10 = String.valueOf(arg10.getAction());
                    v10 = v10.length() != 0 ? v1.concat(v10) : new String(v1);
                    Log.d(v0, v10);
                }

                return;
            }
        }

        v0 = arg10.getStringExtra("google.message_id");
        int v2 = 2;
        if(TextUtils.isEmpty(((CharSequence)v0))) {
            v1_1 = Tasks.forResult(null);
        }
        else {
            Bundle v1_2 = new Bundle();
            v1_2.putString("google.message_id", v0);
            v1_1 = d.a(((Context)this)).a(v2, v1_2);
        }

        int v5 = 3;
        if(TextUtils.isEmpty(((CharSequence)v0))) {
        label_53:
            v0_1 = 0;
        }
        else if(FirebaseMessagingService.zzdo.contains(v0)) {
            if(Log.isLoggable("FirebaseMessaging", v5)) {
                String v3 = "FirebaseMessaging";
                String v7 = "Received duplicate message: ";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v7.concat(v0) : new String(v7);
                Log.d(v3, v0);
            }

            v0_1 = 1;
        }
        else {
            if(FirebaseMessagingService.zzdo.size() >= 10) {
                FirebaseMessagingService.zzdo.remove();
            }

            FirebaseMessagingService.zzdo.add(v0);
            goto label_53;
        }

        if(v0_1 == 0) {
            v0 = arg10.getStringExtra("message_type");
            if(v0 == null) {
                v0 = "gcm";
            }

            int v7_1 = v0.hashCode();
            if(v7_1 != -2062414158) {
                if(v7_1 != 102161) {
                    if(v7_1 != 814694033) {
                        if(v7_1 != 814800675) {
                            goto label_117;
                        }
                        else if(v0.equals("send_event")) {
                        }
                        else {
                            goto label_117;
                        }
                    }
                    else if(v0.equals("send_error")) {
                        v2 = 3;
                    }
                    else {
                        goto label_117;
                    }
                }
                else if(v0.equals("gcm")) {
                    v2 = 0;
                }
                else {
                    goto label_117;
                }
            }
            else if(v0.equals("deleted_messages")) {
                v2 = 1;
            }
            else {
            label_117:
                v2 = -1;
            }

            switch(v2) {
                case 0: {
                    goto label_143;
                }
                case 1: {
                    goto label_141;
                }
                case 2: {
                    goto label_137;
                }
                case 3: {
                    goto label_126;
                }
            }

            v10 = "FirebaseMessaging";
            String v2_1 = "Received message with unknown type: ";
            v0 = String.valueOf(v0);
            v0 = v0.length() != 0 ? v2_1.concat(v0) : new String(v2_1);
            Log.w(v10, v0);
            goto label_168;
        label_137:
            this.onMessageSent(arg10.getStringExtra("google.message_id"));
            goto label_168;
        label_141:
            this.onDeletedMessages();
            goto label_168;
        label_126:
            v0 = arg10.getStringExtra("google.message_id");
            if(v0 == null) {
                v0 = arg10.getStringExtra("message_id");
            }

            this.onSendError(v0, new com.google.firebase.messaging.d(arg10.getStringExtra("error")));
            goto label_168;
        label_143:
            if(b.e(arg10)) {
                b.a(arg10);
            }

            Bundle v0_2 = arg10.getExtras();
            if(v0_2 == null) {
                v0_2 = new Bundle();
            }

            v0_2.remove("android.support.content.wakelockid");
            if(e.a(v0_2)) {
                if(new e(((Context)this)).c(v0_2)) {
                    goto label_168;
                }
                else if(b.e(arg10)) {
                    b.d(arg10);
                }
            }

            this.onMessageReceived(new RemoteMessage(v0_2));
        }

    label_168:
        long v2_2 = 1;
        try {
            Tasks.await(v1_1, v2_2, TimeUnit.SECONDS);
            return;
        }
        catch(TimeoutException v10_1) {
            v10 = String.valueOf(v10_1);
            StringBuilder v2_3 = new StringBuilder(String.valueOf(v10).length() + 20);
            v2_3.append("Message ack failed: ");
            v2_3.append(v10);
            Log.w("FirebaseMessaging", v2_3.toString());
            return;
        }
    }

    static void zzj(Bundle arg2) {
        Iterator v2 = arg2.keySet().iterator();
        while(v2.hasNext()) {
            Object v0 = v2.next();
            if(v0 == null) {
                continue;
            }

            if(!((String)v0).startsWith("google.c.")) {
                continue;
            }

            v2.remove();
        }
    }
}

