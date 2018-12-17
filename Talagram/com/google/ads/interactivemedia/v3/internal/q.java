package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Iterator;

public class q {
    public interface a {
        void a(boolean arg1);
    }

    @SuppressLint(value={"StaticFieldLeak"}) private static q a;
    private Context b;
    private BroadcastReceiver c;
    private boolean d;
    private boolean e;
    private a f;

    static {
        q.a = new q();
    }

    private q() {
        super();
    }

    public void a(Context arg1) {
        this.b = arg1.getApplicationContext();
    }

    public static q a() {
        return q.a;
    }

    static void a(q arg0, boolean arg1) {
        arg0.a(arg1);
    }

    private void a(boolean arg2) {
        if(this.e != arg2) {
            this.e = arg2;
            if(this.d) {
                this.g();
                if(this.f != null) {
                    this.f.a(this.d());
                }
            }
        }
    }

    public void a(a arg1) {
        this.f = arg1;
    }

    public void b() {
        this.e();
        this.d = true;
        this.g();
    }

    public void c() {
        this.f();
        this.d = false;
        this.e = false;
        this.f = null;
    }

    public boolean d() {
        return this.e ^ 1;
    }

    private void e() {
        this.c = new BroadcastReceiver() {
            public void onReceive(Context arg3, Intent arg4) {
                if(arg4 == null) {
                    return;
                }

                if("android.intent.action.SCREEN_OFF".equals(arg4.getAction())) {
                    q.a(this.a, true);
                }
                else {
                    if(!"android.intent.action.USER_PRESENT".equals(arg4.getAction())) {
                        if("android.intent.action.SCREEN_ON".equals(arg4.getAction())) {
                            Object v3 = arg3.getSystemService("keyguard");
                            if(v3 == null) {
                                return;
                            }
                            else if(!((KeyguardManager)v3).inKeyguardRestrictedInputMode()) {
                            }
                            else {
                                return;
                            }
                        }
                        else {
                            return;
                        }
                    }

                    q.a(this.a, false);
                }
            }
        };
        IntentFilter v0 = new IntentFilter();
        v0.addAction("android.intent.action.SCREEN_OFF");
        v0.addAction("android.intent.action.SCREEN_ON");
        v0.addAction("android.intent.action.USER_PRESENT");
        this.b.registerReceiver(this.c, v0);
    }

    private void f() {
        if(this.b != null && this.c != null) {
            this.b.unregisterReceiver(this.c);
            this.c = null;
        }
    }

    private void g() {
        int v0 = this.e ^ 1;
        Iterator v1 = p.a().b().iterator();
        while(v1.hasNext()) {
            v1.next().e().a(((boolean)v0));
        }
    }
}

