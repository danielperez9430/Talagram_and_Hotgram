package org.telegram.customization.Model;

public class JoinChannelModel {
    long expireTime;
    String username;

    public JoinChannelModel() {
        super();
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public String getUsername() {
        return this.username;
    }

    public void setExpireTime(long arg1) {
        this.expireTime = arg1;
    }

    public void setUsername(String arg1) {
        this.username = arg1;
    }
}

