package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build$VERSION;
import android.support.v7.a.a$b;
import android.support.v7.a.a$d;
import android.support.v7.a.a$j;
import android.view.ViewConfiguration;

public class a {
    private Context a;

    private a(Context arg1) {
        super();
        this.a = arg1;
    }

    public static a a(Context arg1) {
        return new a(arg1);
    }

    public int a() {
        Configuration v0 = this.a.getResources().getConfiguration();
        int v1 = v0.screenWidthDp;
        int v2 = v0.screenHeightDp;
        int v3 = 600;
        if(v0.smallestScreenWidthDp <= v3 && v1 <= v3) {
            int v0_1 = 720;
            v3 = 960;
            if(v1 > v3 && v2 > v0_1) {
                return 5;
            }

            if(v1 > v0_1 && v2 > v3) {
                return 5;
            }

            if(v1 < 500) {
                v0_1 = 480;
                v3 = 640;
                if(v1 > v3 && v2 > v0_1) {
                    return 4;
                }

                if(v1 > v0_1 && v2 > v3) {
                    return 4;
                }

                if(v1 >= 360) {
                    return 3;
                }

                return 2;
            }

            return 4;
        }

        return 5;
    }

    public boolean b() {
        if(Build$VERSION.SDK_INT >= 19) {
            return 1;
        }

        return ViewConfiguration.get(this.a).hasPermanentMenuKey() ^ 1;
    }

    public int c() {
        return this.a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean d() {
        return this.a.getResources().getBoolean(b.abc_action_bar_embed_tabs);
    }

    public int e() {
        TypedArray v0 = this.a.obtainStyledAttributes(null, j.ActionBar, android.support.v7.a.a$a.actionBarStyle, 0);
        int v1 = v0.getLayoutDimension(j.ActionBar_height, 0);
        Resources v2 = this.a.getResources();
        if(!this.d()) {
            v1 = Math.min(v1, v2.getDimensionPixelSize(d.abc_action_bar_stacked_max_height));
        }

        v0.recycle();
        return v1;
    }

    public boolean f() {
        boolean v0 = this.a.getApplicationInfo().targetSdkVersion < 14 ? true : false;
        return v0;
    }

    public int g() {
        return this.a.getResources().getDimensionPixelSize(d.abc_action_bar_stacked_tab_max_width);
    }
}

