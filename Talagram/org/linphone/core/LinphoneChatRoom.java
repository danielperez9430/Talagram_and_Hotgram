package org.linphone.core;

public interface LinphoneChatRoom {
    void compose();

    LinphoneChatMessage createFileTransferMessage(LinphoneContent arg1);

    LinphoneChatMessage createLinphoneChatMessage(String arg1);

    LinphoneChatMessage createLinphoneChatMessage(String arg1, String arg2, State arg3, long arg4, boolean arg5, boolean arg6);

    void deleteHistory();

    void deleteMessage(LinphoneChatMessage arg1);

    LinphoneCall getCall();

    long getChar();

    LinphoneCore getCore();

    LinphoneChatMessage[] getHistory();

    LinphoneChatMessage[] getHistory(int arg1);

    LinphoneChatMessage[] getHistoryRange(int arg1, int arg2);

    int getHistorySize();

    LinphoneAddress getPeerAddress();

    int getUnreadMessagesCount();

    boolean isRemoteComposing();

    void markAsRead();

    void sendChatMessage(LinphoneChatMessage arg1);

    void sendMessage(String arg1);

    @Deprecated void sendMessage(LinphoneChatMessage arg1, StateListener arg2);
}

