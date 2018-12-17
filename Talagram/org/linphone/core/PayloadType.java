package org.linphone.core;

public interface PayloadType {
    String getMime();

    int getRate();

    String getRecvFmtp();

    String getSendFmtp();

    void setRecvFmtp(String arg1);

    void setSendFmtp(String arg1);
}

