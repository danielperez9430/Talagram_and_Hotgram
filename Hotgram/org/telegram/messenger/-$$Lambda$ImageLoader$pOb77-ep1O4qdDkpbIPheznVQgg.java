package org.telegram.messenger;

public final class -$$Lambda$ImageLoader$pOb77-ep1O4qdDkpbIPheznVQgg implements Runnable {
    public -$$Lambda$ImageLoader$pOb77-ep1O4qdDkpbIPheznVQgg(ImageLoader arg1, String arg2) {
        super();
        this.f$0 = arg1;
        this.f$1 = arg2;
    }

    public final void run() {
        ImageLoader.lambda$cancelForceLoadingForImageReceiver$4(this.f$0, this.f$1);
    }
}

