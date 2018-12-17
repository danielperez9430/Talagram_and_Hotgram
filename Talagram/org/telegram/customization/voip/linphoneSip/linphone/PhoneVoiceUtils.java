package org.telegram.customization.voip.linphoneSip.linphone;

import android.util.Log;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAuthInfo;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.LinphoneCoreFactory;
import org.linphone.core.LinphoneProxyConfig;

public class PhoneVoiceUtils {
    private static final String TAG = "PhoneVoiceUtils";
    private LinphoneCore mLinphoneCore;
    private static volatile PhoneVoiceUtils sPhoneVoiceUtils;

    private PhoneVoiceUtils() {
        super();
        this.mLinphoneCore = null;
        this.mLinphoneCore = LinphoneManager.getLc();
        this.mLinphoneCore.enableEchoCancellation(true);
        this.mLinphoneCore.enableEchoLimiter(true);
    }

    public void clearAccounts() {
        LinphoneProxyConfig[] v0 = this.mLinphoneCore.getProxyConfigList();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            this.mLinphoneCore.removeProxyConfig(v0[v2]);
        }
    }

    public static PhoneVoiceUtils getInstance() {
        if(PhoneVoiceUtils.sPhoneVoiceUtils == null) {
            Class v0 = PhoneVoiceUtils.class;
            __monitor_enter(v0);
            try {
                if(PhoneVoiceUtils.sPhoneVoiceUtils == null) {
                    PhoneVoiceUtils.sPhoneVoiceUtils = new PhoneVoiceUtils();
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
        return PhoneVoiceUtils.sPhoneVoiceUtils;
    }

    public void hangUp() {
        LinphoneCall v0 = this.mLinphoneCore.getCurrentCall();
        if(v0 != null) {
            this.mLinphoneCore.terminateCall(v0);
        }
        else if(this.mLinphoneCore.isInConference()) {
            this.mLinphoneCore.terminateConference();
        }
        else {
            this.mLinphoneCore.terminateAllCalls();
        }
    }

    public boolean isMuted() {
        return this.mLinphoneCore.isMicMuted();
    }

    public void registerUserAuth(String arg10, String arg11, String arg12) {
        Log.e("PhoneVoiceUtils", "registerUserAuth name = " + arg10);
        Log.e("PhoneVoiceUtils", "registerUserAuth pw = " + arg11);
        Log.e("PhoneVoiceUtils", "registerUserAuth host = " + arg12);
        String v0_1 = "sip:" + arg10 + "@" + arg12;
        v1 = new StringBuilder();
        v1.append("sip:");
        v1.append(arg12);
        LinphoneAddress v1_1 = LinphoneCoreFactory.instance().createLinphoneAddress(v1.toString());
        LinphoneAddress v0_2 = LinphoneCoreFactory.instance().createLinphoneAddress(v0_1);
        LinphoneAuthInfo v10 = LinphoneCoreFactory.instance().createAuthInfo(arg10, null, arg11, null, null, arg12);
        LinphoneProxyConfig v11 = this.mLinphoneCore.createProxyConfig(v0_2.asString(), v1_1.asStringUriOnly(), v1_1.asStringUriOnly(), true);
        v11.enableAvpf(false);
        v11.setAvpfRRInterval(0);
        v11.enableQualityReporting(false);
        v11.setQualityReportingCollector(null);
        v11.setQualityReportingInterval(0);
        v11.enableRegister(true);
        this.mLinphoneCore.addProxyConfig(v11);
        this.mLinphoneCore.addAuthInfo(v10);
        this.mLinphoneCore.setDefaultProxyConfig(v11);
    }

    public LinphoneCall startSingleCallingTo(PhoneBean arg5) {
        LinphoneCall v5_2;
        LinphoneAddress v5_1;
        LinphoneCall v0 = null;
        try {
            LinphoneCore v1 = this.mLinphoneCore;
            StringBuilder v2 = new StringBuilder();
            v2.append(arg5.getUserName());
            v2.append("@");
            v2.append(arg5.getHost());
            v5_1 = v1.interpretUrl(v2.toString());
        }
        catch(LinphoneCoreException v5) {
            v5.printStackTrace();
            Log.e("startSingleCallingTo", " LinphoneCoreException0:" + v5.toString());
            return v0;
        }

        LinphoneCallParams v1_1 = this.mLinphoneCore.createCallParams(v0);
        v1_1.setVideoEnabled(false);
        try {
            v5_2 = this.mLinphoneCore.inviteAddressWithParams(v5_1, v1_1);
        }
        catch(LinphoneCoreException v5) {
            v5.printStackTrace();
            Log.e("startSingleCallingTo", " LinphoneCoreException1:" + v5.toString());
            v5_2 = v0;
        }

        return v5_2;
    }

    public LinphoneCall startVideoCall(PhoneBean arg5) {
        LinphoneCall v5_2;
        LinphoneAddress v5_1;
        LinphoneCall v0 = null;
        try {
            LinphoneCore v1 = this.mLinphoneCore;
            StringBuilder v2 = new StringBuilder();
            v2.append(arg5.getUserName());
            v2.append("@");
            v2.append(arg5.getHost());
            v5_1 = v1.interpretUrl(v2.toString());
        }
        catch(LinphoneCoreException v5) {
            v5.printStackTrace();
            Log.e("startVideoCall ", " LinphoneCoreException0:" + v5.toString());
            return v0;
        }

        LinphoneCallParams v1_1 = this.mLinphoneCore.createCallParams(v0);
        v1_1.setVideoEnabled(true);
        v1_1.enableLowBandwidth(false);
        try {
            v5_2 = this.mLinphoneCore.inviteAddressWithParams(v5_1, v1_1);
        }
        catch(LinphoneCoreException v5) {
            v5.printStackTrace();
            Log.e("startVideoCall ", " LinphoneCoreException1:" + v5.toString());
            v5_2 = v0;
        }

        return v5_2;
    }

    public void toggleMicro(boolean arg2) {
        this.mLinphoneCore.muteMic(arg2);
    }

    public void toggleSpeaker(boolean arg2) {
        this.mLinphoneCore.enableSpeaker(arg2);
    }

    public void unregister() {
        LinphoneAddress v3_2;
        LinphoneProxyConfig[] v0 = this.mLinphoneCore.getProxyConfigList();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            LinphoneProxyConfig v3 = v0[v2];
            String v4 = null;
            try {
                v3_2 = LinphoneCoreFactory.instance().createLinphoneAddress(v3.getIdentity());
            }
            catch(LinphoneCoreException v3_1) {
                v3_1.printStackTrace();
                v3_2 = ((LinphoneAddress)v4);
            }

            LinphoneManager.getLc().removeAuthInfo(LinphoneManager.getLc().findAuthInfo(v3_2.getUserName(), v4, v3_2.getDomain()));
        }
    }
}

