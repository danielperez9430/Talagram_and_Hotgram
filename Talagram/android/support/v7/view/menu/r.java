package android.support.v7.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.a.a.a;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

class r extends c implements Menu {
    r(Context arg1, a arg2) {
        super(arg1, arg2);
    }

    public MenuItem add(int arg2) {
        return this.a(this.b.add(arg2));
    }

    public MenuItem add(int arg2, int arg3, int arg4, int arg5) {
        return this.a(this.b.add(arg2, arg3, arg4, arg5));
    }

    public MenuItem add(int arg2, int arg3, int arg4, CharSequence arg5) {
        return this.a(this.b.add(arg2, arg3, arg4, arg5));
    }

    public MenuItem add(CharSequence arg2) {
        return this.a(this.b.add(arg2));
    }

    public int addIntentOptions(int arg13, int arg14, int arg15, ComponentName arg16, Intent[] arg17, Intent arg18, int arg19, MenuItem[] arg20) {
        r v0 = this;
        MenuItem[] v1 = arg20;
        MenuItem[] v2 = v1 != null ? new MenuItem[v1.length] : null;
        int v3 = v0.b.addIntentOptions(arg13, arg14, arg15, arg16, arg17, arg18, arg19, v2);
        if(v2 != null) {
            int v4 = 0;
            int v5 = v2.length;
            while(v4 < v5) {
                v1[v4] = this.a(v2[v4]);
                ++v4;
            }
        }

        return v3;
    }

    public SubMenu addSubMenu(int arg2) {
        return this.a(this.b.addSubMenu(arg2));
    }

    public SubMenu addSubMenu(int arg2, int arg3, int arg4, int arg5) {
        return this.a(this.b.addSubMenu(arg2, arg3, arg4, arg5));
    }

    public SubMenu addSubMenu(int arg2, int arg3, int arg4, CharSequence arg5) {
        return this.a(this.b.addSubMenu(arg2, arg3, arg4, arg5));
    }

    public SubMenu addSubMenu(CharSequence arg2) {
        return this.a(this.b.addSubMenu(arg2));
    }

    public void clear() {
        this.a();
        this.b.clear();
    }

    public void close() {
        this.b.close();
    }

    public MenuItem findItem(int arg2) {
        return this.a(this.b.findItem(arg2));
    }

    public MenuItem getItem(int arg2) {
        return this.a(this.b.getItem(arg2));
    }

    public boolean hasVisibleItems() {
        return this.b.hasVisibleItems();
    }

    public boolean isShortcutKey(int arg2, KeyEvent arg3) {
        return this.b.isShortcutKey(arg2, arg3);
    }

    public boolean performIdentifierAction(int arg2, int arg3) {
        return this.b.performIdentifierAction(arg2, arg3);
    }

    public boolean performShortcut(int arg2, KeyEvent arg3, int arg4) {
        return this.b.performShortcut(arg2, arg3, arg4);
    }

    public void removeGroup(int arg2) {
        this.a(arg2);
        this.b.removeGroup(arg2);
    }

    public void removeItem(int arg2) {
        this.b(arg2);
        this.b.removeItem(arg2);
    }

    public void setGroupCheckable(int arg2, boolean arg3, boolean arg4) {
        this.b.setGroupCheckable(arg2, arg3, arg4);
    }

    public void setGroupEnabled(int arg2, boolean arg3) {
        this.b.setGroupEnabled(arg2, arg3);
    }

    public void setGroupVisible(int arg2, boolean arg3) {
        this.b.setGroupVisible(arg2, arg3);
    }

    public void setQwertyMode(boolean arg2) {
        this.b.setQwertyMode(arg2);
    }

    public int size() {
        return this.b.size();
    }
}

