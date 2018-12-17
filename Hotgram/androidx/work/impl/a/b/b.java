package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.j;

public class b extends c {
    public b(Context arg1) {
        super(arg1);
    }

    public Boolean a() {
        BroadcastReceiver v2 = null;
        Intent v0 = this.a.registerReceiver(v2, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if(v0 == null) {
            j.e("BatteryNotLowTracker", "getInitialState - null intent received", new Throwable[0]);
            return ((Boolean)v2);
        }

        int v2_1 = v0.getIntExtra("plugged", 0);
        int v3 = v0.getIntExtra("status", -1);
        float v4 = (((float)v0.getIntExtra("level", -1))) / (((float)v0.getIntExtra("scale", -1)));
        boolean v0_1 = true;
        if(v2_1 == 0 && v3 != 1) {
            if(v4 > 0.15f) {
            }
            else {
                v0_1 = false;
            }
        }

        return Boolean.valueOf(v0_1);
    }

    public void a(Context arg6, Intent arg7) {
        if(arg7.getAction() == null) {
            return;
        }

        j.b("BatteryNotLowTracker", String.format("Received %s", arg7.getAction()), new Throwable[0]);
        String v6 = arg7.getAction();
        int v7 = -1;
        int v0 = v6.hashCode();
        if(v0 != -1980154005) {
            if(v0 != 490310653) {
            }
            else if(v6.equals("android.intent.action.BATTERY_LOW")) {
                v7 = 1;
            }
        }
        else if(v6.equals("android.intent.action.BATTERY_OKAY")) {
            v7 = 0;
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
        v0.addAction("android.intent.action.BATTERY_OKAY");
        v0.addAction("android.intent.action.BATTERY_LOW");
        return v0;
    }

    public Object c() {
        return this.a();
    }
}

