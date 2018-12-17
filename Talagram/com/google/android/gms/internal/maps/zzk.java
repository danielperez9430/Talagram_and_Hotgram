package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface zzk extends IInterface {
    float getBearing();

    LatLngBounds getBounds();

    float getHeight();

    String getId();

    LatLng getPosition();

    float getTransparency();

    float getWidth();

    float getZIndex();

    boolean isClickable();

    boolean isVisible();

    void remove();

    void setBearing(float arg1);

    void setClickable(boolean arg1);

    void setDimensions(float arg1);

    void setPosition(LatLng arg1);

    void setPositionFromBounds(LatLngBounds arg1);

    void setTransparency(float arg1);

    void setVisible(boolean arg1);

    void setZIndex(float arg1);

    void zza(float arg1, float arg2);

    boolean zzb(zzk arg1);

    void zze(IObjectWrapper arg1);

    void zzf(IObjectWrapper arg1);

    int zzi();

    IObjectWrapper zzj();
}

