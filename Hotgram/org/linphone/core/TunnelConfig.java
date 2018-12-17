package org.linphone.core;

public interface TunnelConfig {
    int getDelay();

    String getHost();

    int getPort();

    int getRemoteUdpMirrorPort();

    void setDelay(int arg1);

    void setHost(String arg1);

    void setPort(int arg1);

    void setRemoteUdpMirrorPort(int arg1);
}

