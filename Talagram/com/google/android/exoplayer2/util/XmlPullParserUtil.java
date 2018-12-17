package com.google.android.exoplayer2.util;

import org.xmlpull.v1.XmlPullParser;

public final class XmlPullParserUtil {
    private XmlPullParserUtil() {
        super();
    }

    public static String getAttributeValue(XmlPullParser arg3, String arg4) {
        int v0 = arg3.getAttributeCount();
        int v1;
        for(v1 = 0; v1 < v0; ++v1) {
            if(arg4.equals(arg3.getAttributeName(v1))) {
                return arg3.getAttributeValue(v1);
            }
        }

        return null;
    }

    public static boolean isEndTag(XmlPullParser arg1) {
        boolean v1 = arg1.getEventType() == 3 ? true : false;
        return v1;
    }

    public static boolean isEndTag(XmlPullParser arg1, String arg2) {
        boolean v1 = !XmlPullParserUtil.isEndTag(arg1) || !arg1.getName().equals(arg2) ? false : true;
        return v1;
    }

    public static boolean isStartTag(XmlPullParser arg1) {
        boolean v1 = arg1.getEventType() == 2 ? true : false;
        return v1;
    }

    public static boolean isStartTag(XmlPullParser arg1, String arg2) {
        boolean v1 = !XmlPullParserUtil.isStartTag(arg1) || !arg1.getName().equals(arg2) ? false : true;
        return v1;
    }
}

