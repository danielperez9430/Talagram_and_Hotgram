package androidx.work.impl.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager$NetworkCallback;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build$VERSION;
import androidx.work.j;

public class e extends d {
    class a extends BroadcastReceiver {
        a(e arg1) {
            this.a = arg1;
            super();
        }

        public void onReceive(Context arg2, Intent arg3) {
            if(arg3 != null) {
                if(arg3.getAction() == null) {
                }
                else if(arg3.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    j.b("NetworkStateTracker", "Network broadcast received", new Throwable[0]);
                    this.a.a(this.a.b());
                }
            }
        }
    }

    class b extends ConnectivityManager$NetworkCallback {
        b(e arg1) {
            this.a = arg1;
            super();
        }

        public void onCapabilitiesChanged(Network arg4, NetworkCapabilities arg5) {
            j.b("NetworkStateTracker", String.format("Network capabilities changed: %s", arg5), new Throwable[0]);
            this.a.a(this.a.b());
        }

        public void onLost(Network arg3) {
            j.b("NetworkStateTracker", "Network connection lost", new Throwable[0]);
            this.a.a(this.a.b());
        }
    }

    private final ConnectivityManager b;
    private b c;
    private a d;

    public e(Context arg2) {
        super(arg2);
        this.b = this.a.getSystemService("connectivity");
        if(e.f()) {
            this.c = new b(this);
        }
        else {
            this.d = new a(this);
        }
    }

    public androidx.work.impl.a.b a() {
        return this.b();
    }

    androidx.work.impl.a.b b() {
        NetworkInfo v0 = this.b.getActiveNetworkInfo();
        boolean v1 = true;
        boolean v3 = v0 == null || !v0.isConnected() ? false : true;
        boolean v4 = this.g();
        boolean v5 = android.support.v4.c.a.a(this.b);
        if(v0 == null || (v0.isRoaming())) {
            v1 = false;
        }
        else {
        }

        return new androidx.work.impl.a.b(v3, v4, v5, v1);
    }

    public Object c() {
        return this.a();
    }

    public void d() {
        if(e.f()) {
            j.b("NetworkStateTracker", "Registering network callback", new Throwable[0]);
            this.b.registerDefaultNetworkCallback(this.c);
        }
        else {
            j.b("NetworkStateTracker", "Registering broadcast receiver", new Throwable[0]);
            this.a.registerReceiver(this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    public void e() {
        if(e.f()) {
            j.b("NetworkStateTracker", "Unregistering network callback", new Throwable[0]);
            this.b.unregisterNetworkCallback(this.c);
        }
        else {
            j.b("NetworkStateTracker", "Unregistering broadcast receiver", new Throwable[0]);
            this.a.unregisterReceiver(this.d);
        }
    }

    private static boolean f() {
        boolean v0 = Build$VERSION.SDK_INT >= 24 ? true : false;
        return v0;
    }

    private boolean g() {
        boolean v1 = false;
        if(Build$VERSION.SDK_INT < 23) {
            return 0;
        }

        NetworkCapabilities v0 = this.b.getNetworkCapabilities(this.b.getActiveNetwork());
        if(v0 != null && (v0.hasCapability(16))) {
            v1 = true;
        }

        return v1;
    }
}

