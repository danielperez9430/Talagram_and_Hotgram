package android.support.v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler$Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings$Secure;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;
import java.util.Set;

public final class z {
    class a implements e {
        final String a;
        final int b;
        final String c;
        final boolean d;

        a(String arg1, int arg2, String arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = false;
        }

        public void a(s arg4) {
            if(this.d) {
                arg4.a(this.a);
            }
            else {
                arg4.a(this.a, this.b, this.c);
            }
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("CancelTask[");
            v0.append("packageName:");
            v0.append(this.a);
            v0.append(", id:");
            v0.append(this.b);
            v0.append(", tag:");
            v0.append(this.c);
            v0.append(", all:");
            v0.append(this.d);
            v0.append("]");
            return v0.toString();
        }
    }

    class b implements e {
        final String a;
        final int b;
        final String c;
        final Notification d;

        b(String arg1, int arg2, String arg3, Notification arg4) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        public void a(s arg5) {
            arg5.a(this.a, this.b, this.c, this.d);
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder("NotifyTask[");
            v0.append("packageName:");
            v0.append(this.a);
            v0.append(", id:");
            v0.append(this.b);
            v0.append(", tag:");
            v0.append(this.c);
            v0.append("]");
            return v0.toString();
        }
    }

    class c {
        final ComponentName a;
        final IBinder b;

        c(ComponentName arg1, IBinder arg2) {
            super();
            this.a = arg1;
            this.b = arg2;
        }
    }

    class d implements ServiceConnection, Handler$Callback {
        class android.support.v4.app.z$d$a {
            final ComponentName a;
            boolean b;
            s c;
            ArrayDeque d;
            int e;

            android.support.v4.app.z$d$a(ComponentName arg3) {
                super();
                this.b = false;
                this.d = new ArrayDeque();
                this.e = 0;
                this.a = arg3;
            }
        }

        private final Context a;
        private final HandlerThread b;
        private final Handler c;
        private final Map d;
        private Set e;

        d(Context arg2) {
            super();
            this.d = new HashMap();
            this.e = new HashSet();
            this.a = arg2;
            this.b = new HandlerThread("NotificationManagerCompat");
            this.b.start();
            this.c = new Handler(this.b.getLooper(), ((Handler$Callback)this));
        }

        public void a(e arg3) {
            this.c.obtainMessage(0, arg3).sendToTarget();
        }

        private void a() {
            Object v1_2;
            Set v0 = z.b(this.a);
            if(v0.equals(this.e)) {
                return;
            }

            this.e = v0;
            List v1 = this.a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet v2 = new HashSet();
            Iterator v1_1 = v1.iterator();
            while(v1_1.hasNext()) {
                Object v3 = v1_1.next();
                if(!v0.contains(((ResolveInfo)v3).serviceInfo.packageName)) {
                    continue;
                }

                ComponentName v4 = new ComponentName(((ResolveInfo)v3).serviceInfo.packageName, ((ResolveInfo)v3).serviceInfo.name);
                if(((ResolveInfo)v3).serviceInfo.permission != null) {
                    Log.w("NotifManCompat", "Permission present on component " + v4 + ", not adding listener record.");
                    continue;
                }

                ((Set)v2).add(v4);
            }

            Iterator v0_1 = ((Set)v2).iterator();
            while(true) {
                int v3_1 = 3;
                if(!v0_1.hasNext()) {
                    break;
                }

                v1_2 = v0_1.next();
                if(this.d.containsKey(v1_2)) {
                    continue;
                }

                if(Log.isLoggable("NotifManCompat", v3_1)) {
                    Log.d("NotifManCompat", "Adding listener record for " + v1_2);
                }

                this.d.put(v1_2, new android.support.v4.app.z$d$a(((ComponentName)v1_2)));
            }

            v0_1 = this.d.entrySet().iterator();
            while(v0_1.hasNext()) {
                v1_2 = v0_1.next();
                if(((Set)v2).contains(((Map$Entry)v1_2).getKey())) {
                    continue;
                }

                if(Log.isLoggable("NotifManCompat", v3_1)) {
                    Log.d("NotifManCompat", "Removing listener record for " + ((Map$Entry)v1_2).getKey());
                }

                this.b(((Map$Entry)v1_2).getValue());
                v0_1.remove();
            }
        }

        private void a(ComponentName arg2) {
            Object v2 = this.d.get(arg2);
            if(v2 != null) {
                this.b(((android.support.v4.app.z$d$a)v2));
            }
        }

        private void a(ComponentName arg2, IBinder arg3) {
            Object v2 = this.d.get(arg2);
            if(v2 != null) {
                ((android.support.v4.app.z$d$a)v2).c = android.support.v4.app.s$a.a(arg3);
                ((android.support.v4.app.z$d$a)v2).e = 0;
                this.d(((android.support.v4.app.z$d$a)v2));
            }
        }

        private boolean a(android.support.v4.app.z$d$a arg4) {
            if(arg4.b) {
                return 1;
            }

            arg4.b = this.a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(arg4.a), ((ServiceConnection)this), 33);
            if(arg4.b) {
                arg4.e = 0;
            }
            else {
                Log.w("NotifManCompat", "Unable to bind to listener " + arg4.a);
                this.a.unbindService(((ServiceConnection)this));
            }

            return arg4.b;
        }

        private void b(android.support.v4.app.z$d$a arg2) {
            if(arg2.b) {
                this.a.unbindService(((ServiceConnection)this));
                arg2.b = false;
            }

            arg2.c = null;
        }

        private void b(ComponentName arg2) {
            Object v2 = this.d.get(arg2);
            if(v2 != null) {
                this.d(((android.support.v4.app.z$d$a)v2));
            }
        }

        private void b(e arg4) {
            this.a();
            Iterator v0 = this.d.values().iterator();
            while(v0.hasNext()) {
                Object v1 = v0.next();
                ((android.support.v4.app.z$d$a)v1).d.add(arg4);
                this.d(((android.support.v4.app.z$d$a)v1));
            }
        }

        private void c(android.support.v4.app.z$d$a arg6) {
            int v2 = 3;
            if(this.c.hasMessages(v2, arg6.a)) {
                return;
            }

            ++arg6.e;
            if(arg6.e > 6) {
                Log.w("NotifManCompat", "Giving up on delivering " + arg6.d.size() + " tasks to " + arg6.a + " after " + arg6.e + " retries");
                arg6.d.clear();
                return;
            }

            int v0 = (1 << arg6.e - 1) * 1000;
            if(Log.isLoggable("NotifManCompat", v2)) {
                Log.d("NotifManCompat", "Scheduling retry for " + v0 + " ms");
            }

            this.c.sendMessageDelayed(this.c.obtainMessage(v2, arg6.a), ((long)v0));
        }

        private void d(android.support.v4.app.z$d$a arg6) {
            int v1 = 3;
            if(Log.isLoggable("NotifManCompat", v1)) {
                Log.d("NotifManCompat", "Processing component " + arg6.a + ", " + arg6.d.size() + " queued tasks");
            }

            if(arg6.d.isEmpty()) {
                return;
            }

            if((this.a(arg6)) && arg6.c != null) {
                while(true) {
                    Object v0 = arg6.d.peek();
                    if(v0 == null) {
                        break;
                    }

                    try {
                        if(Log.isLoggable("NotifManCompat", v1)) {
                            Log.d("NotifManCompat", "Sending task " + v0);
                        }

                        ((e)v0).a(arg6.c);
                        arg6.d.remove();
                        continue;
                    }
                    catch(RemoteException v0_1) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + arg6.a, ((Throwable)v0_1));
                        break;
                    }
                    catch(DeadObjectException ) {
                        if(!Log.isLoggable("NotifManCompat", v1)) {
                            break;
                        }

                        Log.d("NotifManCompat", "Remote service has died: " + arg6.a);
                        break;
                    }
                }

                if(!arg6.d.isEmpty()) {
                    this.c(arg6);
                }

                return;
            }

            this.c(arg6);
        }

        public boolean handleMessage(Message arg3) {
            switch(arg3.what) {
                case 0: {
                    goto label_16;
                }
                case 1: {
                    goto label_11;
                }
                case 2: {
                    goto label_8;
                }
                case 3: {
                    goto label_5;
                }
            }

            return 0;
        label_5:
            this.b(arg3.obj);
            return 1;
        label_8:
            this.a(arg3.obj);
            return 1;
        label_11:
            this.a(arg3.obj.a, arg3.obj.b);
            return 1;
        label_16:
            this.b(arg3.obj);
            return 1;
        }

        public void onServiceConnected(ComponentName arg4, IBinder arg5) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + arg4);
            }

            this.c.obtainMessage(1, new c(arg4, arg5)).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName arg4) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + arg4);
            }

            this.c.obtainMessage(2, arg4).sendToTarget();
        }
    }

    interface e {
        void a(s arg1);
    }

    private static final Object a;
    private static String b;
    private static Set c;
    private final Context d;
    private final NotificationManager e;
    private static final Object f;
    private static d g;

    static {
        z.a = new Object();
        z.c = new HashSet();
        z.f = new Object();
    }

    private z(Context arg2) {
        super();
        this.d = arg2;
        this.e = this.d.getSystemService("notification");
    }

    public static z a(Context arg1) {
        return new z(arg1);
    }

    private void a(e arg4) {
        Object v0 = z.f;
        __monitor_enter(v0);
        try {
            if(z.g == null) {
                z.g = new d(this.d.getApplicationContext());
            }

            z.g.a(arg4);
            __monitor_exit(v0);
            return;
        label_14:
            __monitor_exit(v0);
        }
        catch(Throwable v4) {
            goto label_14;
        }

        throw v4;
    }

    private static boolean a(Notification arg1) {
        Bundle v1 = w.a(arg1);
        boolean v1_1 = v1 == null || !v1.getBoolean("android.support.useSideChannel") ? false : true;
        return v1_1;
    }

    public void a(int arg2) {
        this.a(null, arg2);
    }

    public void a(String arg3, int arg4) {
        this.e.cancel(arg3, arg4);
        if(Build$VERSION.SDK_INT <= 19) {
            this.a(new a(this.d.getPackageName(), arg4, arg3));
        }
    }

    public void a(int arg2, Notification arg3) {
        this.a(null, arg2, arg3);
    }

    public void a(String arg3, int arg4, Notification arg5) {
        if(z.a(arg5)) {
            this.a(new b(this.d.getPackageName(), arg4, arg3, arg5));
            this.e.cancel(arg3, arg4);
        }
        else {
            this.e.notify(arg3, arg4, arg5);
        }
    }

    public boolean a() {
        if(Build$VERSION.SDK_INT >= 24) {
            return this.e.areNotificationsEnabled();
        }

        boolean v2 = true;
        if(Build$VERSION.SDK_INT >= 19) {
            Object v0 = this.d.getSystemService("appops");
            ApplicationInfo v1 = this.d.getApplicationInfo();
            String v3 = this.d.getApplicationContext().getPackageName();
            int v1_1 = v1.uid;
            try {
                Class v4 = Class.forName(AppOpsManager.class.getName());
                if(v4.getMethod("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}).invoke(v0, new Object[]{Integer.valueOf(v4.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class).intValue()), Integer.valueOf(v1_1), v3}).intValue() == 0) {
                }
                else {
                    goto label_49;
                }
            }
            catch(RuntimeException ) {
                return v2;
            }

            return v2;
        label_49:
            v2 = false;
        }

        return v2;
    }

    public static Set b(Context arg6) {
        String v6 = Settings$Secure.getString(arg6.getContentResolver(), "enabled_notification_listeners");
        Object v0 = z.a;
        __monitor_enter(v0);
        if(v6 != null) {
            try {
                if(!v6.equals(z.b)) {
                    String[] v1 = v6.split(":", -1);
                    HashSet v2 = new HashSet(v1.length);
                    int v3 = v1.length;
                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        ComponentName v5 = ComponentName.unflattenFromString(v1[v4]);
                        if(v5 != null) {
                            ((Set)v2).add(v5.getPackageName());
                        }
                    }

                    z.c = ((Set)v2);
                    z.b = v6;
                }

            label_30:
                __monitor_exit(v0);
                return z.c;
            label_33:
                __monitor_exit(v0);
                goto label_34;
            }
            catch(Throwable v6_1) {
                goto label_33;
            }
        }

        goto label_30;
    label_34:
        throw v6_1;
    }
}

