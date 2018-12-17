package android.support.v7.widget;

import android.content.Context;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.n;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow$OnDismissListener;

public class av {
    public interface a {
        void a(av arg1);
    }

    public interface b {
        boolean onMenuItemClick(MenuItem arg1);
    }

    final n a;
    b b;
    a c;
    private final Context d;
    private final h e;
    private final View f;

    public av(Context arg2, View arg3) {
        this(arg2, arg3, 0);
    }

    public av(Context arg7, View arg8, int arg9) {
        this(arg7, arg8, arg9, android.support.v7.a.a$a.popupMenuStyle, 0);
    }

    public av(Context arg10, View arg11, int arg12, int arg13, int arg14) {
        super();
        this.d = arg10;
        this.f = arg11;
        this.e = new h(arg10);
        this.e.a(new android.support.v7.view.menu.h$a() {
            public void a(h arg1) {
            }

            public boolean a(h arg1, MenuItem arg2) {
                if(this.a.b != null) {
                    return this.a.b.onMenuItemClick(arg2);
                }

                return 0;
            }
        });
        this.a = new n(arg10, this.e, arg11, false, arg13, arg14);
        this.a.a(arg12);
        this.a.a(new PopupWindow$OnDismissListener() {
            public void onDismiss() {
                if(this.a.c != null) {
                    this.a.c.a(this.a);
                }
            }
        });
    }

    public Menu a() {
        return this.e;
    }

    public void a(b arg1) {
        this.b = arg1;
    }

    public void b() {
        this.a.a();
    }
}

