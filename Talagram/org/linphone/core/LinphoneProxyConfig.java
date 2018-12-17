package org.linphone.core;

public interface LinphoneProxyConfig {
    boolean avpfEnabled();

    void done();

    LinphoneProxyConfig edit();

    void enableAvpf(boolean arg1);

    void enablePublish(boolean arg1);

    void enableQualityReporting(boolean arg1);

    LinphoneProxyConfig enableRegister(boolean arg1);

    LinphoneAddress getAddress();

    int getAvpfRRInterval();

    String getContactParameters();

    String getContactUriParameters();

    String getCustomHeader(String arg1);

    boolean getDialEscapePlus();

    String getDialPrefix();

    String getDomain();

    Reason getError();

    ErrorInfo getErrorInfo();

    int getExpires();

    String getIdentity();

    int getPrivacy();

    String getProxy();

    int getPublishExpires();

    String getQualityReportingCollector();

    int getQualityReportingInterval();

    String getRealm();

    String getRoute();

    RegistrationState getState();

    Object getUserData();

    boolean isPhoneNumber(String arg1);

    boolean isRegistered();

    int lookupCCCFromE164(String arg1);

    int lookupCCCFromIso(String arg1);

    String normalizePhoneNumber(String arg1);

    LinphoneAddress normalizeSipUri(String arg1);

    boolean publishEnabled();

    boolean qualityReportingEnabled();

    boolean registerEnabled();

    void setAddress(LinphoneAddress arg1);

    void setAvpfRRInterval(int arg1);

    void setContactParameters(String arg1);

    void setContactUriParameters(String arg1);

    void setCustomHeader(String arg1, String arg2);

    void setDialEscapePlus(boolean arg1);

    void setDialPrefix(String arg1);

    void setExpires(int arg1);

    void setIdentity(String arg1);

    void setPrivacy(int arg1);

    void setProxy(String arg1);

    void setPublishExpires(int arg1);

    void setQualityReportingCollector(String arg1);

    void setQualityReportingInterval(int arg1);

    void setRealm(String arg1);

    void setRoute(String arg1);

    void setUserData(Object arg1);
}

