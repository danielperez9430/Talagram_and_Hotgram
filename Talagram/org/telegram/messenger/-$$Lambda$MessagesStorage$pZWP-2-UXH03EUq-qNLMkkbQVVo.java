package org.telegram.messenger;

public final class -$$Lambda$MessagesStorage$pZWP-2-UXH03EUq-qNLMkkbQVVo implements Runnable {
    public -$$Lambda$MessagesStorage$pZWP-2-UXH03EUq-qNLMkkbQVVo(MessagesStorage arg1, int arg2, long arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        MessagesStorage.lambda$resetMentionsCount$49(this.f$0, this.f$1, this.f$2);
    }
}

