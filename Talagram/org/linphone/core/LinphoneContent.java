package org.linphone.core;

public interface LinphoneContent {
    byte[] getData();

    String getDataAsString();

    String getEncoding();

    int getExpectedSize();

    String getName();

    int getRealSize();

    String getSubtype();

    String getType();

    void setData(byte[] arg1);

    void setEncoding(String arg1);

    void setExpectedSize(int arg1);

    void setName(String arg1);

    void setStringData(String arg1);

    void setSubtype(String arg1);

    void setType(String arg1);
}

