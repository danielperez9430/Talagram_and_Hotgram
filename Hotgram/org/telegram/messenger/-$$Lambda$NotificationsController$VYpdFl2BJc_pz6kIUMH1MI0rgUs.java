package org.telegram.messenger;

import android.graphics.ImageDecoder$ImageInfo;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.graphics.ImageDecoder$Source;
import android.graphics.ImageDecoder;

public final class -$$Lambda$NotificationsController$VYpdFl2BJc_pz6kIUMH1MI0rgUs implements ImageDecoder$OnHeaderDecodedListener {
    static {
        -$$Lambda$NotificationsController$VYpdFl2BJc_pz6kIUMH1MI0rgUs.INSTANCE = new -$$Lambda$NotificationsController$VYpdFl2BJc_pz6kIUMH1MI0rgUs();
    }

    private -$$Lambda$NotificationsController$VYpdFl2BJc_pz6kIUMH1MI0rgUs() {
        super();
    }

    public final void onHeaderDecoded(ImageDecoder arg1, ImageDecoder$ImageInfo arg2, ImageDecoder$Source arg3) {
        NotificationsController.lambda$showExtraNotifications$28(arg1, arg2, arg3);
    }
}

