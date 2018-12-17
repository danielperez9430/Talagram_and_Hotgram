package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.SecureDocument;
import org.telegram.tgnet.TLRPC$TL_account_saveSecureValue;
import org.telegram.tgnet.TLRPC$TL_error;
import org.telegram.tgnet.TLRPC$TL_secureRequiredType;
import org.telegram.tgnet.TLRPC$TL_secureValue;

public final class -$$Lambda$PassportActivity$19$1$0kRj7ir3NwvnbVVMIq70mwI_7cA implements Runnable {
    public -$$Lambda$PassportActivity$19$1$0kRj7ir3NwvnbVVMIq70mwI_7cA(org.telegram.ui.PassportActivity$19$1 arg3, TL_error arg4, ErrorRunnable arg5, String arg6, TL_account_saveSecureValue arg7, boolean arg8, TL_secureRequiredType arg9, TL_secureRequiredType arg10, TL_secureValue arg11, TL_secureValue arg12, ArrayList arg13, SecureDocument arg14, SecureDocument arg15, SecureDocument arg16, ArrayList arg17, String arg18, String arg19, int arg20, Runnable arg21) {
        super();
        this.f$0 = arg3;
        this.f$1 = arg4;
        this.f$2 = arg5;
        this.f$3 = arg6;
        this.f$4 = arg7;
        this.f$5 = arg8;
        this.f$6 = arg9;
        this.f$7 = arg10;
        this.f$8 = arg11;
        this.f$9 = arg12;
        this.f$10 = arg13;
        this.f$11 = arg14;
        this.f$12 = arg15;
        this.f$13 = arg16;
        this.f$14 = arg17;
        this.f$15 = arg18;
        this.f$16 = arg19;
        this.f$17 = arg20;
        this.f$18 = arg21;
    }

    public final void run() {
        org.telegram.ui.PassportActivity$19$1.lambda$onResult$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18);
    }
}

