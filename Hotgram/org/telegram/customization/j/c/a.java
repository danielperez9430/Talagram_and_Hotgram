package org.telegram.customization.j.c;

import android.app.Application;
import android.content.Context;
import org.telegram.customization.util.a.d;
import org.telegram.customization.util.a.g;
import org.telegram.customization.voip.LinphoneSipWrapper;
import org.telegram.customization.voip.SipWhatsUp;
import org.telegram.customization.voip.SipWrapper;

public class a {
    private Application a;

    public a(Application arg1) {
        super();
        this.a = arg1;
    }

    Application a() {
        return this.a;
    }

    d a(Application arg2) {
        return new d(((Context)arg2));
    }

    org.telegram.customization.util.a.a b(Application arg2) {
        return new org.telegram.customization.util.a.a(((Context)arg2));
    }

    g c(Application arg2) {
        return new g(((Context)arg2));
    }

    SipWrapper d(Application arg2) {
        return new SipWrapper(((Context)arg2));
    }

    LinphoneSipWrapper e(Application arg2) {
        return new LinphoneSipWrapper(((Context)arg2));
    }

    SipWhatsUp f(Application arg2) {
        return new SipWhatsUp(((Context)arg2));
    }
}

