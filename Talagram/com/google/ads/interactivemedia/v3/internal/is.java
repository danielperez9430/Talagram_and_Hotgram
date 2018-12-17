package com.google.ads.interactivemedia.v3.internal;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot$ClickListener;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import java.util.ArrayList;
import java.util.List;

public class is implements CompanionAdSlot {
    private int a;
    private int b;
    private ViewGroup c;
    private String d;
    private final List e;

    public is() {
        super();
        this.e = new ArrayList(1);
    }

    public List a() {
        return this.e;
    }

    public void a(String arg1) {
        this.d = arg1;
    }

    public void addClickListener(ClickListener arg2) {
        this.e.add(arg2);
    }

    public ViewGroup getContainer() {
        return this.c;
    }

    public int getHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.a;
    }

    public boolean isFilled() {
        boolean v0 = this.c.findViewWithTag(this.d) != null ? true : false;
        return v0;
    }

    public void removeClickListener(ClickListener arg2) {
        this.e.remove(arg2);
    }

    public void setContainer(ViewGroup arg1) {
        this.c = arg1;
    }

    public void setSize(int arg1, int arg2) {
        this.a = arg1;
        this.b = arg2;
    }
}

