package org.telegram.customization.dynamicadapter.data;

import com.google.a.a.c;

public class SLSChannel {
    @c(a="username") String channelId;
    @c(a="channel_id") long id;
    @c(a="ia") boolean isAdmin;
    @c(a="ic") boolean isCreator;
    @c(a="iic") boolean isInChat;
    @c(a="im") boolean isMute;
    @c(a="title") String name;
    int userId;

    public SLSChannel() {
        super();
    }

    public String getChannelId() {
        return this.channelId;
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getUserId() {
        return this.userId;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean isCreator() {
        return this.isCreator;
    }

    public boolean isInChat() {
        return this.isInChat;
    }

    public boolean isMute() {
        return this.isMute;
    }

    public void setAdmin(boolean arg1) {
        this.isAdmin = arg1;
    }

    public void setChannelId(String arg1) {
        this.channelId = arg1;
    }

    public void setCreator(boolean arg1) {
        this.isCreator = arg1;
    }

    public void setId(long arg1) {
        this.id = arg1;
    }

    public void setInChat(boolean arg1) {
        this.isInChat = arg1;
    }

    public void setMute(boolean arg1) {
        this.isMute = arg1;
    }

    public void setName(String arg1) {
        this.name = arg1;
    }

    public void setUserId(int arg1) {
        this.userId = arg1;
    }
}

