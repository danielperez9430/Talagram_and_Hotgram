package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.ImaSdkSettings;
import com.google.ads.interactivemedia.v3.internal.ii$b;
import com.google.ads.interactivemedia.v3.internal.js;
import java.util.List;
import java.util.Map;

final class h extends o {
    class com.google.ads.interactivemedia.v3.impl.data.h$1 {
    }

    final class a implements com.google.ads.interactivemedia.v3.impl.data.o$a {
        private Map adTagParameters;
        private String adTagUrl;
        private String adsResponse;
        private String apiKey;
        private String assetKey;
        private String authToken;
        private Map companionSlots;
        private Float contentDuration;
        private List contentKeywords;
        private String contentSourceId;
        private String contentTitle;
        private String env;
        private Map extraParameters;
        private String format;
        private js identifierInfo;
        private Boolean isTv;
        private Integer linearAdSlotHeight;
        private Integer linearAdSlotWidth;
        private Float liveStreamPrefetchSeconds;
        private b marketAppInfo;
        private String msParameter;
        private String network;
        private ImaSdkSettings settings;
        private String streamActivityMonitorId;
        private Boolean useQAStreamBaseUrl;
        private Float vastLoadTimeout;
        private String videoId;
        private com.google.ads.interactivemedia.v3.internal.in$a videoPlayActivation;
        private com.google.ads.interactivemedia.v3.internal.in$b videoPlayMuted;

        a() {
            super();
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a adTagParameters(Map arg1) {
            this.adTagParameters = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a adTagUrl(String arg1) {
            this.adTagUrl = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a adsResponse(String arg1) {
            this.adsResponse = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a apiKey(String arg1) {
            this.apiKey = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a assetKey(String arg1) {
            this.assetKey = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a authToken(String arg1) {
            this.authToken = arg1;
            return this;
        }

        public o build() {
            return new h(this.adsResponse, this.adTagUrl, this.assetKey, this.authToken, this.contentSourceId, this.videoId, this.apiKey, this.format, this.adTagParameters, this.env, this.network, this.contentDuration, this.contentKeywords, this.contentTitle, this.vastLoadTimeout, this.liveStreamPrefetchSeconds, this.companionSlots, this.extraParameters, this.isTv, this.msParameter, this.linearAdSlotWidth, this.linearAdSlotHeight, this.streamActivityMonitorId, this.identifierInfo, this.useQAStreamBaseUrl, this.videoPlayActivation, this.videoPlayMuted, this.settings, this.marketAppInfo, null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a companionSlots(Map arg1) {
            this.companionSlots = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a contentDuration(Float arg1) {
            this.contentDuration = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a contentKeywords(List arg1) {
            this.contentKeywords = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a contentSourceId(String arg1) {
            this.contentSourceId = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a contentTitle(String arg1) {
            this.contentTitle = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a env(String arg1) {
            this.env = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a extraParameters(Map arg1) {
            this.extraParameters = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a format(String arg1) {
            this.format = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a identifierInfo(js arg1) {
            this.identifierInfo = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a isTv(Boolean arg1) {
            this.isTv = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a linearAdSlotHeight(Integer arg1) {
            this.linearAdSlotHeight = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a linearAdSlotWidth(Integer arg1) {
            this.linearAdSlotWidth = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a liveStreamPrefetchSeconds(Float arg1) {
            this.liveStreamPrefetchSeconds = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a marketAppInfo(b arg1) {
            this.marketAppInfo = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a msParameter(String arg1) {
            this.msParameter = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a network(String arg1) {
            this.network = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a settings(ImaSdkSettings arg1) {
            this.settings = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a streamActivityMonitorId(String arg1) {
            this.streamActivityMonitorId = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a useQAStreamBaseUrl(Boolean arg1) {
            this.useQAStreamBaseUrl = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a vastLoadTimeout(Float arg1) {
            this.vastLoadTimeout = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a videoId(String arg1) {
            this.videoId = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a videoPlayActivation(com.google.ads.interactivemedia.v3.internal.in$a arg1) {
            this.videoPlayActivation = arg1;
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.o$a videoPlayMuted(com.google.ads.interactivemedia.v3.internal.in$b arg1) {
            this.videoPlayMuted = arg1;
            return this;
        }
    }

    private final Map adTagParameters;
    private final String adTagUrl;
    private final String adsResponse;
    private final String apiKey;
    private final String assetKey;
    private final String authToken;
    private final Map companionSlots;
    private final Float contentDuration;
    private final List contentKeywords;
    private final String contentSourceId;
    private final String contentTitle;
    private final String env;
    private final Map extraParameters;
    private final String format;
    private final js identifierInfo;
    private final Boolean isTv;
    private final Integer linearAdSlotHeight;
    private final Integer linearAdSlotWidth;
    private final Float liveStreamPrefetchSeconds;
    private final b marketAppInfo;
    private final String msParameter;
    private final String network;
    private final ImaSdkSettings settings;
    private final String streamActivityMonitorId;
    private final Boolean useQAStreamBaseUrl;
    private final Float vastLoadTimeout;
    private final String videoId;
    private final com.google.ads.interactivemedia.v3.internal.in$a videoPlayActivation;
    private final com.google.ads.interactivemedia.v3.internal.in$b videoPlayMuted;

    private h(String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9, String arg10, Map arg11, String arg12, String arg13, Float arg14, List arg15, String arg16, Float arg17, Float arg18, Map arg19, Map arg20, Boolean arg21, String arg22, Integer arg23, Integer arg24, String arg25, js arg26, Boolean arg27, com.google.ads.interactivemedia.v3.internal.in$a arg28, com.google.ads.interactivemedia.v3.internal.in$b arg29, ImaSdkSettings arg30, b arg31) {
        super();
        this.adsResponse = arg3;
        this.adTagUrl = arg4;
        this.assetKey = arg5;
        this.authToken = arg6;
        this.contentSourceId = arg7;
        this.videoId = arg8;
        this.apiKey = arg9;
        this.format = arg10;
        this.adTagParameters = arg11;
        this.env = arg12;
        this.network = arg13;
        this.contentDuration = arg14;
        this.contentKeywords = arg15;
        this.contentTitle = arg16;
        this.vastLoadTimeout = arg17;
        this.liveStreamPrefetchSeconds = arg18;
        this.companionSlots = arg19;
        this.extraParameters = arg20;
        this.isTv = arg21;
        this.msParameter = arg22;
        this.linearAdSlotWidth = arg23;
        this.linearAdSlotHeight = arg24;
        this.streamActivityMonitorId = arg25;
        this.identifierInfo = arg26;
        this.useQAStreamBaseUrl = arg27;
        this.videoPlayActivation = arg28;
        this.videoPlayMuted = arg29;
        this.settings = arg30;
        this.marketAppInfo = arg31;
    }

    h(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, Map arg9, String arg10, String arg11, Float arg12, List arg13, String arg14, Float arg15, Float arg16, Map arg17, Map arg18, Boolean arg19, String arg20, Integer arg21, Integer arg22, String arg23, js arg24, Boolean arg25, com.google.ads.interactivemedia.v3.internal.in$a arg26, com.google.ads.interactivemedia.v3.internal.in$b arg27, ImaSdkSettings arg28, b arg29, com.google.ads.interactivemedia.v3.impl.data.h$1 arg30) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19, arg20, arg21, arg22, arg23, arg24, arg25, arg26, arg27, arg28, arg29);
    }

    public Map adTagParameters() {
        return this.adTagParameters;
    }

    public String adTagUrl() {
        return this.adTagUrl;
    }

    public String adsResponse() {
        return this.adsResponse;
    }

    public String apiKey() {
        return this.apiKey;
    }

    public String assetKey() {
        return this.assetKey;
    }

    public String authToken() {
        return this.authToken;
    }

    public Map companionSlots() {
        return this.companionSlots;
    }

    public Float contentDuration() {
        return this.contentDuration;
    }

    public List contentKeywords() {
        return this.contentKeywords;
    }

    public String contentSourceId() {
        return this.contentSourceId;
    }

    public String contentTitle() {
        return this.contentTitle;
    }

    public String env() {
        return this.env;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((h)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof o)) {
            if(this.adsResponse == null) {
                if(((o)arg5).adsResponse() == null) {
                    goto label_15;
                }
                else {
                    goto label_268;
                }
            }
            else if(this.adsResponse.equals(((o)arg5).adsResponse())) {
            label_15:
                if(this.adTagUrl != null) {
                    if(this.adTagUrl.equals(((o)arg5).adTagUrl())) {
                        goto label_24;
                    }

                    goto label_268;
                }
                else if(((o)arg5).adTagUrl() == null) {
                }
                else {
                    goto label_268;
                }

            label_24:
                if(this.assetKey != null) {
                    if(this.assetKey.equals(((o)arg5).assetKey())) {
                        goto label_33;
                    }

                    goto label_268;
                }
                else if(((o)arg5).assetKey() == null) {
                }
                else {
                    goto label_268;
                }

            label_33:
                if(this.authToken != null) {
                    if(this.authToken.equals(((o)arg5).authToken())) {
                        goto label_42;
                    }

                    goto label_268;
                }
                else if(((o)arg5).authToken() == null) {
                }
                else {
                    goto label_268;
                }

            label_42:
                if(this.contentSourceId != null) {
                    if(this.contentSourceId.equals(((o)arg5).contentSourceId())) {
                        goto label_51;
                    }

                    goto label_268;
                }
                else if(((o)arg5).contentSourceId() == null) {
                }
                else {
                    goto label_268;
                }

            label_51:
                if(this.videoId != null) {
                    if(this.videoId.equals(((o)arg5).videoId())) {
                        goto label_60;
                    }

                    goto label_268;
                }
                else if(((o)arg5).videoId() == null) {
                }
                else {
                    goto label_268;
                }

            label_60:
                if(this.apiKey != null) {
                    if(this.apiKey.equals(((o)arg5).apiKey())) {
                        goto label_69;
                    }

                    goto label_268;
                }
                else if(((o)arg5).apiKey() == null) {
                }
                else {
                    goto label_268;
                }

            label_69:
                if(this.format != null) {
                    if(this.format.equals(((o)arg5).format())) {
                        goto label_78;
                    }

                    goto label_268;
                }
                else if(((o)arg5).format() == null) {
                }
                else {
                    goto label_268;
                }

            label_78:
                if(this.adTagParameters != null) {
                    if(this.adTagParameters.equals(((o)arg5).adTagParameters())) {
                        goto label_87;
                    }

                    goto label_268;
                }
                else if(((o)arg5).adTagParameters() == null) {
                }
                else {
                    goto label_268;
                }

            label_87:
                if(this.env != null) {
                    if(this.env.equals(((o)arg5).env())) {
                        goto label_96;
                    }

                    goto label_268;
                }
                else if(((o)arg5).env() == null) {
                }
                else {
                    goto label_268;
                }

            label_96:
                if(this.network != null) {
                    if(this.network.equals(((o)arg5).network())) {
                        goto label_105;
                    }

                    goto label_268;
                }
                else if(((o)arg5).network() == null) {
                }
                else {
                    goto label_268;
                }

            label_105:
                if(this.contentDuration != null) {
                    if(this.contentDuration.equals(((o)arg5).contentDuration())) {
                        goto label_114;
                    }

                    goto label_268;
                }
                else if(((o)arg5).contentDuration() == null) {
                }
                else {
                    goto label_268;
                }

            label_114:
                if(this.contentKeywords != null) {
                    if(this.contentKeywords.equals(((o)arg5).contentKeywords())) {
                        goto label_123;
                    }

                    goto label_268;
                }
                else if(((o)arg5).contentKeywords() == null) {
                }
                else {
                    goto label_268;
                }

            label_123:
                if(this.contentTitle != null) {
                    if(this.contentTitle.equals(((o)arg5).contentTitle())) {
                        goto label_132;
                    }

                    goto label_268;
                }
                else if(((o)arg5).contentTitle() == null) {
                }
                else {
                    goto label_268;
                }

            label_132:
                if(this.vastLoadTimeout != null) {
                    if(this.vastLoadTimeout.equals(((o)arg5).vastLoadTimeout())) {
                        goto label_141;
                    }

                    goto label_268;
                }
                else if(((o)arg5).vastLoadTimeout() == null) {
                }
                else {
                    goto label_268;
                }

            label_141:
                if(this.liveStreamPrefetchSeconds != null) {
                    if(this.liveStreamPrefetchSeconds.equals(((o)arg5).liveStreamPrefetchSeconds())) {
                        goto label_150;
                    }

                    goto label_268;
                }
                else if(((o)arg5).liveStreamPrefetchSeconds() == null) {
                }
                else {
                    goto label_268;
                }

            label_150:
                if(this.companionSlots != null) {
                    if(this.companionSlots.equals(((o)arg5).companionSlots())) {
                        goto label_159;
                    }

                    goto label_268;
                }
                else if(((o)arg5).companionSlots() == null) {
                }
                else {
                    goto label_268;
                }

            label_159:
                if(this.extraParameters != null) {
                    if(this.extraParameters.equals(((o)arg5).extraParameters())) {
                        goto label_168;
                    }

                    goto label_268;
                }
                else if(((o)arg5).extraParameters() == null) {
                }
                else {
                    goto label_268;
                }

            label_168:
                if(this.isTv != null) {
                    if(this.isTv.equals(((o)arg5).isTv())) {
                        goto label_177;
                    }

                    goto label_268;
                }
                else if(((o)arg5).isTv() == null) {
                }
                else {
                    goto label_268;
                }

            label_177:
                if(this.msParameter != null) {
                    if(this.msParameter.equals(((o)arg5).msParameter())) {
                        goto label_186;
                    }

                    goto label_268;
                }
                else if(((o)arg5).msParameter() == null) {
                }
                else {
                    goto label_268;
                }

            label_186:
                if(this.linearAdSlotWidth != null) {
                    if(this.linearAdSlotWidth.equals(((o)arg5).linearAdSlotWidth())) {
                        goto label_195;
                    }

                    goto label_268;
                }
                else if(((o)arg5).linearAdSlotWidth() == null) {
                }
                else {
                    goto label_268;
                }

            label_195:
                if(this.linearAdSlotHeight != null) {
                    if(this.linearAdSlotHeight.equals(((o)arg5).linearAdSlotHeight())) {
                        goto label_204;
                    }

                    goto label_268;
                }
                else if(((o)arg5).linearAdSlotHeight() == null) {
                }
                else {
                    goto label_268;
                }

            label_204:
                if(this.streamActivityMonitorId != null) {
                    if(this.streamActivityMonitorId.equals(((o)arg5).streamActivityMonitorId())) {
                        goto label_213;
                    }

                    goto label_268;
                }
                else if(((o)arg5).streamActivityMonitorId() == null) {
                }
                else {
                    goto label_268;
                }

            label_213:
                if(this.identifierInfo != null) {
                    if(this.identifierInfo.equals(((o)arg5).identifierInfo())) {
                        goto label_222;
                    }

                    goto label_268;
                }
                else if(((o)arg5).identifierInfo() == null) {
                }
                else {
                    goto label_268;
                }

            label_222:
                if(this.useQAStreamBaseUrl != null) {
                    if(this.useQAStreamBaseUrl.equals(((o)arg5).useQAStreamBaseUrl())) {
                        goto label_231;
                    }

                    goto label_268;
                }
                else if(((o)arg5).useQAStreamBaseUrl() == null) {
                }
                else {
                    goto label_268;
                }

            label_231:
                if(this.videoPlayActivation != null) {
                    if(this.videoPlayActivation.equals(((o)arg5).videoPlayActivation())) {
                        goto label_240;
                    }

                    goto label_268;
                }
                else if(((o)arg5).videoPlayActivation() == null) {
                }
                else {
                    goto label_268;
                }

            label_240:
                if(this.videoPlayMuted != null) {
                    if(this.videoPlayMuted.equals(((o)arg5).videoPlayMuted())) {
                        goto label_249;
                    }

                    goto label_268;
                }
                else if(((o)arg5).videoPlayMuted() == null) {
                }
                else {
                    goto label_268;
                }

            label_249:
                if(this.settings != null) {
                    if(this.settings.equals(((o)arg5).settings())) {
                        goto label_258;
                    }

                    goto label_268;
                }
                else if(((o)arg5).settings() == null) {
                }
                else {
                    goto label_268;
                }

            label_258:
                if(this.marketAppInfo == null) {
                    if(((o)arg5).marketAppInfo() != null) {
                        goto label_268;
                    }

                    return v0;
                }

                if(!this.marketAppInfo.equals(((o)arg5).marketAppInfo())) {
                    goto label_268;
                }
            }
            else {
            label_268:
                v0 = false;
            }

            return v0;
        }

        return 0;
    }

    public Map extraParameters() {
        return this.extraParameters;
    }

    public String format() {
        return this.format;
    }

    public int hashCode() {
        int v1 = 0;
        int v0 = this.adsResponse == null ? 0 : this.adsResponse.hashCode();
        int v2 = 1000003;
        v0 = (v0 ^ v2) * v2;
        int v3 = this.adTagUrl == null ? 0 : this.adTagUrl.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.assetKey == null ? 0 : this.assetKey.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.authToken == null ? 0 : this.authToken.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.contentSourceId == null ? 0 : this.contentSourceId.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.videoId == null ? 0 : this.videoId.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.apiKey == null ? 0 : this.apiKey.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.format == null ? 0 : this.format.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.adTagParameters == null ? 0 : this.adTagParameters.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.env == null ? 0 : this.env.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.network == null ? 0 : this.network.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.contentDuration == null ? 0 : this.contentDuration.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.contentKeywords == null ? 0 : this.contentKeywords.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.contentTitle == null ? 0 : this.contentTitle.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.vastLoadTimeout == null ? 0 : this.vastLoadTimeout.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.liveStreamPrefetchSeconds == null ? 0 : this.liveStreamPrefetchSeconds.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.companionSlots == null ? 0 : this.companionSlots.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.extraParameters == null ? 0 : this.extraParameters.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.isTv == null ? 0 : this.isTv.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.msParameter == null ? 0 : this.msParameter.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.linearAdSlotWidth == null ? 0 : this.linearAdSlotWidth.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.linearAdSlotHeight == null ? 0 : this.linearAdSlotHeight.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.streamActivityMonitorId == null ? 0 : this.streamActivityMonitorId.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.identifierInfo == null ? 0 : this.identifierInfo.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.useQAStreamBaseUrl == null ? 0 : this.useQAStreamBaseUrl.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.videoPlayActivation == null ? 0 : this.videoPlayActivation.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.videoPlayMuted == null ? 0 : this.videoPlayMuted.hashCode();
        v0 = (v0 ^ v3) * v2;
        v3 = this.settings == null ? 0 : this.settings.hashCode();
        v0 = (v0 ^ v3) * v2;
        if(this.marketAppInfo == null) {
        }
        else {
            v1 = this.marketAppInfo.hashCode();
        }

        return v0 ^ v1;
    }

    public js identifierInfo() {
        return this.identifierInfo;
    }

    public Boolean isTv() {
        return this.isTv;
    }

    public Integer linearAdSlotHeight() {
        return this.linearAdSlotHeight;
    }

    public Integer linearAdSlotWidth() {
        return this.linearAdSlotWidth;
    }

    public Float liveStreamPrefetchSeconds() {
        return this.liveStreamPrefetchSeconds;
    }

    public b marketAppInfo() {
        return this.marketAppInfo;
    }

    public String msParameter() {
        return this.msParameter;
    }

    public String network() {
        return this.network;
    }

    public ImaSdkSettings settings() {
        return this.settings;
    }

    public String streamActivityMonitorId() {
        return this.streamActivityMonitorId;
    }

    public String toString() {
        String v1 = this.adsResponse;
        String v2 = this.adTagUrl;
        String v3 = this.assetKey;
        String v4 = this.authToken;
        String v5 = this.contentSourceId;
        String v6 = this.videoId;
        String v7 = this.apiKey;
        String v8 = this.format;
        String v9 = String.valueOf(this.adTagParameters);
        String v10 = this.env;
        String v11 = this.network;
        String v12 = String.valueOf(this.contentDuration);
        String v13 = String.valueOf(this.contentKeywords);
        String v14 = this.contentTitle;
        String v16 = String.valueOf(this.vastLoadTimeout);
        String v17 = String.valueOf(this.liveStreamPrefetchSeconds);
        String v18 = String.valueOf(this.companionSlots);
        String v19 = String.valueOf(this.extraParameters);
        String v20 = String.valueOf(this.isTv);
        String v21 = this.msParameter;
        String v22 = String.valueOf(this.linearAdSlotWidth);
        String v23 = String.valueOf(this.linearAdSlotHeight);
        String v24 = this.streamActivityMonitorId;
        String v25 = String.valueOf(this.identifierInfo);
        String v26 = String.valueOf(this.useQAStreamBaseUrl);
        String v27 = String.valueOf(this.videoPlayActivation);
        String v28 = String.valueOf(this.videoPlayMuted);
        String v29 = String.valueOf(this.settings);
        String v15 = String.valueOf(this.marketAppInfo);
        String v31 = v15;
        StringBuilder v15_1 = new StringBuilder(String.valueOf(v1).length() + 466 + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length() + String.valueOf(v5).length() + String.valueOf(v6).length() + String.valueOf(v7).length() + String.valueOf(v8).length() + String.valueOf(v9).length() + String.valueOf(v10).length() + String.valueOf(v11).length() + String.valueOf(v12).length() + String.valueOf(v13).length() + String.valueOf(v14).length() + String.valueOf(v16).length() + String.valueOf(v17).length() + String.valueOf(v18).length() + String.valueOf(v19).length() + String.valueOf(v20).length() + String.valueOf(v21).length() + String.valueOf(v22).length() + String.valueOf(v23).length() + String.valueOf(v24).length() + String.valueOf(v25).length() + String.valueOf(v26).length() + String.valueOf(v27).length() + String.valueOf(v28).length() + String.valueOf(v29).length() + String.valueOf(v15).length());
        v15_1.append("GsonAdsRequest{adsResponse=");
        v15_1.append(v1);
        v15_1.append(", adTagUrl=");
        v15_1.append(v2);
        v15_1.append(", assetKey=");
        v15_1.append(v3);
        v15_1.append(", authToken=");
        v15_1.append(v4);
        v15_1.append(", contentSourceId=");
        v15_1.append(v5);
        v15_1.append(", videoId=");
        v15_1.append(v6);
        v15_1.append(", apiKey=");
        v15_1.append(v7);
        v15_1.append(", format=");
        v15_1.append(v8);
        v15_1.append(", adTagParameters=");
        v15_1.append(v9);
        v15_1.append(", env=");
        v15_1.append(v10);
        v15_1.append(", network=");
        v15_1.append(v11);
        v15_1.append(", contentDuration=");
        v15_1.append(v12);
        v15_1.append(", contentKeywords=");
        v15_1.append(v13);
        v15_1.append(", contentTitle=");
        v15_1.append(v14);
        v15_1.append(", vastLoadTimeout=");
        v15_1.append(v16);
        v15_1.append(", liveStreamPrefetchSeconds=");
        v15_1.append(v17);
        v15_1.append(", companionSlots=");
        v15_1.append(v18);
        v15_1.append(", extraParameters=");
        v15_1.append(v19);
        v15_1.append(", isTv=");
        v15_1.append(v20);
        v15_1.append(", msParameter=");
        v15_1.append(v21);
        v15_1.append(", linearAdSlotWidth=");
        v15_1.append(v22);
        v15_1.append(", linearAdSlotHeight=");
        v15_1.append(v23);
        v15_1.append(", streamActivityMonitorId=");
        v15_1.append(v24);
        v15_1.append(", identifierInfo=");
        v15_1.append(v25);
        v15_1.append(", useQAStreamBaseUrl=");
        v15_1.append(v26);
        v15_1.append(", videoPlayActivation=");
        v15_1.append(v27);
        v15_1.append(", videoPlayMuted=");
        v15_1.append(v28);
        v15_1.append(", settings=");
        v15_1.append(v29);
        v15_1.append(", marketAppInfo=");
        v15_1.append(v31);
        v15_1.append("}");
        return v15_1.toString();
    }

    public Boolean useQAStreamBaseUrl() {
        return this.useQAStreamBaseUrl;
    }

    public Float vastLoadTimeout() {
        return this.vastLoadTimeout;
    }

    public String videoId() {
        return this.videoId;
    }

    public com.google.ads.interactivemedia.v3.internal.in$a videoPlayActivation() {
        return this.videoPlayActivation;
    }

    public com.google.ads.interactivemedia.v3.internal.in$b videoPlayMuted() {
        return this.videoPlayMuted;
    }
}

