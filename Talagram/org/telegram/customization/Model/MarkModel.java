package org.telegram.customization.Model;

public class MarkModel {
    long chatId;
    long messageId;

    public MarkModel() {
        super();
    }

    public long getChatId() {
        return this.chatId;
    }

    public long getMessageId() {
        return this.messageId;
    }

    public void setChatId(long arg1) {
        this.chatId = arg1;
    }

    public void setMessageId(long arg1) {
        this.messageId = arg1;
    }
}

