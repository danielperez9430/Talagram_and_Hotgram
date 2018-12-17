package org.linphone.core.video;

import android.hardware.Camera$Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.linphone.core.VideoSize;

final class VideoUtil {
    private VideoUtil() {
        super();
    }

    public static List createList(List arg4) {
        ArrayList v0 = new ArrayList(arg4.size());
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            Object v1 = v4.next();
            ((List)v0).add(new VideoSize(((Camera$Size)v1).width, ((Camera$Size)v1).height));
        }

        return ((List)v0);
    }
}

