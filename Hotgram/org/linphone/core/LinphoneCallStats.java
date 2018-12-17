package org.linphone.core;

import java.util.Vector;

public interface LinphoneCallStats {
    public class IceState {
        public static IceState Failed;
        public static IceState HostConnection;
        public static IceState InProgress;
        public static IceState NotActivated;
        public static IceState ReflexiveConnection;
        public static IceState RelayConnection;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            IceState.values = new Vector();
            IceState.NotActivated = new IceState(0, "Not activated");
            IceState.Failed = new IceState(1, "Failed");
            IceState.InProgress = new IceState(2, "In progress");
            IceState.HostConnection = new IceState(3, "Host connection");
            IceState.ReflexiveConnection = new IceState(4, "Reflexive connection");
            IceState.RelayConnection = new IceState(5, "Relay connection");
        }

        private IceState(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            IceState.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static IceState fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < IceState.values.size(); ++v0) {
                Object v1 = IceState.values.elementAt(v0);
                if(((IceState)v1).mValue == arg3) {
                    return ((IceState)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("IceState not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    public enum LinphoneAddressFamily {
        public static final enum LinphoneAddressFamily INET;
        public static final enum LinphoneAddressFamily INET_6;
        public static final enum LinphoneAddressFamily UNSPEC;
        private int value;

        static {
            LinphoneAddressFamily.INET = new LinphoneAddressFamily("INET", 0, 0);
            LinphoneAddressFamily.INET_6 = new LinphoneAddressFamily("INET_6", 1, 1);
            LinphoneAddressFamily.UNSPEC = new LinphoneAddressFamily("UNSPEC", 2, 2);
            LinphoneAddressFamily.$VALUES = new LinphoneAddressFamily[]{LinphoneAddressFamily.INET, LinphoneAddressFamily.INET_6, LinphoneAddressFamily.UNSPEC};
        }

        private LinphoneAddressFamily(String arg1, int arg2, int arg3) {
            super(arg1, arg2);
            this.value = arg3;
        }

        public int getInt() {
            return this.value;
        }

        public static LinphoneAddressFamily valueOf(String arg1) {
            return Enum.valueOf(LinphoneAddressFamily.class, arg1);
        }

        public static LinphoneAddressFamily[] values() {
            // Method was not decompiled
        }
    }

    public class MediaType {
        public static MediaType Audio;
        public static MediaType Text;
        public static MediaType Video;
        private final String mStringValue;
        protected final int mValue;
        private static Vector values;

        static {
            MediaType.values = new Vector();
            MediaType.Audio = new MediaType(0, "Audio");
            MediaType.Video = new MediaType(1, "Video");
            MediaType.Text = new MediaType(2, "Text");
        }

        private MediaType(int arg1, String arg2) {
            super();
            this.mValue = arg1;
            MediaType.values.addElement(this);
            this.mStringValue = arg2;
        }

        public static MediaType fromInt(int arg3) {
            int v0;
            for(v0 = 0; v0 < MediaType.values.size(); ++v0) {
                Object v1 = MediaType.values.elementAt(v0);
                if(((MediaType)v1).mValue == arg3) {
                    return ((MediaType)v1);
                }
            }

            StringBuilder v1_1 = new StringBuilder();
            v1_1.append("MediaType not found [");
            v1_1.append(arg3);
            v1_1.append("]");
            throw new RuntimeException(v1_1.toString());
        }

        public String toString() {
            return this.mStringValue;
        }
    }

    String getDecoderName(PayloadType arg1);

    float getDownloadBandwidth();

    String getEncoderName(PayloadType arg1);

    IceState getIceState();

    int getIpFamilyOfRemote();

    float getJitterBufferSize();

    long getLatePacketsCumulativeNumber();

    float getLocalLateRate();

    float getLocalLossRate();

    MediaType getMediaType();

    float getReceiverInterarrivalJitter();

    float getReceiverLossRate();

    float getRoundTripDelay();

    float getSenderInterarrivalJitter();

    float getSenderLossRate();

    float getUploadBandwidth();
}

