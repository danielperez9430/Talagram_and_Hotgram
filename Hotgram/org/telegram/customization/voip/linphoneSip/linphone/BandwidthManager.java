package org.telegram.customization.voip.linphoneSip.linphone;

import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;

public class BandwidthManager {
    public static final int HIGH_RESOLUTION = 0;
    public static final int LOW_BANDWIDTH = 2;
    public static final int LOW_RESOLUTION = 1;
    private int currentProfile;
    private static BandwidthManager instance;

    private BandwidthManager() {
        super();
        this.currentProfile = 0;
    }

    public int getCurrentProfile() {
        return this.currentProfile;
    }

    public static final BandwidthManager getInstance() {
        BandwidthManager v1_1;
        Class v0 = BandwidthManager.class;
        __monitor_enter(v0);
        try {
            if(BandwidthManager.instance == null) {
                BandwidthManager.instance = new BandwidthManager();
            }

            v1_1 = BandwidthManager.instance;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public boolean isVideoPossible() {
        boolean v0 = this.currentProfile != 2 ? true : false;
        return v0;
    }

    public void updateWithProfileSettings(LinphoneCore arg2, LinphoneCallParams arg3) {
        if(arg3 != null) {
            if(!this.isVideoPossible()) {
                arg3.setVideoEnabled(false);
                arg3.setAudioBandwidth(40);
            }
            else {
                arg3.setVideoEnabled(true);
                arg3.setAudioBandwidth(0);
            }
        }
    }
}

