package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;

public class StreetViewPanorama {
    public interface OnStreetViewPanoramaCameraChangeListener {
        void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera arg1);
    }

    public interface OnStreetViewPanoramaChangeListener {
        void onStreetViewPanoramaChange(StreetViewPanoramaLocation arg1);
    }

    public interface OnStreetViewPanoramaClickListener {
        void onStreetViewPanoramaClick(StreetViewPanoramaOrientation arg1);
    }

    public interface OnStreetViewPanoramaLongClickListener {
        void onStreetViewPanoramaLongClick(StreetViewPanoramaOrientation arg1);
    }

    private final IStreetViewPanoramaDelegate zzbn;

    public StreetViewPanorama(IStreetViewPanoramaDelegate arg2) {
        super();
        this.zzbn = Preconditions.checkNotNull(arg2, "delegate");
    }

    public void animateTo(StreetViewPanoramaCamera arg2, long arg3) {
        try {
            this.zzbn.animateTo(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public StreetViewPanoramaLocation getLocation() {
        try {
            return this.zzbn.getStreetViewPanoramaLocation();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public StreetViewPanoramaCamera getPanoramaCamera() {
        try {
            return this.zzbn.getPanoramaCamera();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public boolean isPanningGesturesEnabled() {
        try {
            return this.zzbn.isPanningGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public boolean isStreetNamesEnabled() {
        try {
            return this.zzbn.isStreetNamesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public boolean isUserNavigationEnabled() {
        try {
            return this.zzbn.isUserNavigationEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.zzbn.isZoomGesturesEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation arg2) {
        try {
            IObjectWrapper v2_1 = this.zzbn.orientationToPoint(arg2);
            if(v2_1 == null) {
                return null;
            }

            return ObjectWrapper.unwrap(v2_1);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point arg2) {
        try {
            return this.zzbn.pointToOrientation(ObjectWrapper.wrap(arg2));
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzbn.setOnStreetViewPanoramaCameraChangeListener(null);
            return;
        label_7:
            this.zzbn.setOnStreetViewPanoramaCameraChangeListener(new zzae(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzbn.setOnStreetViewPanoramaChangeListener(null);
            return;
        label_7:
            this.zzbn.setOnStreetViewPanoramaChangeListener(new zzad(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzbn.setOnStreetViewPanoramaClickListener(null);
            return;
        label_7:
            this.zzbn.setOnStreetViewPanoramaClickListener(new zzaf(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnStreetViewPanoramaLongClickListener(OnStreetViewPanoramaLongClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzbn.setOnStreetViewPanoramaLongClickListener(null);
            return;
        label_7:
            this.zzbn.setOnStreetViewPanoramaLongClickListener(new zzag(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public void setPanningGesturesEnabled(boolean arg2) {
        try {
            this.zzbn.enablePanning(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setPosition(LatLng arg2) {
        try {
            this.zzbn.setPosition(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setPosition(LatLng arg2, int arg3) {
        try {
            this.zzbn.setPositionWithRadius(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setPosition(LatLng arg2, int arg3, StreetViewSource arg4) {
        try {
            this.zzbn.setPositionWithRadiusAndSource(arg2, arg3, arg4);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setPosition(LatLng arg2, StreetViewSource arg3) {
        try {
            this.zzbn.setPositionWithSource(arg2, arg3);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setPosition(String arg2) {
        try {
            this.zzbn.setPositionWithID(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setStreetNamesEnabled(boolean arg2) {
        try {
            this.zzbn.enableStreetNames(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setUserNavigationEnabled(boolean arg2) {
        try {
            this.zzbn.enableUserNavigation(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public void setZoomGesturesEnabled(boolean arg2) {
        try {
            this.zzbn.enableZoom(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }
}

