package org.telegram.messenger;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class GcmInstanceIDListenerService extends FirebaseInstanceIdService {
    public GcmInstanceIDListenerService() {
        super();
    }

    static void lambda$null$1(int arg0, String arg1) {
        MessagesController.getInstance(arg0).registerForPush(arg1);
    }

    static void lambda$onTokenRefresh$0(String arg2) {
        if(BuildVars.LOGS_ENABLED) {
            FileLog.d("Refreshed token: " + arg2);
        }

        ApplicationLoader.postInitApplication();
        GcmInstanceIDListenerService.sendRegistrationToServer(arg2);
    }

    static void lambda$sendRegistrationToServer$2(String arg3) {
        SharedConfig.pushString = arg3;
        int v1;
        for(v1 = 0; v1 < 3; ++v1) {
            UserConfig v2 = UserConfig.getInstance(v1);
            v2.registeredForPush = false;
            v2.saveConfig(false);
            if(UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId() != 0) {
                AndroidUtilities.runOnUIThread(new -$$Lambda$GcmInstanceIDListenerService$-tyyowLAVYYBps9OdmDJbMXYbx4(v1, arg3));
            }
        }
    }

    public void onTokenRefresh() {
        try {
            AndroidUtilities.runOnUIThread(new -$$Lambda$GcmInstanceIDListenerService$U0of4u_gjpwP0kERRtDmy8FPZHU(FirebaseInstanceId.a().d()));
        }
        catch(Throwable v0) {
            FileLog.e(v0);
        }
    }

    public static void sendRegistrationToServer(String arg2) {
        Utilities.stageQueue.postRunnable(new -$$Lambda$GcmInstanceIDListenerService$ic4eRcywWgltcM02b44diphgNCE(arg2));
    }
}

