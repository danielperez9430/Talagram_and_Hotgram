package org.linphone.core;

import java.nio.ByteBuffer;

public interface LinphoneCoreListener {
    @Deprecated void authInfoRequested(LinphoneCore arg1, String arg2, String arg3, String arg4);

    void authenticationRequested(LinphoneCore arg1, LinphoneAuthInfo arg2, AuthMethod arg3);

    void callEncryptionChanged(LinphoneCore arg1, LinphoneCall arg2, boolean arg3, String arg4);

    void callState(LinphoneCore arg1, LinphoneCall arg2, State arg3, String arg4);

    void callStatsUpdated(LinphoneCore arg1, LinphoneCall arg2, LinphoneCallStats arg3);

    void configuringStatus(LinphoneCore arg1, RemoteProvisioningState arg2, String arg3);

    @Deprecated void displayMessage(LinphoneCore arg1, String arg2);

    @Deprecated void displayStatus(LinphoneCore arg1, String arg2);

    @Deprecated void displayWarning(LinphoneCore arg1, String arg2);

    void dtmfReceived(LinphoneCore arg1, LinphoneCall arg2, int arg3);

    void ecCalibrationStatus(LinphoneCore arg1, EcCalibratorStatus arg2, int arg3, Object arg4);

    void fileTransferProgressIndication(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, int arg4);

    void fileTransferRecv(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, byte[] arg4, int arg5);

    int fileTransferSend(LinphoneCore arg1, LinphoneChatMessage arg2, LinphoneContent arg3, ByteBuffer arg4, int arg5);

    void friendListCreated(LinphoneCore arg1, LinphoneFriendList arg2);

    void friendListRemoved(LinphoneCore arg1, LinphoneFriendList arg2);

    void globalState(LinphoneCore arg1, GlobalState arg2, String arg3);

    void infoReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneInfoMessage arg3);

    void isComposingReceived(LinphoneCore arg1, LinphoneChatRoom arg2);

    void messageReceived(LinphoneCore arg1, LinphoneChatRoom arg2, LinphoneChatMessage arg3);

    void newSubscriptionRequest(LinphoneCore arg1, LinphoneFriend arg2, String arg3);

    void notifyPresenceReceived(LinphoneCore arg1, LinphoneFriend arg2);

    void notifyReceived(LinphoneCore arg1, LinphoneCall arg2, LinphoneAddress arg3, byte[] arg4);

    void notifyReceived(LinphoneCore arg1, LinphoneEvent arg2, String arg3, LinphoneContent arg4);

    void publishStateChanged(LinphoneCore arg1, LinphoneEvent arg2, PublishState arg3);

    void registrationState(LinphoneCore arg1, LinphoneProxyConfig arg2, RegistrationState arg3, String arg4);

    @Deprecated void show(LinphoneCore arg1);

    void subscriptionStateChanged(LinphoneCore arg1, LinphoneEvent arg2, SubscriptionState arg3);

    void transferState(LinphoneCore arg1, LinphoneCall arg2, State arg3);

    void uploadProgressIndication(LinphoneCore arg1, int arg2, int arg3);

    void uploadStateChanged(LinphoneCore arg1, LogCollectionUploadState arg2, String arg3);
}

