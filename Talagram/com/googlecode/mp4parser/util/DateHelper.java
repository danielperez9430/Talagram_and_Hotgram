package com.googlecode.mp4parser.util;

import java.util.Date;

public class DateHelper {
    public DateHelper() {
        super();
    }

    public static Date convert(long arg3) {
        return new Date((arg3 - 2082844800) * 1000);
    }

    public static long convert(Date arg4) {
        return arg4.getTime() / 1000 + 2082844800;
    }
}

