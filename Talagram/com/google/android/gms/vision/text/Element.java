package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.internal.vision.zzag;
import java.util.ArrayList;
import java.util.List;

public class Element implements Text {
    private zzag zzcy;

    Element(zzag arg1) {
        super();
        this.zzcy = arg1;
    }

    public Rect getBoundingBox() {
        return zzc.zza(((Text)this));
    }

    public List getComponents() {
        return new ArrayList();
    }

    public Point[] getCornerPoints() {
        return zzc.zza(this.zzcy.zzdj);
    }

    public String getLanguage() {
        return this.zzcy.zzdd;
    }

    public String getValue() {
        return this.zzcy.zzdm;
    }
}

