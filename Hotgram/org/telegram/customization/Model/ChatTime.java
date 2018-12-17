package org.telegram.customization.Model;

public class ChatTime {
    long cId;
    long time;

    public ChatTime() {
        super();
    }

    public long getTime() {
        return this.time;
    }

    public long getcId() {
        return this.cId;
    }

    public void setTime(long arg1) {
        this.time = arg1;
    }

    public void setcId(long arg1) {
        this.cId = arg1;
    }
}

