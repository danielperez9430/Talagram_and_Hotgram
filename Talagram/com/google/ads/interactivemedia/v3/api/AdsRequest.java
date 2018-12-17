package com.google.ads.interactivemedia.v3.api;

import com.google.ads.interactivemedia.v3.api.player.ContentProgressProvider;
import java.util.List;
import java.util.Map;

public interface AdsRequest {
    AdDisplayContainer getAdDisplayContainer();

    String getAdTagUrl();

    String getAdsResponse();

    ContentProgressProvider getContentProgressProvider();

    String getExtraParameter(String arg1);

    Map getExtraParameters();

    Object getUserRequestContext();

    void setAdDisplayContainer(AdDisplayContainer arg1);

    void setAdTagUrl(String arg1);

    void setAdWillAutoPlay(boolean arg1);

    void setAdWillPlayMuted(boolean arg1);

    void setAdsResponse(String arg1);

    void setContentDuration(float arg1);

    void setContentKeywords(List arg1);

    void setContentProgressProvider(ContentProgressProvider arg1);

    void setContentTitle(String arg1);

    void setExtraParameter(String arg1, String arg2);

    void setLiveStreamPrefetchSeconds(float arg1);

    void setUserRequestContext(Object arg1);

    void setVastLoadTimeout(float arg1);
}

