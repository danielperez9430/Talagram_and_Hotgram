package com.persianswitch.sdk.payment.managers;

import android.content.Context;
import com.persianswitch.sdk.base.BaseSetting;
import com.persianswitch.sdk.base.db.phoenix.query.Where;
import com.persianswitch.sdk.base.db.phoenix.repo.IPhoenixModel;
import com.persianswitch.sdk.base.log.SDKLog;
import com.persianswitch.sdk.base.utils.ResourceUtils;
import com.persianswitch.sdk.base.utils.strings.StringUtils;
import com.persianswitch.sdk.base.webservice.OpCode;
import com.persianswitch.sdk.base.webservice.ResultPack;
import com.persianswitch.sdk.base.webservice.StatusCode;
import com.persianswitch.sdk.base.webservice.SyncWebService;
import com.persianswitch.sdk.base.webservice.WebService$WSStatus;
import com.persianswitch.sdk.base.webservice.WebService;
import com.persianswitch.sdk.base.webservice.data.WSRequest;
import com.persianswitch.sdk.base.webservice.data.WSResponse;
import com.persianswitch.sdk.base.webservice.data.WSTranRequest;
import com.persianswitch.sdk.base.webservice.data.WSTranResponse$ExpirationStatus;
import com.persianswitch.sdk.base.webservice.data.WSTranResponse;
import com.persianswitch.sdk.payment.SDKConfig;
import com.persianswitch.sdk.payment.model.Bank;
import com.persianswitch.sdk.payment.model.HostDataRequestField;
import com.persianswitch.sdk.payment.model.TransactionStatus;
import com.persianswitch.sdk.payment.model.UserCard$CardProtocolConverter;
import com.persianswitch.sdk.payment.model.UserCard;
import com.persianswitch.sdk.payment.payment.ISuggestionUpdate;
import com.persianswitch.sdk.payment.repo.CardRepo;
import com.persianswitch.sdk.payment.webservice.SDKSyncWebServiceCallback;
import com.persianswitch.sdk.payment.webservice.SDKWebServiceCallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CardManager {
    class com.persianswitch.sdk.payment.managers.CardManager$1 implements SyncCardCallback {
        com.persianswitch.sdk.payment.managers.CardManager$1(CardManager arg1) {
            this.a = arg1;
            super();
        }

        public void a() {
            long v0 = System.currentTimeMillis();
            BaseSetting.b(CardManager.a(this.a), v0);
            SDKLog.a("CardManager", "onCardsSynced [timestamp = %d]", new Object[]{Long.valueOf(v0)});
        }

        public void a(WSResponse arg1) {
        }
    }

    class ResponseCardProtocolConverter implements ProtocolConverter {
        class ResponseCard {
            final List a;
            final boolean b;
            final boolean c;
            final boolean d;

            ResponseCard(List arg1, boolean arg2, boolean arg3, boolean arg4) {
                super();
                this.a = arg1;
                this.b = arg2;
                this.c = arg3;
                this.d = arg4;
            }
        }

        private final Context a;
        private final boolean b;
        private final UserCard c;
        private final CardProtocolConverter d;

        ResponseCardProtocolConverter(Context arg1, boolean arg2, UserCard arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
            this.d = new CardProtocolConverter(this.a);
        }

        public ResponseCard a(String arg12) {
            boolean v4_2;
            ExpirationStatus v5_2;
            String[] v12_1;
            boolean v0 = this.b;
            ArrayList v1 = new ArrayList();
            boolean v2 = true;
            try {
                v12_1 = StringUtils.a(arg12).split("&&", 3);
                ExpirationStatus v4 = ExpirationStatus.a(v12_1[1]);
                if(!StringUtils.a("0;0", v12_1[0])) {
                    Long v5 = StringUtils.d(StringUtils.a(v12_1[0]).split(";")[0]);
                    Long v6 = StringUtils.d(StringUtils.a(v12_1[0]).split(";")[1]);
                    this.c.g();
                    this.c.a(v5);
                    this.c.b(v6);
                    new CardRepo(this.a).a(this.c);
                    if(v5 != null && v6 != null) {
                        long v9 = 0;
                        if(v5.longValue() > v9 && v6.longValue() > v9) {
                            int v5_1 = Bank.a(v6.longValue()).c();
                            if(v5_1 > 0) {
                                if(this.c.a(true) == null) {
                                    this.c.a(ResourceUtils.a(this.a, "fa", v5_1), true);
                                }

                                if(this.c.a(false) != null) {
                                    goto label_65;
                                }

                                this.c.a(ResourceUtils.a(this.a, "en", v5_1), false);
                            }

                        label_65:
                            ((List)v1).add(this.c);
                        }
                    }
                }

                if(v4 == ExpirationStatus.b) {
                    int v4_1 = this.c.h() ^ 1;
                }
                else {
                    goto label_83;
                }
            }
            catch(Exception v12) {
                goto label_137;
            }

            try {
                this.c.d(true);
                new CardRepo(this.a).a(this.c);
                goto label_102;
            }
            catch(Exception v12) {
                v2 = v0;
                goto label_139;
            }

            try {
            label_83:
                v5_2 = ExpirationStatus.d;
                if(v4 == v5_2) {
                    if(!this.c.h()) {
                        goto label_91;
                    }

                    goto label_88;
                }
                else {
                    goto label_101;
                }
            }
            catch(Exception v12) {
            label_137:
                v2 = v0;
                v4_2 = false;
            }

        label_139:
            boolean v5_3 = false;
            goto label_140;
        label_88:
            v4_2 = true;
            v5_3 = true;
            goto label_93;
        label_91:
            v4_2 = false;
            v5_3 = false;
            try {
            label_93:
                this.c.d(false);
                new CardRepo(this.a).a(this.c);
                goto label_103;
            label_101:
                v4_2 = false;
            label_102:
                v5_3 = false;
            label_103:
                if(!this.b) {
                    goto label_131;
                }

                arg12 = StringUtils.a(v12_1[2]);
                if("1;1;1;1;".equals(arg12)) {
                    goto label_144;
                }

                if(!"0;0;0;0".equals(arg12)) {
                    goto label_117;
                }
            }
            catch(Exception v12) {
                v2 = v0;
                goto label_140;
            }

            v2 = false;
            goto label_144;
            try {
            label_117:
                v12_1 = StringUtils.a(arg12).split("&");
                int v0_1 = v12_1.length;
                int v6_1;
                for(v6_1 = 0; true; ++v6_1) {
                    if(v6_1 >= v0_1) {
                        goto label_144;
                    }

                    ((List)v1).add(this.d.a(v12_1[v6_1]));
                }
            }
            catch(Exception v12) {
            }

        label_140:
            SDKLog.a("CardManager", "New Card Don\'t Save In Database", ((Throwable)v12), new Object[0]);
            goto label_144;
        label_131:
            v2 = v0;
        label_144:
            return new ResponseCard(((List)v1), v2, v4_2, ((boolean)v5_2));
        }
    }

    public interface SyncCardCallback {
        void a();

        void a(WSResponse arg1);
    }

    public final SyncCardCallback a;
    private final Context b;

    public CardManager(Context arg2) {
        super();
        this.a = new com.persianswitch.sdk.payment.managers.CardManager$1(this);
        this.b = arg2;
    }

    static Context a(CardManager arg0) {
        return arg0.b;
    }

    private void a(WSResponse arg7, SyncCardCallback arg8) {
        String v0 = arg7.h()[1];
        if("0".equalsIgnoreCase(v0)) {
            this.c();
            if(arg8 != null) {
                arg8.a();
            }
        }
        else if(!StringUtils.a(v0)) {
            try {
                ArrayList v1 = new ArrayList();
                if(!StringUtils.a(v0)) {
                    CardProtocolConverter v2 = new CardProtocolConverter(this.b);
                    String[] v0_1 = v0.split("&");
                    int v3 = v0_1.length;
                    int v4;
                    for(v4 = 0; v4 < v3; ++v4) {
                        ((List)v1).add(v2.a(v0_1[v4]));
                    }
                }

                new CardRepo(this.b).a(Where.b());
                this.a(((List)v1), arg8);
            }
            catch(Exception ) {
            label_36:
                arg8.a(arg7);
            }
        }
        else {
            goto label_36;
        }
    }

    private void a(List arg3, SyncCardCallback arg4) {
        CardRepo v0 = new CardRepo(this.b.getApplicationContext());
        if(arg3 != null) {
            Iterator v3 = arg3.iterator();
            while(v3.hasNext()) {
                v0.a(v3.next());
            }
        }

        if(arg4 != null) {
            arg4.a();
        }
    }

    static void a(CardManager arg0, WSResponse arg1, SyncCardCallback arg2) {
        arg0.a(arg1, arg2);
    }

    public SyncCardCallback a() {
        return this.a;
    }

    public void a(SyncCardCallback arg7) {
        Context v0 = this.b;
        WSTranRequest v1 = WSTranRequest.a(this.b, new SDKConfig(), OpCode.i.a(), 0);
        v1.a(HostDataRequestField.a(v0).a());
        v1.a(new String[]{""});
        ResultPack v0_1 = SyncWebService.a(((WSRequest)v1)).a(this.b, new SDKSyncWebServiceCallback());
        WSResponse v1_1 = v0_1.b();
        if(v0_1.a() == TransactionStatus.a) {
            this.a(v1_1, arg7);
        }
        else {
            this.a().a(v1_1);
        }
    }

    public void a(boolean arg6, UserCard arg7, WSTranResponse arg8, ISuggestionUpdate arg9) {
        ResponseCard v0 = new ResponseCardProtocolConverter(this.b, arg6, arg7).a(arg8.k());
        boolean v1 = v0.c;
        boolean v2 = v0.d;
        if(v0.b) {
            this.c();
        }

        try {
            this.a(v0.a, this.a);
        }
        catch(Exception ) {
            this.a.a(((WSResponse)arg8));
        }

        if(arg6) {
            this.a.a();
        }

        boolean v4 = true;
        if(arg8.c() == StatusCode.w) {
            new CardRepo(this.b).d(((IPhoenixModel)arg7));
            v2 = false;
        }
        else if(arg8.c() == StatusCode.F) {
            arg7.d(false);
            new CardRepo(this.b).a(((IPhoenixModel)arg7));
            v2 = true;
        }
        else {
            v4 = v1;
        }

        if(arg9 != null && (v4)) {
            arg9.a(v2);
        }
    }

    public void b() {
        this.b(this.a);
    }

    public void b(SyncCardCallback arg6) {
        WSTranRequest v0 = WSTranRequest.a(this.b, new SDKConfig(), OpCode.i.a(), 0);
        v0.a(HostDataRequestField.a(this.b).a());
        v0.a(new String[]{""});
        WebService.b(((WSRequest)v0)).b(this.b, new SDKWebServiceCallback(arg6) {
            public void a() {
            }

            public void a(WSStatus arg1, String arg2, WSResponse arg3) {
                if(this.b != null) {
                    this.b.a(arg3);
                }
            }

            public void a(String arg2, WSResponse arg3) {
                CardManager.a(this.c, arg3, this.b);
            }
        });
    }

    private void c() {
        new CardRepo(this.b).a(Where.b());
    }
}

