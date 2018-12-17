package org.linphone.core;

import java.util.Vector;
import org.linphone.mediastream.Factory;
import org.linphone.mediastream.video.AndroidVideoWindowImpl;

public interface LinphoneCore {
    public final class AdaptiveRateAlgorithm {
        public static final AdaptiveRateAlgorithm Simple;
        public static final AdaptiveRateAlgorithm Stateful;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            AdaptiveRateAlgorithm.values = new Vector();
            AdaptiveRateAlgorithm.Simple = new AdaptiveRateAlgorithm(0, "Simple");
            AdaptiveRateAlgorithm.Stateful = new AdaptiveRateAlgorithm(1, "Stateful");
        }

        private AdaptiveRateAlgorithm(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            AdaptiveRateAlgorithm.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static AdaptiveRateAlgorithm fromString(String arg3) {
            int v0;
            for(v0 = 0; v0 < AdaptiveRateAlgorithm.values.size(); ++v0) {
                Object v1 = AdaptiveRateAlgorithm.values.elementAt(v0);
                if(((AdaptiveRateAlgorithm)v1).mStringValue.equalsIgnoreCase(arg3)) {
                    return ((AdaptiveRateAlgorithm)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("AdaptiveRateAlgorithm not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class AuthMethod {
        public static AuthMethod AuthMethodHttpDigest;
        public static AuthMethod AuthMethodTls;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            AuthMethod.values = new Vector();
            AuthMethod.AuthMethodHttpDigest = new AuthMethod(0, "AuthMethodHttpDigest");
            AuthMethod.AuthMethodTls = new AuthMethod(1, "AuthMethodTls");
        }

        private AuthMethod(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            AuthMethod.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static AuthMethod fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < AuthMethod.values.size(); ++v0) {
                Object v1 = AuthMethod.values.elementAt(v0);
                if(((AuthMethod)v1).mValue == arg3) {
                    return ((AuthMethod)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("auth method not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class EcCalibratorStatus {
        public static final int DONE_NO_ECHO_STATUS = 3;
        public static final int DONE_STATUS = 1;
        public static EcCalibratorStatus Done = null;
        public static EcCalibratorStatus DoneNoEcho = null;
        public static final int FAILED_STATUS = 2;
        public static EcCalibratorStatus Failed;
        public static final int IN_PROGRESS_STATUS;
        public static EcCalibratorStatus InProgress;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            EcCalibratorStatus.values = new Vector();
            EcCalibratorStatus.InProgress = new EcCalibratorStatus(0, "InProgress");
            EcCalibratorStatus.Done = new EcCalibratorStatus(1, "Done");
            EcCalibratorStatus.Failed = new EcCalibratorStatus(2, "Failed");
            EcCalibratorStatus.DoneNoEcho = new EcCalibratorStatus(3, "DoneNoEcho");
        }

        private EcCalibratorStatus(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            EcCalibratorStatus.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static EcCalibratorStatus fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < EcCalibratorStatus.values.size(); ++v0) {
                Object v1 = EcCalibratorStatus.values.elementAt(v0);
                if(((EcCalibratorStatus)v1).mValue == arg3) {
                    return ((EcCalibratorStatus)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("status not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }

        public int value() {
            return this.mValue;
        }
    }

    @Deprecated public class FirewallPolicy {
        public static FirewallPolicy NoFirewall;
        public static FirewallPolicy UseIce;
        public static FirewallPolicy UseNatAddress;
        public static FirewallPolicy UseStun;
        public static FirewallPolicy UseUpnp;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            FirewallPolicy.values = new Vector();
            FirewallPolicy.NoFirewall = new FirewallPolicy(0, "NoFirewall");
            FirewallPolicy.UseNatAddress = new FirewallPolicy(1, "UseNatAddress");
            FirewallPolicy.UseStun = new FirewallPolicy(2, "UseStun");
            FirewallPolicy.UseIce = new FirewallPolicy(3, "UseIce");
            FirewallPolicy.UseUpnp = new FirewallPolicy(4, "UseUpnp");
        }

        private FirewallPolicy(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            FirewallPolicy.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static FirewallPolicy fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < FirewallPolicy.values.size(); ++v0) {
                Object v1 = FirewallPolicy.values.elementAt(v0);
                if(((FirewallPolicy)v1).mValue == arg3) {
                    return ((FirewallPolicy)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }

        public int value() {
            return this.mValue;
        }
    }

    public class GlobalState {
        public static GlobalState GlobalConfiguring;
        public static GlobalState GlobalOff;
        public static GlobalState GlobalOn;
        public static GlobalState GlobalShutdown;
        public static GlobalState GlobalStartup;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            GlobalState.values = new Vector();
            GlobalState.GlobalOff = new GlobalState(0, "GlobalOff");
            GlobalState.GlobalStartup = new GlobalState(1, "GlobalStartup");
            GlobalState.GlobalOn = new GlobalState(2, "GlobalOn");
            GlobalState.GlobalShutdown = new GlobalState(3, "GlobalShutdown");
            GlobalState.GlobalConfiguring = new GlobalState(4, "GlobalConfiguring");
        }

        private GlobalState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            GlobalState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static GlobalState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < GlobalState.values.size(); ++v0) {
                Object v1 = GlobalState.values.elementAt(v0);
                if(((GlobalState)v1).mValue == arg3) {
                    return ((GlobalState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public final class LinphoneLimeState {
        public static final LinphoneLimeState Disabled;
        public static final LinphoneLimeState Mandatory;
        public static final LinphoneLimeState Preferred;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            LinphoneLimeState.values = new Vector();
            LinphoneLimeState.Disabled = new LinphoneLimeState(0, "None");
            LinphoneLimeState.Mandatory = new LinphoneLimeState(1, "Mandatory");
            LinphoneLimeState.Preferred = new LinphoneLimeState(2, "Preferred");
        }

        private LinphoneLimeState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            LinphoneLimeState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static LinphoneLimeState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < LinphoneLimeState.values.size(); ++v0) {
                Object v1 = LinphoneLimeState.values.elementAt(v0);
                if(((LinphoneLimeState)v1).mValue == arg3) {
                    return ((LinphoneLimeState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("LinphoneLimeState not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class LogCollectionUploadState {
        public static LogCollectionUploadState LogCollectionUploadStateDelivered;
        public static LogCollectionUploadState LogCollectionUploadStateInProgress;
        public static LogCollectionUploadState LogCollectionUploadStateNotDelivered;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            LogCollectionUploadState.values = new Vector();
            LogCollectionUploadState.LogCollectionUploadStateInProgress = new LogCollectionUploadState(0, "LinphoneCoreLogCollectionUploadStateInProgress");
            LogCollectionUploadState.LogCollectionUploadStateDelivered = new LogCollectionUploadState(1, "LinphoneCoreLogCollectionUploadStateDelivered");
            LogCollectionUploadState.LogCollectionUploadStateNotDelivered = new LogCollectionUploadState(2, "LinphoneCoreLogCollectionUploadStateNotDelivered");
        }

        private LogCollectionUploadState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            LogCollectionUploadState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static LogCollectionUploadState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < LogCollectionUploadState.values.size(); ++v0) {
                Object v1 = LogCollectionUploadState.values.elementAt(v0);
                if(((LogCollectionUploadState)v1).mValue == arg3) {
                    return ((LogCollectionUploadState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public final class MediaDirection {
        public static final MediaDirection Inactive;
        public static final MediaDirection Invalid;
        public static final MediaDirection RecvOnly;
        public static final MediaDirection SendOnly;
        public static final MediaDirection SendRecv;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            MediaDirection.values = new Vector();
            MediaDirection.Invalid = new MediaDirection(-1, "Invalid");
            MediaDirection.Inactive = new MediaDirection(0, "Inactive");
            MediaDirection.SendOnly = new MediaDirection(1, "SendOnly");
            MediaDirection.RecvOnly = new MediaDirection(2, "RecvOnly");
            MediaDirection.SendRecv = new MediaDirection(3, "SendRecv");
        }

        private MediaDirection(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            MediaDirection.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static MediaDirection fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < MediaDirection.values.size(); ++v0) {
                Object v1 = MediaDirection.values.elementAt(v0);
                if(((MediaDirection)v1).mValue == arg3) {
                    return ((MediaDirection)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("MediaDirection not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public final class MediaEncryption {
        public static final MediaEncryption DTLS;
        public static final MediaEncryption None;
        public static final MediaEncryption SRTP;
        public static final MediaEncryption ZRTP;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            MediaEncryption.values = new Vector();
            MediaEncryption.None = new MediaEncryption(0, "None");
            MediaEncryption.SRTP = new MediaEncryption(1, "SRTP");
            MediaEncryption.ZRTP = new MediaEncryption(2, "ZRTP");
            MediaEncryption.DTLS = new MediaEncryption(3, "DTLS");
        }

        private MediaEncryption(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            MediaEncryption.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static MediaEncryption fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < MediaEncryption.values.size(); ++v0) {
                Object v1 = MediaEncryption.values.elementAt(v0);
                if(((MediaEncryption)v1).mValue == arg3) {
                    return ((MediaEncryption)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("MediaEncryption not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class RegistrationState {
        public static RegistrationState RegistrationCleared;
        public static RegistrationState RegistrationFailed;
        public static RegistrationState RegistrationNone;
        public static RegistrationState RegistrationOk;
        public static RegistrationState RegistrationProgress;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            RegistrationState.values = new Vector();
            RegistrationState.RegistrationNone = new RegistrationState(0, "RegistrationNone");
            RegistrationState.RegistrationProgress = new RegistrationState(1, "RegistrationProgress");
            RegistrationState.RegistrationOk = new RegistrationState(2, "RegistrationOk");
            RegistrationState.RegistrationCleared = new RegistrationState(3, "RegistrationCleared");
            RegistrationState.RegistrationFailed = new RegistrationState(4, "RegistrationFailed");
        }

        private RegistrationState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            RegistrationState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static RegistrationState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < RegistrationState.values.size(); ++v0) {
                Object v1 = RegistrationState.values.elementAt(v0);
                if(((RegistrationState)v1).mValue == arg3) {
                    return ((RegistrationState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class RemoteProvisioningState {
        public static RemoteProvisioningState ConfiguringFailed;
        public static RemoteProvisioningState ConfiguringSkipped;
        public static RemoteProvisioningState ConfiguringSuccessful;
        private final String mStringValue;
        private final int mValue;
        private static Vector values;

        static {
            RemoteProvisioningState.values = new Vector();
            RemoteProvisioningState.ConfiguringSuccessful = new RemoteProvisioningState(0, "ConfiguringSuccessful");
            RemoteProvisioningState.ConfiguringFailed = new RemoteProvisioningState(1, "ConfiguringFailed");
            RemoteProvisioningState.ConfiguringSkipped = new RemoteProvisioningState(2, "ConfiguringSkipped");
        }

        private RemoteProvisioningState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            RemoteProvisioningState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static RemoteProvisioningState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < RemoteProvisioningState.values.size(); ++v0) {
                Object v1 = RemoteProvisioningState.values.elementAt(v0);
                if(((RemoteProvisioningState)v1).mValue == arg3) {
                    return ((RemoteProvisioningState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("state not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public final class StreamType {
        public static final StreamType Audio;
        public static final StreamType Text;
        public static final StreamType Unknown;
        public static final StreamType Video;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            StreamType.values = new Vector();
            StreamType.Audio = new StreamType(0, "Audio");
            StreamType.Video = new StreamType(1, "Video");
            StreamType.Text = new StreamType(2, "Text");
            StreamType.Unknown = new StreamType(3, "Unknown");
        }

        private StreamType(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            StreamType.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static StreamType fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < StreamType.values.size(); ++v0) {
                Object v1 = StreamType.values.elementAt(v0);
                if(((StreamType)v1).mValue == arg3) {
                    return ((StreamType)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("StreamType not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public class Transports {
        public int tcp;
        public int tls;
        public int udp;

        public Transports() {
            super();
        }

        public Transports(Transports arg2) {
            super();
            this.udp = arg2.udp;
            this.tcp = arg2.tcp;
            this.tls = arg2.tls;
        }

        public String toString() {
            return "udp[" + this.udp + "] tcp[" + this.tcp + "] tls[" + this.tls + "]";
        }
    }

    public enum TunnelMode {
        public static final enum TunnelMode auto;
        public static final enum TunnelMode disable;
        public static final enum TunnelMode enable;
        private final int value;

        static {
            TunnelMode.disable = new TunnelMode("disable", 0, 0);
            TunnelMode.enable = new TunnelMode("enable", 1, 1);
            TunnelMode.auto = new TunnelMode("auto", 2, 2);
            TunnelMode.$VALUES = new TunnelMode[]{TunnelMode.disable, TunnelMode.enable, TunnelMode.auto};
        }

        private TunnelMode(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.value = arg3;
        }

        public static int enumToInt(TunnelMode arg0) {
            return arg0.value;
        }

        public static TunnelMode intToEnum(int arg0) {
            switch(arg0) {
                case 0: {
                    goto label_7;
                }
                case 1: {
                    goto label_5;
                }
                case 2: {
                    goto label_3;
                }
            }

            return TunnelMode.disable;
        label_3:
            return TunnelMode.auto;
        label_5:
            return TunnelMode.enable;
        label_7:
            return TunnelMode.disable;
        }

        public static TunnelMode valueOf(String arg1) {
            return Enum.valueOf(TunnelMode.class, arg1);
        }

        public static TunnelMode[] values() {
            // Method was not decompiled
        }
    }

    public class UpnpState {
        public static UpnpState Adding;
        public static UpnpState Blacklisted;
        public static UpnpState Idle;
        public static UpnpState Ko;
        public static UpnpState NotAvailable;
        public static UpnpState Ok;
        public static UpnpState Pending;
        public static UpnpState Removing;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            UpnpState.values = new Vector();
            UpnpState.Idle = new UpnpState(0, "Idle");
            UpnpState.Pending = new UpnpState(1, "Pending");
            UpnpState.Adding = new UpnpState(2, "Adding");
            UpnpState.Removing = new UpnpState(3, "Removing");
            UpnpState.NotAvailable = new UpnpState(4, "Not available");
            UpnpState.Ok = new UpnpState(5, "Ok");
            UpnpState.Ko = new UpnpState(6, "Ko");
            UpnpState.Blacklisted = new UpnpState(7, "Blacklisted");
        }

        private UpnpState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            UpnpState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static UpnpState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < UpnpState.values.size(); ++v0) {
                Object v1 = UpnpState.values.elementAt(v0);
                if(((UpnpState)v1).mValue == arg3) {
                    return ((UpnpState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("UpnpState not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    void acceptCall(LinphoneCall arg1);

    void acceptCallUpdate(LinphoneCall arg1, LinphoneCallParams arg2);

    void acceptCallWithParams(LinphoneCall arg1, LinphoneCallParams arg2);

    boolean acceptEarlyMedia(LinphoneCall arg1);

    boolean acceptEarlyMediaWithParams(LinphoneCall arg1, LinphoneCallParams arg2);

    void addAllToConference();

    void addAuthInfo(LinphoneAuthInfo arg1);

    void addFriend(LinphoneFriend arg1);

    void addFriendList(LinphoneFriendList arg1);

    void addListener(LinphoneCoreListener arg1);

    void addProxyConfig(LinphoneProxyConfig arg1);

    void addToConference(LinphoneCall arg1);

    @Deprecated void adjustSoftwareVolume(int arg1);

    boolean audioMulticastEnabled();

    boolean chatEnabled();

    void clearAuthInfos();

    void clearCallLogs();

    void clearProxyConfigs();

    LinphoneCallParams createCallParams(LinphoneCall arg1);

    LinphoneConference createConference(LinphoneConferenceParams arg1);

    LinphoneFriend createFriend();

    LinphoneFriend createFriendWithAddress(String arg1);

    LinphoneInfoMessage createInfoMessage();

    LinphoneFriendList createLinphoneFriendList();

    LinphonePlayer createLocalPlayer(AndroidVideoWindowImpl arg1);

    LinphoneNatPolicy createNatPolicy();

    LinphoneProxyConfig createProxyConfig();

    LinphoneProxyConfig createProxyConfig(String arg1, String arg2, String arg3, boolean arg4);

    LinphoneEvent createPublish(LinphoneAddress arg1, String arg2, int arg3);

    LinphoneEvent createSubscribe(LinphoneAddress arg1, String arg2, int arg3);

    void declineCall(LinphoneCall arg1, Reason arg2);

    void deferCallUpdate(LinphoneCall arg1);

    void destroy();

    void disableChat(Reason arg1);

    boolean dnsSrvEnabled();

    void enableAdaptiveRateControl(boolean arg1);

    void enableAudioMulticast(boolean arg1);

    void enableChat();

    void enableDnsSrv(boolean arg1);

    void enableEchoCancellation(boolean arg1);

    void enableEchoLimiter(boolean arg1);

    void enableIpv6(boolean arg1);

    void enableKeepAlive(boolean arg1);

    void enableOpenH264(boolean arg1);

    void enablePayloadType(PayloadType arg1, boolean arg2);

    void enableSdp200Ack(boolean arg1);

    void enableSpeaker(boolean arg1);

    void enableVideo(boolean arg1, boolean arg2);

    void enableVideoMulticast(boolean arg1);

    boolean enterConference();

    LinphoneAuthInfo findAuthInfo(String arg1, String arg2, String arg3);

    LinphoneCall findCallFromUri(String arg1);

    LinphoneFriend findFriendByAddress(String arg1);

    PayloadType findPayloadType(String arg1);

    PayloadType findPayloadType(String arg1, int arg2);

    PayloadType findPayloadType(String arg1, int arg2, int arg3);

    AdaptiveRateAlgorithm getAdaptiveRateAlgorithm();

    PayloadType[] getAudioCodecs();

    int getAudioDscp();

    String getAudioMulticastAddr();

    int getAudioMulticastTtl();

    LinphoneAuthInfo[] getAuthInfosList();

    LinphoneCallLog[] getCallLogs();

    LinphoneCall[] getCalls();

    int getCallsNb();

    LinphoneChatRoom getChatRoom(LinphoneAddress arg1);

    LinphoneChatRoom[] getChatRooms();

    LinphoneConference getConference();

    int getConferenceSize();

    LpConfig getConfig();

    LinphoneCall getCurrentCall();

    LinphoneProxyConfig getDefaultProxyConfig();

    int getDownloadBandwidth();

    String getFileTransferServer();

    @Deprecated FirewallPolicy getFirewallPolicy();

    LinphoneFriend[] getFriendList();

    LinphoneFriendList[] getFriendLists();

    GlobalState getGlobalState();

    String getHttpProxyHost();

    int getHttpProxyPort();

    LinphoneCallLog getLastOutgoingCallLog();

    LinphoneLimeState getLimeEncryption();

    Factory getMSFactory();

    int getMaxCalls();

    MediaEncryption getMediaEncryption();

    int getMissedCallsCount();

    int getMtu();

    LinphoneNatPolicy getNatPolicy();

    int getNortpTimeout();

    LinphoneChatRoom getOrCreateChatRoom(String arg1);

    int getPayloadTypeBitrate(PayloadType arg1);

    int getPayloadTypeNumber(PayloadType arg1);

    int getPlayLevel();

    float getPlaybackGain();

    float getPreferredFramerate();

    VideoSize getPreferredVideoSize();

    @Deprecated OnlineStatus getPresenceInfo();

    PresenceModel getPresenceModel();

    String getPrimaryContact();

    String getPrimaryContactDisplayName();

    String getPrimaryContactUsername();

    String getProvisioningUri();

    LinphoneProxyConfig[] getProxyConfigList();

    LinphoneAddress getRemoteAddress();

    String getRemoteRingbackTone();

    String getRing();

    Transports getSignalingTransportPorts();

    int getSipDscp();

    int getSipTransportTimeout();

    String getStunServer();

    String[] getSupportedVideoSizes();

    String getTlsCertificate();

    String getTlsCertificatePath();

    String getTlsKey();

    String getTlsKeyPath();

    int getUploadBandwidth();

    String getUpnpExternalIpaddress();

    UpnpState getUpnpState();

    boolean getUseRfc2833ForDtmfs();

    boolean getUseSipInfoForDtmfs();

    String getVersion();

    boolean getVideoAutoAcceptPolicy();

    boolean getVideoAutoInitiatePolicy();

    PayloadType[] getVideoCodecs();

    int getVideoDevice();

    int getVideoDscp();

    String getVideoMulticastAddr();

    int getVideoMulticastTtl();

    String getVideoPreset();

    boolean hasBuiltInEchoCanceler();

    boolean hasCrappyOpenGL();

    LinphoneAddress interpretUrl(String arg1);

    LinphoneCall invite(String arg1);

    LinphoneCall invite(LinphoneAddress arg1);

    LinphoneCall inviteAddressWithParams(LinphoneAddress arg1, LinphoneCallParams arg2);

    boolean isAdaptiveRateControlEnabled();

    boolean isEchoCancellationEnabled();

    boolean isEchoLimiterEnabled();

    boolean isInComingInvitePending();

    boolean isInConference();

    boolean isIncall();

    boolean isIpv6Enabled();

    boolean isKeepAliveEnabled();

    boolean isLimeEncryptionAvailable();

    boolean isMediaEncryptionMandatory();

    boolean isMicMuted();

    @Deprecated boolean isMyself(String arg1);

    boolean isNetworkReachable();

    boolean isPayloadTypeEnabled(PayloadType arg1);

    boolean isSdp200AckEnabled();

    boolean isSpeakerEnabled();

    boolean isTunnelAvailable();

    boolean isVCardSupported();

    boolean isVideoEnabled();

    boolean isVideoSupported();

    void iterate();

    void leaveConference();

    boolean mediaEncryptionSupported(MediaEncryption arg1);

    void migrateCallLogs();

    int migrateToMultiTransport();

    void muteMic(boolean arg1);

    boolean needsEchoCalibration();

    boolean openH264Enabled();

    boolean pauseAllCalls();

    boolean pauseCall(LinphoneCall arg1);

    boolean payloadTypeIsVbr(PayloadType arg1);

    void playDtmf(char arg1, int arg2);

    LinphoneEvent publish(LinphoneAddress arg1, String arg2, int arg3, LinphoneContent arg4);

    void refreshRegisters();

    void reloadMsPlugins(String arg1);

    void reloadSoundDevices();

    void removeAuthInfo(LinphoneAuthInfo arg1);

    void removeCallLog(LinphoneCallLog arg1);

    void removeFriend(LinphoneFriend arg1);

    void removeFriendList(LinphoneFriendList arg1);

    void removeFromConference(LinphoneCall arg1);

    void removeListener(LinphoneCoreListener arg1);

    void removeProxyConfig(LinphoneProxyConfig arg1);

    void resetLogCollection();

    void resetMissedCallsCount();

    boolean resumeCall(LinphoneCall arg1);

    void sendDtmf(char arg1);

    void sendDtmfs(String arg1);

    void setAdaptiveRateAlgorithm(AdaptiveRateAlgorithm arg1);

    void setAudioCodecs(PayloadType[] arg1);

    void setAudioDscp(int arg1);

    void setAudioJittcomp(int arg1);

    void setAudioMulticastAddr(String arg1);

    void setAudioMulticastTtl(int arg1);

    void setAudioPort(int arg1);

    void setAudioPortRange(int arg1, int arg2);

    void setCallErrorTone(Reason arg1, String arg2);

    void setCallLogsDatabasePath(String arg1);

    void setChatDatabasePath(String arg1);

    void setContext(Object arg1);

    void setCpuCount(int arg1);

    void setDefaultProxyConfig(LinphoneProxyConfig arg1);

    void setDefaultSoundDevices();

    void setDeviceRotation(int arg1);

    void setDnsServers(String[] arg1);

    void setDownloadBandwidth(int arg1);

    void setDownloadPtime(int arg1);

    void setFileTransferServer(String arg1);

    @Deprecated void setFirewallPolicy(FirewallPolicy arg1);

    void setFriendsDatabasePath(String arg1);

    void setHttpProxyHost(String arg1);

    void setHttpProxyPort(int arg1);

    void setInCallTimeout(int arg1);

    void setIncomingTimeout(int arg1);

    void setLimeEncryption(LinphoneLimeState arg1);

    void setMaxCalls(int arg1);

    void setMediaEncryption(MediaEncryption arg1);

    void setMediaEncryptionMandatory(boolean arg1);

    void setMediaNetworkReachable(boolean arg1);

    void setMicrophoneGain(float arg1);

    void setMtu(int arg1);

    void setNatPolicy(LinphoneNatPolicy arg1);

    void setNetworkReachable(boolean arg1);

    void setNortpTimeout(int arg1);

    void setPayloadTypeBitrate(PayloadType arg1, int arg2);

    void setPayloadTypeNumber(PayloadType arg1, int arg2);

    void setPlayFile(String arg1);

    void setPlayLevel(int arg1);

    void setPlaybackGain(float arg1);

    void setPreferredFramerate(float arg1);

    void setPreferredVideoSize(VideoSize arg1);

    void setPreferredVideoSizeByName(String arg1);

    @Deprecated void setPresenceInfo(int arg1, String arg2, OnlineStatus arg3);

    void setPresenceModel(PresenceModel arg1);

    void setPreviewWindow(Object arg1);

    void setPrimaryContact(String arg1);

    void setPrimaryContact(String arg1, String arg2);

    void setProvisioningUri(String arg1);

    void setRemoteRingbackTone(String arg1);

    void setRing(String arg1);

    void setRingback(String arg1);

    void setRootCA(String arg1);

    void setRootCAData(String arg1);

    void setSignalingTransportPorts(Transports arg1);

    void setSipDscp(int arg1);

    void setSipNetworkReachable(boolean arg1);

    void setSipTransportTimeout(int arg1);

    void setStaticPicture(String arg1);

    void setStunServer(String arg1);

    void setTlsCertificate(String arg1);

    void setTlsCertificatePath(String arg1);

    void setTlsKey(String arg1);

    void setTlsKeyPath(String arg1);

    void setTone(ToneID arg1, String arg2);

    void setUploadBandwidth(int arg1);

    void setUploadPtime(int arg1);

    void setUseRfc2833ForDtmfs(boolean arg1);

    void setUseSipInfoForDtmfs(boolean arg1);

    void setUserAgent(String arg1, String arg2);

    void setUserCertificatesPath(String arg1);

    void setVerifyServerCN(boolean arg1);

    void setVerifyServerCertificates(boolean arg1);

    void setVideoCodecs(PayloadType[] arg1);

    void setVideoDevice(int arg1);

    void setVideoDscp(int arg1);

    void setVideoJittcomp(int arg1);

    void setVideoMulticastAddr(String arg1);

    void setVideoMulticastTtl(int arg1);

    void setVideoPolicy(boolean arg1, boolean arg2);

    void setVideoPort(int arg1);

    void setVideoPortRange(int arg1, int arg2);

    void setVideoPreset(String arg1);

    void setVideoWindow(Object arg1);

    void setZrtpSecretsCache(String arg1);

    boolean soundResourcesLocked();

    void startConferenceRecording(String arg1);

    void startEchoCalibration(LinphoneCoreListener arg1);

    int startEchoTester(int arg1);

    LinphoneCall startReferedCall(LinphoneCall arg1, LinphoneCallParams arg2);

    void stopConferenceRecording();

    void stopDtmf();

    int stopEchoTester();

    void stopRinging();

    LinphoneEvent subscribe(LinphoneAddress arg1, String arg2, int arg3, LinphoneContent arg4);

    void terminateAllCalls();

    void terminateCall(LinphoneCall arg1);

    void terminateConference();

    void transferCall(LinphoneCall arg1, String arg2);

    void transferCallToAnother(LinphoneCall arg1, LinphoneCall arg2);

    void tunnelAddServer(TunnelConfig arg1);

    void tunnelAddServerAndMirror(String arg1, int arg2, int arg3, int arg4);

    @Deprecated void tunnelAutoDetect();

    void tunnelCleanServers();

    @Deprecated void tunnelEnable(boolean arg1);

    void tunnelEnableSip(boolean arg1);

    TunnelMode tunnelGetMode();

    TunnelConfig[] tunnelGetServers();

    void tunnelSetHttpProxy(String arg1, int arg2, String arg3, String arg4);

    void tunnelSetMode(TunnelMode arg1);

    boolean tunnelSipEnabled();

    int updateCall(LinphoneCall arg1, LinphoneCallParams arg2);

    void uploadLogCollection();

    boolean upnpAvailable();

    boolean videoMulticastEnabled();
}

