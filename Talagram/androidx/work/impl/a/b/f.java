package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.j;

public class f extends c {
    public f(Context arg1) {
        super(arg1);
    }

    public Boolean a() {
        BroadcastReceiver v2 = null;
        Intent v0 = this.a.registerReceiver(v2, this.b());
        if(v0 != null) {
            if(v0.getAction() == null) {
            }
            else {
                String v0_1 = v0.getAction();
                int v3 = -1;
                int v4 = v0_1.hashCode();
                if(v4 != -1181163412) {
                    if(v4 != -730838620) {
                    }
                    else if(v0_1.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                        v3 = 0;
                    }
                }
                else if(v0_1.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                    v3 = 1;
                }

                switch(v3) {
                    case 0: {
                        goto label_31;
                    }
                    case 1: {
                        goto label_29;
                    }
                }

                return ((Boolean)v2);
            label_29:
                return Boolean.valueOf(false);
            label_31:
                return Boolean.valueOf(true);
            }
        }

        return Boolean.valueOf(true);
    }

    public void a(Context arg6, Intent arg7) {
        if(arg7.getAction() == null) {
            return;
        }

        j.b("StorageNotLowTracker", String.format("Received %s", arg7.getAction()), new Throwable[0]);
        String v6 = arg7.getAction();
        int v7 = -1;
        int v0 = v6.hashCode();
        if(v0 != -1181163412) {
            if(v0 != -730838620) {
            }
            else if(v6.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                v7 = 0;
            }
        }
        else if(v6.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
            v7 = 1;
        }

        switch(v7) {
            case 0: {
                goto label_34;
            }
            case 1: {
                goto label_32;
            }
        }

        return;
    label_34:
        Boolean v6_1 = Boolean.valueOf(true);
        goto label_35;
    label_32:
        v6_1 = Boolean.valueOf(false);
    label_35:
        this.a(v6_1);
    }

    public IntentFilter b() {
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.intent.action.DEVICE_STORAGE_OK");
        v0.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return v0;
    }

    public Object c() {
        return this.a();
    }
}

