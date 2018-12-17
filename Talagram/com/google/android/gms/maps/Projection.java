package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class Projection {
    private final IProjectionDelegate zzbm;

    Projection(IProjectionDelegate arg1) {
        super();
        this.zzbm = arg1;
    }

    public final LatLng fromScreenLocation(Point arg2) {
        try {
            return this.zzbm.fromScreenLocation(ObjectWrapper.wrap(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final VisibleRegion getVisibleRegion() {
        try {
            return this.zzbm.getVisibleRegion();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Point toScreenLocation(LatLng arg2) {
        try {
            return ObjectWrapper.unwrap(this.zzbm.toScreenLocation(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

