package com.persianswitch.sdk.payment.model.res;

import android.content.Context;
import com.persianswitch.sdk.base.webservice.data.WSResponse;
import org.json.JSONObject;

abstract class AbsResponse {
    AbsResponse(Context arg1, WSResponse arg2) {
        super();
        this.a(arg1, arg2.g());
    }

    abstract void a(Context arg1, JSONObject arg2);
}

