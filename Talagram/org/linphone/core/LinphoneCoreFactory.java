package org.linphone.core;

import android.content.Context;
import java.io.PrintStream;
import org.linphone.tools.OpenH264DownloadHelper;

public abstract class LinphoneCoreFactory {
    private static String factoryName = "org.linphone.core.LinphoneCoreFactoryImpl";
    protected Context fcontext;
    static LinphoneCoreFactory theLinphoneCoreFactory;

    static {
    }

    public LinphoneCoreFactory() {
        super();
    }

    public abstract LinphoneAccountCreator createAccountCreator(LinphoneCore arg1, String arg2);

    public abstract LinphoneAuthInfo createAuthInfo(String arg1, String arg2, String arg3, String arg4);

    public abstract LinphoneAuthInfo createAuthInfo(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6);

    public abstract LinphoneAddress createLinphoneAddress(String arg1);

    public abstract LinphoneAddress createLinphoneAddress(String arg1, String arg2, String arg3);

    public abstract LinphoneContent createLinphoneContent(String arg1, String arg2, String arg3);

    public abstract LinphoneContent createLinphoneContent(String arg1, String arg2, byte[] arg3, String arg4);

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener arg1, String arg2, String arg3, Object arg4, Object arg5);

    public abstract LinphoneCore createLinphoneCore(LinphoneCoreListener arg1, Object arg2);

    public abstract LinphoneFriend createLinphoneFriend();

    public abstract LinphoneFriend createLinphoneFriend(String arg1);

    public abstract LpConfig createLpConfig(String arg1);

    public abstract LpConfig createLpConfigFromString(String arg1);

    public abstract OpenH264DownloadHelper createOpenH264DownloadHelper();

    public abstract PresenceActivity createPresenceActivity(PresenceActivityType arg1, String arg2);

    public abstract PresenceModel createPresenceModel();

    public abstract PresenceModel createPresenceModel(PresenceActivityType arg1, String arg2);

    public abstract PresenceModel createPresenceModel(PresenceActivityType arg1, String arg2, String arg3, String arg4);

    public abstract PresenceService createPresenceService(String arg1, PresenceBasicStatus arg2, String arg3);

    public abstract TunnelConfig createTunnelConfig();

    public abstract void enableLogCollection(boolean arg1);

    public abstract DialPlan[] getAllDialPlan();

    public static final LinphoneCoreFactory instance() {
        LinphoneCoreFactory v1_2;
        Class v0 = LinphoneCoreFactory.class;
        __monitor_enter(v0);
        try {
            if(LinphoneCoreFactory.theLinphoneCoreFactory != null) {
                goto label_22;
            }

            LinphoneCoreFactory.theLinphoneCoreFactory = Class.forName(LinphoneCoreFactory.factoryName).newInstance();
            goto label_22;
        }
        catch(Throwable v1) {
        }
        catch(Exception ) {
            try {
                PrintStream v1_1 = System.err;
                v1_1.println("Cannot instanciate factory [" + LinphoneCoreFactory.factoryName + "]");
            label_22:
                v1_2 = LinphoneCoreFactory.theLinphoneCoreFactory;
            }
            catch(Throwable v1) {
                __monitor_exit(v0);
                throw v1;
            }
        }

        __monitor_exit(v0);
        return v1_2;
    }

    public abstract void setDebugMode(boolean arg1, String arg2);

    public static void setFactoryClassName(String arg0) {
        LinphoneCoreFactory.factoryName = arg0;
    }

    public abstract void setLogCollectionPath(String arg1);

    public abstract void setLogHandler(LinphoneLogHandler arg1);
}

