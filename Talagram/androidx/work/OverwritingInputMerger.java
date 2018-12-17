package androidx.work;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class OverwritingInputMerger extends i {
    public OverwritingInputMerger() {
        super();
    }

    public e a(List arg4) {
        a v0 = new a();
        HashMap v1 = new HashMap();
        Iterator v4 = arg4.iterator();
        while(v4.hasNext()) {
            ((Map)v1).putAll(v4.next().a());
        }

        v0.a(((Map)v1));
        return v0.a();
    }
}

