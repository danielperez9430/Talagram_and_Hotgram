package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.ki;

@ki(a=f.class) public abstract class CompanionData {
    public enum a {
        public static final enum a Html;
        public static final enum a IFrame;
        public static final enum a Static;

        static {
            a.Html = new a("Html", 0);
            a.Static = new a("Static", 1);
            a.IFrame = new a("IFrame", 2);
            a.$VALUES = new a[]{a.Html, a.Static, a.IFrame};
        }

        private a(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static a valueOf(String arg1) {
            return Enum.valueOf(a.class, arg1);
        }

        public static a[] values() {
            // Method was not decompiled
        }
    }

    private String companionId;

    public CompanionData() {
        super();
    }

    public abstract String clickThroughUrl();

    public String companionId() {
        return this.companionId;
    }

    private static CompanionData create(String arg1, String arg2, String arg3, a arg4) {
        return new f(arg1, arg2, arg3, arg4);
    }

    public static CompanionData create(String arg0, String arg1, String arg2, String arg3, a arg4) {
        CompanionData v1 = CompanionData.create(arg1, arg2, arg3, arg4);
        v1.companionId = arg0;
        return v1;
    }

    public abstract String size();

    public abstract String src();

    public String toString() {
        String v0 = this.companionId();
        String v1 = this.size();
        String v2 = this.src();
        String v3 = this.clickThroughUrl();
        String v4 = String.valueOf(this.type());
        StringBuilder v6 = new StringBuilder(String.valueOf(v0).length() + 66 + String.valueOf(v1).length() + String.valueOf(v2).length() + String.valueOf(v3).length() + String.valueOf(v4).length());
        v6.append("CompanionData [companionId=");
        v6.append(v0);
        v6.append(", size=");
        v6.append(v1);
        v6.append(", src=");
        v6.append(v2);
        v6.append(", clickThroughUrl=");
        v6.append(v3);
        v6.append(", type=");
        v6.append(v4);
        v6.append("]");
        return v6.toString();
    }

    public abstract a type();
}

