package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera$Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;

public class AndroidCameraConfiguration {
    public class AndroidCamera {
        public class Size {
            public final int height;
            public final int width;

            public Size(int arg1, int arg2) {
                super();
                this.width = arg1;
                this.height = arg2;
            }
        }

        public boolean frontFacing;
        public int id;
        public int orientation;
        public List resolutions;

        public AndroidCamera(int arg5, boolean arg6, int arg7, List arg8) {
            super();
            this.resolutions = new ArrayList(arg8.size());
            Iterator v8 = arg8.iterator();
            while(v8.hasNext()) {
                Object v0 = v8.next();
                this.resolutions.add(new Size(((Camera$Size)v0).width, ((Camera$Size)v0).height));
            }

            this.id = arg5;
            this.frontFacing = arg6;
            this.orientation = arg7;
        }

        public AndroidCamera(int arg1, boolean arg2, List arg3, int arg4) {
            super();
            this.id = arg1;
            this.frontFacing = arg2;
            this.orientation = arg4;
            this.resolutions = arg3;
        }
    }

    private static AndroidCamera[] camerasCache;

    public AndroidCameraConfiguration() {
        super();
    }

    public static boolean hasFrontCamera() {
        AndroidCameraConfiguration.initCamerasCache();
        AndroidCamera[] v0 = AndroidCameraConfiguration.camerasCache;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            if(v0[v3].frontFacing) {
                return 1;
            }
        }

        return 0;
    }

    public static boolean hasSeveralCameras() {
        AndroidCameraConfiguration.initCamerasCache();
        boolean v1 = true;
        if(AndroidCameraConfiguration.camerasCache.length > 1) {
        }
        else {
            v1 = false;
        }

        return v1;
    }

    private static void initCamerasCache() {
        AndroidCamera[] v0_1;
        if(AndroidCameraConfiguration.camerasCache != null && AndroidCameraConfiguration.camerasCache.length != 0) {
            return;
        }

        try {
            if(Version.sdk() < 9) {
                v0_1 = AndroidCameraConfiguration.probeCamerasSDK5();
            }
            else if(Version.sdk() >= 21) {
                v0_1 = AndroidCameraConfiguration.probeCamerasSDK21();
            }
            else {
                v0_1 = AndroidCameraConfiguration.probeCamerasSDK9();
            }

            AndroidCameraConfiguration.camerasCache = v0_1;
        }
        catch(Exception v0) {
            Log.e(new Object[]{"Error: cannot retrieve cameras information (busy ?)", v0});
            v0.printStackTrace();
            AndroidCameraConfiguration.camerasCache = new AndroidCamera[0];
        }
    }

    static AndroidCamera[] probeCamerasSDK21() {
        return AndroidCameraConfigurationReader21.probeCameras();
    }

    static AndroidCamera[] probeCamerasSDK5() {
        return AndroidCameraConfigurationReader5.probeCameras();
    }

    static AndroidCamera[] probeCamerasSDK9() {
        return AndroidCameraConfigurationReader9.probeCameras();
    }

    public static AndroidCamera[] retrieveCameras() {
        AndroidCameraConfiguration.initCamerasCache();
        return AndroidCameraConfiguration.camerasCache;
    }
}

