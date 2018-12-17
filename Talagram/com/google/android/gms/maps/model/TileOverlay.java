package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzac;

public final class TileOverlay {
    private final zzac zzeg;

    public TileOverlay(zzac arg1) {
        super();
        this.zzeg = Preconditions.checkNotNull(arg1);
    }

    public final void clearTileCache() {
        try {
            this.zzeg.clearTileCache();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean equals(Object arg2) {
        if(!(arg2 instanceof TileOverlay)) {
            return 0;
        }

        try {
            return this.zzeg.zza(((TileOverlay)arg2).zzeg);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final boolean getFadeIn() {
        try {
            return this.zzeg.getFadeIn();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final String getId() {
        try {
            return this.zzeg.getId();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getTransparency() {
        try {
            return this.zzeg.getTransparency();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getZIndex() {
        try {
            return this.zzeg.getZIndex();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final int hashCode() {
        try {
            return this.zzeg.zzi();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isVisible() {
        try {
            return this.zzeg.isVisible();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void remove() {
        try {
            this.zzeg.remove();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setFadeIn(boolean arg2) {
        try {
            this.zzeg.setFadeIn(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTransparency(float arg2) {
        try {
            this.zzeg.setTransparency(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setVisible(boolean arg2) {
        try {
            this.zzeg.setVisible(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setZIndex(float arg2) {
        try {
            this.zzeg.setZIndex(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

