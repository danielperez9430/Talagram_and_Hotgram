package org.telegram.customization.Model;

import org.telegram.messenger.UserConfig;

public class TMData {
    String message;
    long messageId;
    String phoneNumber;
    long userId;

    public TMData(String arg3, long arg4) {
        super();
        this.userId = ((long)UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId());
        this.phoneNumber = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().phone;
        this.message = arg3;
        this.messageId = arg4;
    }

    public String getMessage() {
        return this.message;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setMessage(String arg1) {
        this.message = arg1;
    }

    public void setMessageId(long arg1) {
        this.messageId = arg1;
    }

    public void setPhoneNumber(String arg1) {
        this.phoneNumber = arg1;
    }

    public void setUserId(long arg1) {
        this.userId = arg1;
    }
}

