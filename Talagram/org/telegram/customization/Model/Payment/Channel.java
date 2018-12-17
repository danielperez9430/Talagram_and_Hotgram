package org.telegram.customization.Model.Payment;

public class Channel {
    long channelId;
    String username;

    public Channel() {
        super();
    }

    public long getChannelId() {
        return this.channelId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setChannelId(long arg1) {
        this.channelId = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }
}

