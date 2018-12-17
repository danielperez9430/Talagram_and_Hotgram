package org.telegram.customization.j.a;

import android.app.Application;
import com.google.a.f;
import org.telegram.customization.i.b.e;
import org.telegram.customization.i.b.h;
import org.telegram.customization.i.i;
import org.telegram.customization.i.j;
import org.telegram.customization.k.b;
import org.telegram.customization.k.c;
import org.telegram.customization.util.a.d;
import org.telegram.customization.util.a.g;
import org.telegram.customization.voip.LinphoneSipWrapper;
import org.telegram.customization.voip.SipWhatsUp;
import org.telegram.messenger.ApplicationLoader;

public class a {
    public org.telegram.customization.util.a.a adsPrefs;
    public j api;
    private i apiMapper;
    public Application app;
    public org.telegram.customization.k.a bus;
    private b busMapper;
    public f gson;
    private boolean needsApi;
    private boolean needsEventBus;
    public d prefs;
    public g sipPrefs;
    public SipWhatsUp sipWhatsUp;
    public volatile LinphoneSipWrapper sipWrapper;
    public h vastApi;
    private org.telegram.customization.i.b.f vastApiCallbackMapper;

    public a(boolean arg2) {
        this(arg2, false);
    }

    public a() {
        this(false, false);
    }

    public a(boolean arg2, boolean arg3) {
        super();
        ApplicationLoader.getComponent().a(this);
        this.needsApi = arg2;
        this.needsEventBus = arg3;
    }

    public void attach() {
        if(this.needsApi) {
            org.telegram.customization.i.h v0 = this.getApiCallback();
            e v1 = this.getVastApiCallback();
            if(v0 != null) {
                this.apiMapper = new i(v0);
                this.api.a(this.apiMapper);
            }

            if(v1 == null) {
                goto label_18;
            }

            this.vastApiCallbackMapper = new org.telegram.customization.i.b.f(v1);
            this.vastApi.a(this.vastApiCallbackMapper);
        }

    label_18:
        if(this.needsEventBus) {
            c v0_1 = this.getEventBusCallback();
            if(v0_1 != null) {
                this.busMapper = new b(v0_1);
                this.bus.a(this.busMapper);
            }
        }
    }

    public void detach() {
        if((this.needsApi) && this.apiMapper != null) {
            this.api.b(this.apiMapper);
        }

        if((this.needsApi) && this.vastApiCallbackMapper != null) {
            this.vastApi.b(this.vastApiCallbackMapper);
        }

        if((this.needsEventBus) && this.busMapper != null) {
            this.bus.b(this.busMapper);
        }
    }

    protected org.telegram.customization.i.h getApiCallback() {
        return null;
    }

    protected c getEventBusCallback() {
        return null;
    }

    protected e getVastApiCallback() {
        return null;
    }
}

