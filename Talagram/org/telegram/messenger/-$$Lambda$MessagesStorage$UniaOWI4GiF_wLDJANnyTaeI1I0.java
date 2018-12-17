package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$UniaOWI4GiF_wLDJANnyTaeI1I0 implements Runnable {
    public -$$Lambda$MessagesStorage$UniaOWI4GiF_wLDJANnyTaeI1I0(MessagesStorage arg1, int arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        MessagesStorage.lambda$updateDbToLastVersion$1(this.f$0, this.f$1);
    }
}

