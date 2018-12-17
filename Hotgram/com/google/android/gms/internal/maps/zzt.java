package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzt extends IInterface {
    float getAlpha();

    String getId();

    LatLng getPosition();

    float getRotation();

    String getSnippet();

    String getTitle();

    float getZIndex();

    void hideInfoWindow();

    boolean isDraggable();

    boolean isFlat();

    boolean isInfoWindowShown();

    boolean isVisible();

    void remove();

    void setAlpha(float arg1);

    void setAnchor(float arg1, float arg2);

    void setDraggable(boolean arg1);

    void setFlat(boolean arg1);

    void setInfoWindowAnchor(float arg1, float arg2);

    void setPosition(LatLng arg1);

    void setRotation(float arg1);

    void setSnippet(String arg1);

    void setTitle(String arg1);

    void setVisible(boolean arg1);

    void setZIndex(float arg1);

    void showInfoWindow();

    void zze(IObjectWrapper arg1);

    void zzg(IObjectWrapper arg1);

    int zzi();

    IObjectWrapper zzj();

    boolean zzj(zzt arg1);
}

