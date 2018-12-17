package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzw;
import java.util.List;

public final class Polygon {
    private final zzw zzdv;

    public Polygon(zzw arg1) {
        super();
        this.zzdv = Preconditions.checkNotNull(arg1);
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof Polygon)) {
            return 0;
        }

        try {
            return this.zzdv.zzb(((Polygon)arg2).zzdv);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final int getFillColor() {
        try {
            return this.zzdv.getFillColor();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getHoles() {
        try {
            return this.zzdv.getHoles();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzdv.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getPoints() {
        try {
            return this.zzdv.getPoints();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getStrokeColor() {
        try {
            return this.zzdv.getStrokeColor();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getStrokeJointType() {
        try {
            return this.zzdv.getStrokeJointType();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getStrokePattern() {
        try {
            return PatternItem.zza(this.zzdv.getStrokePattern());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getStrokeWidth() {
        try {
            return this.zzdv.getStrokeWidth();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzdv.zzj());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzdv.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzdv.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzdv.isClickable();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isGeodesic() {
        try {
            return this.zzdv.isGeodesic();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzdv.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzdv.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setClickable(boolean arg2) {
        try {
            this.zzdv.setClickable(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setFillColor(int arg2) {
        try {
            this.zzdv.setFillColor(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setGeodesic(boolean arg2) {
        try {
            this.zzdv.setGeodesic(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setHoles(List arg2) {
        try {
            this.zzdv.setHoles(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPoints(List arg2) {
        try {
            this.zzdv.setPoints(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokeColor(int arg2) {
        try {
            this.zzdv.setStrokeColor(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokeJointType(int arg2) {
        try {
            this.zzdv.setStrokeJointType(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokePattern(List arg2) {
        try {
            this.zzdv.setStrokePattern(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStrokeWidth(float arg2) {
        try {
            this.zzdv.setStrokeWidth(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTag(Object arg2) {
        try {
            this.zzdv.zze(ObjectWrapper.wrap(arg2));
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzdv.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzdv.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

