package com.google.android.gms.maps.internal;

import android.os.IInterface;

public interface IUiSettingsDelegate extends IInterface {
    boolean isCompassEnabled();

    boolean isIndoorLevelPickerEnabled();

    boolean isMapToolbarEnabled();

    boolean isMyLocationButtonEnabled();

    boolean isRotateGesturesEnabled();

    boolean isScrollGesturesEnabled();

    boolean isTiltGesturesEnabled();

    boolean isZoomControlsEnabled();

    boolean isZoomGesturesEnabled();

    void setAllGesturesEnabled(boolean arg1);

    void setCompassEnabled(boolean arg1);

    void setIndoorLevelPickerEnabled(boolean arg1);

    void setMapToolbarEnabled(boolean arg1);

    void setMyLocationButtonEnabled(boolean arg1);

    void setRotateGesturesEnabled(boolean arg1);

    void setScrollGesturesEnabled(boolean arg1);

    void setTiltGesturesEnabled(boolean arg1);

    void setZoomControlsEnabled(boolean arg1);

    void setZoomGesturesEnabled(boolean arg1);
}

