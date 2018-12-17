package com.persianswitch.sdk.base.webservice;

import android.content.Context;
import com.persianswitch.sdk.base.Config;
import com.persianswitch.sdk.base.webservice.data.WSResponse;

public interface IWebServiceCallback {
    void a();

    void a(WSStatus arg1, String arg2, WSResponse arg3);

    void a(String arg1, WSResponse arg2);

    boolean a(Context arg1, Config arg2, WSResponse arg3, WebService arg4, IWebServiceCallback arg5);

    void b();
}

