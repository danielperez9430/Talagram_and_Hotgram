package com.crashlytics.android.c;

import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.content.Context;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import c.a.a.a.a.g.o;
import java.util.concurrent.CountDownLatch;

class f {
    interface a {
        void a(boolean arg1);
    }

    class b {
        private boolean a;
        private final CountDownLatch b;

        b(com.crashlytics.android.c.f$1 arg1) {
            this();
        }

        private b() {
            super();
            this.a = false;
            this.b = new CountDownLatch(1);
        }

        void a(boolean arg1) {
            this.a = arg1;
            this.b.countDown();
        }

        boolean a() {
            return this.a;
        }

        void b() {
            try {
                this.b.await();
                return;
            }
            catch(InterruptedException ) {
                return;
            }
        }
    }

    private final b a;
    private final AlertDialog$Builder b;

    private f(AlertDialog$Builder arg1, b arg2) {
        super();
        this.a = arg2;
        this.b = arg1;
    }

    private static int a(float arg0, int arg1) {
        return ((int)(arg0 * (((float)arg1))));
    }

    private static ScrollView a(Activity arg5, String arg6) {
        float v0 = arg5.getResources().getDisplayMetrics().density;
        int v1 = f.a(v0, 5);
        TextView v2 = new TextView(((Context)arg5));
        v2.setAutoLinkMask(15);
        v2.setText(((CharSequence)arg6));
        v2.setTextAppearance(((Context)arg5), 16973892);
        v2.setPadding(v1, v1, v1, v1);
        v2.setFocusable(false);
        ScrollView v6 = new ScrollView(((Context)arg5));
        v6.setPadding(f.a(v0, 14), f.a(v0, 2), f.a(v0, 10), f.a(v0, 12));
        v6.addView(((View)v2));
        return v6;
    }

    public static f a(Activity arg5, o arg6, a arg7) {
        b v0 = new b(null);
        r v1 = new r(((Context)arg5), arg6);
        AlertDialog$Builder v2 = new AlertDialog$Builder(((Context)arg5));
        v2.setView(f.a(arg5, v1.b())).setTitle(v1.a()).setCancelable(false).setNeutralButton(v1.c(), new DialogInterface$OnClickListener(v0) {
            public void onClick(DialogInterface arg2, int arg3) {
                this.a.a(true);
                arg2.dismiss();
            }
        });
        if(arg6.d) {
            v2.setNegativeButton(v1.e(), new DialogInterface$OnClickListener(v0) {
                public void onClick(DialogInterface arg2, int arg3) {
                    this.a.a(false);
                    arg2.dismiss();
                }
            });
        }

        if(arg6.f) {
            v2.setPositiveButton(v1.d(), new DialogInterface$OnClickListener(arg7, v0) {
                public void onClick(DialogInterface arg2, int arg3) {
                    this.a.a(true);
                    this.b.a(true);
                    arg2.dismiss();
                }
            });
        }

        return new f(v2, v0);
    }

    public void a() {
        this.b.show();
    }

    public void b() {
        this.a.b();
    }

    public boolean c() {
        return this.a.a();
    }
}

