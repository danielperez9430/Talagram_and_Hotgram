package org.linphone.mediastream.video.capture.hwconf;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Size;
import java.util.ArrayList;
import java.util.List;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.MediastreamerAndroidContext;

public class AndroidCameraConfigurationReader21 {
    public AndroidCameraConfigurationReader21() {
        super();
    }

    @TargetApi(value=21) public static AndroidCamera[] probeCameras() {
        Context v0 = MediastreamerAndroidContext.getContext();
        if(v0 != null) {
            Object v0_1 = v0.getSystemService("camera");
            try {
                String[] v3 = ((CameraManager)v0_1).getCameraIdList();
                ArrayList v4 = new ArrayList(v3.length);
                int v5 = 0;
                int v6 = 0;
                while(v5 < v3.length) {
                    CameraCharacteristics v7 = ((CameraManager)v0_1).getCameraCharacteristics(v3[v5]);
                    boolean v8 = v7.get(CameraCharacteristics.LENS_FACING).intValue() == 0 ? true : false;
                    int v9 = v7.get(CameraCharacteristics.SENSOR_ORIENTATION).intValue();
                    Size[] v7_1 = v7.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(256);
                    ArrayList v10 = new ArrayList(v7_1.length);
                    int v11;
                    for(v11 = 0; v11 < v7_1.length; ++v11) {
                        ((List)v10).add(new org.linphone.mediastream.video.capture.hwconf.AndroidCameraConfiguration$AndroidCamera$Size(v7_1[v11].getWidth(), v7_1[v11].getHeight()));
                    }

                    ((List)v4).add(new AndroidCamera(v5, v8, ((List)v10), v9));
                    ++v6;
                    ++v5;
                }

                return ((List)v4).toArray(new AndroidCamera[v6]);
            }
            catch(CameraAccessException v0_2) {
                Log.e(new Object[]{v0_2});
            }
        }

        return new AndroidCamera[0];
    }
}

