package org.linphone.core;

public interface LinphoneNatPolicy {
    void clear();

    void enableIce(boolean arg1);

    void enableStun(boolean arg1);

    void enableTurn(boolean arg1);

    void enableUpnp(boolean arg1);

    String getStunServer();

    String getStunServerUsername();

    boolean iceEnabled();

    void setStunServer(String arg1);

    void setStunServerUsername(String arg1);

    boolean stunEnabled();

    boolean turnEnabled();

    boolean upnpEnabled();
}

