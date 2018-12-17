package org.linphone.core;

public interface PresenceNote {
    String getContent();

    String getLang();

    long getNativePtr();

    int setContent(String arg1);

    int setLang(String arg1);
}

