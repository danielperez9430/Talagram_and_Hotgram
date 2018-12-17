package com.google.android.exoplayer2.source;

public final class DefaultCompositeSequenceableLoaderFactory implements CompositeSequenceableLoaderFactory {
    public DefaultCompositeSequenceableLoaderFactory() {
        super();
    }

    public SequenceableLoader createCompositeSequenceableLoader(SequenceableLoader[] arg2) {
        return new CompositeSequenceableLoader(arg2);
    }
}

