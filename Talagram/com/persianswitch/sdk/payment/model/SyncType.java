package com.persianswitch.sdk.payment.model;

import com.persianswitch.sdk.base.utils.func.Option;
import com.persianswitch.sdk.base.utils.strings.StringUtils;

public final class SyncType {
    public static final SyncType a;
    public static final SyncType b;
    public static final SyncType[] c;
    private final int d;
    private final int e;
    private final int f;

    static {
        SyncType.a = new SyncType(201, 100, 1);
        SyncType.b = new SyncType(201, 101, 1);
        SyncType.c = new SyncType[]{SyncType.b, SyncType.a};
    }

    private SyncType(int arg1, int arg2, int arg3) {
        super();
        this.d = arg1;
        this.e = arg2;
        this.f = arg3;
    }

    public static SyncType a(String arg4) {
        String[] v4 = arg4.split("\\.", 3);
        return new SyncType(Option.a(StringUtils.e(v4[0]), Integer.valueOf(0)).intValue(), Option.a(StringUtils.e(v4[1]), Integer.valueOf(0)).intValue(), Option.a(StringUtils.e(v4[2]), Integer.valueOf(0)).intValue());
    }

    public static SyncType a(int arg1, int arg2, int arg3) {
        return new SyncType(arg1, arg2, arg3);
    }

    public int a() {
        return this.d;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public String d() {
        return StringUtils.a(".", new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f)});
    }
}

