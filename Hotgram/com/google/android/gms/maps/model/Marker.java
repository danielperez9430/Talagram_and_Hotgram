package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;

public final class Marker {
    private final zzt zzdl;

    public Marker(zzt arg1) {
        super();
        this.zzdl = Preconditions.checkNotNull(arg1);
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof Marker)) {
            return 0;
        }

        try {
            return this.zzdl.zzj(((Marker)arg2).zzdl);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final float getAlpha() {
        try {
            return this.zzdl.getAlpha();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzdl.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final LatLng getPosition() {
        try {
            return this.zzdl.getPosition();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getRotation() {
        try {
            return this.zzdl.getRotation();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getSnippet() {
        try {
            return this.zzdl.getSnippet();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzdl.zzj());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getTitle() {
        try {
            return this.zzdl.getTitle();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzdl.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdl.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void hideInfoWindow() {
        try {
            this.zzdl.hideInfoWindow();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isDraggable() {
        try {
            return this.zzdl.isDraggable();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isFlat() {
        try {
            return this.zzdl.isFlat();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isInfoWindowShown() {
        try {
            return this.zzdl.isInfoWindowShown();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzdl.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzdl.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setAlpha(float arg2) {
        try {
            this.zzdl.setAlpha(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setAnchor(float arg2, float arg3) {
        try {
            this.zzdl.setAnchor(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setDraggable(boolean arg2) {
        try {
            this.zzdl.setDraggable(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setFlat(boolean arg2) {
        try {
            this.zzdl.setFlat(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setIcon(BitmapDescriptor arg2) {
        if(arg2 != null) {
            goto label_7;
        }

        try {
            this.zzdl.zzg(null);
            return;
        label_7:
            this.zzdl.zzg(arg2.zza());
            return;
        label_6:
        }
        catch(RemoteException v2) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v2);
    }

    public final void setInfoWindowAnchor(float arg2, float arg3) {
        try {
            this.zzdl.setInfoWindowAnchor(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPosition(LatLng arg2) {
        if(arg2 != null) {
            try {
                this.zzdl.setPosition(arg2);
                return;
            }
            catch(RemoteException v2) {
                throw new RuntimeRemoteException(v2);
            }
        }

        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    public final void setRotation(float arg2) {
        try {
            this.zzdl.setRotation(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setSnippet(String arg2) {
        try {
            this.zzdl.setSnippet(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTag(Object arg2) {
        try {
            this.zzdl.zze(ObjectWrapper.wrap(arg2));
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTitle(String arg2) {
        try {
            this.zzdl.setTitle(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzdl.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzdl.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void showInfoWindow() {
        try {
            this.zzdl.showInfoWindow();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }
}

