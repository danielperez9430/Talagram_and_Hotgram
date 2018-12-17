package com.google.ads.interactivemedia.v3.impl.data;

import java.util.List;
import java.util.Map;

final class k extends TestingConfiguration {
    class com.google.ads.interactivemedia.v3.impl.data.k$1 {
    }

    final class a implements Builder {
        private Boolean disableExperiments;
        private Boolean enableMonitorAppLifecycle;
        private Map extraParams;
        private List forceExperimentIds;
        private Boolean forceTvMode;
        private Boolean ignoreStrictModeFalsePositives;
        private Boolean useTestStreamManager;
        private Boolean useVideoElementMock;
        private Float videoElementMockDuration;

        a() {
            super();
        }

        public TestingConfiguration build() {
            String v0 = "";
            if(this.disableExperiments == null) {
                v0 = String.valueOf(v0).concat(" disableExperiments");
            }

            if(this.useVideoElementMock == null) {
                v0 = String.valueOf(v0).concat(" useVideoElementMock");
            }

            if(this.videoElementMockDuration == null) {
                v0 = String.valueOf(v0).concat(" videoElementMockDuration");
            }

            if(this.useTestStreamManager == null) {
                v0 = String.valueOf(v0).concat(" useTestStreamManager");
            }

            if(this.enableMonitorAppLifecycle == null) {
                v0 = String.valueOf(v0).concat(" enableMonitorAppLifecycle");
            }

            if(this.forceTvMode == null) {
                v0 = String.valueOf(v0).concat(" forceTvMode");
            }

            if(this.ignoreStrictModeFalsePositives == null) {
                v0 = String.valueOf(v0).concat(" ignoreStrictModeFalsePositives");
            }

            if(!v0.isEmpty()) {
                String v2 = "Missing required properties:";
                v0 = String.valueOf(v0);
                v0 = v0.length() != 0 ? v2.concat(v0) : new String(v2);
                throw new IllegalStateException(v0);
            }

            return new k(this.disableExperiments.booleanValue(), this.useVideoElementMock.booleanValue(), this.videoElementMockDuration.floatValue(), this.useTestStreamManager.booleanValue(), this.enableMonitorAppLifecycle.booleanValue(), this.forceExperimentIds, this.forceTvMode.booleanValue(), this.ignoreStrictModeFalsePositives.booleanValue(), this.extraParams, null);
        }

        public Builder disableExperiments(boolean arg1) {
            this.disableExperiments = Boolean.valueOf(arg1);
            return this;
        }

        public Builder enableMonitorAppLifecycle(boolean arg1) {
            this.enableMonitorAppLifecycle = Boolean.valueOf(arg1);
            return this;
        }

        public Builder extraParams(Map arg1) {
            this.extraParams = arg1;
            return this;
        }

        public Builder forceExperimentIds(List arg1) {
            this.forceExperimentIds = arg1;
            return this;
        }

        public Builder forceTvMode(boolean arg1) {
            this.forceTvMode = Boolean.valueOf(arg1);
            return this;
        }

        public Builder ignoreStrictModeFalsePositives(boolean arg1) {
            this.ignoreStrictModeFalsePositives = Boolean.valueOf(arg1);
            return this;
        }

        public Builder useTestStreamManager(boolean arg1) {
            this.useTestStreamManager = Boolean.valueOf(arg1);
            return this;
        }

        public Builder useVideoElementMock(boolean arg1) {
            this.useVideoElementMock = Boolean.valueOf(arg1);
            return this;
        }

        public Builder videoElementMockDuration(float arg1) {
            this.videoElementMockDuration = Float.valueOf(arg1);
            return this;
        }
    }

    private final boolean disableExperiments;
    private final boolean enableMonitorAppLifecycle;
    private final Map extraParams;
    private final List forceExperimentIds;
    private final boolean forceTvMode;
    private final boolean ignoreStrictModeFalsePositives;
    private final boolean useTestStreamManager;
    private final boolean useVideoElementMock;
    private final float videoElementMockDuration;

    private k(boolean arg1, boolean arg2, float arg3, boolean arg4, boolean arg5, List arg6, boolean arg7, boolean arg8, Map arg9) {
        super();
        this.disableExperiments = arg1;
        this.useVideoElementMock = arg2;
        this.videoElementMockDuration = arg3;
        this.useTestStreamManager = arg4;
        this.enableMonitorAppLifecycle = arg5;
        this.forceExperimentIds = arg6;
        this.forceTvMode = arg7;
        this.ignoreStrictModeFalsePositives = arg8;
        this.extraParams = arg9;
    }

    k(boolean arg1, boolean arg2, float arg3, boolean arg4, boolean arg5, List arg6, boolean arg7, boolean arg8, Map arg9, com.google.ads.interactivemedia.v3.impl.data.k$1 arg10) {
        this(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    }

    public boolean disableExperiments() {
        return this.disableExperiments;
    }

    public boolean enableMonitorAppLifecycle() {
        return this.enableMonitorAppLifecycle;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((k)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof TestingConfiguration)) {
            if(this.disableExperiments != ((TestingConfiguration)arg5).disableExperiments() || this.useVideoElementMock != ((TestingConfiguration)arg5).useVideoElementMock() || Float.floatToIntBits(this.videoElementMockDuration) != Float.floatToIntBits(((TestingConfiguration)arg5).videoElementMockDuration()) || this.useTestStreamManager != ((TestingConfiguration)arg5).useTestStreamManager() || this.enableMonitorAppLifecycle != ((TestingConfiguration)arg5).enableMonitorAppLifecycle()) {
            label_48:
                v0 = false;
            }
            else {
                if(this.forceExperimentIds != null) {
                    if(this.forceExperimentIds.equals(((TestingConfiguration)arg5).forceExperimentIds())) {
                        goto label_32;
                    }

                    goto label_48;
                }
                else if(((TestingConfiguration)arg5).forceExperimentIds() == null) {
                }
                else {
                    goto label_48;
                }

            label_32:
                if(this.forceTvMode != ((TestingConfiguration)arg5).forceTvMode()) {
                    goto label_48;
                }

                if(this.ignoreStrictModeFalsePositives != ((TestingConfiguration)arg5).ignoreStrictModeFalsePositives()) {
                    goto label_48;
                }

                if(this.extraParams == null) {
                    if(((TestingConfiguration)arg5).extraParams() != null) {
                        goto label_48;
                    }

                    return v0;
                }

                if(!this.extraParams.equals(((TestingConfiguration)arg5).extraParams())) {
                    goto label_48;
                }
            }

            return v0;
        }

        return 0;
    }

    public Map extraParams() {
        return this.extraParams;
    }

    public List forceExperimentIds() {
        return this.forceExperimentIds;
    }

    public boolean forceTvMode() {
        return this.forceTvMode;
    }

    public int hashCode() {
        int v1 = 1237;
        int v0 = this.disableExperiments ? 1231 : 1237;
        int v3 = 1000003;
        v0 = (v0 ^ v3) * v3;
        int v4 = this.useVideoElementMock ? 1231 : 1237;
        v0 = ((v0 ^ v4) * v3 ^ Float.floatToIntBits(this.videoElementMockDuration)) * v3;
        v4 = this.useTestStreamManager ? 1231 : 1237;
        v0 = (v0 ^ v4) * v3;
        v4 = this.enableMonitorAppLifecycle ? 1231 : 1237;
        v0 = (v0 ^ v4) * v3;
        int v5 = 0;
        v4 = this.forceExperimentIds == null ? 0 : this.forceExperimentIds.hashCode();
        v0 = (v0 ^ v4) * v3;
        v4 = this.forceTvMode ? 1231 : 1237;
        v0 = (v0 ^ v4) * v3;
        if(this.ignoreStrictModeFalsePositives) {
            v1 = 1231;
        }

        v0 = (v0 ^ v1) * v3;
        if(this.extraParams == null) {
        }
        else {
            v5 = this.extraParams.hashCode();
        }

        return v0 ^ v5;
    }

    public boolean ignoreStrictModeFalsePositives() {
        return this.ignoreStrictModeFalsePositives;
    }

    public String toString() {
        boolean v0 = this.disableExperiments;
        boolean v1 = this.useVideoElementMock;
        float v2 = this.videoElementMockDuration;
        boolean v3 = this.useTestStreamManager;
        boolean v4 = this.enableMonitorAppLifecycle;
        String v5 = String.valueOf(this.forceExperimentIds);
        boolean v6 = this.forceTvMode;
        boolean v7 = this.ignoreStrictModeFalsePositives;
        String v8 = String.valueOf(this.extraParams);
        StringBuilder v10 = new StringBuilder(String.valueOf(v5).length() + 268 + String.valueOf(v8).length());
        v10.append("TestingConfiguration{disableExperiments=");
        v10.append(v0);
        v10.append(", useVideoElementMock=");
        v10.append(v1);
        v10.append(", videoElementMockDuration=");
        v10.append(v2);
        v10.append(", useTestStreamManager=");
        v10.append(v3);
        v10.append(", enableMonitorAppLifecycle=");
        v10.append(v4);
        v10.append(", forceExperimentIds=");
        v10.append(v5);
        v10.append(", forceTvMode=");
        v10.append(v6);
        v10.append(", ignoreStrictModeFalsePositives=");
        v10.append(v7);
        v10.append(", extraParams=");
        v10.append(v8);
        v10.append("}");
        return v10.toString();
    }

    public boolean useTestStreamManager() {
        return this.useTestStreamManager;
    }

    public boolean useVideoElementMock() {
        return this.useVideoElementMock;
    }

    public float videoElementMockDuration() {
        return this.videoElementMockDuration;
    }
}

