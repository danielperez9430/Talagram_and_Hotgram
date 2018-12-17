package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.impl.utils.d;
import androidx.work.j;

public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {
    public ConstraintProxyUpdateReceiver() {
        super();
    }

    public static Intent a(boolean arg2, boolean arg3, boolean arg4, boolean arg5) {
        Intent v0 = new Intent("androidx.work.impl.background.systemalarm.UpdateProxies");
        v0.putExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", arg2).putExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", arg3).putExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", arg4).putExtra("KEY_NETWORK_STATE_PROXY_ENABLED", arg5);
        return v0;
    }

    public void onReceive(Context arg10, Intent arg11) {
        String v0;
        if(arg11 != null) {
            v0 = arg11.getAction();
        }
        else {
            Object v0_1 = null;
        }

        if(!"androidx.work.impl.background.systemalarm.UpdateProxies".equals(v0)) {
            j.b("ConstrntProxyUpdtRecvr", String.format("Ignoring unknown action %s", v0), new Throwable[0]);
        }
        else {
            boolean v0_2 = arg11.getBooleanExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", false);
            boolean v1 = arg11.getBooleanExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", false);
            boolean v4 = arg11.getBooleanExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", false);
            boolean v11 = arg11.getBooleanExtra("KEY_NETWORK_STATE_PROXY_ENABLED", false);
            j.b("ConstrntProxyUpdtRecvr", String.format("Updating proxies: BatteryNotLowProxy enabled (%s), BatteryChargingProxy enabled (%s), StorageNotLowProxy (%s), NetworkStateProxy enabled (%s)", Boolean.valueOf(v0_2), Boolean.valueOf(v1), Boolean.valueOf(v4), Boolean.valueOf(v11)), new Throwable[0]);
            d.a(arg10, BatteryNotLowProxy.class, v0_2);
            d.a(arg10, BatteryChargingProxy.class, v1);
            d.a(arg10, StorageNotLowProxy.class, v4);
            d.a(arg10, NetworkStateProxy.class, v11);
        }
    }
}

