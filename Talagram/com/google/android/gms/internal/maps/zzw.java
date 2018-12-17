package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzw extends IInterface {
    int getFillColor();

    List getHoles();

    String getId();

    List getPoints();

    int getStrokeColor();

    int getStrokeJointType();

    List getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean arg1);

    void setFillColor(int arg1);

    void setGeodesic(boolean arg1);

    void setHoles(List arg1);

    void setPoints(List arg1);

    void setStrokeColor(int arg1);

    void setStrokeJointType(int arg1);

    void setStrokePattern(List arg1);

    void setStrokeWidth(float arg1);

    void setVisible(boolean arg1);

    void setZIndex(float arg1);

    boolean zzb(zzw arg1);

    void zze(IObjectWrapper arg1);

    int zzi();

    IObjectWrapper zzj();
}

