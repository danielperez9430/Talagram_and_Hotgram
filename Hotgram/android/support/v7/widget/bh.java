package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.os.Build$VERSION;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class bh extends ContextWrapper {
    private static final Object a;
    private static ArrayList b;
    private final Resources c;
    private final Resources$Theme d;

    static {
        bh.a = new Object();
    }

    private bh(Context arg3) {
        super(arg3);
        if(bp.a()) {
            this.c = new bp(((Context)this), arg3.getResources());
            this.d = this.c.newTheme();
            this.d.setTo(arg3.getTheme());
        }
        else {
            this.c = new bj(((Context)this), arg3.getResources());
            this.d = null;
        }
    }

    public static Context a(Context arg4) {
        Object v2;
        if(bh.b(arg4)) {
            Object v0 = bh.a;
            __monitor_enter(v0);
            try {
                if(bh.b == null) {
                    bh.b = new ArrayList();
                }
                else {
                    int v1;
                    for(v1 = bh.b.size() - 1; v1 >= 0; --v1) {
                        v2 = bh.b.get(v1);
                        if(v2 == null || ((WeakReference)v2).get() == null) {
                            bh.b.remove(v1);
                        }
                    }

                    for(v1 = bh.b.size() - 1; v1 >= 0; --v1) {
                        v2 = bh.b.get(v1);
                        v2 = v2 != null ? ((WeakReference)v2).get() : null;
                        if(v2 != null && ((bh)v2).getBaseContext() == arg4) {
                            __monitor_exit(v0);
                            return ((Context)v2);
                        }
                    }
                }

                bh v1_1 = new bh(arg4);
                bh.b.add(new WeakReference(v1_1));
                __monitor_exit(v0);
                return ((Context)v1_1);
            label_49:
                __monitor_exit(v0);
            }
            catch(Throwable v4) {
                goto label_49;
            }

            throw v4;
        }

        return arg4;
    }

    private static boolean b(Context arg2) {
        boolean v1 = false;
        if(!(arg2 instanceof bh) && !(arg2.getResources() instanceof bj)) {
            if((arg2.getResources() instanceof bp)) {
            }
            else {
                if(Build$VERSION.SDK_INT >= 21 && !bp.a()) {
                    return v1;
                }

                v1 = true;
            }
        }

        return v1;
    }

    public AssetManager getAssets() {
        return this.c.getAssets();
    }

    public Resources getResources() {
        return this.c;
    }

    public Resources$Theme getTheme() {
        Resources$Theme v0 = this.d == null ? super.getTheme() : this.d;
        return v0;
    }

    public void setTheme(int arg3) {
        if(this.d == null) {
            super.setTheme(arg3);
        }
        else {
            this.d.applyStyle(arg3, true);
        }
    }
}

