package android.support.v4.app;

import android.app.Activity;
import android.arch.lifecycle.d;
import android.arch.lifecycle.g;
import android.arch.lifecycle.u;
import android.arch.lifecycle.v;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;

public class h extends ag implements v, a, c {
    class android.support.v4.app.h$1 extends Handler {
        android.support.v4.app.h$1(h arg1) {
            this.a = arg1;
            super();
        }

        public void handleMessage(Message arg3) {
            if(arg3.what != 2) {
                super.handleMessage(arg3);
            }
            else {
                this.a.a_();
                this.a.b.m();
            }
        }
    }

    class android.support.v4.app.h$a extends k {
        public android.support.v4.app.h$a(h arg1) {
            this.a = arg1;
            super(arg1);
        }

        public View a(int arg2) {
            return this.a.findViewById(arg2);
        }

        public void a(Fragment arg2, Intent arg3, int arg4, Bundle arg5) {
            this.a.a(arg2, arg3, arg4, arg5);
        }

        public void a(Fragment arg11, IntentSender arg12, int arg13, Intent arg14, int arg15, int arg16, int arg17, Bundle arg18) {
            this.a.a(arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18);
        }

        public void a(Fragment arg2, String[] arg3, int arg4) {
            this.a.a(arg2, arg3, arg4);
        }

        public void a(String arg2, FileDescriptor arg3, PrintWriter arg4, String[] arg5) {
            this.a.dump(arg2, arg3, arg4, arg5);
        }

        public boolean a() {
            Window v0 = this.a.getWindow();
            boolean v0_1 = v0 == null || v0.peekDecorView() == null ? false : true;
            return v0_1;
        }

        public boolean a(Fragment arg1) {
            return this.a.isFinishing() ^ 1;
        }

        public boolean a(String arg2) {
            return android.support.v4.app.a.a(this.a, arg2);
        }

        public LayoutInflater b() {
            return this.a.getLayoutInflater().cloneInContext(this.a);
        }

        public void b(Fragment arg2) {
            this.a.a(arg2);
        }

        public h c() {
            return this.a;
        }

        public void d() {
            this.a.c();
        }

        public boolean e() {
            boolean v0 = this.a.getWindow() != null ? true : false;
            return v0;
        }

        public int f() {
            Window v0 = this.a.getWindow();
            int v0_1 = v0 == null ? 0 : v0.getAttributes().windowAnimations;
            return v0_1;
        }

        public Object g() {
            return this.c();
        }
    }

    final class b {
        Object a;
        u b;
        n c;

        b() {
            super();
        }
    }

    final Handler a;
    final j b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    int i;
    android.support.v4.f.n j;
    private u k;

    public h() {
        super();
        this.a = new android.support.v4.app.h$1(this);
        this.b = j.a(new android.support.v4.app.h$a(this));
        this.e = true;
    }

    private static boolean a(l arg4, android.arch.lifecycle.d$b arg5) {
        Iterator v4 = arg4.c().iterator();
        int v0;
        for(v0 = 0; v4.hasNext(); v0 |= h.a(v1_1, arg5)) {
            Object v1 = v4.next();
            if(v1 == null) {
                continue;
            }

            if(((Fragment)v1).getLifecycle().a().a(android.arch.lifecycle.d$b.d)) {
                ((Fragment)v1).mLifecycleRegistry.a(arg5);
                v0 = 1;
            }

            l v1_1 = ((Fragment)v1).peekChildFragmentManager();
            if(v1_1 == null) {
                continue;
            }
        }

        return ((boolean)v0);
    }

    final View a(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        return this.b.a(arg2, arg3, arg4, arg5);
    }

    public final void a(int arg2) {
        if(!this.f && arg2 != -1) {
            h.b(arg2);
        }
    }

    public void a(Fragment arg1) {
    }

    public void a(Fragment arg4, Intent arg5, int arg6, Bundle arg7) {
        this.h = true;
        int v1 = -1;
        if(arg6 == v1) {
            try {
                android.support.v4.app.a.a(((Activity)this), arg5, v1, arg7);
            }
            catch(Throwable v4) {
                goto label_20;
            }

            this.h = false;
            return;
        }

        try {
            h.b(arg6);
            android.support.v4.app.a.a(((Activity)this), arg5, (this.b(arg4) + 1 << 16) + (arg6 & 65535), arg7);
        }
        catch(Throwable v4) {
        label_20:
            this.h = false;
            throw v4;
        }

        this.h = false;
    }

    public void a(Fragment arg12, IntentSender arg13, int arg14, Intent arg15, int arg16, int arg17, int arg18, Bundle arg19) {
        h v9 = this;
        int v0 = arg14;
        v9.g = true;
        if(v0 == -1) {
            h v1 = this;
            IntentSender v2 = arg13;
            int v3 = arg14;
            Intent v4 = arg15;
            int v5 = arg16;
            int v6 = arg17;
            int v7 = arg18;
            Bundle v8 = arg19;
            try {
                android.support.v4.app.a.a(((Activity)v1), v2, v3, v4, v5, v6, v7, v8);
            }
            catch(Throwable v0_1) {
                goto label_37;
            }

            v9.g = false;
            return;
        }

        try {
            h.b(arg14);
            android.support.v4.app.a.a(this, arg13, (this.b(arg12) + 1 << 16) + (v0 & 65535), arg15, arg16, arg17, arg18, arg19);
        }
        catch(Throwable v0_1) {
        label_37:
            v9.g = false;
            throw v0_1;
        }

        v9.g = false;
    }

    void a(Fragment arg3, String[] arg4, int arg5) {
        if(arg5 == -1) {
            android.support.v4.app.a.a(((Activity)this), arg4, arg5);
            return;
        }

        h.b(arg5);
        try {
            this.f = true;
            android.support.v4.app.a.a(((Activity)this), arg4, (this.b(arg3) + 1 << 16) + (arg5 & 65535));
        }
        catch(Throwable v3) {
            this.f = false;
            throw v3;
        }

        this.f = false;
    }

    protected boolean a(View arg2, Menu arg3) {
        return super.onPreparePanel(0, arg2, arg3);
    }

    protected void a_() {
        this.b.h();
    }

    private int b(Fragment arg4) {
        int v1 = 65534;
        if(this.j.b() < v1) {
            while(this.j.f(this.i) >= 0) {
                this.i = (this.i + 1) % v1;
            }

            int v0 = this.i;
            this.j.b(v0, arg4.mWho);
            this.i = (this.i + 1) % v1;
            return v0;
        }

        throw new IllegalStateException("Too many pending Fragment activity results.");
    }

    static void b(int arg1) {
        if((arg1 & -65536) == 0) {
            return;
        }

        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }

    public Object b() {
        return null;
    }

    @Deprecated public void c() {
        this.invalidateOptionsMenu();
    }

    public l d() {
        return this.b.a();
    }

    public void dump(String arg3, FileDescriptor arg4, PrintWriter arg5, String[] arg6) {
        super.dump(arg3, arg4, arg5, arg6);
        arg5.print(arg3);
        arg5.print("Local FragmentActivity ");
        arg5.print(Integer.toHexString(System.identityHashCode(this)));
        arg5.println(" State:");
        String v0_1 = arg3 + "  ";
        arg5.print(v0_1);
        arg5.print("mCreated=");
        arg5.print(this.c);
        arg5.print(" mResumed=");
        arg5.print(this.d);
        arg5.print(" mStopped=");
        arg5.print(this.e);
        if(this.getApplication() != null) {
            t.a(((g)this)).a(v0_1, arg4, arg5, arg6);
        }

        this.b.a().a(arg3, arg4, arg5, arg6);
    }

    private void e() {
        do {
        }
        while(h.a(this.d(), android.arch.lifecycle.d$b.c));
    }

    public d getLifecycle() {
        return super.getLifecycle();
    }

    public u getViewModelStore() {
        if(this.getApplication() != null) {
            if(this.k == null) {
                Object v0 = this.getLastNonConfigurationInstance();
                if(v0 != null) {
                    this.k = ((b)v0).b;
                }

                if(this.k != null) {
                    goto label_13;
                }

                this.k = new u();
            }

        label_13:
            return this.k;
        }

        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can\'t request ViewModel before onCreate call.");
    }

    protected void onActivityResult(int arg4, int arg5, Intent arg6) {
        this.b.b();
        int v0 = arg4 >> 16;
        if(v0 != 0) {
            --v0;
            Object v1 = this.j.a(v0);
            this.j.c(v0);
            if(v1 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }

            Fragment v0_1 = this.b.a(((String)v1));
            if(v0_1 == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for who: " + (((String)v1)));
            }
            else {
                v0_1.onActivityResult(arg4 & 65535, arg5, arg6);
            }

            return;
        }

        android.support.v4.app.a$b v0_2 = android.support.v4.app.a.a();
        if(v0_2 != null && (v0_2.a(((Activity)this), arg4, arg5, arg6))) {
            return;
        }

        super.onActivityResult(arg4, arg5, arg6);
    }

    public void onBackPressed() {
        l v0 = this.b.a();
        boolean v1 = v0.d();
        if((v1) && Build$VERSION.SDK_INT <= 25) {
            return;
        }

        if((v1) || !v0.b()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration arg2) {
        super.onConfigurationChanged(arg2);
        this.b.b();
        this.b.a(arg2);
    }

    protected void onCreate(Bundle arg7) {
        Fragment v1 = null;
        this.b.a(v1);
        super.onCreate(arg7);
        Object v0 = this.getLastNonConfigurationInstance();
        if(v0 != null && ((b)v0).b != null && this.k == null) {
            this.k = ((b)v0).b;
        }

        if(arg7 != null) {
            Parcelable v3 = arg7.getParcelable("android:support:fragments");
            j v4 = this.b;
            if(v0 != null) {
                n v1_1 = ((b)v0).c;
            }

            v4.a(v3, ((n)v1));
            if(!arg7.containsKey("android:support:next_request_index")) {
                goto label_52;
            }

            this.i = arg7.getInt("android:support:next_request_index");
            int[] v0_1 = arg7.getIntArray("android:support:request_indicies");
            String[] v7 = arg7.getStringArray("android:support:request_fragment_who");
            if(v0_1 != null && v7 != null) {
                if(v0_1.length != v7.length) {
                }
                else {
                    this.j = new android.support.v4.f.n(v0_1.length);
                    int v1_2 = 0;
                    while(true) {
                        if(v1_2 < v0_1.length) {
                            this.j.b(v0_1[v1_2], v7[v1_2]);
                            ++v1_2;
                            continue;
                        }
                        else {
                            goto label_52;
                        }
                    }
                }
            }

            Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
        }

    label_52:
        if(this.j == null) {
            this.j = new android.support.v4.f.n();
            this.i = 0;
        }

        this.b.e();
    }

    public boolean onCreatePanelMenu(int arg3, Menu arg4) {
        if(arg3 == 0) {
            return super.onCreatePanelMenu(arg3, arg4) | this.b.a(arg4, this.getMenuInflater());
        }

        return super.onCreatePanelMenu(arg3, arg4);
    }

    public View onCreateView(View arg2, String arg3, Context arg4, AttributeSet arg5) {
        View v0 = this.a(arg2, arg3, arg4, arg5);
        if(v0 == null) {
            return super.onCreateView(arg2, arg3, arg4, arg5);
        }

        return v0;
    }

    public View onCreateView(String arg2, Context arg3, AttributeSet arg4) {
        View v0 = this.a(null, arg2, arg3, arg4);
        if(v0 == null) {
            return super.onCreateView(arg2, arg3, arg4);
        }

        return v0;
    }

    protected void onDestroy() {
        super.onDestroy();
        if(this.k != null && !this.isChangingConfigurations()) {
            this.k.a();
        }

        this.b.k();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.b.l();
    }

    public boolean onMenuItemSelected(int arg2, MenuItem arg3) {
        if(super.onMenuItemSelected(arg2, arg3)) {
            return 1;
        }

        if(arg2 != 0) {
            if(arg2 != 6) {
                return 0;
            }

            return this.b.b(arg3);
        }

        return this.b.a(arg3);
    }

    public void onMultiWindowModeChanged(boolean arg2) {
        this.b.a(arg2);
    }

    protected void onNewIntent(Intent arg1) {
        super.onNewIntent(arg1);
        this.b.b();
    }

    public void onPanelClosed(int arg2, Menu arg3) {
        if(arg2 != 0) {
        }
        else {
            this.b.b(arg3);
        }

        super.onPanelClosed(arg2, arg3);
    }

    protected void onPause() {
        super.onPause();
        this.d = false;
        int v1 = 2;
        if(this.a.hasMessages(v1)) {
            this.a.removeMessages(v1);
            this.a_();
        }

        this.b.i();
    }

    public void onPictureInPictureModeChanged(boolean arg2) {
        this.b.b(arg2);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.a.removeMessages(2);
        this.a_();
        this.b.m();
    }

    public boolean onPreparePanel(int arg1, View arg2, Menu arg3) {
        if(arg1 == 0 && arg3 != null) {
            return this.a(arg2, arg3) | this.b.a(arg3);
        }

        return super.onPreparePanel(arg1, arg2, arg3);
    }

    public void onRequestPermissionsResult(int arg5, String[] arg6, int[] arg7) {
        this.b.b();
        int v1 = 65535;
        int v0 = arg5 >> 16 & v1;
        if(v0 != 0) {
            --v0;
            Object v2 = this.j.a(v0);
            this.j.c(v0);
            if(v2 == null) {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            else {
                Fragment v0_1 = this.b.a(((String)v2));
                if(v0_1 == null) {
                    Log.w("FragmentActivity", "Activity result no fragment exists for who: " + (((String)v2)));
                }
                else {
                    v0_1.onRequestPermissionsResult(arg5 & v1, arg6, arg7);
                }
            }
        }
    }

    protected void onResume() {
        super.onResume();
        this.a.sendEmptyMessage(2);
        this.d = true;
        this.b.m();
    }

    public final Object onRetainNonConfigurationInstance() {
        Object v0 = this.b();
        n v1 = this.b.d();
        if(v1 == null && this.k == null && v0 == null) {
            return null;
        }

        b v2 = new b();
        v2.a = v0;
        v2.b = this.k;
        v2.c = v1;
        return v2;
    }

    protected void onSaveInstanceState(Bundle arg5) {
        super.onSaveInstanceState(arg5);
        this.e();
        Parcelable v0 = this.b.c();
        if(v0 != null) {
            arg5.putParcelable("android:support:fragments", v0);
        }

        if(this.j.b() > 0) {
            arg5.putInt("android:support:next_request_index", this.i);
            int[] v0_1 = new int[this.j.b()];
            String[] v1 = new String[this.j.b()];
            int v2;
            for(v2 = 0; v2 < this.j.b(); ++v2) {
                v0_1[v2] = this.j.d(v2);
                v1[v2] = this.j.e(v2);
            }

            arg5.putIntArray("android:support:request_indicies", v0_1);
            arg5.putStringArray("android:support:request_fragment_who", v1);
        }
    }

    protected void onStart() {
        super.onStart();
        this.e = false;
        if(!this.c) {
            this.c = true;
            this.b.f();
        }

        this.b.b();
        this.b.m();
        this.b.g();
    }

    public void onStateNotSaved() {
        this.b.b();
    }

    protected void onStop() {
        super.onStop();
        this.e = true;
        this.e();
        this.b.j();
    }

    public void startActivityForResult(Intent arg2, int arg3) {
        if(!this.h && arg3 != -1) {
            h.b(arg3);
        }

        super.startActivityForResult(arg2, arg3);
    }

    public void startActivityForResult(Intent arg2, int arg3, Bundle arg4) {
        if(!this.h && arg3 != -1) {
            h.b(arg3);
        }

        super.startActivityForResult(arg2, arg3, arg4);
    }

    public void startIntentSenderForResult(IntentSender arg2, int arg3, Intent arg4, int arg5, int arg6, int arg7) {
        if(!this.g && arg3 != -1) {
            h.b(arg3);
        }

        super.startIntentSenderForResult(arg2, arg3, arg4, arg5, arg6, arg7);
    }

    public void startIntentSenderForResult(IntentSender arg2, int arg3, Intent arg4, int arg5, int arg6, int arg7, Bundle arg8) {
        if(!this.g && arg3 != -1) {
            h.b(arg3);
        }

        super.startIntentSenderForResult(arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }
}

