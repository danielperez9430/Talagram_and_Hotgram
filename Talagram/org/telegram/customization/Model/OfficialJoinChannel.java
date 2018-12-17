package org.telegram.customization.Model;

public class OfficialJoinChannel {
    String channelUserName;
    int isForce;

    public OfficialJoinChannel() {
        super();
    }

    public String getChannelUserName() {
        return this.channelUserName;
    }

    public int isForce() {
        return this.isForce;
    }

    public void setChannelUserName(String arg1) {
        this.channelUserName = arg1;
    }

    public void setForce(int arg1) {
        this.isForce = arg1;
    }
}

