package org.telegram.customization.voip.linphoneSip.utils;

import android.content.Context;
import android.net.NetworkInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.linphone.core.LinphoneCall$State;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LpConfig;
import org.linphone.mediastream.Log;
import org.telegram.customization.voip.linphoneSip.linphone.LinphoneManager;

public class LinphoneUtils {
    public LinphoneUtils() {
        super();
    }

    public static void copyFromPackage(Context arg3, int arg4, String arg5) {
        FileOutputStream v5 = arg3.openFileOutput(arg5, 0);
        InputStream v3 = arg3.getResources().openRawResource(arg4);
        byte[] v4 = new byte[8048];
        while(true) {
            int v1 = v3.read(v4);
            if(v1 == -1) {
                break;
            }

            v5.write(v4, 0, v1);
        }

        v5.flush();
        v5.close();
        v3.close();
    }

    public static void copyIfNotExist(Context arg1, int arg2, String arg3) {
        File v0 = new File(arg3);
        if(!v0.exists()) {
            LinphoneUtils.copyFromPackage(arg1, arg2, v0.getName());
        }
    }

    public static LpConfig getConfig(Context arg3) {
        LinphoneCore v0 = LinphoneUtils.getLc();
        if(v0 != null) {
            return v0.getConfig();
        }

        if(LinphoneManager.isInstanceiated()) {
            Log.w(new Object[]{"LinphoneManager not instanciated yet..."});
            LinphoneCoreFactory v0_1 = LinphoneCoreFactory.instance();
            StringBuilder v1 = new StringBuilder();
            v1.append(arg3.getFilesDir().getAbsolutePath());
            v1.append("/.linphonerc");
            return v0_1.createLpConfig(v1.toString());
        }

        return LinphoneCoreFactory.instance().createLpConfig(LinphoneManager.getInstance().mLinphoneConfigFile);
    }

    public static String getFreeSWITCHServer() {
        return "5.145.118.150";
    }

    private static LinphoneCore getLc() {
        if(!LinphoneManager.isInstanceiated()) {
            return null;
        }

        return LinphoneManager.getLcIfManagerNotDestroyOrNull();
    }

    public static List getLinphoneCalls(LinphoneCore arg1) {
        return new ArrayList(Arrays.asList(arg1.getCalls()));
    }

    public static boolean isCallEstablished(LinphoneCall arg2) {
        boolean v0 = false;
        if(arg2 == null) {
            return 0;
        }

        State v1 = arg2.getState();
        if((LinphoneUtils.isCallRunning(arg2)) || v1 == State.Paused || v1 == State.PausedByRemote || v1 == State.Pausing) {
            v0 = true;
        }

        return v0;
    }

    public static boolean isCallRunning(LinphoneCall arg2) {
        boolean v0 = false;
        if(arg2 == null) {
            return 0;
        }

        State v2 = arg2.getState();
        if(v2 == State.Connected || v2 == State.CallUpdating || v2 == State.CallUpdatedByRemote || v2 == State.StreamsRunning || v2 == State.Resuming) {
            v0 = true;
        }

        return v0;
    }

    private static boolean isConnectionFast(int arg0, int arg1) {
        if(arg0 == 0) {
            if(arg1 != 11) {
                switch(arg1) {
                    case 1: 
                    case 2: {
                        return 0;
                    }
                    default: {
                        return 1;
                    }
                }
            }

            return 0;
        }

        return 1;
    }

    public static boolean isHighBandwidthConnection(Context arg1) {
        NetworkInfo v1 = arg1.getSystemService("connectivity").getActiveNetworkInfo();
        boolean v1_1 = v1 == null || !v1.isConnected() || !LinphoneUtils.isConnectionFast(v1.getType(), v1.getSubtype()) ? false : true;
        return v1_1;
    }
}

