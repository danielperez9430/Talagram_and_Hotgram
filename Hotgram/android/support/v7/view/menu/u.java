package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class u extends h implements SubMenu {
    private h d;
    private j e;

    public u(Context arg1, h arg2, j arg3) {
        super(arg1);
        this.d = arg2;
        this.e = arg3;
    }

    public String a() {
        int v0 = this.e != null ? this.e.getItemId() : 0;
        if(v0 == 0) {
            return null;
        }

        return super.a() + ":" + v0;
    }

    public void a(a arg2) {
        this.d.a(arg2);
    }

    boolean a(h arg2, MenuItem arg3) {
        boolean v2 = (super.a(arg2, arg3)) || (this.d.a(arg2, arg3)) ? true : false;
        return v2;
    }

    public boolean b() {
        return this.d.b();
    }

    public boolean c() {
        return this.d.c();
    }

    public boolean c(j arg2) {
        return this.d.c(arg2);
    }

    public boolean d() {
        return this.d.d();
    }

    public boolean d(j arg2) {
        return this.d.d(arg2);
    }

    public MenuItem getItem() {
        return this.e;
    }

    public h q() {
        return this.d.q();
    }

    public void setGroupDividerEnabled(boolean arg2) {
        this.d.setGroupDividerEnabled(arg2);
    }

    public SubMenu setHeaderIcon(int arg1) {
        return super.e(arg1);
    }

    public SubMenu setHeaderIcon(Drawable arg1) {
        return super.a(arg1);
    }

    public SubMenu setHeaderTitle(CharSequence arg1) {
        return super.a(arg1);
    }

    public SubMenu setHeaderTitle(int arg1) {
        return super.d(arg1);
    }

    public SubMenu setHeaderView(View arg1) {
        return super.a(arg1);
    }

    public SubMenu setIcon(int arg2) {
        this.e.setIcon(arg2);
        return this;
    }

    public SubMenu setIcon(Drawable arg2) {
        this.e.setIcon(arg2);
        return this;
    }

    public void setQwertyMode(boolean arg2) {
        this.d.setQwertyMode(arg2);
    }

    public Menu t() {
        return this.d;
    }
}

