package org.telegram.messenger.camera;

import android.hardware.Camera;
import java.util.ArrayList;

public class CameraInfo {
    protected Camera camera;
    protected int cameraId;
    protected final int frontCamera;
    protected ArrayList pictureSizes;
    protected ArrayList previewSizes;

    public CameraInfo(int arg2, int arg3) {
        super();
        this.pictureSizes = new ArrayList();
        this.previewSizes = new ArrayList();
        this.cameraId = arg2;
        this.frontCamera = arg3;
    }

    private Camera getCamera() {
        return this.camera;
    }

    public int getCameraId() {
        return this.cameraId;
    }

    public ArrayList getPictureSizes() {
        return this.pictureSizes;
    }

    public ArrayList getPreviewSizes() {
        return this.previewSizes;
    }

    public boolean isFrontface() {
        boolean v0 = this.frontCamera != 0 ? true : false;
        return v0;
    }
}

