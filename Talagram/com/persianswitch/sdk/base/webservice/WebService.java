package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import com.persianswitch.sdk.R$string;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.base.manager.RequestTimeManager;
import com.persianswitch.sdk.base.security.SecurityManager;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.webservice.data.WSRequest;
import com.persianswitch.sdk.base.webservice.data.WSResponse;
import com.persianswitch.sdk.base.webservice.data.WSTranRequest;
import com.persianswitch.sdk.base.webservice.data.WSTranResponse;
import com.persianswitch.sdk.base.webservice.exception.WSCallException;
import com.persianswitch.sdk.base.webservice.exception.WSConnectTimeoutException;
import com.persianswitch.sdk.base.webservice.exception.WSHttpStatusException;
import com.persianswitch.sdk.base.webservice.exception.WSParseResponseException;
import com.persianswitch.sdk.base.webservice.exception.WSRequestEncryptionException;
import com.persianswitch.sdk.base.webservice.exception.WSSSLConfigurationException;
import com.persianswitch.sdk.base.webservice.exception.WSSocketTimeoutException;
import com.persianswitch.sdk.payment.SDKConfig;

public class WebService {
    public enum WSStatus {
        public static final enum WSStatus a;
        public static final enum WSStatus b;
        public static final enum WSStatus c;
        public static final enum WSStatus d;
        public static final enum WSStatus e;
        public static final enum WSStatus f;

        static {
            WSStatus.a = new WSStatus("CONNECT_ERROR", 0);
            WSStatus.b = new WSStatus("BAD_REQUEST", 1);
            WSStatus.c = new WSStatus("NO_RESPONSE", 2);
            WSStatus.d = new WSStatus("SERVER_INTERNAL_ERROR", 3);
            WSStatus.e = new WSStatus("PARSE_ERROR", 4);
            WSStatus.f = new WSStatus("BUSINESS_ERROR", 5);
            WSStatus.g = new WSStatus[]{WSStatus.a, WSStatus.b, WSStatus.c, WSStatus.d, WSStatus.e, WSStatus.f};
        }

        private WSStatus(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public boolean a() {
            boolean v0 = this == WSStatus.c || this == WSStatus.d || this == WSStatus.e ? true : false;
            return v0;
        }

        public static WSStatus valueOf(String arg1) {
            return Enum.valueOf(WSStatus.class, arg1);
        }

        public static WSStatus[] values() {
            // Method was not decompiled
        }
    }

    private WSRequest a;

    WebService(WSRequest arg2) {
        super();
        if(arg2 != null) {
            this.a = arg2;
            return;
        }

        throw new IllegalArgumentException("request can not be null!");
    }

    private void a(Context arg3, Config arg4, HttpResult arg5, IWebServiceCallback arg6) {
        WSTranResponse v5_1;
        int v5;
        WSStatus v4;
        arg6.b();
        WSResponse v1 = null;
        if((arg5.a() instanceof WSConnectTimeoutException)) {
            v4 = WSStatus.a;
            v5 = string.asanpardakht_message_error_connect;
            goto label_7;
        }

        if((arg5.a() instanceof WSHttpStatusException)) {
            WSCallException v4_1 = arg5.a();
            if(((WSHttpStatusException)v4_1).a() >= 500) {
                if(((WSHttpStatusException)v4_1).a() >= 600) {
                }
                else {
                    v4 = WSStatus.d;
                    v5 = string.asanpardakht_message_error_server_internal_error;
                    goto label_7;
                }
            }
        }
        else if(!(arg5.a() instanceof WSSSLConfigurationException)) {
            if((arg5.a() instanceof WSRequestEncryptionException)) {
            }
            else {
                goto label_34;
            }
        }

        v4 = WSStatus.b;
        v5 = string.asanpardakht_message_error_bad_request;
        goto label_7;
    label_34:
        if((arg5.a() instanceof WSParseResponseException)) {
            v4 = WSStatus.e;
            v5 = string.asanpardakht_message_error_bad_response;
            goto label_7;
        }

        if(!(arg5.a() instanceof WSSocketTimeoutException) && arg5.c() != null) {
            try {
                if((this.a instanceof WSTranRequest)) {
                    v5_1 = WSTranResponse.b(arg5.c());
                }
                else {
                    WSResponse v5_2 = WSResponse.a(arg5.c());
                }

                this.a(arg3, arg4, ((WSResponse)v5_1), arg6);
                return;
            }
            catch(Exception ) {
                v4 = WSStatus.e;
                v5 = string.asanpardakht_message_error_internal_error;
                goto label_7;
            }
        }

        v4 = WSStatus.c;
        v5 = string.asanpardakht_message_error_no_response;
    label_7:
        arg6.a(v4, arg3.getString(v5), v1);
    }

    private void a(Context arg7, Config arg8, WSResponse arg9, IWebServiceCallback arg10) {
        if(arg10.a(arg7, arg8, arg9, this, arg10)) {
            return;
        }

        if(arg9.c() == StatusCode.b) {
            arg10.a(StringUtils.a(arg9.d()), arg9);
        }
        else {
            String v8 = arg9.d();
            if(StringUtils.a(v8)) {
                v8 = StatusCode.a(arg7, arg9.b());
            }

            arg10.a(WSStatus.f, v8, arg9);
        }
    }

    static void a(WebService arg0, Context arg1, Config arg2, HttpResult arg3, IWebServiceCallback arg4) {
        arg0.a(arg1, arg2, arg3, arg4);
    }

    WSWorker a(Context arg7, Config arg8, long arg9, IWebServiceCallback arg11) {
        byte[] v3;
        this.a.a(BaseSetting.b(arg7));
        this.a.e(String.valueOf(new RequestTimeManager().a(arg7)));
        String v0 = this.a.h();
        String v1 = this.a.a(arg8);
        new byte[0];
        try {
            v3 = SecurityManager.a(arg7).a();
        }
        catch(Exception ) {
            SDKLog.c("WebService", "error in generate transaction secret key", new Object[0]);
        }

        return new Builder(arg7, arg8).a(v1).b(v0).a(OpCode.a(this.a.c()).b()).a(v3).a(arg9).a(new WSWorkerListener(arg11, arg7, arg8) {
            public void a() {
                this.a.a();
            }

            public void a(HttpResult arg5) {
                WebService.a(this.d, this.b, this.c, arg5, this.a);
            }
        }).a();
    }

    public void a(Context arg7, long arg8, IWebServiceCallback arg10) {
        this.a(arg7, new SDKConfig(), arg8, arg10);
    }

    public void a(Context arg1, SDKConfig arg2, long arg3, IWebServiceCallback arg5) {
        this.a(arg1, ((Config)arg2), arg3, arg5).a();
    }

    public static WebService b(WSRequest arg1) {
        return new WebService(arg1);
    }

    public void b(Context arg7, IWebServiceCallback arg8) {
        this.a(arg7, new SDKConfig(), 0, arg8);
    }
}

