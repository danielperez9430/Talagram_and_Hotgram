package org.linphone.core;

public interface LinphoneInfoMessage {
    void addHeader(String arg1, String arg2);

    LinphoneContent getContent();

    String getHeader(String arg1);

    void setContent(LinphoneContent arg1);
}

