package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzk;

public final class GroundOverlay {
    private final zzk zzcv;

    public GroundOverlay(zzk arg1) {
        super();
        this.zzcv = Preconditions.checkNotNull(arg1);
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof GroundOverlay)) {
            return 0;
        }

        try {
            return this.zzcv.zzb(((GroundOverlay)arg2).zzcv);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final float getBearing() {
        try {
            return this.zzcv.getBearing();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final LatLngBounds getBounds() {
        try {
            return this.zzcv.getBounds();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getHeight() {
        try {
            return this.zzcv.getHeight();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzcv.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final LatLng getPosition() {
        try {
            return this.zzcv.getPosition();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzcv.zzj());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getTransparency() {
        try {
            return this.zzcv.getTransparency();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getWidth() {
        try {
            return this.zzcv.getWidth();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzcv.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzcv.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzcv.isClickable();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzcv.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzcv.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setBearing(float arg2) {
        try {
            this.zzcv.setBearing(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setClickable(boolean arg2) {
        try {
            this.zzcv.setClickable(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setDimensions(float arg2) {
        try {
            this.zzcv.setDimensions(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setDimensions(float arg2, float arg3) {
        try {
            this.zzcv.zza(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setImage(BitmapDescriptor arg2) {
        Preconditions.checkNotNull(arg2, "imageDescriptor must not be null");
        try {
            this.zzcv.zzf(arg2.zza());
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPosition(LatLng arg2) {
        try {
            this.zzcv.setPosition(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPositionFromBounds(LatLngBounds arg2) {
        try {
            this.zzcv.setPositionFromBounds(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTag(Object arg2) {
        try {
            this.zzcv.zze(ObjectWrapper.wrap(arg2));
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTransparency(float arg2) {
        try {
            this.zzcv.setTransparency(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzcv.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzcv.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

