package org.telegram.customization.Model;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$User;

public class CallModel {
    boolean inComingCall;
    long time;
    private User user;
    int userId;

    public CallModel(int arg1, long arg2, boolean arg4, User arg5) {
        super();
        this.userId = arg1;
        this.time = arg2;
        this.inComingCall = arg4;
        this.user = arg5;
    }

    public long getTime() {
        return this.time;
    }

    public User getUser() {
        return MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf(this.getUserId()));
    }

    public int getUserId() {
        return this.userId;
    }

    public boolean isInComingCall() {
        return this.inComingCall;
    }

    public void setInComingCall(boolean arg1) {
        this.inComingCall = arg1;
    }

    public void setTime(long arg1) {
        this.time = arg1;
    }

    public void setUser(User arg1) {
        this.user = arg1;
    }

    public void setUserId(int arg1) {
        this.userId = arg1;
    }
}

