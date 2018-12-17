package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListAdapter;

public class b extends g implements DialogInterface {
    public class a {
        private final android.support.v7.app.AlertController$a a;
        private final int b;

        public a(Context arg2) {
            this(arg2, b.a(arg2, 0));
        }

        public a(Context arg4, int arg5) {
            super();
            this.a = new android.support.v7.app.AlertController$a(new ContextThemeWrapper(arg4, b.a(arg4, arg5)));
            this.b = arg5;
        }

        public Context a() {
            return this.a.a;
        }

        public a a(ListAdapter arg2, DialogInterface$OnClickListener arg3) {
            this.a.w = arg2;
            this.a.x = arg3;
            return this;
        }

        public a a(View arg2) {
            this.a.g = arg2;
            return this;
        }

        public a a(CharSequence arg2) {
            this.a.f = arg2;
            return this;
        }

        public a a(Drawable arg2) {
            this.a.d = arg2;
            return this;
        }

        public a a(DialogInterface$OnKeyListener arg2) {
            this.a.u = arg2;
            return this;
        }

        public a a(DialogInterface$OnCancelListener arg2) {
            this.a.s = arg2;
            return this;
        }

        public a a(CharSequence arg2, DialogInterface$OnClickListener arg3) {
            this.a.i = arg2;
            this.a.k = arg3;
            return this;
        }

        public a a(boolean arg2) {
            this.a.r = arg2;
            return this;
        }

        public b b() {
            b v0 = new b(this.a.a, this.b);
            this.a.a(v0.a);
            v0.setCancelable(this.a.r);
            if(this.a.r) {
                v0.setCanceledOnTouchOutside(true);
            }

            v0.setOnCancelListener(this.a.s);
            v0.setOnDismissListener(this.a.t);
            if(this.a.u != null) {
                v0.setOnKeyListener(this.a.u);
            }

            return v0;
        }

        public a b(CharSequence arg2) {
            this.a.h = arg2;
            return this;
        }

        public b c() {
            b v0 = this.b();
            v0.show();
            return v0;
        }
    }

    final AlertController a;

    protected b(Context arg2, int arg3) {
        super(arg2, b.a(arg2, arg3));
        this.a = new AlertController(this.getContext(), ((g)this), this.getWindow());
    }

    static int a(Context arg2, int arg3) {
        if((arg3 >>> 24 & 255) >= 1) {
            return arg3;
        }

        TypedValue v3 = new TypedValue();
        arg2.getTheme().resolveAttribute(android.support.v7.a.a$a.alertDialogTheme, v3, true);
        return v3.resourceId;
    }

    protected void onCreate(Bundle arg1) {
        super.onCreate(arg1);
        this.a.a();
    }

    public boolean onKeyDown(int arg2, KeyEvent arg3) {
        if(this.a.a(arg2, arg3)) {
            return 1;
        }

        return super.onKeyDown(arg2, arg3);
    }

    public boolean onKeyUp(int arg2, KeyEvent arg3) {
        if(this.a.b(arg2, arg3)) {
            return 1;
        }

        return super.onKeyUp(arg2, arg3);
    }

    public void setTitle(CharSequence arg2) {
        super.setTitle(arg2);
        this.a.a(arg2);
    }
}

