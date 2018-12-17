package android.support.v7.view.menu;

import android.content.DialogInterface$OnClickListener;
import android.content.DialogInterface$OnDismissListener;
import android.content.DialogInterface$OnKeyListener;
import android.content.DialogInterface;
import android.os.IBinder;
import android.support.v7.a.a$g;
import android.support.v7.app.b;
import android.view.KeyEvent$DispatcherState;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager$LayoutParams;

class i implements DialogInterface$OnClickListener, DialogInterface$OnDismissListener, DialogInterface$OnKeyListener, a {
    f a;
    private h b;
    private b c;
    private a d;

    public i(h arg1) {
        super();
        this.b = arg1;
    }

    public void a(IBinder arg6) {
        h v0 = this.b;
        android.support.v7.app.b$a v1 = new android.support.v7.app.b$a(v0.f());
        this.a = new f(v1.a(), g.abc_list_menu_item_layout);
        this.a.a(((a)this));
        this.b.a(this.a);
        v1.a(this.a.a(), ((DialogInterface$OnClickListener)this));
        View v2 = v0.p();
        if(v2 != null) {
            v1.a(v2);
        }
        else {
            v1.a(v0.o()).a(v0.n());
        }

        v1.a(((DialogInterface$OnKeyListener)this));
        this.c = v1.b();
        this.c.setOnDismissListener(((DialogInterface$OnDismissListener)this));
        WindowManager$LayoutParams v0_1 = this.c.getWindow().getAttributes();
        v0_1.type = 1003;
        if(arg6 != null) {
            v0_1.token = arg6;
        }

        v0_1.flags |= 131072;
        this.c.show();
    }

    public void a() {
        if(this.c != null) {
            this.c.dismiss();
        }
    }

    public void a(h arg2, boolean arg3) {
        if((arg3) || arg2 == this.b) {
            this.a();
        }

        if(this.d != null) {
            this.d.a(arg2, arg3);
        }
    }

    public boolean a(h arg2) {
        if(this.d != null) {
            return this.d.a(arg2);
        }

        return 0;
    }

    public void onClick(DialogInterface arg2, int arg3) {
        this.b.a(this.a.a().getItem(arg3), 0);
    }

    public void onDismiss(DialogInterface arg3) {
        this.a.a(this.b, true);
    }

    public boolean onKey(DialogInterface arg3, int arg4, KeyEvent arg5) {
        if(arg4 == 82 || arg4 == 4) {
            if(arg5.getAction() == 0 && arg5.getRepeatCount() == 0) {
                Window v3 = this.c.getWindow();
                if(v3 != null) {
                    View v3_1 = v3.getDecorView();
                    if(v3_1 != null) {
                        KeyEvent$DispatcherState v3_2 = v3_1.getKeyDispatcherState();
                        if(v3_2 != null) {
                            v3_2.startTracking(arg5, this);
                            return 1;
                        }
                        else {
                            goto label_35;
                        }
                    }
                    else {
                        goto label_35;
                    }
                }
                else {
                    goto label_35;
                }
            }

            if(arg5.getAction() != 1) {
                goto label_35;
            }

            if(arg5.isCanceled()) {
                goto label_35;
            }

            Window v0 = this.c.getWindow();
            if(v0 == null) {
                goto label_35;
            }

            View v0_1 = v0.getDecorView();
            if(v0_1 == null) {
                goto label_35;
            }

            KeyEvent$DispatcherState v0_2 = v0_1.getKeyDispatcherState();
            if(v0_2 == null) {
                goto label_35;
            }

            if(!v0_2.isTracking(arg5)) {
                goto label_35;
            }

            this.b.a(true);
            arg3.dismiss();
            return 1;
        }

    label_35:
        return this.b.performShortcut(arg4, arg5, 0);
    }
}

