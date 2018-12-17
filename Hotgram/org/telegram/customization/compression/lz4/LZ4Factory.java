package org.telegram.customization.compression.lz4;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Iterator;
import org.telegram.customization.compression.a.b;
import org.telegram.customization.compression.a.d;

public final class LZ4Factory {
    private static LZ4Factory JAVA_SAFE_INSTANCE;
    private static LZ4Factory JAVA_UNSAFE_INSTANCE;
    private static LZ4Factory NATIVE_INSTANCE;
    private final LZ4Compressor fastCompressor;
    private final LZ4FastDecompressor fastDecompressor;
    private final LZ4Compressor highCompressor;
    private final LZ4Compressor[] highCompressors;
    private final String impl;
    private final LZ4SafeDecompressor safeDecompressor;

    private LZ4Factory(String arg20) {
        LZ4Factory v0 = this;
        super();
        v0.highCompressors = new LZ4Compressor[18];
        v0.impl = arg20;
        StringBuilder v2 = new StringBuilder();
        v2.append("org.telegram.customization.compression.lz4.LZ4");
        v2.append(arg20);
        v2.append("Compressor");
        v0.fastCompressor = LZ4Factory.classInstance(v2.toString());
        v2 = new StringBuilder();
        v2.append("org.telegram.customization.compression.lz4.LZ4HC");
        v2.append(arg20);
        v2.append("Compressor");
        v0.highCompressor = LZ4Factory.classInstance(v2.toString());
        v2 = new StringBuilder();
        v2.append("org.telegram.customization.compression.lz4.LZ4");
        v2.append(arg20);
        v2.append("FastDecompressor");
        v0.fastDecompressor = LZ4Factory.classInstance(v2.toString());
        v2 = new StringBuilder();
        v2.append("org.telegram.customization.compression.lz4.LZ4");
        v2.append(arg20);
        v2.append("SafeDecompressor");
        v0.safeDecompressor = LZ4Factory.classInstance(v2.toString());
        Constructor v1 = v0.highCompressor.getClass().getDeclaredConstructor(Integer.TYPE);
        int v6 = 9;
        v0.highCompressors[v6] = v0.highCompressor;
        int v3;
        for(v3 = 1; v3 <= 17; ++v3) {
            if(v3 == v6) {
            }
            else {
                v0.highCompressors[v3] = v1.newInstance(Integer.valueOf(v3));
            }
        }

        byte[] v1_1 = new byte[]{97, 98, 99, 100, 32, 32, 32, 32, 32, 32, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106};
        Iterator v2_1 = Arrays.asList(new LZ4Compressor[]{v0.fastCompressor, v0.highCompressor}).iterator();
        while(true) {
            if(!v2_1.hasNext()) {
                return;
            }

            Object v6_1 = v2_1.next();
            int v12 = ((LZ4Compressor)v6_1).maxCompressedLength(v1_1.length);
            byte[] v3_1 = new byte[v12];
            int v4 = ((LZ4Compressor)v6_1).compress(v1_1, 0, v1_1.length, v3_1, 0, v12);
            byte[] v6_2 = new byte[v1_1.length];
            v0.fastDecompressor.decompress(v3_1, 0, v6_2, 0, v1_1.length);
            if(!Arrays.equals(v1_1, v6_2)) {
                goto label_123;
            }

            Arrays.fill(v6_2, 0);
            if(v0.safeDecompressor.decompress(v3_1, 0, v4, v6_2, 0) == v1_1.length && (Arrays.equals(v1_1, v6_2))) {
                continue;
            }

            break;
        }

        throw new AssertionError();
    label_123:
        throw new AssertionError();
    }

    private static Object classInstance(String arg1) {
        ClassLoader v0 = LZ4Factory.class.getClassLoader();
        if(v0 == null) {
            v0 = ClassLoader.getSystemClassLoader();
        }

        return v0.loadClass(arg1).getField("INSTANCE").get(null);
    }

    public LZ4Decompressor decompressor() {
        return this.fastDecompressor();
    }

    public LZ4Compressor fastCompressor() {
        return this.fastCompressor;
    }

    public LZ4FastDecompressor fastDecompressor() {
        return this.fastDecompressor;
    }

    public static LZ4Factory fastestInstance() {
        if(!b.a()) {
            if(b.class.getClassLoader() == ClassLoader.getSystemClassLoader()) {
            }
            else {
                return LZ4Factory.fastestJavaInstance();
            }
        }

        try {
            return LZ4Factory.nativeInstance();
        }
        catch(Throwable ) {
            return LZ4Factory.fastestJavaInstance();
        }
    }

    public static LZ4Factory fastestJavaInstance() {
        if(d.a()) {
            try {
                return LZ4Factory.unsafeInstance();
            }
            catch(Throwable ) {
                return LZ4Factory.safeInstance();
            }
        }

        return LZ4Factory.safeInstance();
    }

    public LZ4Compressor highCompressor() {
        return this.highCompressor;
    }

    public LZ4Compressor highCompressor(int arg2) {
        if(arg2 > 17) {
            arg2 = 17;
        }
        else if(arg2 < 1) {
            arg2 = 9;
        }

        return this.highCompressors[arg2];
    }

    private static LZ4Factory instance(String arg1) {
        try {
            return new LZ4Factory(arg1);
        }
        catch(Exception v1) {
            throw new AssertionError(v1);
        }
    }

    public static LZ4Factory nativeInstance() {
        LZ4Factory v1_1;
        Class v0 = LZ4Factory.class;
        __monitor_enter(v0);
        try {
            if(LZ4Factory.NATIVE_INSTANCE == null) {
                LZ4Factory.NATIVE_INSTANCE = LZ4Factory.instance("JNI");
            }

            v1_1 = LZ4Factory.NATIVE_INSTANCE;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public LZ4SafeDecompressor safeDecompressor() {
        return this.safeDecompressor;
    }

    public static LZ4Factory safeInstance() {
        LZ4Factory v1_1;
        Class v0 = LZ4Factory.class;
        __monitor_enter(v0);
        try {
            if(LZ4Factory.JAVA_SAFE_INSTANCE == null) {
                LZ4Factory.JAVA_SAFE_INSTANCE = LZ4Factory.instance("JavaSafe");
            }

            v1_1 = LZ4Factory.JAVA_SAFE_INSTANCE;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":" + this.impl;
    }

    public LZ4UnknownSizeDecompressor unknownSizeDecompressor() {
        return this.safeDecompressor();
    }

    public static LZ4Factory unsafeInstance() {
        LZ4Factory v1_1;
        Class v0 = LZ4Factory.class;
        __monitor_enter(v0);
        try {
            if(LZ4Factory.JAVA_UNSAFE_INSTANCE == null) {
                LZ4Factory.JAVA_UNSAFE_INSTANCE = LZ4Factory.instance("JavaUnsafe");
            }

            v1_1 = LZ4Factory.JAVA_UNSAFE_INSTANCE;
        }
        catch(Throwable v1) {
            __monitor_exit(v0);
            throw v1;
        }

        __monitor_exit(v0);
        return v1_1;
    }
}

