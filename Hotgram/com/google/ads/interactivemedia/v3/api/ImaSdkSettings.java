package com.google.ads.interactivemedia.v3.api;

public interface ImaSdkSettings {
    public static final int DEFAULT_MAX_REDIRECTS = 4;

    boolean doesRestrictToCustomPlayer();

    boolean getAutoPlayAdBreaks();

    boolean getEnableOmidExperimentally();

    String getLanguage();

    int getMaxRedirects();

    String getPlayerType();

    String getPlayerVersion();

    String getPpid();

    boolean isDebugMode();

    void setAutoPlayAdBreaks(boolean arg1);

    void setDebugMode(boolean arg1);

    void setEnableOmidExperimentally(boolean arg1);

    void setLanguage(String arg1);

    void setMaxRedirects(int arg1);

    void setPlayerType(String arg1);

    void setPlayerVersion(String arg1);

    void setPpid(String arg1);

    void setRestrictToCustomPlayer(boolean arg1);

    String toString();
}

