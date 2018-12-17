package org.telegram.customization.fetch;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.c;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.telegram.customization.fetch.b.b;

public final class FetchService extends Service {
    class org.telegram.customization.fetch.FetchService$9 extends BroadcastReceiver {
        org.telegram.customization.fetch.FetchService$9(FetchService arg1) {
            this.a = arg1;
            super();
        }

        public void onReceive(Context arg3, Intent arg4) {
            if(arg4 != null) {
                long v3 = e.a(arg4);
                if(FetchService.g(this.a).containsKey(Long.valueOf(v3))) {
                    FetchService.g(this.a).remove(Long.valueOf(v3));
                }

                FetchService.b(this.a);
            }
        }
    }

    private Context a;
    private a b;
    private c c;
    private SharedPreferences d;
    private volatile boolean e;
    private volatile boolean f;
    private int g;
    private boolean h;
    private long i;
    private int j;
    private final ExecutorService k;
    private final List l;
    private final ConcurrentHashMap m;
    private final BroadcastReceiver n;

    public FetchService() {
        super();
        this.e = false;
        this.f = false;
        this.g = 1;
        this.h = true;
        this.i = 2000;
        this.j = 200;
        this.k = Executors.newSingleThreadExecutor();
        this.l = new ArrayList();
        this.m = new ConcurrentHashMap();
        this.n = new org.telegram.customization.fetch.FetchService$9(this);
    }

    public static IntentFilter a() {
        return new IntentFilter("com.tonyodev.fetch.event_action_update");
    }

    static a a(FetchService arg0) {
        return arg0.b;
    }

    private void a(int arg3) {
        this.j = arg3;
        this.d.edit().putInt("com.tonyodev.fetch.extra_network_id", arg3).apply();
        if(this.m.size() > 0) {
            this.c();
        }

        this.b();
    }

    private void a(int arg2, long arg3, long arg5, int arg7) {
        Cursor v2;
        if(arg2 == 480) {
            v2 = this.b.e(arg5);
        }
        else if(arg2 != 482) {
            v2 = this.b.d();
        }
        else {
            v2 = this.b.a(arg7);
        }

        this.a(arg3, f.c(v2, true, this.h));
        this.b();
    }

    private void a(long arg3, ArrayList arg5) {
        Intent v0 = new Intent("com.tonyodev.fetch.event_action_query");
        v0.putExtra("com.tonyodev.fetch.extra_query_id", arg3);
        v0.putExtra("com.tonyodev.fetch.extra_query_result", ((Serializable)arg5));
        this.c.a(v0);
    }

    private void a(long arg3) {
        if(this.m.containsKey(Long.valueOf(arg3))) {
            Object v3 = this.m.get(Long.valueOf(arg3));
            if(v3 != null) {
                ((e)v3).b();
            }
        }
    }

    private void a(long arg2, int arg4) {
        if((this.b.a(arg2, arg4)) && this.m.size() > 0) {
            this.c();
        }

        this.b();
    }

    private void a(long arg3, String arg5) {
        if(this.m.containsKey(Long.valueOf(arg3))) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$8 v0 = new BroadcastReceiver(arg3, arg5) {
                public void onReceive(Context arg4, Intent arg5) {
                    if(e.a(arg5) == this.a) {
                        FetchService.b(this.c, this.a, this.b);
                        FetchService.e(this.c).a(((BroadcastReceiver)this));
                        FetchService.f(this.c).remove(this);
                        FetchService.b(this.c, false);
                        FetchService.b(this.c);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.a(arg3);
        }
        else {
            this.b(arg3, arg5);
            this.b();
        }
    }

    public static void a(Context arg3) {
        if(arg3 != null) {
            try {
                Intent v0 = new Intent(arg3, FetchService.class);
                v0.putExtra("com.tonyodev.fetch.action_type", 315);
                arg3.startService(v0);
                return;
            }
            catch(Exception ) {
                return;
            }
        }

        throw new NullPointerException("Context cannot be null");
    }

    public static void a(Context arg2, Bundle arg3) {
        if(arg2 != null) {
            if(arg3 == null) {
                arg3 = new Bundle();
            }

            Intent v0 = new Intent(arg2, FetchService.class);
            v0.putExtras(arg3);
            arg2.startService(v0);
            return;
        }

        throw new NullPointerException("Context cannot be null");
    }

    private void a(Intent arg3) {
        if(arg3 == null) {
            return;
        }

        if(!this.k.isShutdown()) {
            this.k.execute(new Runnable(arg3) {
                public void run() {
                    FetchService.a(this.b).h();
                    long v2 = -1;
                    long v8 = this.a.getLongExtra("com.tonyodev.fetch.extra_id", v2);
                    int v4 = -1;
                    int v5 = 600;
                    switch(this.a.getIntExtra("com.tonyodev.fetch.action_type", v4)) {
                        case 310: {
                            FetchService.a(this.b, this.a.getStringExtra("com.tonyodev.fetch.extra_url"), this.a.getStringExtra("com.tonyodev.fetch.extra_file_path"), this.a.getParcelableArrayListExtra("com.tonyodev.fetch.extra_headers"), this.a.getIntExtra("com.tonyodev.fetch.extra_priority", v5));
                            break;
                        }
                        case 311: {
                            FetchService.a(this.b, v8);
                            break;
                        }
                        case 312: {
                            FetchService.c(this.b, v8);
                            break;
                        }
                        case 313: {
                            FetchService.b(this.b, v8);
                            break;
                        }
                        case 314: {
                            FetchService.a(this.b, this.a.getIntExtra("com.tonyodev.fetch.extra_network_id", 200));
                            break;
                        }
                        case 316: {
                            FetchService.a(this.b, this.a.getIntExtra("com.tonyodev.fetch.extra_query_type", 481), this.a.getLongExtra("com.tonyodev.fetch.extra_query_id", v2), v8, this.a.getIntExtra("com.tonyodev.fetch.extra_status", v4));
                            break;
                        }
                        case 317: {
                            FetchService.a(this.b, v8, this.a.getIntExtra("com.tonyodev.fetch.extra_priority", v5));
                            break;
                        }
                        case 318: {
                            FetchService.d(this.b, v8);
                            break;
                        }
                        case 319: {
                            FetchService.c(this.b);
                            break;
                        }
                        case 320: {
                            FetchService.a(this.b, this.a.getBooleanExtra("com.tonyodev.fetch.extra_logging_id", true));
                            break;
                        }
                        case 321: {
                            FetchService.b(this.b, this.a.getIntExtra("com.tonyodev.fetch.extra_concurrent_download_limit", 1));
                            break;
                        }
                        case 322: {
                            FetchService.a(this.b, v8, this.a.getStringExtra("com.tonyodev.fetch.extra_url"));
                            break;
                        }
                        case 323: {
                            FetchService.e(this.b, this.a.getLongExtra("com.tonyodev.fetch.extra_on_update_interval", 2000));
                            break;
                        }
                        case 324: {
                            FetchService.f(this.b, v8);
                            break;
                        }
                        case 325: {
                            FetchService.d(this.b);
                            break;
                        }
                        default: {
                            FetchService.b(this.b);
                            break;
                        }
                    }
                }
            });
        }
    }

    static void a(FetchService arg0, int arg1) {
        arg0.a(arg1);
    }

    static void a(FetchService arg0, boolean arg1) {
        arg0.a(arg1);
    }

    static void a(FetchService arg0, long arg1) {
        arg0.c(arg1);
    }

    static void a(FetchService arg0, long arg1, int arg3) {
        arg0.a(arg1, arg3);
    }

    static void a(FetchService arg0, String arg1, String arg2, ArrayList arg3, int arg4) {
        arg0.a(arg1, arg2, arg3, arg4);
    }

    static void a(FetchService arg0, int arg1, long arg2, long arg4, int arg6) {
        arg0.a(arg1, arg2, arg4, arg6);
    }

    static void a(FetchService arg0, long arg1, String arg3) {
        arg0.a(arg1, arg3);
    }

    private void a(String arg2, long arg3, String arg5, String arg6, int arg7, ArrayList arg8, int arg9, int arg10) {
        Intent v0 = new Intent(arg2);
        v0.putExtra("com.tonyodev.fetch.extra_id", arg3);
        v0.putExtra("com.tonyodev.fetch.extra_status", arg7);
        v0.putExtra("com.tonyodev.fetch.extra_url", arg5);
        v0.putExtra("com.tonyodev.fetch.extra_file_path", arg6);
        v0.putExtra("com.tonyodev.fetch.extra_headers", ((Serializable)arg8));
        v0.putExtra("com.tonyodev.fetch.extra_progress", 0);
        v0.putExtra("com.tonyodev.fetch.extra_file_size", 0);
        v0.putExtra("com.tonyodev.fetch.extra_error", arg10);
        v0.putExtra("com.tonyodev.fetch.extra_priority", arg9);
        this.c.a(v0);
    }

    private void a(String arg26, String arg27, ArrayList arg28, int arg29) {
        int v9_1;
        ArrayList v12;
        String v6;
        String v5;
        FetchService v1;
        int v7;
        String v2_1;
        File v2;
        long v0_2;
        long v3;
        ArrayList v8;
        FetchService v11 = this;
        String v10 = arg26;
        String v9 = arg27;
        if(v10 != null && v9 != null) {
            if(arg28 == null) {
                try {
                    v8 = new ArrayList();
                    goto label_16;
                }
                catch(Throwable v0) {
                }
                catch(b v0_1) {
                    v8 = arg28;
                    goto label_84;
                label_15:
                    v8 = arg28;
                    try {
                    label_16:
                        v3 = f.a();
                        String v18 = f.b(((List)v8), v11.h);
                        long v21 = 0;
                        v0_2 = 0;
                        v2 = f.f(arg27);
                        if(v2.exists()) {
                            goto label_24;
                        }

                        goto label_28;
                    }
                    catch(b v0_1) {
                        goto label_64;
                    }

                    try {
                    label_24:
                        v0_2 = v2.length();
                    }
                    catch(b v0_1) {
                        goto label_84;
                    }

                label_28:
                    long v19 = v0_2;
                    try {
                        if(!v11.b.a(v3, arg26, arg27, 900, v18, v19, v21, arg29, -1)) {
                            goto label_51;
                        }

                        v2_1 = "com.tonyodev.fetch.event_action_enqueued";
                        v7 = 900;
                        v1 = this;
                        v5 = arg26;
                        v6 = arg27;
                        v12 = v8;
                        v9_1 = arg29;
                        v10 = null;
                    }
                    catch(b v0_1) {
                    label_64:
                        goto label_84;
                    }

                    try {
                        v1.a(v2_1, v3, v5, v6, v7, v8, v9_1, ((int)v10));
                        goto label_96;
                    label_51:
                        v12 = v8;
                        throw new b("could not enqueue request", -117);
                    }
                    catch(b v0_1) {
                        v8 = v12;
                        goto label_84;
                    }

                label_65:
                    String v13 = v9;
                    String v14 = v10;
                    try {
                        StringBuilder v2_2 = new StringBuilder();
                        v2_2.append("Request was not properly formatted. url:");
                        v2_2.append(v14);
                        v2_2.append(", filePath:");
                        v2_2.append(v13);
                        throw new b(v2_2.toString(), -116);
                    }
                    catch(Throwable v0) {
                    label_98:
                        this.b();
                        throw v0;
                    }
                    catch(b v0_1) {
                        v8 = arg28;
                        try {
                        label_84:
                            if(v11.h) {
                                v0_1.printStackTrace();
                            }

                            this.a("com.tonyodev.fetch.event_action_enqueue_failed", -1, arg26, arg27, -900, v8, arg29, v0_1.a());
                        }
                        catch(Throwable v0) {
                            goto label_98;
                        }

                    label_96:
                        this.b();
                        return;
                    }
                }
            }
            else {
                goto label_15;
            }

            goto label_16;
        }

        goto label_65;
    }

    private void a(boolean arg3) {
        this.h = arg3;
        this.d.edit().putBoolean("com.tonyodev.fetch.extra_logging_id", arg3).apply();
        this.b.a(this.h);
        this.b();
    }

    private void b() {
        __monitor_enter(this);
        try {
            if(!this.f) {
                goto label_5;
            }
        }
        catch(Throwable v0) {
            goto label_93;
        }

        __monitor_exit(this);
        return;
        try {
        label_5:
            boolean v0_1 = f.b(this.a);
            boolean v1 = f.a(this.a);
            if((!v0_1 || this.j == 201 && !v1) && this.m.size() > 0) {
                this.e = true;
                this.c();
                this.e = false;
                goto label_90;
            }

            if((v0_1) && this.m.size() < this.g && (this.b.f())) {
                this.e = true;
                try {
                    Cursor v0_3 = this.b.e();
                    if(v0_3 == null) {
                    }
                    else if(v0_3.isClosed()) {
                    }
                    else if(v0_3.getCount() > 0) {
                        org.telegram.customization.fetch.d.c v0_4 = f.a(v0_3, true, this.h);
                        e v1_1 = new e(this.a, v0_4.a(), v0_4.c(), v0_4.d(), v0_4.i(), v0_4.g(), this.h, this.i);
                        this.b.a(v0_4.a(), 901, -1);
                        this.m.put(Long.valueOf(v1_1.c()), v1_1);
                        new Thread(((Runnable)v1_1)).start();
                    }
                    else {
                    }

                    goto label_70;
                }
                catch(Exception v0_2) {
                    try {
                        if(this.h) {
                            v0_2.printStackTrace();
                            goto label_70;
                        }
                        else {
                        label_70:
                            this.e = false;
                            if(this.m.size() >= this.g) {
                            }
                            else if(this.b.f()) {
                                this.b();
                            }
                            else {
                            }

                            goto label_90;
                        }

                    label_80:
                        if(this.e) {
                            goto label_90;
                        }

                        if(this.m.size() != 0) {
                            goto label_90;
                        }

                        if(this.b.f()) {
                            goto label_90;
                        }

                        this.f = true;
                        this.stopSelf();
                        goto label_90;
                    }
                    catch(Throwable v0) {
                    label_93:
                        __monitor_exit(this);
                        throw v0;
                    }
                }
            }

            goto label_80;
        }
        catch(Throwable v0) {
            goto label_93;
        }

    label_90:
        __monitor_exit(this);
    }

    private void b(long arg2, String arg4) {
        this.b.a(arg2, arg4);
        this.b.d(arg2);
    }

    static void b(FetchService arg0, long arg1, String arg3) {
        arg0.b(arg1, arg3);
    }

    static boolean b(FetchService arg0, boolean arg1) {
        arg0.e = arg1;
        return arg1;
    }

    static void b(FetchService arg0) {
        arg0.b();
    }

    static void b(FetchService arg0, int arg1) {
        arg0.b(arg1);
    }

    static void b(FetchService arg0, long arg1) {
        arg0.e(arg1);
    }

    private void b(int arg3) {
        this.g = 1;
        this.d.edit().putInt("com.tonyodev.fetch.extra_concurrent_download_limit", 1).apply();
        if(this.m.size() > 0) {
            this.c();
        }

        this.b();
    }

    private void b(long arg11) {
        if(this.m.containsKey(Long.valueOf(arg11))) {
            return;
        }

        if(this.b.b(arg11)) {
            org.telegram.customization.fetch.d.c v11 = f.a(this.b.e(arg11), true, this.h);
            if(v11 != null) {
                f.a(this.c, v11.a(), v11.b(), v11.e(), v11.f(), v11.g(), v11.h());
            }
        }

        this.b();
    }

    static boolean b(Context arg2) {
        return arg2.getSharedPreferences("com.tonyodev.fetch.shared_preferences", 0).getBoolean("com.tonyodev.fetch.extra_logging_id", true);
    }

    private void c() {
        Iterator v0 = this.m.keySet().iterator();
        while(v0.hasNext()) {
            Object v1 = this.m.get(v0.next());
            if(v1 == null) {
                continue;
            }

            ((e)v1).b();
        }
    }

    static void c(FetchService arg0) {
        arg0.d();
    }

    static void c(FetchService arg0, long arg1) {
        arg0.b(arg1);
    }

    private void c(long arg4) {
        if(this.m.containsKey(Long.valueOf(arg4))) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$3 v0 = new BroadcastReceiver(arg4) {
                public void onReceive(Context arg4, Intent arg5) {
                    if(e.a(arg5) == this.a) {
                        FetchService.g(this.b, this.a);
                        FetchService.e(this.b).a(((BroadcastReceiver)this));
                        FetchService.f(this.b).remove(this);
                        FetchService.b(this.b, false);
                        FetchService.b(this.b);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.a(arg4);
        }
        else {
            this.d(arg4);
            this.b();
        }
    }

    static void d(FetchService arg0) {
        arg0.f();
    }

    static void d(FetchService arg0, long arg1) {
        arg0.i(arg1);
    }

    private void d(long arg11) {
        if(this.b.a(arg11)) {
            org.telegram.customization.fetch.d.c v11 = f.a(this.b.e(arg11), true, this.h);
            if(v11 != null) {
                f.a(this.c, v11.a(), v11.b(), v11.e(), v11.f(), v11.g(), v11.h());
            }
        }
    }

    private void d() {
        if(this.m.size() > 0) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$5 v0 = new BroadcastReceiver() {
                public void onReceive(Context arg2, Intent arg3) {
                    if(arg3 != null) {
                        FetchService.h(this.a, e.a(arg3));
                    }

                    if(FetchService.g(this.a).size() == 0) {
                        FetchService.h(this.a);
                        FetchService.e(this.a).a(((BroadcastReceiver)this));
                        FetchService.f(this.a).remove(this);
                        FetchService.b(this.a, false);
                        FetchService.b(this.a);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.c();
        }
        else {
            this.e();
            this.b();
        }
    }

    static c e(FetchService arg0) {
        return arg0.c;
    }

    static void e(FetchService arg0, long arg1) {
        arg0.j(arg1);
    }

    private void e(long arg4) {
        if(this.m.containsKey(Long.valueOf(arg4))) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$4 v0 = new BroadcastReceiver(arg4) {
                public void onReceive(Context arg4, Intent arg5) {
                    if(e.a(arg5) == this.a) {
                        FetchService.h(this.b, this.a);
                        FetchService.e(this.b).a(((BroadcastReceiver)this));
                        FetchService.f(this.b).remove(this);
                        FetchService.b(this.b, false);
                        FetchService.b(this.b);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.a(arg4);
        }
        else {
            this.f(arg4);
            this.b();
        }
    }

    private void e() {
        List v0 = f.b(this.b.d(), true, this.h);
        if(v0 != null && (this.b.c())) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                f.c(((org.telegram.customization.fetch.d.c)v1).d());
                f.a(this.c, ((org.telegram.customization.fetch.d.c)v1).a(), 905, 0, 0, 0, -1);
            }
        }
    }

    static List f(FetchService arg0) {
        return arg0.l;
    }

    static void f(FetchService arg0, long arg1) {
        arg0.g(arg1);
    }

    private void f() {
        if(this.m.size() > 0) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$6 v0 = new BroadcastReceiver() {
                public void onReceive(Context arg2, Intent arg3) {
                    if(arg3 != null) {
                        FetchService.i(this.a, e.a(arg3));
                    }

                    if(FetchService.g(this.a).size() == 0) {
                        FetchService.i(this.a);
                        FetchService.e(this.a).a(((BroadcastReceiver)this));
                        FetchService.f(this.a).remove(this);
                        FetchService.b(this.a, false);
                        FetchService.b(this.a);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.c();
        }
        else {
            this.g();
            this.b();
        }
    }

    private void f(long arg12) {
        if(f.a(this.b.e(arg12), true, this.h) != null && (this.b.c(arg12))) {
            f.a(this.c, arg12, 905, 0, 0, 0, -1);
        }
    }

    static void g(FetchService arg0, long arg1) {
        arg0.d(arg1);
    }

    static ConcurrentHashMap g(FetchService arg0) {
        return arg0.m;
    }

    private void g() {
        List v0 = f.b(this.b.d(), true, this.h);
        if(v0 != null && (this.b.c())) {
            Iterator v0_1 = v0.iterator();
            while(v0_1.hasNext()) {
                Object v1 = v0_1.next();
                f.a(this.c, ((org.telegram.customization.fetch.d.c)v1).a(), 905, ((org.telegram.customization.fetch.d.c)v1).e(), ((org.telegram.customization.fetch.d.c)v1).f(), ((org.telegram.customization.fetch.d.c)v1).g(), -1);
            }
        }
    }

    private void g(long arg4) {
        if(this.m.containsKey(Long.valueOf(arg4))) {
            this.e = true;
            org.telegram.customization.fetch.FetchService$7 v0 = new BroadcastReceiver(arg4) {
                public void onReceive(Context arg4, Intent arg5) {
                    if(e.a(arg5) == this.a) {
                        FetchService.i(this.b, this.a);
                        FetchService.e(this.b).a(((BroadcastReceiver)this));
                        FetchService.f(this.b).remove(this);
                        FetchService.b(this.b, false);
                        FetchService.b(this.b);
                    }
                }
            };
            this.l.add(v0);
            this.c.a(((BroadcastReceiver)v0), e.a());
            this.a(arg4);
        }
        else {
            this.h(arg4);
            this.b();
        }
    }

    static void h(FetchService arg0, long arg1) {
        arg0.f(arg1);
    }

    static void h(FetchService arg0) {
        arg0.e();
    }

    private void h(long arg13) {
        org.telegram.customization.fetch.d.c v0 = f.a(this.b.e(arg13), true, this.h);
        if(v0 != null && (this.b.c(arg13))) {
            f.a(this.c, arg13, 905, v0.e(), v0.f(), v0.g(), -1);
        }
    }

    private int h() {
        return this.d.getInt("com.tonyodev.fetch.extra_network_id", 200);
    }

    private void i(long arg11) {
        if(this.m.containsKey(Long.valueOf(arg11))) {
            return;
        }

        if(this.b.d(arg11)) {
            org.telegram.customization.fetch.d.c v11 = f.a(this.b.e(arg11), true, this.h);
            if(v11 != null) {
                f.a(this.c, v11.a(), v11.b(), v11.e(), v11.f(), v11.g(), v11.h());
            }
        }

        this.b();
    }

    static void i(FetchService arg0, long arg1) {
        arg0.h(arg1);
    }

    static void i(FetchService arg0) {
        arg0.g();
    }

    private int i() {
        return this.d.getInt("com.tonyodev.fetch.extra_concurrent_download_limit", 1);
    }

    private void j(long arg3) {
        this.i = arg3;
        this.d.edit().putLong("com.tonyodev.fetch.extra_on_update_interval", arg3).apply();
        if(this.m.size() > 0) {
            this.c();
        }

        this.b();
    }

    private boolean j() {
        return this.d.getBoolean("com.tonyodev.fetch.extra_logging_id", true);
    }

    private long k() {
        this.i = this.d.getLong("com.tonyodev.fetch.extra_on_update_interval", 2000);
        return this.i;
    }

    public IBinder onBind(Intent arg1) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.a = this.getApplicationContext();
        this.c = c.a(this.a);
        this.d = this.getSharedPreferences("com.tonyodev.fetch.shared_preferences", 0);
        this.b = a.a(this.a);
        this.c.a(this.n, e.a());
        this.l.add(this.n);
        this.g = this.i();
        this.j = this.h();
        this.h = this.j();
        this.i = this.k();
        this.b.a(this.h);
        if(!this.k.isShutdown()) {
            this.k.execute(new Runnable() {
                public void run() {
                    FetchService.a(this.a).h();
                    FetchService.a(this.a).g();
                }
            });
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.f = true;
        if(!this.k.isShutdown()) {
            this.k.shutdown();
        }

        this.c();
        Iterator v0 = this.l.iterator();
        while(v0.hasNext()) {
            this.c.a(v0.next());
        }

        this.l.clear();
    }

    public int onStartCommand(Intent arg1, int arg2, int arg3) {
        if(arg1 == null) {
            return super.onStartCommand(arg1, arg2, arg3);
        }

        this.a(arg1);
        return 1;
    }
}

