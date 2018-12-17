package org.telegram.customization.Model;

public class MediaUsageStatistics {
    public static final int MEDIA_TYPE_CALLS = 5;
    public static final int MEDIA_TYPE_FILES = 4;
    public static final int MEDIA_TYPE_MESSAGE_AND_OTHER_DATA = 5;
    public static final int MEDIA_TYPE_PHOTOS = 1;
    public static final int MEDIA_TYPE_TOTAL = 6;
    public static final int MEDIA_TYPE_VIDEOS = 2;
    public static final int MEDIA_TYPE_VOICE_AND_VIDEO_MESSAGE = 3;
    public static final int NETWORK_TYPE_MOBILE = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    long bytesReceived;
    long bytesSent;
    int mediaType;
    int received;
    int sent;
    long totalTime;

    public MediaUsageStatistics() {
        super();
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public long getBytesSent() {
        return this.bytesSent;
    }

    public int getMediaType() {
        return this.mediaType;
    }

    public int getReceived() {
        return this.received;
    }

    public int getSent() {
        return this.sent;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setBytesReceived(long arg1) {
        this.bytesReceived = arg1;
    }

    public void setBytesSent(long arg1) {
        this.bytesSent = arg1;
    }

    public void setMediaType(int arg1) {
        this.mediaType = arg1;
    }

    public void setReceived(int arg1) {
        this.received = arg1;
    }

    public void setSent(int arg1) {
        this.sent = arg1;
    }

    public void setTotalTime(long arg1) {
        this.totalTime = arg1;
    }
}

