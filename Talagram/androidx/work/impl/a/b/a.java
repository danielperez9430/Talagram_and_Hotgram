package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build$VERSION;
import androidx.work.j;

public class a extends c {
    public a(Context arg1) {
        super(arg1);
    }

    private boolean a(Intent arg5) {
        boolean v1 = true;
        if(Build$VERSION.SDK_INT >= 23) {
            int v5 = arg5.getIntExtra("status", -1);
            if(v5 != 2) {
                if(v5 == 5) {
                }
                else {
                    goto label_13;
                }
            }
        }
        else if(arg5.getIntExtra("plugged", 0) == 0) {
        label_13:
            v1 = false;
        }

        return v1;
    }

    public Boolean a() {
        BroadcastReceiver v2 = null;
        Intent v0 = this.a.registerReceiver(v2, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if(v0 == null) {
            j.e("BatteryChrgTracker", "getInitialState - null intent received", new Throwable[0]);
            return ((Boolean)v2);
        }

        return Boolean.valueOf(this.a(v0));
    }

    public void a(Context arg5, Intent arg6) {
        int v5_1;
        String v5 = arg6.getAction();
        if(v5 == null) {
            return;
        }

        j.b("BatteryChrgTracker", String.format("Received %s", v5), new Throwable[0]);
        int v0 = v5.hashCode();
        if(v0 != -1886648615) {
            if(v0 != -54942926) {
                if(v0 != 948344062) {
                    if(v0 != 1019184907) {
                        goto label_43;
                    }
                    else if(v5.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        v5_1 = 2;
                    }
                    else {
                        goto label_43;
                    }
                }
                else if(v5.equals("android.os.action.CHARGING")) {
                    v5_1 = 0;
                }
                else {
                    goto label_43;
                }
            }
            else if(v5.equals("android.os.action.DISCHARGING")) {
                v5_1 = 1;
            }
            else {
                goto label_43;
            }
        }
        else if(v5.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            v5_1 = 3;
        }
        else {
        label_43:
            v5_1 = -1;
        }

        switch(v5_1) {
            case 0: 
            case 2: {
                goto label_48;
            }
            case 1: 
            case 3: {
                goto label_46;
            }
        }

        return;
    label_46:
        Boolean v5_2 = Boolean.valueOf(false);
        goto label_49;
    label_48:
        v5_2 = Boolean.valueOf(true);
    label_49:
        this.a(v5_2);
    }

    public IntentFilter b() {
        String v1;
        IntentFilter v0 = new IntentFilter();
        if(Build$VERSION.SDK_INT >= 23) {
            v0.addAction("android.os.action.CHARGING");
            v1 = "android.os.action.DISCHARGING";
        }
        else {
            v0.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            v1 = "android.intent.action.ACTION_POWER_DISCONNECTED";
        }

        v0.addAction(v1);
        return v0;
    }

    public Object c() {
        return this.a();
    }
}

