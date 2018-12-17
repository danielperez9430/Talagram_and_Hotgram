package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources$Theme;
import android.content.res.Resources;
import android.os.Build$VERSION;
import android.support.v7.a.a$i;
import android.view.LayoutInflater;

public class d extends ContextWrapper {
    private int a;
    private Resources$Theme b;
    private LayoutInflater c;
    private Configuration d;
    private Resources e;

    public d(Context arg1, int arg2) {
        super(arg1);
        this.a = arg2;
    }

    public d() {
        super(null);
    }

    public d(Context arg1, Resources$Theme arg2) {
        super(arg1);
        this.b = arg2;
    }

    public int a() {
        return this.a;
    }

    protected void a(Resources$Theme arg1, int arg2, boolean arg3) {
        arg1.applyStyle(arg2, true);
    }

    protected void attachBaseContext(Context arg1) {
        super.attachBaseContext(arg1);
    }

    private Resources b() {
        Resources v0;
        if(this.e == null) {
            if(this.d == null) {
                v0 = super.getResources();
            }
            else if(Build$VERSION.SDK_INT >= 17) {
                v0 = this.createConfigurationContext(this.d).getResources();
            }
            else {
                goto label_14;
            }

            this.e = v0;
        }

    label_14:
        return this.e;
    }

    private void c() {
        boolean v0 = this.b == null ? true : false;
        if(v0) {
            this.b = this.getResources().newTheme();
            Resources$Theme v1 = this.getBaseContext().getTheme();
            if(v1 != null) {
                this.b.setTo(v1);
            }
        }

        this.a(this.b, this.a, v0);
    }

    public AssetManager getAssets() {
        return this.getResources().getAssets();
    }

    public Resources getResources() {
        return this.b();
    }

    public Object getSystemService(String arg2) {
        if("layout_inflater".equals(arg2)) {
            if(this.c == null) {
                this.c = LayoutInflater.from(this.getBaseContext()).cloneInContext(((Context)this));
            }

            return this.c;
        }

        return this.getBaseContext().getSystemService(arg2);
    }

    public Resources$Theme getTheme() {
        if(this.b != null) {
            return this.b;
        }

        if(this.a == 0) {
            this.a = i.Theme_AppCompat_Light;
        }

        this.c();
        return this.b;
    }

    public void setTheme(int arg2) {
        if(this.a != arg2) {
            this.a = arg2;
            this.c();
        }
    }
}

