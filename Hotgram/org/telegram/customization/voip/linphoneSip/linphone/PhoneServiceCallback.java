package org.telegram.customization.voip.linphoneSip.linphone;

import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCore$RegistrationState;

public abstract class PhoneServiceCallback {
    public PhoneServiceCallback() {
        super();
    }

    public void callConnected() {
    }

    public void callReleased() {
    }

    public void incomingCall(LinphoneCall arg1) {
    }

    public void registrationState(RegistrationState arg1) {
    }
}

