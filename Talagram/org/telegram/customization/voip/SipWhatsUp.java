package org.telegram.customization.voip;

import android.content.Context;
import android.util.Log;
import com.google.a.f;
import f.l;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import okhttp3.aa;
import org.telegram.customization.Model.WhatsupNotif;
import org.telegram.customization.Model.voip.WhatsUpBaseResponse;
import org.telegram.customization.i.c.e;
import org.telegram.customization.i.c.h;
import org.telegram.customization.j.a.a;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC$User;
import utils.a.b;

public class SipWhatsUp {
    enum WhatsUpState {
        public static final enum WhatsUpState ALIVE;
        public static final enum WhatsUpState CLOSED;

        static {
            WhatsUpState.CLOSED = new WhatsUpState("CLOSED", 0);
            WhatsUpState.ALIVE = new WhatsUpState("ALIVE", 1);
            WhatsUpState.$VALUES = new WhatsUpState[]{WhatsUpState.CLOSED, WhatsUpState.ALIVE};
        }

        private WhatsUpState(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static WhatsUpState valueOf(String arg1) {
            return Enum.valueOf(WhatsUpState.class, arg1);
        }

        public static WhatsUpState[] values() {
            // Method was not decompiled
        }
    }

    Context ctx;
    volatile WhatsUpState state;
    h voipApi;

    public SipWhatsUp(Context arg2) {
        super();
        this.state = WhatsUpState.CLOSED;
        this.ctx = arg2;
        ApplicationLoader.getComponent().a(this);
    }

    public void startConnection() {
        if(this.state != WhatsUpState.CLOSED) {
            return;
        }

        try {
            new Thread() {
                public void run() {
                    super.run();
                    try {
                        SipWhatsUp.this.startConnectionInternal();
                        return;
                    }
                    catch(Throwable ) {
                        return;
                    }
                }
            }.start();
            return;
        }
        catch(Throwable ) {
            return;
        }
    }

    public void startConnectionInternal() {
        if(this.state != WhatsUpState.CLOSED) {
            return;
        }

        User v0 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
        if(v0 == null) {
            return;
        }

        this.state = WhatsUpState.ALIVE;
        b.k(false);
        org.telegram.customization.voip.SipWhatsUp$2 v2 = new e(new a()) {
            public void getWhatsUpFailure(Object arg3, aa arg4, Object arg5, l arg6) {
                b.k(true);
                SipWhatsUp.this.state = WhatsUpState.CLOSED;
                long v3 = this.val$injectable.sipPrefs.d();
                if(v3 == 120) {
                    this.val$injectable.sipPrefs.a(30000);
                }
                else {
                    this.val$injectable.sipPrefs.a(v3 * 2);
                }
            }

            public void getWhatsUpResult(WhatsUpBaseResponse arg3, aa arg4, Object arg5, l arg6) {
                try {
                    Iterator v3_1 = arg3.getData().getNotifs().iterator();
                    if(!v3_1.hasNext()) {
                        goto label_57;
                    }

                    Object v3_2 = v3_1.next();
                    Log.d("LINPHONE_TAG", "register info : " + ((WhatsupNotif)v3_2).getParams().getUser() + " : " + ((WhatsupNotif)v3_2).getParams().getPassword());
                    Log.d("slsCall getWhatsUpResul", "getWhatsUpResult register info : " + ((WhatsupNotif)v3_2).getParams().getUser() + " : " + ((WhatsupNotif)v3_2).getParams().getPassword());
                    this.val$injectable.sipWrapper.setWhatsupNotif(((WhatsupNotif)v3_2));
                    this.val$injectable.sipWrapper.register(((WhatsupNotif)v3_2).getParams().getDomain(), ((WhatsupNotif)v3_2).getParams().getPort(), ((WhatsupNotif)v3_2).getParams().getUser(), ((WhatsupNotif)v3_2).getParams().getPassword());
                    b.q(new f().a(v3_2));
                }
                catch(Throwable v3) {
                    v3.printStackTrace();
                }

            label_57:
                b.k(true);
                SipWhatsUp.this.state = WhatsUpState.CLOSED;
                this.val$injectable.sipPrefs.a(3000);
            }
        };
        Log.d("alireza", "alireza call sip");
        try {
            StringBuilder v1_1 = new StringBuilder();
            v1_1.append(v0.phone);
            v1_1.append("");
            this.voipApi.a(new org.telegram.customization.i.c.f(((e)v2)), ((long)v0.id), org.telegram.customization.util.a.c(v1_1.toString()));
        }
        catch(UnsupportedEncodingException v0_1) {
            v0_1.printStackTrace();
        }
        catch(NoSuchAlgorithmException v1) {
            h v3 = this.voipApi;
            org.telegram.customization.i.c.f v4 = new org.telegram.customization.i.c.f(((e)v2));
            long v5 = ((long)v0.id);
            v3.a(v4, v5, v0.phone + "");
            v1.printStackTrace();
        }
    }
}

