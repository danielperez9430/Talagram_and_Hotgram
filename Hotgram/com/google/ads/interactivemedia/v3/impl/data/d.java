package com.google.ads.interactivemedia.v3.impl.data;

final class d extends a {
    class com.google.ads.interactivemedia.v3.impl.data.d$1 {
    }

    final class com.google.ads.interactivemedia.v3.impl.data.d$a implements com.google.ads.interactivemedia.v3.impl.data.a$a {
        private String appState;
        private String eventId;
        private Long nativeTime;
        private Boolean nativeViewAttached;
        private m nativeViewBounds;
        private Boolean nativeViewHidden;
        private m nativeViewVisibleBounds;
        private Double nativeVolume;
        private String queryId;
        private String vastEvent;

        com.google.ads.interactivemedia.v3.impl.data.d$a() {
            super();
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a appState(String arg2) {
            if(arg2 != null) {
                this.appState = arg2;
                return this;
            }

            throw new NullPointerException("Null appState");
        }

        public a build() {
            com.google.ads.interactivemedia.v3.impl.data.d$a v0 = this;
            String v1 = "";
            if(v0.queryId == null) {
                v1 = String.valueOf(v1).concat(" queryId");
            }

            if(v0.eventId == null) {
                v1 = String.valueOf(v1).concat(" eventId");
            }

            if(v0.vastEvent == null) {
                v1 = String.valueOf(v1).concat(" vastEvent");
            }

            if(v0.appState == null) {
                v1 = String.valueOf(v1).concat(" appState");
            }

            if(v0.nativeTime == null) {
                v1 = String.valueOf(v1).concat(" nativeTime");
            }

            if(v0.nativeVolume == null) {
                v1 = String.valueOf(v1).concat(" nativeVolume");
            }

            if(v0.nativeViewAttached == null) {
                v1 = String.valueOf(v1).concat(" nativeViewAttached");
            }

            if(v0.nativeViewHidden == null) {
                v1 = String.valueOf(v1).concat(" nativeViewHidden");
            }

            if(v0.nativeViewBounds == null) {
                v1 = String.valueOf(v1).concat(" nativeViewBounds");
            }

            if(v0.nativeViewVisibleBounds == null) {
                v1 = String.valueOf(v1).concat(" nativeViewVisibleBounds");
            }

            if(!v1.isEmpty()) {
                String v3 = "Missing required properties:";
                v1 = String.valueOf(v1);
                v1 = v1.length() != 0 ? v3.concat(v1) : new String(v3);
                throw new IllegalStateException(v1);
            }

            return new d(v0.queryId, v0.eventId, v0.vastEvent, v0.appState, v0.nativeTime.longValue(), v0.nativeVolume.doubleValue(), v0.nativeViewAttached.booleanValue(), v0.nativeViewHidden.booleanValue(), v0.nativeViewBounds, v0.nativeViewVisibleBounds, null);
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a eventId(String arg2) {
            if(arg2 != null) {
                this.eventId = arg2;
                return this;
            }

            throw new NullPointerException("Null eventId");
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeTime(long arg1) {
            this.nativeTime = Long.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewAttached(boolean arg1) {
            this.nativeViewAttached = Boolean.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewBounds(m arg2) {
            if(arg2 != null) {
                this.nativeViewBounds = arg2;
                return this;
            }

            throw new NullPointerException("Null nativeViewBounds");
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewHidden(boolean arg1) {
            this.nativeViewHidden = Boolean.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeViewVisibleBounds(m arg2) {
            if(arg2 != null) {
                this.nativeViewVisibleBounds = arg2;
                return this;
            }

            throw new NullPointerException("Null nativeViewVisibleBounds");
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a nativeVolume(double arg1) {
            this.nativeVolume = Double.valueOf(arg1);
            return this;
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a queryId(String arg2) {
            if(arg2 != null) {
                this.queryId = arg2;
                return this;
            }

            throw new NullPointerException("Null queryId");
        }

        public com.google.ads.interactivemedia.v3.impl.data.a$a vastEvent(String arg2) {
            if(arg2 != null) {
                this.vastEvent = arg2;
                return this;
            }

            throw new NullPointerException("Null vastEvent");
        }
    }

    private final String appState;
    private final String eventId;
    private final long nativeTime;
    private final boolean nativeViewAttached;
    private final m nativeViewBounds;
    private final boolean nativeViewHidden;
    private final m nativeViewVisibleBounds;
    private final double nativeVolume;
    private final String queryId;
    private final String vastEvent;

    private d(String arg1, String arg2, String arg3, String arg4, long arg5, double arg7, boolean arg9, boolean arg10, m arg11, m arg12) {
        super();
        this.queryId = arg1;
        this.eventId = arg2;
        this.vastEvent = arg3;
        this.appState = arg4;
        this.nativeTime = arg5;
        this.nativeVolume = arg7;
        this.nativeViewAttached = arg9;
        this.nativeViewHidden = arg10;
        this.nativeViewBounds = arg11;
        this.nativeViewVisibleBounds = arg12;
    }

    d(String arg1, String arg2, String arg3, String arg4, long arg5, double arg7, boolean arg9, boolean arg10, m arg11, m arg12, com.google.ads.interactivemedia.v3.impl.data.d$1 arg13) {
        this(arg1, arg2, arg3, arg4, arg5, arg7, arg9, arg10, arg11, arg12);
    }

    public String appState() {
        return this.appState;
    }

    public boolean equals(Object arg8) {
        boolean v0 = true;
        if((((d)arg8)) == this) {
            return 1;
        }

        if((arg8 instanceof a)) {
            if(!this.queryId.equals(((a)arg8).queryId()) || !this.eventId.equals(((a)arg8).eventId()) || !this.vastEvent.equals(((a)arg8).vastEvent()) || !this.appState.equals(((a)arg8).appState()) || this.nativeTime != ((a)arg8).nativeTime() || Double.doubleToLongBits(this.nativeVolume) != Double.doubleToLongBits(((a)arg8).nativeVolume()) || this.nativeViewAttached != ((a)arg8).nativeViewAttached() || this.nativeViewHidden != ((a)arg8).nativeViewHidden() || !this.nativeViewBounds.equals(((a)arg8).nativeViewBounds()) || !this.nativeViewVisibleBounds.equals(((a)arg8).nativeViewVisibleBounds())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public String eventId() {
        return this.eventId;
    }

    public int hashCode() {
        int v1 = 1000003;
        int v0 = ((((((this.queryId.hashCode() ^ v1) * v1 ^ this.eventId.hashCode()) * v1 ^ this.vastEvent.hashCode()) * v1 ^ this.appState.hashCode()) * v1 ^ (((int)(this.nativeTime >>> 32 ^ this.nativeTime)))) * v1 ^ (((int)(Double.doubleToLongBits(this.nativeVolume) >>> 32 ^ Double.doubleToLongBits(this.nativeVolume))))) * v1;
        int v3 = 1237;
        int v2 = this.nativeViewAttached ? 1231 : 1237;
        v0 = (v0 ^ v2) * v1;
        if(this.nativeViewHidden) {
            v3 = 1231;
        }

        return ((v0 ^ v3) * v1 ^ this.nativeViewBounds.hashCode()) * v1 ^ this.nativeViewVisibleBounds.hashCode();
    }

    public long nativeTime() {
        return this.nativeTime;
    }

    public boolean nativeViewAttached() {
        return this.nativeViewAttached;
    }

    public m nativeViewBounds() {
        return this.nativeViewBounds;
    }

    public boolean nativeViewHidden() {
        return this.nativeViewHidden;
    }

    public m nativeViewVisibleBounds() {
        return this.nativeViewVisibleBounds;
    }

    public double nativeVolume() {
        return this.nativeVolume;
    }

    public String queryId() {
        return this.queryId;
    }

    public String toString() {
        String v0 = this.queryId;
        String v1 = this.eventId;
        String v2 = this.vastEvent;
        String v3 = this.appState;
        long v4 = this.nativeTime;
        double v6 = this.nativeVolume;
        boolean v8 = this.nativeViewAttached;
        boolean v9 = this.nativeViewHidden;
        String v10 = String.valueOf(this.nativeViewBounds);
        String v11 = String.valueOf(this.nativeViewVisibleBounds);
        StringBuilder v13 = new StringBuilder(String.valueOf(v0).length() + 229 + String.valueOf(v1).length() + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v10).length() + String.valueOf(v11).length());
        v13.append("ActivityMonitorData{queryId=");
        v13.append(v0);
        v13.append(", eventId=");
        v13.append(v1);
        v13.append(", vastEvent=");
        v13.append(v2);
        v13.append(", appState=");
        v13.append(v3);
        v13.append(", nativeTime=");
        v13.append(v4);
        v13.append(", nativeVolume=");
        v13.append(v6);
        v13.append(", nativeViewAttached=");
        v13.append(v8);
        v13.append(", nativeViewHidden=");
        v13.append(v9);
        v13.append(", nativeViewBounds=");
        v13.append(v10);
        v13.append(", nativeViewVisibleBounds=");
        v13.append(v11);
        v13.append("}");
        return v13.toString();
    }

    public String vastEvent() {
        return this.vastEvent;
    }
}

