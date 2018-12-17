package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import com.persianswitch.sdk.R$string;

public enum StatusCode {
    public static final enum StatusCode A;
    public static final enum StatusCode B;
    public static final enum StatusCode C;
    public static final enum StatusCode D;
    public static final enum StatusCode E;
    public static final enum StatusCode F;
    public static final enum StatusCode G;
    public static final enum StatusCode H;
    public static final enum StatusCode I;
    public static final enum StatusCode J;
    public static final enum StatusCode K;
    public static final enum StatusCode L;
    public static final enum StatusCode M;
    public static final enum StatusCode N;
    public static final enum StatusCode O;
    public static final enum StatusCode P;
    public static final enum StatusCode Q;
    public static final enum StatusCode R;
    public static final enum StatusCode S;
    public static final enum StatusCode T;
    public static final enum StatusCode U;
    public static final enum StatusCode V;
    public static final enum StatusCode W;
    public static final enum StatusCode X;
    public static final enum StatusCode Y;
    public static final enum StatusCode Z;
    public static final enum StatusCode a;
    public static final enum StatusCode aa;
    public static final enum StatusCode ab;
    private final int ac;
    private int ad;
    private boolean ae;
    public static final enum StatusCode b;
    public static final enum StatusCode c;
    public static final enum StatusCode d;
    public static final enum StatusCode e;
    public static final enum StatusCode f;
    public static final enum StatusCode g;
    public static final enum StatusCode h;
    public static final enum StatusCode i;
    public static final enum StatusCode j;
    public static final enum StatusCode k;
    public static final enum StatusCode l;
    public static final enum StatusCode m;
    public static final enum StatusCode n;
    public static final enum StatusCode o;
    public static final enum StatusCode p;
    public static final enum StatusCode q;
    public static final enum StatusCode r;
    public static final enum StatusCode s;
    public static final enum StatusCode t;
    public static final enum StatusCode u;
    public static final enum StatusCode v;
    public static final enum StatusCode w;
    public static final enum StatusCode x;
    public static final enum StatusCode y;
    public static final enum StatusCode z;

    static {
        StatusCode.a = new StatusCode("UNDEFINED_STATUS", 0, -1);
        StatusCode.b = new StatusCode("SUCCESS", 1, 0, string.asanpardakht_message_success);
        StatusCode.c = new StatusCode("FINANCIAL_ERROR", 2, 999);
        StatusCode.d = new StatusCode("UNKNOWN_TRANSACTION_RESULT", 3, 1001, string.asanpardakht_message_error_1001, true);
        StatusCode.e = new StatusCode("FAILED_TRANSACTION", 4, 1002, string.asanpardakht_message_error_1002);
        StatusCode.f = new StatusCode("GLOBAL_WARNING", 5, 1010, string.asanpardakht_message_error_1010);
        StatusCode.g = new StatusCode("DUPLICATE_MERCHANT_TRANSACTION", 6, 1098, string.asanpardakht_message_error_1098);
        StatusCode.h = new StatusCode("INVALID_MERCHANT_TIME", 7, 1099, string.asanpardakht_message_error_1099);
        StatusCode.i = new StatusCode("MERCHANT_BAD_REQUEST", 8, 1100, string.asanpardakht_message_error_1100);
        StatusCode.j = new StatusCode("BAD_REQUEST", 9, 1101, string.asanpardakht_message_error_1101);
        StatusCode.k = new StatusCode("APP_NOT_REGISTERED", 10, 1102, string.asanpardakht_message_error_1102);
        StatusCode.l = new StatusCode("APP_HAS_BEEN_DEACTIVATED", 11, 1103, string.asanpardakht_message_error_1103);
        StatusCode.m = new StatusCode("APP_WORM_STATUS", 12, 1104, string.asanpardakht_message_error_1104);
        StatusCode.n = new StatusCode("APP_MUST_UPDATE", 13, 1105, string.asanpardakht_message_error_1105);
        StatusCode.o = new StatusCode("WRONG_ACTIVATION_CODE", 14, 1106, string.asanpardakht_message_error_1106);
        StatusCode.p = new StatusCode("WRONG_ACTIVATION_DATA", 15, 1107, string.asanpardakht_message_error_1107);
        StatusCode.q = new StatusCode("APP_HAS_ACTIVE_USER", 16, 1108, string.asanpardakht_message_error_1108);
        StatusCode.r = new StatusCode("MAX_TRY_PASSWORD", 17, 1109, string.asanpardakht_message_error_1109);
        StatusCode.s = new StatusCode("SOON_SHAKE_AND_SAVE", 18, 1110, string.asanpardakht_message_error_1110);
        StatusCode.t = new StatusCode("TRANSACTION_NOT_FOUND", 19, 1111, string.asanpardakht_message_error_1111);
        StatusCode.u = new StatusCode("TRANSACTION_CAN_NOT_RESTORE", 20, 1112, string.asanpardakht_message_error_1112);
        StatusCode.v = new StatusCode("INVALID_CARD_NO", 21, 1113, string.asanpardakht_message_error_1113);
        StatusCode.w = new StatusCode("CARD_NOT_FOUND", 22, 1114, string.asanpardakht_message_error_1114);
        StatusCode.x = new StatusCode("CARD_HAS_BEEN_DEACTIVATED", 23, 1115, string.asanpardakht_message_error_1115);
        StatusCode.y = new StatusCode("SYNC_TIME_BY_SERVER_FAILED", 24, 1116, string.asanpardakht_message_error_1116);
        StatusCode.z = new StatusCode("NEED_APP_RE_VERIFICATION", 25, 1117, string.asanpardakht_message_error_1117);
        StatusCode.A = new StatusCode("NEED_SEND_CVV2_GLOBAL", 26, 1118, string.asanpardakht_message_error_1118);
        StatusCode.B = new StatusCode("NEED_SSL_CONNECTION", 27, 1119, string.asanpardakht_message_error_1119);
        StatusCode.C = new StatusCode("USER_IS_EXIST", 28, 1120, string.asanpardakht_message_error_1120);
        StatusCode.D = new StatusCode("WRONG_USER_OR_PASSWORD", 29, 1121, string.asanpardakht_message_error_1121);
        StatusCode.E = new StatusCode("NEED_SEND_CVV2", 30, 1123, string.asanpardakht_message_error_1123);
        StatusCode.F = new StatusCode("EXPIRATION_DATE_NOT_FOUND", 31, 1124, string.asanpardakht_message_error_1124);
        StatusCode.G = new StatusCode("MOBILE_OPERATOR_NOT_SPECIFY", 32, 1126, string.asanpardakht_message_error_1126);
        StatusCode.H = new StatusCode("SECURITY_REQUIREMENT_NOT_PASSED", 33, 1127, string.asanpardakht_message_error_1127);
        StatusCode.I = new StatusCode("NON_SECURITY_REQUIREMENT_NOT_PASSED", 34, 1128, string.asanpardakht_message_error_1128);
        StatusCode.J = new StatusCode("SYNC_DATA_CODE_NOT_FOUND", 35, 1130, string.asanpardakht_message_error_1130);
        StatusCode.K = new StatusCode("INVALID_MERCHANT_CODE", 36, 1135, string.asanpardakht_message_error_1135);
        StatusCode.L = new StatusCode("WRONG_UPLOAD_INFORMATION", 37, 1140, string.asanpardakht_message_error_1140);
        StatusCode.M = new StatusCode("WRONG_UPLOAD_FILE_SIZE", 38, 1141, string.asanpardakht_message_error_1140);
        StatusCode.N = new StatusCode("INVALID_FILE_FOR_UPLOAD", 39, 1142, string.asanpardakht_message_error_1142);
        StatusCode.O = new StatusCode("TOO_LONG_FILE_FOR_UPLOAD", 40, 1143, string.asanpardakht_message_error_1143);
        StatusCode.P = new StatusCode("WRONG_FILE_EXTENSION", 41, 1144, string.asanpardakht_message_error_1144);
        StatusCode.Q = new StatusCode("UPLOAD_FAILED", 42, 1145, string.asanpardakht_message_error_1145);
        StatusCode.R = new StatusCode("CAMPAIGN_TIME_IS_OVER", 43, 1150, string.asanpardakht_message_error_1150);
        StatusCode.S = new StatusCode("BACKUP_DATA_NOT_FOUND", 44, 1160, string.asanpardakht_message_error_1160);
        StatusCode.T = new StatusCode("CAMPAIGN_INFO_NOT_FOUND", 45, 1161, string.asanpardakht_message_error_1161);
        StatusCode.U = new StatusCode("DATA_NOT_FOUND", 46, 1161, string.asanpardakht_message_error_1161);
        StatusCode.V = new StatusCode("WRONG_DATA_TOKEN", 47, 1162, string.asanpardakht_message_error_1162);
        StatusCode.W = new StatusCode("ACTIVATION_CODE_HAS_ALREADY_BEEN_SENT", 48, 1171, string.asanpardakht_message_error_1171);
        StatusCode.X = new StatusCode("TOO_MANY_INVALID_ACTIVATION_CODE", 49, 1172, string.asanpardakht_message_error_1172);
        StatusCode.Y = new StatusCode("SERVER_INTERNAL_ERROR", 50, 1200, string.asanpardakht_message_error_1200);
        StatusCode.Z = new StatusCode("UNKNOWN_ERROR", 51, 1201, string.asanpardakht_message_error_1201, true);
        StatusCode.aa = new StatusCode("SERVER_IS_BUSY", 52, 1202, string.asanpardakht_message_error_1202);
        StatusCode.ab = new StatusCode("NOTIFICATION_CALL_ID_NOT_FOUND", 53, 1301, string.asanpardakht_message_error_1301);
        StatusCode.af = new StatusCode[]{StatusCode.a, StatusCode.b, StatusCode.c, StatusCode.d, StatusCode.e, StatusCode.f, StatusCode.g, StatusCode.h, StatusCode.i, StatusCode.j, StatusCode.k, StatusCode.l, StatusCode.m, StatusCode.n, StatusCode.o, StatusCode.p, StatusCode.q, StatusCode.r, StatusCode.s, StatusCode.t, StatusCode.u, StatusCode.v, StatusCode.w, StatusCode.x, StatusCode.y, StatusCode.z, StatusCode.A, StatusCode.B, StatusCode.C, StatusCode.D, StatusCode.E, StatusCode.F, StatusCode.G, StatusCode.H, StatusCode.I, StatusCode.J, StatusCode.K, StatusCode.L, StatusCode.M, StatusCode.N, StatusCode.O, StatusCode.P, StatusCode.Q, StatusCode.R, StatusCode.S, StatusCode.T, StatusCode.U, StatusCode.V, StatusCode.W, StatusCode.X, StatusCode.Y, StatusCode.Z, StatusCode.aa, StatusCode.ab};
    }

    private StatusCode(String arg2, int arg3, int arg4) {
        this(arg2, arg3, arg4, -1);
    }

    private StatusCode(String arg7, int arg8, int arg9, int arg10) {
        this(arg7, arg8, arg9, arg10, false);
    }

    private StatusCode(String arg1, int arg2, int arg3, int arg4, boolean arg5) {
        super(arg1, arg2);
        this.ac = arg3;
        this.ad = arg4;
        this.ae = arg5;
    }

    public static StatusCode a(int arg5) {
        if(arg5 == 0) {
            return StatusCode.b;
        }

        if(arg5 >= 1 && arg5 <= 999) {
            return StatusCode.c;
        }

        StatusCode[] v0 = StatusCode.values();
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            StatusCode v3 = v0[v2];
            if(v3.a() == arg5) {
                return v3;
            }
        }

        return StatusCode.a;
    }

    public int a() {
        return this.ac;
    }

    private String a(Context arg2) {
        if(this != StatusCode.a) {
            if(this == StatusCode.c) {
            }
            else {
                return arg2.getString(this.ad);
            }
        }

        return null;
    }

    public static String a(Context arg3, int arg4) {
        String v0 = StatusCode.a(arg4).a(arg3);
        if(v0 != null) {
        }
        else {
            v0 = arg3.getString(string.asanpardakht_message_error_general, new Object[]{Integer.valueOf(arg4)});
        }

        return v0;
    }

    public boolean b() {
        return this.ae;
    }

    public static StatusCode valueOf(String arg1) {
        return Enum.valueOf(StatusCode.class, arg1);
    }

    public static StatusCode[] values() {
        // Method was not decompiled
    }
}

