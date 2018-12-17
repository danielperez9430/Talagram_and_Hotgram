package com.persianswitch.sdk.payment.payment;

import android.os.Bundle;
import com.persianswitch.sdk.payment.StateRecoverable;
import com.persianswitch.sdk.payment.model.PaymentProfile;

public interface ReportContract {
    public interface ActionListener extends com.persianswitch.sdk.base.BaseContract$ActionListener, StateRecoverable {
        void a();

        void b();
    }

    public interface View extends com.persianswitch.sdk.base.BaseContract$View {
        void a(long arg1);

        void a(Bundle arg1);

        void a(PaymentProfile arg1);
    }

}

