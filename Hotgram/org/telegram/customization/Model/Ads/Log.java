package org.telegram.customization.Model.Ads;

public class Log {
    public static final int ACTION_CLICK = 2;
    public static final int ACTION_VIEW = 4;
    int action;
    int channelId;
    int messageId;
    String url;

    public Log() {
        super();
    }

    public int getAction() {
        return this.action;
    }

    public int getChannelId() {
        return this.channelId;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAction(int arg1) {
        this.action = arg1;
    }

    public void setChannelId(int arg1) {
        this.channelId = arg1;
    }

    public void setMessageId(int arg1) {
        this.messageId = arg1;
    }

    public void setUrl(String arg1) {
        this.url = arg1;
    }
}

