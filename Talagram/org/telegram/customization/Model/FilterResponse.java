package org.telegram.customization.Model;

public class FilterResponse {
    long channelId;
    boolean isFilter;
    boolean showDialog;

    public FilterResponse() {
        super();
        this.showDialog = false;
    }

    public long getChannelId() {
        return this.channelId;
    }

    public boolean isFilter() {
        return this.isFilter;
    }

    public boolean isShowDialog() {
        return this.showDialog;
    }

    public void setChannelId(long arg1) {
        this.channelId = arg1;
    }

    public void setFilter(boolean arg1) {
        this.isFilter = arg1;
    }

    public void setShowDialog(boolean arg1) {
        this.showDialog = arg1;
    }
}

