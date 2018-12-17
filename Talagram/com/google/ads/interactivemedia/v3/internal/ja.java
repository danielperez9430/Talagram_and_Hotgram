package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;

public final class ja implements ImaSdkSettings {
    private boolean autoPlayAdBreaks;
    private boolean debugMode;
    private boolean enableOmidExperimentally;
    private transient String language;
    private int numRedirects;
    private final boolean onScreenDetection;
    private String playerType;
    private String playerVersion;
    private String ppid;
    private transient boolean restrictToCustomPlayer;

    public ja() {
        super();
        this.numRedirects = 4;
        this.onScreenDetection = true;
        this.autoPlayAdBreaks = true;
        this.enableOmidExperimentally = false;
        this.debugMode = false;
        this.language = "en";
    }

    public boolean doesRestrictToCustomPlayer() {
        return this.restrictToCustomPlayer;
    }

    public boolean getAutoPlayAdBreaks() {
        return this.autoPlayAdBreaks;
    }

    public boolean getEnableOmidExperimentally() {
        return this.enableOmidExperimentally;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getMaxRedirects() {
        return this.numRedirects;
    }

    public String getPlayerType() {
        return this.playerType;
    }

    public String getPlayerVersion() {
        return this.playerVersion;
    }

    public String getPpid() {
        return this.ppid;
    }

    public boolean isDebugMode() {
        return this.debugMode;
    }

    public void setAutoPlayAdBreaks(boolean arg1) {
        this.autoPlayAdBreaks = arg1;
    }

    public void setDebugMode(boolean arg1) {
        this.debugMode = arg1;
    }

    public void setEnableOmidExperimentally(boolean arg1) {
        this.enableOmidExperimentally = arg1;
    }

    public void setLanguage(String arg1) {
        this.language = arg1;
    }

    public void setMaxRedirects(int arg1) {
        this.numRedirects = arg1;
    }

    public void setPlayerType(String arg1) {
        this.playerType = arg1;
    }

    public void setPlayerVersion(String arg1) {
        this.playerVersion = arg1;
    }

    public void setPpid(String arg1) {
        this.ppid = arg1;
    }

    public void setRestrictToCustomPlayer(boolean arg1) {
        this.restrictToCustomPlayer = arg1;
    }

    public String toString() {
        String v0 = this.ppid;
        int v1 = this.numRedirects;
        String v2 = this.playerType;
        String v3 = this.playerVersion;
        String v4 = this.language;
        boolean v5 = this.restrictToCustomPlayer;
        boolean v6 = this.autoPlayAdBreaks;
        StringBuilder v8 = new StringBuilder(String.valueOf(v0).length() + 160 + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length());
        v8.append("ImaSdkSettings [ppid=");
        v8.append(v0);
        v8.append(", numRedirects=");
        v8.append(v1);
        v8.append(", playerType=");
        v8.append(v2);
        v8.append(", playerVersion=");
        v8.append(v3);
        v8.append(", onScreenDetection=");
        v8.append(true);
        v8.append(", language=");
        v8.append(v4);
        v8.append(", restrictToCustom=");
        v8.append(v5);
        v8.append(", autoPlayAdBreaks=");
        v8.append(v6);
        v8.append("]");
        return v8.toString();
    }
}

