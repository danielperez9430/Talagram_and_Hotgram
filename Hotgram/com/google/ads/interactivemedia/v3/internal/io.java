package com.google.ads.interactivemedia.v3.internal;

final class io extends b {
    private final int appVersion;
    private final String packageName;

    io(int arg1, String arg2) {
        super();
        this.appVersion = arg1;
        if(arg2 != null) {
            this.packageName = arg2;
            return;
        }

        throw new NullPointerException("Null packageName");
    }

    public int appVersion() {
        return this.appVersion;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((io)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof b)) {
            if(this.appVersion != ((b)arg5).appVersion() || !this.packageName.equals(((b)arg5).packageName())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        return (this.appVersion ^ 1000003) * 1000003 ^ this.packageName.hashCode();
    }

    public String packageName() {
        return this.packageName;
    }

    public String toString() {
        int v0 = this.appVersion;
        String v1 = this.packageName;
        StringBuilder v3 = new StringBuilder(String.valueOf(v1).length() + 51);
        v3.append("MarketAppInfo{appVersion=");
        v3.append(v0);
        v3.append(", packageName=");
        v3.append(v1);
        v3.append("}");
        return v3.toString();
    }
}

