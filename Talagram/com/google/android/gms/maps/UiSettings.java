package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
    private final IUiSettingsDelegate zzci;

    UiSettings(IUiSettingsDelegate arg1) {
        super();
        this.zzci = arg1;
    }

    public final boolean isCompassEnabled() {
        try {
            return this.zzci.isCompassEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isIndoorLevelPickerEnabled() {
        try {
            return this.zzci.isIndoorLevelPickerEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isMapToolbarEnabled() {
        try {
            return this.zzci.isMapToolbarEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isMyLocationButtonEnabled() {
        try {
            return this.zzci.isMyLocationButtonEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isRotateGesturesEnabled() {
        try {
            return this.zzci.isRotateGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isScrollGesturesEnabled() {
        try {
            return this.zzci.isScrollGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isTiltGesturesEnabled() {
        try {
            return this.zzci.isTiltGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isZoomControlsEnabled() {
        try {
            return this.zzci.isZoomControlsEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isZoomGesturesEnabled() {
        try {
            return this.zzci.isZoomGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setAllGesturesEnabled(boolean arg2) {
        try {
            this.zzci.setAllGesturesEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setCompassEnabled(boolean arg2) {
        try {
            this.zzci.setCompassEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setIndoorLevelPickerEnabled(boolean arg2) {
        try {
            this.zzci.setIndoorLevelPickerEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMapToolbarEnabled(boolean arg2) {
        try {
            this.zzci.setMapToolbarEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMyLocationButtonEnabled(boolean arg2) {
        try {
            this.zzci.setMyLocationButtonEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setRotateGesturesEnabled(boolean arg2) {
        try {
            this.zzci.setRotateGesturesEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setScrollGesturesEnabled(boolean arg2) {
        try {
            this.zzci.setScrollGesturesEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTiltGesturesEnabled(boolean arg2) {
        try {
            this.zzci.setTiltGesturesEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZoomControlsEnabled(boolean arg2) {
        try {
            this.zzci.setZoomControlsEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZoomGesturesEnabled(boolean arg2) {
        try {
            this.zzci.setZoomGesturesEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

