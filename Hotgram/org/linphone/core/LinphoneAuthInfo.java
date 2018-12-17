package org.linphone.core;

public interface LinphoneAuthInfo {
    LinphoneAuthInfo clone();

    String getDomain();

    String getHa1();

    String getPassword();

    String getRealm();

    String getTlsCertificate();

    String getTlsCertificatePath();

    String getTlsKey();

    String getTlsKeyPath();

    String getUserId();

    String getUsername();

    void setDomain(String arg1);

    void setHa1(String arg1);

    void setPassword(String arg1);

    void setRealm(String arg1);

    void setTlsCertificate(String arg1);

    void setTlsCertificatePath(String arg1);

    void setTlsKey(String arg1);

    void setTlsKeyPath(String arg1);

    void setUserId(String arg1);

    void setUsername(String arg1);
}

