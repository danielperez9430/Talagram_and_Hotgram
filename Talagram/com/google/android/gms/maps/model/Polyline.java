package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzz;
import java.util.List;

public final class Polyline {
    private final zzz zzea;

    public Polyline(zzz arg1) {
        super();
        this.zzea = Preconditions.checkNotNull(arg1);
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof Polyline)) {
            return 0;
        }

        try {
            return this.zzea.zzb(((Polyline)arg2).zzea);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final int getColor() {
        try {
            return this.zzea.getColor();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Cap getEndCap() {
        try {
            return this.zzea.getEndCap().zzg();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzea.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int getJointType() {
        try {
            return this.zzea.getJointType();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getPattern() {
        try {
            return PatternItem.zza(this.zzea.getPattern());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final List getPoints() {
        try {
            return this.zzea.getPoints();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Cap getStartCap() {
        try {
            return this.zzea.getStartCap().zzg();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Object getTag() {
        try {
            return ObjectWrapper.unwrap(this.zzea.zzj());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getWidth() {
        try {
            return this.zzea.getWidth();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzea.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzea.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isClickable() {
        try {
            return this.zzea.isClickable();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isGeodesic() {
        try {
            return this.zzea.isGeodesic();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzea.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzea.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setClickable(boolean arg2) {
        try {
            this.zzea.setClickable(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setColor(int arg2) {
        try {
            this.zzea.setColor(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setEndCap(Cap arg2) {
        Preconditions.checkNotNull(arg2, "endCap must not be null");
        try {
            this.zzea.setEndCap(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setGeodesic(boolean arg2) {
        try {
            this.zzea.setGeodesic(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setJointType(int arg2) {
        try {
            this.zzea.setJointType(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPattern(List arg2) {
        try {
            this.zzea.setPattern(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setPoints(List arg2) {
        try {
            this.zzea.setPoints(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setStartCap(Cap arg2) {
        Preconditions.checkNotNull(arg2, "startCap must not be null");
        try {
            this.zzea.setStartCap(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTag(Object arg2) {
        try {
            this.zzea.zze(ObjectWrapper.wrap(arg2));
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzea.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setWidth(float arg2) {
        try {
            this.zzea.setWidth(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzea.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

