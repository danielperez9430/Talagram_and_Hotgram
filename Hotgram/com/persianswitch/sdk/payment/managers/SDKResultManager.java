package com.persianswitch.sdk.payment.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.persianswitch.sdk.R$string;
import com.persianswitch.sdk.api.Response$General;
import com.persianswitch.sdk.api.Response$Payment;
import com.persianswitch.sdk.api.Response;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.webservice.StatusCode;
import com.persianswitch.sdk.base.webservice.data.WSTranRequest;
import com.persianswitch.sdk.payment.model.PaymentProfile;
import com.persianswitch.sdk.payment.model.TransactionStatus;

public final class SDKResultManager {
    public SDKResultManager() {
        super();
    }

    private static Bundle a(int arg2, String arg3) {
        Bundle v0 = new Bundle();
        v0.putInt(General.a, arg2);
        v0.putString(General.b, arg3);
        return v0;
    }

    public static Bundle a(Context arg1) {
        return SDKResultManager.a(2023, arg1.getString(string.asanpardakht_message_error_2023));
    }

    public static Bundle a(Context arg2, PaymentProfile arg3) {
        if(arg3.j() == TransactionStatus.a) {
            return SDKResultManager.b(arg2, arg3);
        }

        if(arg3.s()) {
            return SDKResultManager.d(arg2, arg3);
        }

        return SDKResultManager.c(arg2, arg3);
    }

    public static Long a(Context arg4, WSTranRequest arg5) {
        return Long.valueOf(BaseSetting.c(arg4) * 100000000 + arg5.a());
    }

    public static void a(Activity arg2, Bundle arg3) {
        Intent v0 = new Intent();
        v0.putExtra(Response.a, arg3);
        arg2.setResult(-1, v0);
        arg2.finish();
    }

    private static Bundle b(Context arg5, PaymentProfile arg6) {
        Bundle v5 = SDKResultManager.a(arg6.c(), arg5.getString(string.asanpardakht_message_sdk_status_successful));
        if(arg6.a() != null) {
            v5.putString(General.c, arg6.a());
        }

        if(arg6.b() != null) {
            v5.putString(General.d, arg6.b());
        }

        if(arg6.t().longValue() > 0) {
            v5.putLong(Payment.a, arg6.t().longValue());
        }

        return v5;
    }

    public static Bundle b(Context arg1) {
        return SDKResultManager.a(1102, arg1.getString(string.asanpardakht_message_sdk_status_register_need));
    }

    private static Bundle c(Context arg5, PaymentProfile arg6) {
        Bundle v5 = SDKResultManager.a(arg6.c(), arg5.getString(string.asanpardakht_message_sdk_status_failed));
        if(arg6.a() != null) {
            v5.putString(General.c, arg6.a());
        }

        if(arg6.b() != null) {
            v5.putString(General.d, arg6.b());
        }

        if(arg6.t().longValue() > 0) {
            v5.putLong(Payment.a, arg6.t().longValue());
        }

        return v5;
    }

    public static Bundle c(Context arg1) {
        int v0 = StatusCode.H.a();
        return SDKResultManager.a(v0, StatusCode.a(arg1, v0));
    }

    private static Bundle d(Context arg5, PaymentProfile arg6) {
        Bundle v5 = SDKResultManager.a(arg6.c(), arg5.getString(string.asanpardakht_message_sdk_status_unknown));
        if(arg6.a() != null) {
            v5.putString(General.c, arg6.a());
        }

        if(arg6.b() != null) {
            v5.putString(General.d, arg6.b());
        }

        if(arg6.t().longValue() > 0) {
            v5.putLong(Payment.a, arg6.t().longValue());
        }

        return v5;
    }

    public static Bundle d(Context arg1) {
        return SDKResultManager.a(2020, arg1.getString(string.asanpardakht_message_sdk_status_canceled));
    }

    public static Bundle e(Context arg1) {
        return SDKResultManager.a(2021, arg1.getString(string.asanpardakht_message_sdk_status_timeout));
    }

    public static boolean f(Context arg5) {
        boolean v5 = BaseSetting.c(arg5) <= 0 || (StringUtils.a(BaseSetting.h(arg5))) ? false : true;
        return v5;
    }
}

