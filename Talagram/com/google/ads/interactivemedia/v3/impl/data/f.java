package com.google.ads.interactivemedia.v3.impl.data;

final class f extends CompanionData {
    private final String clickThroughUrl;
    private final String size;
    private final String src;
    private final a type;

    f(String arg1, String arg2, String arg3, a arg4) {
        super();
        if(arg1 != null) {
            this.size = arg1;
            if(arg2 != null) {
                this.src = arg2;
                if(arg3 != null) {
                    this.clickThroughUrl = arg3;
                    if(arg4 != null) {
                        this.type = arg4;
                        return;
                    }

                    throw new NullPointerException("Null type");
                }

                throw new NullPointerException("Null clickThroughUrl");
            }

            throw new NullPointerException("Null src");
        }

        throw new NullPointerException("Null size");
    }

    public String clickThroughUrl() {
        return this.clickThroughUrl;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if((((f)arg5)) == this) {
            return 1;
        }

        if((arg5 instanceof CompanionData)) {
            if(!this.size.equals(((CompanionData)arg5).size()) || !this.src.equals(((CompanionData)arg5).src()) || !this.clickThroughUrl.equals(((CompanionData)arg5).clickThroughUrl()) || !this.type.equals(((CompanionData)arg5).type())) {
                v0 = false;
            }
            else {
            }

            return v0;
        }

        return 0;
    }

    public int hashCode() {
        return (((this.size.hashCode() ^ 1000003) * 1000003 ^ this.src.hashCode()) * 1000003 ^ this.clickThroughUrl.hashCode()) * 1000003 ^ this.type.hashCode();
    }

    public String size() {
        return this.size;
    }

    public String src() {
        return this.src;
    }

    public a type() {
        return this.type;
    }
}

