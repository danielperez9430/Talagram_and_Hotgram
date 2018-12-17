package org.linphone.mediastream;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build$VERSION;

public class MediastreamerAndroidContext {
    private static final int DEVICE_CHOICE = 0;
    public static final int DEVICE_HAS_BUILTIN_AEC = 1;
    public static final int DEVICE_HAS_BUILTIN_AEC_CRAPPY = 2;
    public static final int DEVICE_HAS_BUILTIN_OPENSLES_AEC = 8;
    public static final int DEVICE_USE_ANDROID_MIC = 4;
    private static MediastreamerAndroidContext instance;
    private static Context mContext;

    private MediastreamerAndroidContext() {
        super();
    }

    public static void enableFilterFromName(String arg3, boolean arg4) {
        if(MediastreamerAndroidContext.getInstance().enableFilterFromNameImpl(arg3, arg4) != 0) {
            StringBuilder v1 = new StringBuilder();
            v1.append("Cannot ");
            String v4 = arg4 ? "enable" : "disable";
            v1.append(v4);
            v1.append(" filter  name [");
            v1.append(arg3);
            v1.append("]");
            throw new MediastreamException(v1.toString());
        }
    }

    private native int enableFilterFromNameImpl(String arg1, boolean arg2) {
    }

    public static boolean filterFromNameEnabled(String arg1) {
        return MediastreamerAndroidContext.getInstance().filterFromNameEnabledImpl(arg1);
    }

    private native boolean filterFromNameEnabledImpl(String arg1) {
    }

    public static Context getContext() {
        return MediastreamerAndroidContext.mContext;
    }

    private static MediastreamerAndroidContext getInstance() {
        if(MediastreamerAndroidContext.instance == null) {
            MediastreamerAndroidContext.instance = new MediastreamerAndroidContext();
        }

        return MediastreamerAndroidContext.instance;
    }

    private static int parseInt(String arg4, int arg5) {
        try {
            arg5 = Integer.parseInt(arg4);
        }
        catch(NumberFormatException ) {
            Object[] v0 = new Object[1];
            v0[0] = "Can\'t parse " + arg4 + " to integer ; using default value " + arg5;
            Log.e(v0);
        }

        return arg5;
    }

    @TargetApi(value=19) public static void setContext(Object arg6) {
        if(arg6 == null) {
            return;
        }

        MediastreamerAndroidContext.mContext = ((Context)arg6);
        boolean v6 = MediastreamerAndroidContext.mContext.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
        boolean v0 = MediastreamerAndroidContext.mContext.getPackageManager().hasSystemFeature("android.hardware.audio.pro");
        Object[] v2 = new Object[1];
        v2[0] = "[Device] hasLowLatencyFeature: " + v6 + ", hasProFeature: " + v0;
        Log.i(v2);
        int v6_1 = 256;
        int v2_1 = 44100;
        MediastreamerAndroidContext v3_1 = MediastreamerAndroidContext.getInstance();
        if(Build$VERSION.SDK_INT >= 19) {
            Object v4 = MediastreamerAndroidContext.mContext.getSystemService("audio");
            v6_1 = MediastreamerAndroidContext.parseInt(((AudioManager)v4).getProperty("android.media.property.OUTPUT_FRAMES_PER_BUFFER"), v6_1);
            v2_1 = MediastreamerAndroidContext.parseInt(((AudioManager)v4).getProperty("android.media.property.OUTPUT_SAMPLE_RATE"), v2_1);
            Object[] v1 = new Object[1];
            v1[0] = "[Device] Output frames per buffer: " + v6_1 + ", output sample rates: " + v2_1 + " for OpenSLES MS sound card.";
            Log.i(v1);
            v3_1.setDeviceFavoriteSampleRate(v2_1);
            v3_1.setDeviceFavoriteBufferSize(v6_1);
        }
        else {
            Log.i(new Object[]{"Android < 4.4 detected, android context not used."});
        }
    }

    private native void setDeviceFavoriteBufferSize(int arg1) {
    }

    private native void setDeviceFavoriteSampleRate(int arg1) {
    }
}

