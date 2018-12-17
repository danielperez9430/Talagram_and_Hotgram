package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzac;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzc;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public final class GoogleMap {
    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker arg1);

        View getInfoWindow(Marker arg1);
    }

    @Deprecated public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition arg1);
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraMoveStartedListener {
        public static final int REASON_API_ANIMATION = 2;
        public static final int REASON_DEVELOPER_ANIMATION = 3;
        public static final int REASON_GESTURE = 1;

        void onCameraMoveStarted(int arg1);
    }

    public interface OnCircleClickListener {
        void onCircleClick(Circle arg1);
    }

    public interface OnGroundOverlayClickListener {
        void onGroundOverlayClick(GroundOverlay arg1);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding arg1);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker arg1);
    }

    public interface OnInfoWindowCloseListener {
        void onInfoWindowClose(Marker arg1);
    }

    public interface OnInfoWindowLongClickListener {
        void onInfoWindowLongClick(Marker arg1);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng arg1);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng arg1);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker arg1);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker arg1);

        void onMarkerDragEnd(Marker arg1);

        void onMarkerDragStart(Marker arg1);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    @Deprecated public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location arg1);
    }

    public interface OnMyLocationClickListener {
        void onMyLocationClick(Location arg1);
    }

    public interface OnPoiClickListener {
        void onPoiClick(PointOfInterest arg1);
    }

    public interface OnPolygonClickListener {
        void onPolygonClick(Polygon arg1);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline arg1);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap arg1);
    }

    final class zza extends zzd {
        private final CancelableCallback zzai;

        zza(CancelableCallback arg1) {
            super();
            this.zzai = arg1;
        }

        public final void onCancel() {
            this.zzai.onCancel();
        }

        public final void onFinish() {
            this.zzai.onFinish();
        }
    }

    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate zzg;
    private UiSettings zzh;

    public GoogleMap(IGoogleMapDelegate arg1) {
        super();
        this.zzg = Preconditions.checkNotNull(arg1);
    }

    public final Circle addCircle(CircleOptions arg3) {
        try {
            return new Circle(this.zzg.addCircle(arg3));
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions arg2) {
        try {
            zzk v2_1 = this.zzg.addGroundOverlay(arg2);
            if(v2_1 == null) {
                return null;
            }

            return new GroundOverlay(v2_1);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }

        return null;
    }

    public final Marker addMarker(MarkerOptions arg2) {
        try {
            zzt v2_1 = this.zzg.addMarker(arg2);
            if(v2_1 == null) {
                return null;
            }

            return new Marker(v2_1);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }

        return null;
    }

    public final Polygon addPolygon(PolygonOptions arg3) {
        try {
            return new Polygon(this.zzg.addPolygon(arg3));
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final Polyline addPolyline(PolylineOptions arg3) {
        try {
            return new Polyline(this.zzg.addPolyline(arg3));
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions arg2) {
        try {
            zzac v2_1 = this.zzg.addTileOverlay(arg2);
            if(v2_1 == null) {
                return null;
            }

            return new TileOverlay(v2_1);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }

        return null;
    }

    public final void animateCamera(CameraUpdate arg2) {
        try {
            this.zzg.animateCamera(arg2.zza());
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void animateCamera(CameraUpdate arg3, int arg4, CancelableCallback arg5) {
        zza v5_1;
        try {
            IGoogleMapDelegate v0 = this.zzg;
            IObjectWrapper v3_1 = arg3.zza();
            if(arg5 == null) {
                zzc v5 = null;
            }
            else {
                v5_1 = new zza(arg5);
            }

            v0.animateCameraWithDurationAndCallback(v3_1, arg4, ((zzc)v5_1));
            return;
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final void animateCamera(CameraUpdate arg3, CancelableCallback arg4) {
        zzc v4;
        try {
            IGoogleMapDelegate v0 = this.zzg;
            IObjectWrapper v3_1 = arg3.zza();
            if(arg4 == null) {
                v4 = null;
            }
            else {
                zza v4_1 = new zza(arg4);
            }

            v0.animateCameraWithCallback(v3_1, v4);
            return;
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final void clear() {
        try {
            this.zzg.clear();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.zzg.getCameraPosition();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final IndoorBuilding getFocusedBuilding() {
        try {
            zzn v0_1 = this.zzg.getFocusedBuilding();
            if(v0_1 == null) {
                return null;
            }

            return new IndoorBuilding(v0_1);
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }

        return null;
    }

    public final int getMapType() {
        try {
            return this.zzg.getMapType();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getMaxZoomLevel() {
        try {
            return this.zzg.getMaxZoomLevel();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final float getMinZoomLevel() {
        try {
            return this.zzg.getMinZoomLevel();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    @Deprecated public final Location getMyLocation() {
        try {
            return this.zzg.getMyLocation();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final Projection getProjection() {
        try {
            return new Projection(this.zzg.getProjection());
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if(this.zzh == null) {
                this.zzh = new UiSettings(this.zzg.getUiSettings());
            }

            return this.zzh;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isBuildingsEnabled() {
        try {
            return this.zzg.isBuildingsEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isIndoorEnabled() {
        try {
            return this.zzg.isIndoorEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.zzg.isMyLocationEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.zzg.isTrafficEnabled();
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void moveCamera(CameraUpdate arg2) {
        try {
            this.zzg.moveCamera(arg2.zza());
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void resetMinMaxZoomPreference() {
        try {
            this.zzg.resetMinMaxZoomPreference();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }

    public final void setBuildingsEnabled(boolean arg2) {
        try {
            this.zzg.setBuildingsEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setContentDescription(String arg2) {
        try {
            this.zzg.setContentDescription(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final boolean setIndoorEnabled(boolean arg2) {
        try {
            return this.zzg.setIndoorEnabled(arg2);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setInfoWindowAdapter(null);
            return;
        label_7:
            this.zzg.setInfoWindowAdapter(new zzg(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setLatLngBoundsForCameraTarget(LatLngBounds arg2) {
        try {
            this.zzg.setLatLngBoundsForCameraTarget(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setLocationSource(LocationSource arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setLocationSource(null);
            return;
        label_7:
            this.zzg.setLocationSource(new zzl(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final boolean setMapStyle(MapStyleOptions arg2) {
        try {
            return this.zzg.setMapStyle(arg2);
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMapType(int arg2) {
        try {
            this.zzg.setMapType(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMaxZoomPreference(float arg2) {
        try {
            this.zzg.setMaxZoomPreference(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMinZoomPreference(float arg2) {
        try {
            this.zzg.setMinZoomPreference(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setMyLocationEnabled(boolean arg2) {
        try {
            this.zzg.setMyLocationEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    @Deprecated public final void setOnCameraChangeListener(OnCameraChangeListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCameraChangeListener(null);
            return;
        label_7:
            this.zzg.setOnCameraChangeListener(new com.google.android.gms.maps.zzt(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnCameraIdleListener(OnCameraIdleListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCameraIdleListener(null);
            return;
        label_7:
            this.zzg.setOnCameraIdleListener(new zzx(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnCameraMoveCanceledListener(OnCameraMoveCanceledListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCameraMoveCanceledListener(null);
            return;
        label_7:
            this.zzg.setOnCameraMoveCanceledListener(new zzw(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnCameraMoveListener(OnCameraMoveListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCameraMoveListener(null);
            return;
        label_7:
            this.zzg.setOnCameraMoveListener(new zzv(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnCameraMoveStartedListener(OnCameraMoveStartedListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCameraMoveStartedListener(null);
            return;
        label_7:
            this.zzg.setOnCameraMoveStartedListener(new zzu(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnCircleClickListener(OnCircleClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnCircleClickListener(null);
            return;
        label_7:
            this.zzg.setOnCircleClickListener(new zzo(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnGroundOverlayClickListener(OnGroundOverlayClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnGroundOverlayClickListener(null);
            return;
        label_7:
            this.zzg.setOnGroundOverlayClickListener(new com.google.android.gms.maps.zzn(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnIndoorStateChangeListener(OnIndoorStateChangeListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnIndoorStateChangeListener(null);
            return;
        label_7:
            this.zzg.setOnIndoorStateChangeListener(new com.google.android.gms.maps.zza(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnInfoWindowClickListener(OnInfoWindowClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnInfoWindowClickListener(null);
            return;
        label_7:
            this.zzg.setOnInfoWindowClickListener(new com.google.android.gms.maps.zzd(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnInfoWindowCloseListener(OnInfoWindowCloseListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnInfoWindowCloseListener(null);
            return;
        label_7:
            this.zzg.setOnInfoWindowCloseListener(new zzf(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnInfoWindowLongClickListener(OnInfoWindowLongClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnInfoWindowLongClickListener(null);
            return;
        label_7:
            this.zzg.setOnInfoWindowLongClickListener(new zze(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMapClickListener(OnMapClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMapClickListener(null);
            return;
        label_7:
            this.zzg.setOnMapClickListener(new zzy(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMapLoadedCallback(OnMapLoadedCallback arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMapLoadedCallback(null);
            return;
        label_7:
            this.zzg.setOnMapLoadedCallback(new com.google.android.gms.maps.zzk(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMapLongClickListener(null);
            return;
        label_7:
            this.zzg.setOnMapLongClickListener(new zzz(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMarkerClickListener(null);
            return;
        label_7:
            this.zzg.setOnMarkerClickListener(new zzb(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMarkerDragListener(null);
            return;
        label_7:
            this.zzg.setOnMarkerDragListener(new com.google.android.gms.maps.zzc(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMyLocationButtonClickListener(null);
            return;
        label_7:
            this.zzg.setOnMyLocationButtonClickListener(new zzi(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    @Deprecated public final void setOnMyLocationChangeListener(OnMyLocationChangeListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMyLocationChangeListener(null);
            return;
        label_7:
            this.zzg.setOnMyLocationChangeListener(new zzh(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnMyLocationClickListener(OnMyLocationClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnMyLocationClickListener(null);
            return;
        label_7:
            this.zzg.setOnMyLocationClickListener(new zzj(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnPoiClickListener(OnPoiClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnPoiClickListener(null);
            return;
        label_7:
            this.zzg.setOnPoiClickListener(new zzs(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnPolygonClickListener(OnPolygonClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnPolygonClickListener(null);
            return;
        label_7:
            this.zzg.setOnPolygonClickListener(new zzp(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener arg3) {
        if(arg3 != null) {
            goto label_7;
        }

        try {
            this.zzg.setOnPolylineClickListener(null);
            return;
        label_7:
            this.zzg.setOnPolylineClickListener(new zzq(this, arg3));
            return;
        label_6:
        }
        catch(RemoteException v3) {
            goto label_6;
        }

        throw new RuntimeRemoteException(v3);
    }

    public final void setPadding(int arg2, int arg3, int arg4, int arg5) {
        try {
            this.zzg.setPadding(arg2, arg3, arg4, arg5);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void setTrafficEnabled(boolean arg2) {
        try {
            this.zzg.setTrafficEnabled(arg2);
            return;
        }
        catch(RemoteException v2) {
            throw new RuntimeRemoteException(v2);
        }
    }

    public final void snapshot(SnapshotReadyCallback arg2) {
        this.snapshot(arg2, null);
    }

    public final void snapshot(SnapshotReadyCallback arg3, Bitmap arg4) {
        IObjectWrapper v4 = arg4 != null ? ObjectWrapper.wrap(arg4) : null;
        try {
            this.zzg.snapshot(new zzr(this, arg3), v4);
            return;
        }
        catch(RemoteException v3) {
            throw new RuntimeRemoteException(v3);
        }
    }

    public final void stopAnimation() {
        try {
            this.zzg.stopAnimation();
            return;
        }
        catch(RemoteException v0) {
            throw new RuntimeRemoteException(v0);
        }
    }
}

