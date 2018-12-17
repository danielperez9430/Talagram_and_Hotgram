package org.telegram.messenger;

public final class -$$Lambda$ImageLoader$XrXp32CG-3M3L0PNTbZtlsZU8Ds implements Runnable {
    public -$$Lambda$ImageLoader$XrXp32CG-3M3L0PNTbZtlsZU8Ds(ImageLoader arg1, int arg2, ImageReceiver arg3) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
        this.f$2 = arg3;
    }

    public final void run() {
        ImageLoader.lambda$cancelLoadingForImageReceiver$2(this.f$0, this.f$1, this.f$2);
    }
}

