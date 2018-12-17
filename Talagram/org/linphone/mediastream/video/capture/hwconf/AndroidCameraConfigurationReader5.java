package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import java.util.ArrayList;
import java.util.List;
import org.linphone.mediastream.Log;

class AndroidCameraConfigurationReader5 {
    AndroidCameraConfigurationReader5() {
        super();
    }

    public static AndroidCamera[] probeCameras() {
        AndroidCamera v2_1;
        ArrayList v0 = new ArrayList(1);
        Camera v2 = Camera.open();
        List v3 = v2.getParameters().getSupportedPreviewSizes();
        v2.release();
        int v4 = 90;
        if(Hacks.isGalaxySOrTab()) {
            Log.d(new Object[]{"Hack Galaxy S : has one or more cameras"});
            if(Hacks.isGalaxySOrTabWithFrontCamera()) {
                Log.d(new Object[]{"Hack Galaxy S : HAS a front camera with id=2"});
                ((List)v0).add(new AndroidCamera(2, true, v4, v3));
            }
            else {
                Log.d(new Object[]{"Hack Galaxy S : NO front camera"});
            }

            Log.d(new Object[]{"Hack Galaxy S : HAS a rear camera with id=1"});
            v2_1 = new AndroidCamera(1, false, v4, v3);
            goto label_48;
        }
        else {
            ((List)v0).add(new AndroidCamera(0, false, v4, v3));
            if(!Hacks.hasTwoCamerasRear0Front1()) {
                goto label_49;
            }

            Log.d(new Object[]{"Hack SPHD700 has 2 cameras a rear with id=0 and a front with id=1"});
            v2_1 = new AndroidCamera(1, true, v4, v3);
        label_48:
            ((List)v0).add(v2_1);
        }

    label_49:
        return ((List)v0).toArray(new AndroidCamera[((List)v0).size()]);
    }
}

