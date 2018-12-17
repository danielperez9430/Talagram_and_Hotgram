package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.ParsingLoadable$Parser;
import java.io.InputStream;
import java.util.List;

public final class FilteringManifestParser implements Parser {
    private final Parser parser;
    private final List streamKeys;

    public FilteringManifestParser(Parser arg1, List arg2) {
        super();
        this.parser = arg1;
        this.streamKeys = arg2;
    }

    public FilterableManifest parse(Uri arg2, InputStream arg3) {
        Object v2 = this.parser.parse(arg2, arg3);
        if(this.streamKeys != null) {
            if(this.streamKeys.isEmpty()) {
            }
            else {
                v2 = ((FilterableManifest)v2).copy(this.streamKeys);
            }
        }

        return ((FilterableManifest)v2);
    }

    public Object parse(Uri arg1, InputStream arg2) {
        return this.parse(arg1, arg2);
    }
}

