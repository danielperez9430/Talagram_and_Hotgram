package org.telegram.customization.Model;

public class DialogStatus {
    long dialogId;
    boolean hasHotgram;
    boolean inviteSent;
    boolean isFilter;
    long lastUpdate;
    String username;

    public DialogStatus() {
        super();
    }

    public DialogStatus(long arg1, boolean arg3, boolean arg4) {
        super();
        this.dialogId = arg1;
        this.hasHotgram = arg3;
        this.inviteSent = arg4;
    }

    public DialogStatus(long arg1, boolean arg3, boolean arg4, boolean arg5) {
        super();
        this.dialogId = arg1;
        this.hasHotgram = arg3;
        this.inviteSent = arg4;
        this.isFilter = arg5;
    }

    public long getDialogId() {
        return this.dialogId;
    }

    public long getLastUpdate() {
        return this.lastUpdate;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isFilter() {
        return this.isFilter;
    }

    public boolean isHasHotgram() {
        return this.hasHotgram;
    }

    public boolean isInviteSent() {
        return this.inviteSent;
    }

    public void setDialogId(long arg1) {
        this.dialogId = arg1;
    }

    public void setFilter(boolean arg1) {
        this.isFilter = arg1;
    }

    public void setHasHotgram(boolean arg1) {
        this.hasHotgram = arg1;
    }

    public void setInviteSent(boolean arg1) {
        this.inviteSent = arg1;
    }

    public void setLastUpdate(long arg1) {
        this.lastUpdate = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }
}

