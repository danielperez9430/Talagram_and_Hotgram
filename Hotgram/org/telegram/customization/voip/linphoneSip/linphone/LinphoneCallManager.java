package org.telegram.customization.voip.linphoneSip.linphone;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore;
import org.linphone.mediastream.Log;

public class LinphoneCallManager {
    private static volatile LinphoneCallManager sCallManager;

    public LinphoneCallManager() {
        super();
    }

    private BandwidthManager bm() {
        return BandwidthManager.getInstance();
    }

    public static LinphoneCallManager getInstance() {
        if(LinphoneCallManager.sCallManager == null) {
            Class v0 = LinphoneCallManager.class;
            __monitor_enter(v0);
            try {
                if(LinphoneCallManager.sCallManager == null) {
                    LinphoneCallManager.sCallManager = new LinphoneCallManager();
                }

                __monitor_exit(v0);
                goto label_14;
            label_12:
                __monitor_exit(v0);
            }
            catch(Throwable v1) {
                goto label_12;
            }

            throw v1;
        }

    label_14:
        return LinphoneCallManager.sCallManager;
    }

    public void updateCall() {
        LinphoneCore v0 = LinphoneManager.getLc();
        LinphoneCall v1 = v0.getCurrentCall();
        if(v1 == null) {
            Log.e(new Object[]{"Trying to updateCall while not in call: doing nothing"});
            return;
        }

        this.bm().updateWithProfileSettings(v0, v1.getCurrentParamsCopy());
        v0.updateCall(v1, null);
    }
}

