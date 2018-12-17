package com.google.android.exoplayer2.upstream.cache;

import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.TreeSet;

final class CachedContent {
    private static final int VERSION_MAX = 2147483647;
    private static final int VERSION_METADATA_INTRODUCED = 2;
    private final TreeSet cachedSpans;
    public final int id;
    public final String key;
    private boolean locked;
    private DefaultContentMetadata metadata;

    public CachedContent(int arg1, String arg2) {
        super();
        this.id = arg1;
        this.key = arg2;
        this.metadata = DefaultContentMetadata.EMPTY;
        this.cachedSpans = new TreeSet();
    }

    public void addSpan(SimpleCacheSpan arg2) {
        this.cachedSpans.add(arg2);
    }

    public boolean applyMetadataMutations(ContentMetadataMutations arg3) {
        DefaultContentMetadata v0 = this.metadata;
        this.metadata = this.metadata.copyWithMutationsApplied(arg3);
        return this.metadata.equals(v0) ^ 1;
    }

    public boolean equals(Object arg5) {
        boolean v0 = true;
        if(this == (((CachedContent)arg5))) {
            return 1;
        }

        if(arg5 != null) {
            if(this.getClass() != arg5.getClass()) {
            }
            else {
                if(this.id != ((CachedContent)arg5).id || !this.key.equals(((CachedContent)arg5).key) || !this.cachedSpans.equals(((CachedContent)arg5).cachedSpans) || !this.metadata.equals(((CachedContent)arg5).metadata)) {
                    v0 = false;
                }
                else {
                }

                return v0;
            }
        }

        return 0;
    }

    public long getCachedBytesLength(long arg11, long arg13) {
        SimpleCacheSpan v0 = this.getSpan(arg11);
        if(v0.isHoleSpan()) {
            arg11 = v0.isOpenEnded() ? 9223372036854775807L : v0.length;
            return -Math.min(arg11, arg13);
        }

        long v1 = arg11 + arg13;
        long v3 = v0.position + v0.length;
        if(v3 < v1) {
            Iterator v0_1 = this.cachedSpans.tailSet(v0, false).iterator();
            do {
                if(v0_1.hasNext()) {
                    Object v5 = v0_1.next();
                    if(((SimpleCacheSpan)v5).position > v3) {
                    }
                    else {
                        v3 = Math.max(v3, ((SimpleCacheSpan)v5).position + ((SimpleCacheSpan)v5).length);
                        if(v3 < v1) {
                            continue;
                        }
                    }
                }

                break;
            }
            while(true);
        }

        return Math.min(v3 - arg11, arg13);
    }

    public ContentMetadata getMetadata() {
        return this.metadata;
    }

    public SimpleCacheSpan getSpan(long arg7) {
        SimpleCacheSpan v0 = SimpleCacheSpan.createLookup(this.key, arg7);
        Object v1 = this.cachedSpans.floor(v0);
        if(v1 != null && ((SimpleCacheSpan)v1).position + ((SimpleCacheSpan)v1).length > arg7) {
            return ((SimpleCacheSpan)v1);
        }

        Object v0_1 = this.cachedSpans.ceiling(v0);
        SimpleCacheSpan v7 = v0_1 == null ? SimpleCacheSpan.createOpenHole(this.key, arg7) : SimpleCacheSpan.createClosedHole(this.key, arg7, ((SimpleCacheSpan)v0_1).position - arg7);
        return v7;
    }

    public TreeSet getSpans() {
        return this.cachedSpans;
    }

    public int hashCode() {
        return this.headerHashCode(2147483647) * 31 + this.cachedSpans.hashCode();
    }

    public int headerHashCode(int arg6) {
        int v0 = this.id * 31 + this.key.hashCode();
        if(arg6 < 2) {
            long v1 = ContentMetadataInternal.getContentLength(this.metadata);
            v0 *= 31;
            arg6 = ((int)(v1 ^ v1 >>> 32));
        }
        else {
            v0 *= 31;
            arg6 = this.metadata.hashCode();
        }

        return arg6;
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean isLocked() {
        return this.locked;
    }

    public static CachedContent readFromStream(int arg3, DataInputStream arg4) {
        CachedContent v2 = new CachedContent(arg4.readInt(), arg4.readUTF());
        if(arg3 < 2) {
            long v3 = arg4.readLong();
            ContentMetadataMutations v0 = new ContentMetadataMutations();
            ContentMetadataInternal.setContentLength(v0, v3);
            v2.applyMetadataMutations(v0);
        }
        else {
            v2.metadata = DefaultContentMetadata.readFromStream(arg4);
        }

        return v2;
    }

    public boolean removeSpan(CacheSpan arg2) {
        if(this.cachedSpans.remove(arg2)) {
            arg2.file.delete();
            return 1;
        }

        return 0;
    }

    public void setLocked(boolean arg1) {
        this.locked = arg1;
    }

    public SimpleCacheSpan touch(SimpleCacheSpan arg5) {
        Assertions.checkState(this.cachedSpans.remove(arg5));
        SimpleCacheSpan v0 = arg5.copyWithUpdatedLastAccessTime(this.id);
        if(arg5.file.renameTo(v0.file)) {
            this.cachedSpans.add(v0);
            return v0;
        }

        StringBuilder v2 = new StringBuilder();
        v2.append("Renaming of ");
        v2.append(arg5.file);
        v2.append(" to ");
        v2.append(v0.file);
        v2.append(" failed.");
        throw new CacheException(v2.toString());
    }

    public void writeToStream(DataOutputStream arg2) {
        arg2.writeInt(this.id);
        arg2.writeUTF(this.key);
        this.metadata.writeToStream(arg2);
    }
}

