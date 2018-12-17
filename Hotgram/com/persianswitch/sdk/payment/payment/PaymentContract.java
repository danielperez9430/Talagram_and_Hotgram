package com.persianswitch.sdk.payment.payment;

import android.os.Bundle;
import com.persianswitch.sdk.base.webservice.WebService$WSStatus;
import com.persianswitch.sdk.base.webservice.data.WSResponse;
import com.persianswitch.sdk.payment.StateRecoverable;
import com.persianswitch.sdk.payment.model.PaymentProfile;
import com.persianswitch.sdk.payment.model.UserCard;

interface PaymentContract {
    public interface ActionListener extends com.persianswitch.sdk.base.BaseContract$ActionListener, StateRecoverable {
        void a();

        void a(boolean arg1);

        void b();

        void c();

        void c(Bundle arg1);

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();

        void j();

        void k();

        void l();

        void m();
    }

    public interface View extends com.persianswitch.sdk.base.BaseContract$View, ISuggestionUpdate {
        void a(long arg1);

        void a(Bundle arg1);

        void a(WSStatus arg1, String arg2, WSResponse arg3);

        void a(PaymentProfile arg1);

        void a(String arg1, PaymentProfile arg2);

        void a(String arg1, boolean arg2);

        void b();

        void b(Bundle arg1);

        void b(PaymentProfile arg1);

        void b(String arg1, boolean arg2);

        void b(boolean arg1);

        void c();

        void c(String arg1, boolean arg2);

        void c(boolean arg1);

        void d(String arg1, boolean arg2);

        String e();

        void e(String arg1, boolean arg2);

        UserCard f();

        void f(String arg1, boolean arg2);

        String g();

        String h();

        String i();

        String j();

        boolean k();

        void l();

        void m();

        void n();

        void o();
    }

}

