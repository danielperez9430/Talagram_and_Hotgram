package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.a.a.b;
import android.view.ActionProvider;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem$OnActionExpandListener;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class a implements b {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private CharSequence e;
    private CharSequence f;
    private Intent g;
    private char h;
    private int i;
    private char j;
    private int k;
    private Drawable l;
    private int m;
    private Context n;
    private MenuItem$OnMenuItemClickListener o;
    private CharSequence p;
    private CharSequence q;
    private ColorStateList r;
    private PorterDuff$Mode s;
    private boolean t;
    private boolean u;
    private int v;

    public a(Context arg3, int arg4, int arg5, int arg6, int arg7, CharSequence arg8) {
        super();
        this.i = 4096;
        this.k = 4096;
        this.m = 0;
        this.r = null;
        this.s = null;
        this.t = false;
        this.u = false;
        this.v = 16;
        this.n = arg3;
        this.a = arg5;
        this.b = arg4;
        this.c = arg6;
        this.d = arg7;
        this.e = arg8;
    }

    public b a(int arg1) {
        throw new UnsupportedOperationException();
    }

    public b a(android.support.v4.view.b arg1) {
        throw new UnsupportedOperationException();
    }

    public b a(View arg1) {
        throw new UnsupportedOperationException();
    }

    public b a(CharSequence arg1) {
        this.p = arg1;
        return this;
    }

    public android.support.v4.view.b a() {
        return null;
    }

    private void b() {
        if(this.l != null && ((this.t) || (this.u))) {
            this.l = android.support.v4.graphics.drawable.a.g(this.l);
            this.l = this.l.mutate();
            if(this.t) {
                android.support.v4.graphics.drawable.a.a(this.l, this.r);
            }

            if(!this.u) {
                return;
            }

            android.support.v4.graphics.drawable.a.a(this.l, this.s);
        }
    }

    public b b(int arg1) {
        this.setShowAsAction(arg1);
        return this;
    }

    public b b(CharSequence arg1) {
        this.q = arg1;
        return this;
    }

    public boolean collapseActionView() {
        return 0;
    }

    public boolean expandActionView() {
        return 0;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.k;
    }

    public char getAlphabeticShortcut() {
        return this.j;
    }

    public CharSequence getContentDescription() {
        return this.p;
    }

    public int getGroupId() {
        return this.b;
    }

    public Drawable getIcon() {
        return this.l;
    }

    public ColorStateList getIconTintList() {
        return this.r;
    }

    public PorterDuff$Mode getIconTintMode() {
        return this.s;
    }

    public Intent getIntent() {
        return this.g;
    }

    public int getItemId() {
        return this.a;
    }

    public ContextMenu$ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.i;
    }

    public char getNumericShortcut() {
        return this.h;
    }

    public int getOrder() {
        return this.d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.e;
    }

    public CharSequence getTitleCondensed() {
        CharSequence v0 = this.f != null ? this.f : this.e;
        return v0;
    }

    public CharSequence getTooltipText() {
        return this.q;
    }

    public boolean hasSubMenu() {
        return 0;
    }

    public boolean isActionViewExpanded() {
        return 0;
    }

    public boolean isCheckable() {
        boolean v1 = true;
        if((this.v & 1) != 0) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    public boolean isChecked() {
        boolean v0 = (this.v & 2) != 0 ? true : false;
        return v0;
    }

    public boolean isEnabled() {
        boolean v0 = (this.v & 16) != 0 ? true : false;
        return v0;
    }

    public boolean isVisible() {
        boolean v0 = (this.v & 8) == 0 ? true : false;
        return v0;
    }

    public MenuItem setActionProvider(ActionProvider arg1) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setActionView(int arg1) {
        return this.a(arg1);
    }

    public MenuItem setActionView(View arg1) {
        return this.a(arg1);
    }

    public MenuItem setAlphabeticShortcut(char arg1) {
        this.j = Character.toLowerCase(arg1);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char arg1, int arg2) {
        this.j = Character.toLowerCase(arg1);
        this.k = KeyEvent.normalizeMetaState(arg2);
        return this;
    }

    public MenuItem setCheckable(boolean arg2) {
        this.v = (((int)arg2)) | this.v & -2;
        return this;
    }

    public MenuItem setChecked(boolean arg2) {
        int v0 = this.v & -3;
        int v2 = arg2 ? 2 : 0;
        this.v = v2 | v0;
        return this;
    }

    public MenuItem setContentDescription(CharSequence arg1) {
        return this.a(arg1);
    }

    public MenuItem setEnabled(boolean arg2) {
        int v0 = this.v & -17;
        int v2 = arg2 ? 16 : 0;
        this.v = v2 | v0;
        return this;
    }

    public MenuItem setIcon(int arg2) {
        this.m = arg2;
        this.l = android.support.v4.content.a.a(this.n, arg2);
        this.b();
        return this;
    }

    public MenuItem setIcon(Drawable arg1) {
        this.l = arg1;
        this.m = 0;
        this.b();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList arg1) {
        this.r = arg1;
        this.t = true;
        this.b();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff$Mode arg1) {
        this.s = arg1;
        this.u = true;
        this.b();
        return this;
    }

    public MenuItem setIntent(Intent arg1) {
        this.g = arg1;
        return this;
    }

    public MenuItem setNumericShortcut(char arg1) {
        this.h = arg1;
        return this;
    }

    public MenuItem setNumericShortcut(char arg1, int arg2) {
        this.h = arg1;
        this.i = KeyEvent.normalizeMetaState(arg2);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem$OnActionExpandListener arg1) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem$OnMenuItemClickListener arg1) {
        this.o = arg1;
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2) {
        this.h = arg1;
        this.j = Character.toLowerCase(arg2);
        return this;
    }

    public MenuItem setShortcut(char arg1, char arg2, int arg3, int arg4) {
        this.h = arg1;
        this.i = KeyEvent.normalizeMetaState(arg3);
        this.j = Character.toLowerCase(arg2);
        this.k = KeyEvent.normalizeMetaState(arg4);
        return this;
    }

    public void setShowAsAction(int arg1) {
    }

    public MenuItem setShowAsActionFlags(int arg1) {
        return this.b(arg1);
    }

    public MenuItem setTitle(int arg2) {
        this.e = this.n.getResources().getString(arg2);
        return this;
    }

    public MenuItem setTitle(CharSequence arg1) {
        this.e = arg1;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence arg1) {
        this.f = arg1;
        return this;
    }

    public MenuItem setTooltipText(CharSequence arg1) {
        return this.b(arg1);
    }

    public MenuItem setVisible(boolean arg3) {
        int v1 = 8;
        int v0 = this.v & v1;
        if(arg3) {
            v1 = 0;
        }

        this.v = v0 | v1;
        return this;
    }
}

