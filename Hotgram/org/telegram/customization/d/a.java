package org.telegram.customization.d;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.customization.Model.FilterHelper;
import org.telegram.customization.a.e;
import org.telegram.ui.ActionBar.Theme;

public class a extends Dialog {
    Activity a;
    String b;
    int c;
    ArrayList d;
    org.telegram.customization.f.a e;
    e f;
    FilterHelper g;
    TextView h;
    RecyclerView i;
    Button j;
    Button k;
    View l;
    View m;
    View n;
    View o;

    public a(Activity arg2, String arg3, int arg4, ArrayList arg5, org.telegram.customization.f.a arg6) {
        super(((Context)arg2));
        this.d = new ArrayList();
        this.g = new FilterHelper();
        this.a = arg2;
        this.b = arg3;
        this.c = arg4;
        this.d = arg5;
        this.e = arg6;
    }

    protected void onCreate(Bundle arg5) {
        super.onCreate(arg5);
        this.requestWindowFeature(1);
        this.setContentView(2131492980);
        this.h = this.findViewById(2131297016);
        this.o = this.findViewById(2131297074);
        this.o.setBackgroundColor(Theme.getColor("actionBarDefault"));
        this.i = this.findViewById(2131296829);
        this.n = this.findViewById(2131296672);
        this.j = this.findViewById(2131296369);
        this.k = this.findViewById(2131296341);
        this.l = this.findViewById(2131296775);
        this.m = this.findViewById(2131297071);
        this.f = new e(this, this.d, this.e);
        this.i.setLayoutManager(new LinearLayoutManager(this.a, 1, false));
        this.i.setAdapter(this.f);
        this.h.setText(this.b);
        int v0 = 8;
        this.l.setVisibility(v0);
        this.i.setVisibility(0);
        if(this.c == 3) {
            this.n.setVisibility(v0);
        }
    }
}

