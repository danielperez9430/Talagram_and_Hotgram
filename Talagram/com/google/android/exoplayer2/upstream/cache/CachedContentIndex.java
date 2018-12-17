package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final int VERSION = 2;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray idToKey;
    private final HashMap keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File arg2) {
        this(arg2, null);
    }

    public CachedContentIndex(File arg2, byte[] arg3) {
        boolean v0 = arg3 != null ? true : false;
        this(arg2, arg3, v0);
    }

    public CachedContentIndex(File arg3, byte[] arg4, boolean arg5) {
        super();
        this.encrypt = arg5;
        boolean v0 = true;
        if(arg4 != null) {
            if(arg4.length == 16) {
            }
            else {
                v0 = false;
            }

            Assertions.checkArgument(v0);
            try {
                this.cipher = CachedContentIndex.getCipher();
                this.secretKeySpec = new SecretKeySpec(arg4, "AES");
            }
            catch(NoSuchPaddingException v3) {
                throw new IllegalStateException(((Throwable)v3));
            }
        }
        else {
            Assertions.checkState((((int)arg5)) ^ 1);
            this.cipher = null;
            this.secretKeySpec = null;
        }

        this.keyToContent = new HashMap();
        this.idToKey = new SparseArray();
        this.atomicFile = new AtomicFile(new File(arg3, "cached_content_index.exi"));
    }

    private void add(CachedContent arg3) {
        this.keyToContent.put(arg3.key, arg3);
        this.idToKey.put(arg3.id, arg3.key);
    }

    private CachedContent addNew(String arg3) {
        CachedContent v1 = new CachedContent(CachedContentIndex.getNewId(this.idToKey), arg3);
        this.add(v1);
        this.changed = true;
        return v1;
    }

    public void applyContentMetadataMutations(String arg1, ContentMetadataMutations arg2) {
        if(this.getOrAdd(arg1).applyMetadataMutations(arg2)) {
            this.changed = true;
        }
    }

    public int assignIdForKey(String arg1) {
        return this.getOrAdd(arg1).id;
    }

    public CachedContent get(String arg2) {
        return this.keyToContent.get(arg2);
    }

    public Collection getAll() {
        return this.keyToContent.values();
    }

    private static Cipher getCipher() {
        if(Util.SDK_INT != 18) {
            goto label_7;
        }

        try {
            return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
        }
        catch(Throwable ) {
        label_7:
            return Cipher.getInstance("AES/CBC/PKCS5PADDING");
        }
    }

    public ContentMetadata getContentMetadata(String arg1) {
        DefaultContentMetadata v1_2;
        CachedContent v1 = this.get(arg1);
        if(v1 != null) {
            ContentMetadata v1_1 = v1.getMetadata();
        }
        else {
            v1_2 = DefaultContentMetadata.EMPTY;
        }

        return ((ContentMetadata)v1_2);
    }

    public String getKeyForId(int arg2) {
        return this.idToKey.get(arg2);
    }

    public Set getKeys() {
        return this.keyToContent.keySet();
    }

    public static int getNewId(SparseArray arg3) {
        int v0 = arg3.size();
        int v2 = v0 == 0 ? 0 : arg3.keyAt(v0 - 1) + 1;
        if(v2 < 0) {
            v2 = 0;
            while(v2 < v0) {
                if(v2 != arg3.keyAt(v2)) {
                }
                else {
                    ++v2;
                    continue;
                }

                return v2;
            }
        }

        return v2;
    }

    public CachedContent getOrAdd(String arg2) {
        CachedContent v0_1;
        Object v0 = this.keyToContent.get(arg2);
        if(v0 == null) {
            v0_1 = this.addNew(arg2);
        }

        return v0_1;
    }

    public void load() {
        Assertions.checkState(this.changed ^ 1);
        if(!this.readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void maybeRemove(String arg3) {
        Object v0 = this.keyToContent.get(arg3);
        if(v0 != null && (((CachedContent)v0).isEmpty()) && !((CachedContent)v0).isLocked()) {
            this.keyToContent.remove(arg3);
            this.idToKey.remove(((CachedContent)v0).id);
            this.changed = true;
        }
    }

    private boolean readFile() {
        int v2_1;
        int v4;
        int v1_1;
        DataInputStream v3;
        BufferedInputStream v2;
        DataInputStream v1 = null;
        try {
            v2 = new BufferedInputStream(this.atomicFile.openRead());
            v3 = new DataInputStream(((InputStream)v2));
        }
        catch(Throwable v0) {
            v3 = v1;
            goto label_73;
        }
        catch(IOException ) {
            v3 = v1;
            goto label_77;
        }

        try {
            v1_1 = v3.readInt();
            if(v1_1 >= 0) {
                v4 = 2;
                if(v1_1 > v4) {
                }
                else if((v3.readInt() & 1) == 0) {
                    goto label_40;
                }
                else if(this.cipher == null) {
                    goto label_19;
                }
                else {
                    goto label_21;
                }
            }

            goto label_67;
        }
        catch(Throwable v0) {
            goto label_73;
        }
        catch(IOException ) {
            goto label_77;
        }

    label_19:
        Util.closeQuietly(((Closeable)v3));
        return 0;
    label_21:
        int v5 = 16;
        try {
            byte[] v5_1 = new byte[v5];
            v3.readFully(v5_1);
            IvParameterSpec v7 = new IvParameterSpec(v5_1);
            try {
                this.cipher.init(v4, this.secretKeySpec, ((AlgorithmParameterSpec)v7));
            }
            catch(InvalidAlgorithmParameterException v1_2) {
                throw new IllegalStateException(((Throwable)v1_2));
            }

            v3 = new DataInputStream(new CipherInputStream(((InputStream)v2), this.cipher));
            goto label_43;
        label_40:
            if(this.encrypt) {
                this.changed = true;
            }

        label_43:
            v2_1 = v3.readInt();
            v4 = 0;
            v5 = 0;
            while(v4 < v2_1) {
                CachedContent v7_1 = CachedContent.readFromStream(v1_1, v3);
                this.add(v7_1);
                v5 += v7_1.headerHashCode(v1_1);
                ++v4;
            }

            v1_1 = v3.readInt();
            if(v3.read() == -1) {
            }
            else {
                goto label_59;
            }
        }
        catch(Throwable v0) {
            goto label_73;
        }
        catch(IOException ) {
            goto label_77;
        }

        v2_1 = 1;
        goto label_60;
    label_59:
        v2_1 = 0;
    label_60:
        if(v1_1 == v5) {
            if(v2_1 == 0) {
            }
            else {
                Util.closeQuietly(((Closeable)v3));
                return 1;
            }
        }

        Util.closeQuietly(((Closeable)v3));
        return 0;
    label_77:
        if(v3 != null) {
            Util.closeQuietly(((Closeable)v3));
        }

        return 0;
    label_73:
        if(v3 != null) {
            Util.closeQuietly(((Closeable)v3));
        }

        throw v0;
    label_67:
        Util.closeQuietly(((Closeable)v3));
        return 0;
    }

    public void removeEmpty() {
        String[] v0 = new String[this.keyToContent.size()];
        this.keyToContent.keySet().toArray(((Object[])v0));
        int v1 = v0.length;
        int v2;
        for(v2 = 0; v2 < v1; ++v2) {
            this.maybeRemove(v0[v2]);
        }
    }

    public void store() {
        if(!this.changed) {
            return;
        }

        this.writeFile();
        this.changed = false;
    }

    private void writeFile() {
        IOException v0_1;
        Closeable v1_4;
        Throwable v0_2;
        int v2;
        DataOutputStream v1_3;
        Closeable v0 = null;
        try {
            OutputStream v1_2 = this.atomicFile.startWrite();
            if(this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(v1_2);
            }
            else {
                this.bufferedOutputStream.reset(v1_2);
            }

            v1_3 = new DataOutputStream(this.bufferedOutputStream);
            v2 = 2;
        }
        catch(Throwable v1) {
            Throwable v7_1 = v1;
            v1_4 = v0;
            v0_2 = v7_1;
            goto label_79;
        }
        catch(IOException v1_1) {
            IOException v7 = v1_1;
            v1_4 = v0;
            v0_1 = v7;
            goto label_75;
        }

        try {
            v1_3.writeInt(v2);
            v1_3.writeInt(this.encrypt);
            if(this.encrypt) {
                byte[] v3 = new byte[16];
                new Random().nextBytes(v3);
                v1_3.write(v3);
                IvParameterSpec v4 = new IvParameterSpec(v3);
                try {
                    this.cipher.init(1, this.secretKeySpec, ((AlgorithmParameterSpec)v4));
                }
                catch(InvalidAlgorithmParameterException v0_3) {
                    throw new IllegalStateException(((Throwable)v0_3));
                }

                v1_3.flush();
                v1_3 = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
            }

            v1_3.writeInt(this.keyToContent.size());
            int v3_1 = 0;
            Iterator v4_1 = this.keyToContent.values().iterator();
            while(v4_1.hasNext()) {
                Object v5 = v4_1.next();
                ((CachedContent)v5).writeToStream(v1_3);
                v3_1 += ((CachedContent)v5).headerHashCode(v2);
            }

            v1_3.writeInt(v3_1);
            this.atomicFile.endWrite(((OutputStream)v1_3));
        }
        catch(IOException v0_1) {
            goto label_65;
        }
        catch(Throwable v0_2) {
            goto label_79;
        }

        Util.closeQuietly(v0);
        return;
    label_65:
        try {
        label_75:
            throw new CacheException(((Throwable)v0_1));
        }
        catch(Throwable v0_2) {
        }

    label_79:
        Util.closeQuietly(v1_4);
        throw v0_2;
    }
}

