package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public interface zzh extends IInterface {
    LatLng getCenter();

    int getFillColor();

    String getId();

    double getRadius();

    int getStrokeColor();

    List getStrokePattern();

    float getStrokeWidth();

    float getZIndex();

    boolean isClickable();

    boolean isVisible();

    void remove();

    void setCenter(LatLng arg1);

    void setClickable(boolean arg1);

    void setFillColor(int arg1);

    void setRadius(double arg1);

    void setStrokeColor(int arg1);

    void setStrokePattern(List arg1);

    void setStrokeWidth(float arg1);

    void setVisible(boolean arg1);

    void setZIndex(float arg1);

    boolean zzb(zzh arg1);

    void zze(IObjectWrapper arg1);

    int zzi();

    IObjectWrapper zzj();
}

