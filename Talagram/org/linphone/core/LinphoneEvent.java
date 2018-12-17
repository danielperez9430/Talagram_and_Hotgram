package org.linphone.core;

public interface LinphoneEvent {
    void acceptSubscription();

    void addCustomHeader(String arg1, String arg2);

    void denySubscription(Reason arg1);

    LinphoneCore getCore();

    String getCustomHeader(String arg1);

    ErrorInfo getErrorInfo();

    String getEventName();

    Reason getReason();

    SubscriptionDir getSubscriptionDir();

    SubscriptionState getSubscriptionState();

    Object getUserContext();

    void notify(LinphoneContent arg1);

    void sendPublish(LinphoneContent arg1);

    void sendSubscribe(LinphoneContent arg1);

    void setUserContext(Object arg1);

    void terminate();

    void updatePublish(LinphoneContent arg1);

    void updateSubscribe(LinphoneContent arg1);
}

