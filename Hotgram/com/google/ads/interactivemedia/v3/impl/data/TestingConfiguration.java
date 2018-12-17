package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;
import java.util.List;
import java.util.Map;

@ki(a=k.class, b={"extraParams", "isTv", "ignoreStrictModeFalsePositives"}) public abstract class TestingConfiguration {
    public interface Builder {
        TestingConfiguration build();

        Builder disableExperiments(boolean arg1);

        Builder enableMonitorAppLifecycle(boolean arg1);

        Builder extraParams(Map arg1);

        Builder forceExperimentIds(List arg1);

        Builder forceTvMode(boolean arg1);

        Builder ignoreStrictModeFalsePositives(boolean arg1);

        Builder useTestStreamManager(boolean arg1);

        Builder useVideoElementMock(boolean arg1);

        Builder videoElementMockDuration(float arg1);
    }

    public static final String PARAMETER_KEY = "tcnfp";

    TestingConfiguration() {
        super();
    }

    public static Builder builder() {
        return new a().disableExperiments(false).useVideoElementMock(false).videoElementMockDuration(30f).useTestStreamManager(false).ignoreStrictModeFalsePositives(false).forceTvMode(false).enableMonitorAppLifecycle(true);
    }

    public Builder copy() {
        return new a().disableExperiments(this.disableExperiments()).useVideoElementMock(this.useVideoElementMock()).videoElementMockDuration(this.videoElementMockDuration()).useTestStreamManager(this.useTestStreamManager()).enableMonitorAppLifecycle(this.enableMonitorAppLifecycle()).forceTvMode(this.forceTvMode()).ignoreStrictModeFalsePositives(this.ignoreStrictModeFalsePositives()).extraParams(this.extraParams());
    }

    public abstract boolean disableExperiments();

    public abstract boolean enableMonitorAppLifecycle();

    public abstract Map extraParams();

    public abstract List forceExperimentIds();

    public abstract boolean forceTvMode();

    public abstract boolean ignoreStrictModeFalsePositives();

    public abstract boolean useTestStreamManager();

    public abstract boolean useVideoElementMock();

    public abstract float videoElementMockDuration();
}

