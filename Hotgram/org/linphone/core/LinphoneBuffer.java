package org.linphone.core;

public interface LinphoneBuffer {
    byte[] getContent();

    int getSize();

    void setContent(byte[] arg1);

    void setSize(int arg1);
}

