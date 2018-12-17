package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera$CameraInfo;
import android.hardware.Camera;
import java.util.ArrayList;
import java.util.List;

class AndroidCameraConfigurationReader9 {
    AndroidCameraConfigurationReader9() {
        super();
    }

    public static AndroidCamera[] probeCameras() {
        ArrayList v0 = new ArrayList(Camera.getNumberOfCameras());
        int v2;
        for(v2 = 0; v2 < Camera.getNumberOfCameras(); ++v2) {
            Camera$CameraInfo v3 = new Camera$CameraInfo();
            Camera.getCameraInfo(v2, v3);
            Camera v4 = Camera.open(v2);
            boolean v7 = true;
            if(v3.facing == 1) {
            }
            else {
                v7 = false;
            }

            ((List)v0).add(new AndroidCamera(v2, v7, v3.orientation, v4.getParameters().getSupportedPreviewSizes()));
            v4.release();
        }

        return ((List)v0).toArray(new AndroidCamera[((List)v0).size()]);
    }
}

