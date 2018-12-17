package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import java.util.List;

public interface zzz extends IInterface {
    int getColor();

    Cap getEndCap();

    String getId();

    int getJointType();

    List getPattern();

    List getPoints();

    Cap getStartCap();

    float getWidth();

    float getZIndex();

    boolean isClickable();

    boolean isGeodesic();

    boolean isVisible();

    void remove();

    void setClickable(boolean arg1);

    void setColor(int arg1);

    void setEndCap(Cap arg1);

    void setGeodesic(boolean arg1);

    void setJointType(int arg1);

    void setPattern(List arg1);

    void setPoints(List arg1);

    void setStartCap(Cap arg1);

    void setVisible(boolean arg1);

    void setWidth(float arg1);

    void setZIndex(float arg1);

    boolean zzb(zzz arg1);

    void zze(IObjectWrapper arg1);

    int zzi();

    IObjectWrapper zzj();
}

