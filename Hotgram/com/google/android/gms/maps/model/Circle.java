package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzh;
import java.util.List;

public final class Circle {
    private final zzh zzcn;

    public Circle(zzh arg1) {
        super();
        this.zzcn = Preconditions.checkNotNull(arg1);
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof Circle)) {
            return 0;
        }

        try {
            return this.zzcn.zzb(((Circle)arg2).zzcn);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final LatLng getCenter() {
        try {
            return this.zzcn.getCenter();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getFillColor() {
        try {
            return this.zzcn.getFillColor();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzcn.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final double getRadius() {
        try {
            return this.zzcn.getRadius();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getStrokeColor() {
        try {
            return this.zzcn.getStrokeColor();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getStrokePattern() {
        try {
            return PatternItem.zza(this.zzcn.getStrokePattern());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getStrokeWidth() {
        try {
            return this.zzcn.getStrokeWidth();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzcn.zzj());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzcn.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzcn.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzcn.isClickable();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzcn.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzcn.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setCenter(LatLng arg2) {
        try {
            this.zzcn.setCenter(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setClickable(boolean arg2) {
        try {
            this.zzcn.setClickable(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setFillColor(int arg2) {
        try {
            this.zzcn.setFillColor(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setRadius(double arg2) {
        try {
            this.zzcn.setRadius(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokeColor(int arg2) {
        try {
            this.zzcn.setStrokeColor(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokePattern(List arg2) {
        try {
            this.zzcn.setStrokePattern(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokeWidth(float arg2) {
        try {
            this.zzcn.setStrokeWidth(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTag(Object arg2) {
        try {
            this.zzcn.zze(ObjectWrapper.wrap(arg2));
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzcn.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzcn.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

