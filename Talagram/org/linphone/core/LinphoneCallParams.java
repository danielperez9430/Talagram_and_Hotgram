package org.linphone.core;

public interface LinphoneCallParams {
    void addCustomHeader(String arg1, String arg2);

    void addCustomSdpAttribute(String arg1, String arg2);

    void addCustomSdpMediaAttribute(StreamType arg1, String arg2, String arg3);

    boolean audioMulticastEnabled();

    void clearCustomSdpAttributes();

    void clearCustomSdpMediaAttributes(StreamType arg1);

    void enableAudioMulticast(boolean arg1);

    void enableLowBandwidth(boolean arg1);

    void enableRealTimeText(boolean arg1);

    void enableVideoMulticast(boolean arg1);

    MediaDirection getAudioDirection();

    String getCustomHeader(String arg1);

    String getCustomSdpAttribute(String arg1);

    String getCustomSdpMediaAttribute(StreamType arg1, String arg2);

    MediaEncryption getMediaEncryption();

    int getPrivacy();

    VideoSize getReceivedVideoSize();

    VideoSize getSentVideoSize();

    String getSessionName();

    PayloadType getUsedAudioCodec();

    PayloadType getUsedVideoCodec();

    MediaDirection getVideoDirection();

    boolean getVideoEnabled();

    boolean isLowBandwidthEnabled();

    boolean realTimeTextEnabled();

    void setAudioBandwidth(int arg1);

    void setAudioDirection(MediaDirection arg1);

    void setMediaEnctyption(MediaEncryption arg1);

    void setPrivacy(int arg1);

    void setRecordFile(String arg1);

    void setSessionName(String arg1);

    void setVideoDirection(MediaDirection arg1);

    void setVideoEnabled(boolean arg1);

    boolean videoMulticastEnabled();
}

