package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map$Entry;
import java.util.Map;

public class ContentMetadataMutations {
    private final Map editedValues;
    private final List removedValues;

    public ContentMetadataMutations() {
        super();
        this.editedValues = new HashMap();
        this.removedValues = new ArrayList();
    }

    private ContentMetadataMutations checkAndSet(String arg3, Object arg4) {
        this.editedValues.put(Assertions.checkNotNull(arg3), Assertions.checkNotNull(arg4));
        this.removedValues.remove(arg3);
        return this;
    }

    public Map getEditedValues() {
        HashMap v0 = new HashMap(this.editedValues);
        Iterator v1 = v0.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = ((Map$Entry)v2).getValue();
            if(!(v3 instanceof byte[])) {
                continue;
            }

            ((Map$Entry)v2).setValue(Arrays.copyOf(((byte[])v3), v3.length));
        }

        return Collections.unmodifiableMap(((Map)v0));
    }

    public List getRemovedValues() {
        return Collections.unmodifiableList(new ArrayList(this.removedValues));
    }

    public ContentMetadataMutations remove(String arg2) {
        this.removedValues.add(arg2);
        this.editedValues.remove(arg2);
        return this;
    }

    public ContentMetadataMutations set(String arg1, long arg2) {
        return this.checkAndSet(arg1, Long.valueOf(arg2));
    }

    public ContentMetadataMutations set(String arg1, String arg2) {
        return this.checkAndSet(arg1, arg2);
    }

    public ContentMetadataMutations set(String arg2, byte[] arg3) {
        return this.checkAndSet(arg2, Arrays.copyOf(arg3, arg3.length));
    }
}

