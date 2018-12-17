package org.linphone.mediastream.video.capture.hwconf;

import android.hardware.Camera;
import android.os.Build;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;

public final class Hacks {
    class BuiltInEchoCancellerModel {
        public String manufacturer;
        public String model;

        public BuiltInEchoCancellerModel(String arg1, String arg2) {
            super();
            this.manufacturer = arg1;
            this.model = arg2;
        }
    }

    private static BuiltInEchoCancellerModel[] mBuiltInEchoCancellerModels;

    static {
        Hacks.mBuiltInEchoCancellerModels = new BuiltInEchoCancellerModel[]{new BuiltInEchoCancellerModel("samsung", "GT-I9100"), new BuiltInEchoCancellerModel("samsung", "GT-I9300")};
    }

    private Hacks() {
        super();
    }

    public static boolean hasBuiltInEchoCanceller() {
        Object[] v0_1;
        BuiltInEchoCancellerModel[] v0 = Hacks.mBuiltInEchoCancellerModels;
        int v1 = v0.length;
        int v3;
        for(v3 = 0; v3 < v1; ++v3) {
            BuiltInEchoCancellerModel v5 = v0[v3];
            if((Build.MANUFACTURER.equals(v5.manufacturer)) && (Build.MODEL.startsWith(v5.model))) {
                v0_1 = new Object[1];
                v0_1[0] = Build.MANUFACTURER + " " + Build.MODEL + " has a built-in echo canceller";
                Log.i(v0_1);
                return 1;
            }
        }

        v0_1 = new Object[1];
        v0_1[0] = Build.MANUFACTURER + " " + Build.MODEL + " doesn\'t have a built-in echo canceller";
        Log.i(v0_1);
        return 0;
    }

    public static boolean hasCamera() {
        int v0;
        boolean v1 = true;
        if(Version.sdkAboveOrEqual(9)) {
            try {
                v0 = Camera.class.getMethod("getNumberOfCameras", null).invoke(null).intValue();
            }
            catch(Exception ) {
                Log.e(new Object[]{"Error getting number of cameras"});
                v0 = 0;
            }

            if(v0 > 0) {
            }
            else {
                v1 = false;
            }

            return v1;
        }

        Log.i(new Object[]{"Hack: considering there IS a camera.\nIf it is not the case, report DEVICE and MODEL to linphone-users@nongnu.org"});
        return 1;
    }

    public static final boolean hasTwoCamerasRear0Front1() {
        boolean v0 = (Hacks.isLGP970()) || (Hacks.isSPHD700()) || (Hacks.isADR6400()) ? true : false;
        return v0;
    }

    private static final boolean isADR6400() {
        boolean v0 = (Build.MODEL.startsWith("ADR6400")) || (Build.DEVICE.startsWith("ADR6400")) ? true : false;
        return v0;
    }

    private static boolean isGT9000() {
        return Build.DEVICE.startsWith("GT-I9000");
    }

    private static boolean isGTP1000() {
        return Build.DEVICE.startsWith("GT-P1000");
    }

    private static boolean isGalaxyS() {
        boolean v0 = (Hacks.isGT9000()) || (Hacks.isSC02B()) || (Hacks.isSGHI896()) || (Hacks.isSPHD700()) ? true : false;
        return v0;
    }

    public static boolean isGalaxySOrTab() {
        boolean v0 = (Hacks.isGalaxyS()) || (Hacks.isGalaxyTab()) ? true : false;
        return v0;
    }

    public static boolean isGalaxySOrTabWithFrontCamera() {
        boolean v0 = !Hacks.isGalaxySOrTab() || (Hacks.isGalaxySOrTabWithoutFrontCamera()) ? false : true;
        return v0;
    }

    private static boolean isGalaxySOrTabWithoutFrontCamera() {
        boolean v0 = (Hacks.isSC02B()) || (Hacks.isSGHI896()) ? true : false;
        return v0;
    }

    public static boolean isGalaxyTab() {
        return Hacks.isGTP1000();
    }

    private static final boolean isLGP970() {
        return Build.DEVICE.startsWith("LG-P970");
    }

    private static boolean isSC02B() {
        return Build.DEVICE.startsWith("SC-02B");
    }

    private static boolean isSGHI896() {
        return Build.DEVICE.startsWith("SGH-I896");
    }

    private static final boolean isSPHD700() {
        return Build.DEVICE.startsWith("SPH-D700");
    }

    public static boolean needGalaxySAudioHack() {
        boolean v0 = !Hacks.isGalaxySOrTab() || (Hacks.isSC02B()) ? false : true;
        return v0;
    }

    public static boolean needPausingCallForSpeakers() {
        return Hacks.needGalaxySAudioHack();
    }

    public static boolean needSoftvolume() {
        boolean v0 = !Hacks.isGalaxySOrTab() || !Version.sdkStrictlyBelow(9) ? false : true;
        return v0;
    }

    public static final void sleep(int arg2) {
        long v0 = ((long)arg2);
        try {
            Thread.sleep(v0);
            return;
        }
        catch(InterruptedException ) {
            return;
        }
    }
}

