package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractContainerBox;
import java.util.Iterator;

public class MediaBox extends AbstractContainerBox {
    public static final String TYPE = "mdia";

    public MediaBox() {
        super("mdia");
    }

    public HandlerBox getHandlerBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof HandlerBox));

        return ((HandlerBox)v1);
    }

    public MediaHeaderBox getMediaHeaderBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof MediaHeaderBox));

        return ((MediaHeaderBox)v1);
    }

    public MediaInformationBox getMediaInformationBox() {
        Object v1;
        Iterator v0 = this.getBoxes().iterator();
        do {
            if(!v0.hasNext()) {
                return null;
            }

            v1 = v0.next();
        }
        while(!(v1 instanceof MediaInformationBox));

        return ((MediaInformationBox)v1);
    }
}

