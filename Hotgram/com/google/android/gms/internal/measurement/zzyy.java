package com.google.android.gms.internal.measurement;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzyy {
    private final ByteBuffer zzbva;
    private zzut zzcfa;
    private int zzcfb;

    private zzyy(ByteBuffer arg2) {
        super();
        this.zzbva = arg2;
        this.zzbva.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzyy(byte[] arg1, int arg2, int arg3) {
        this(ByteBuffer.wrap(arg1, arg2, arg3));
    }

    private static int zza(CharSequence arg8) {
        int v4;
        int v0 = arg8.length();
        int v1 = 0;
        int v2;
        for(v2 = 0; v2 < v0; ++v2) {
            if(arg8.charAt(v2) >= 128) {
                break;
            }
        }

        int v3 = v0;
        while(true) {
            if(v2 < v0) {
                v4 = arg8.charAt(v2);
                int v5 = 2048;
                if(v4 < v5) {
                    v3 += 127 - v4 >>> 31;
                    ++v2;
                    continue;
                }
                else {
                    break;
                }
            }

            goto label_50;
        }

        v4 = arg8.length();
        while(v2 < v4) {
            int v6 = arg8.charAt(v2);
            if(v6 < v5) {
                v1 += 127 - v6 >>> 31;
            }
            else {
                v1 += 2;
                if(55296 <= v6 && v6 <= 57343) {
                    if(Character.codePointAt(arg8, v2) >= 65536) {
                        ++v2;
                    }
                    else {
                        StringBuilder v1_1 = new StringBuilder(39);
                        v1_1.append("Unpaired surrogate at index ");
                        v1_1.append(v2);
                        throw new IllegalArgumentException(v1_1.toString());
                    }
                }
            }

            ++v2;
        }

        v3 += v1;
    label_50:
        if(v3 >= v0) {
            return v3;
        }

        long v0_1 = ((long)v3);
        StringBuilder v3_1 = new StringBuilder(54);
        v3_1.append("UTF-8 length does not fit in int: ");
        v3_1.append(v0_1 + 4294967296L);
        throw new IllegalArgumentException(v3_1.toString());
    }

    public final void zza(int arg2, double arg3) {
        this.zzc(arg2, 1);
        long v2 = Double.doubleToLongBits(arg3);
        if(this.zzbva.remaining() >= 8) {
            this.zzbva.putLong(v2);
            return;
        }

        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    public final void zza(int arg2, float arg3) {
        this.zzc(arg2, 5);
        arg2 = Float.floatToIntBits(arg3);
        if(this.zzbva.remaining() >= 4) {
            this.zzbva.putInt(arg2);
            return;
        }

        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    public final void zza(int arg2, long arg3) {
        this.zzc(arg2, 0);
        this.zzbh(arg3);
    }

    public final void zza(int arg2, zzzg arg3) {
        this.zzc(arg2, 2);
        this.zzb(arg3);
    }

    public static int zzb(int arg1, zzzg arg2) {
        arg1 = zzyy.zzbb(arg1);
        int v2 = arg2.zzvu();
        return arg1 + (zzyy.zzbj(v2) + v2);
    }

    public final void zzb(zzzg arg2) {
        this.zzca(arg2.zzza());
        arg2.zza(this);
    }

    public final void zzb(int arg4, String arg5) {
        this.zzc(arg4, 2);
        try {
            arg4 = zzyy.zzbj(arg5.length());
            if(arg4 == zzyy.zzbj(arg5.length() * 3)) {
                int v0 = this.zzbva.position();
                if(this.zzbva.remaining() >= arg4) {
                    this.zzbva.position(v0 + arg4);
                    zzyy.zzd(((CharSequence)arg5), this.zzbva);
                    int v5 = this.zzbva.position();
                    this.zzbva.position(v0);
                    this.zzca(v5 - v0 - arg4);
                    this.zzbva.position(v5);
                    return;
                }

                throw new zzyz(v0 + arg4, this.zzbva.limit());
            }

            this.zzca(zzyy.zza(((CharSequence)arg5)));
            zzyy.zzd(((CharSequence)arg5), this.zzbva);
            return;
        }
        catch(BufferOverflowException v4) {
            zzyz v5_1 = new zzyz(this.zzbva.position(), this.zzbva.limit());
            v5_1.initCause(((Throwable)v4));
            throw v5_1;
        }
    }

    public final void zzb(int arg2, boolean arg3) {
        this.zzc(arg2, 0);
        byte v2 = ((byte)(((int)arg3)));
        if(this.zzbva.hasRemaining()) {
            this.zzbva.put(v2);
            return;
        }

        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    public static int zzbb(int arg0) {
        return zzyy.zzbj(arg0 << 3);
    }

    public static int zzbc(int arg0) {
        if(arg0 >= 0) {
            return zzyy.zzbj(arg0);
        }

        return 10;
    }

    private final void zzbh(long arg6) {
        while((-128 & arg6) != 0) {
            this.zzbz((((int)arg6)) & 127 | 128);
            arg6 >>>= 7;
        }

        this.zzbz(((int)arg6));
    }

    public static int zzbi(long arg5) {
        long v2 = 0;
        if((-128 & arg5) == v2) {
            return 1;
        }

        if((-16384 & arg5) == v2) {
            return 2;
        }

        if((-2097152 & arg5) == v2) {
            return 3;
        }

        if((-268435456 & arg5) == v2) {
            return 4;
        }

        if((-34359738368L & arg5) == v2) {
            return 5;
        }

        if((-4398046511104L & arg5) == v2) {
            return 6;
        }

        if((-562949953421312L & arg5) == v2) {
            return 7;
        }

        if((-72057594037927936L & arg5) == v2) {
            return 8;
        }

        if((arg5 & -9223372036854775808L) == v2) {
            return 9;
        }

        return 10;
    }

    public static int zzbj(int arg1) {
        if((arg1 & -128) == 0) {
            return 1;
        }

        if((arg1 & -16384) == 0) {
            return 2;
        }

        if((-2097152 & arg1) == 0) {
            return 3;
        }

        if((arg1 & -268435456) == 0) {
            return 4;
        }

        return 5;
    }

    private final void zzbz(int arg3) {
        byte v3 = ((byte)arg3);
        if(this.zzbva.hasRemaining()) {
            this.zzbva.put(v3);
            return;
        }

        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    public static int zzc(int arg0, String arg1) {
        return zzyy.zzbb(arg0) + zzyy.zzfx(arg1);
    }

    public final void zzc(int arg1, int arg2) {
        this.zzca(arg1 << 3 | arg2);
    }

    public final void zzca(int arg2) {
        while((arg2 & -128) != 0) {
            this.zzbz(arg2 & 127 | 128);
            arg2 >>>= 7;
        }

        this.zzbz(arg2);
    }

    public static int zzd(int arg0, long arg1) {
        return zzyy.zzbb(arg0) + zzyy.zzbi(arg1);
    }

    private static void zzd(CharSequence arg13, ByteBuffer arg14) {
        StringBuilder v14;
        char v5_1;
        char v10_1;
        int v11;
        int v10;
        int v8;
        int v7;
        if(!arg14.isReadOnly()) {
            int v1 = 39;
            int v2 = 57343;
            int v3 = 55296;
            int v4 = 2048;
            int v5 = 0;
            int v6 = 128;
            if(arg14.hasArray()) {
                try {
                    byte[] v0 = arg14.array();
                    v7 = arg14.arrayOffset() + arg14.position();
                    v8 = arg14.remaining();
                    int v9 = arg13.length();
                    v8 += v7;
                    while(v5 < v9) {
                        v10 = v5 + v7;
                        if(v10 >= v8) {
                            break;
                        }

                        v11 = arg13.charAt(v5);
                        if(v11 >= v6) {
                            break;
                        }

                        v0[v10] = ((byte)v11);
                        ++v5;
                    }

                    if(v5 == v9) {
                        v7 += v9;
                    }
                    else {
                        v7 += v5;
                        while(true) {
                            if(v5 < v9) {
                                v10_1 = arg13.charAt(v5);
                                if(v10_1 >= v6 || v7 >= v8) {
                                    if(v10_1 < v4 && v7 <= v8 - 2) {
                                        v11 = v7 + 1;
                                        v0[v7] = ((byte)(v10_1 >>> 6 | 960));
                                        v7 = v11 + 1;
                                        v0[v11] = ((byte)(v10_1 & 63 | v6));
                                        goto label_106;
                                    }

                                    if((v10_1 < v3 || v2 < v10_1) && v7 <= v8 - 3) {
                                        v11 = v7 + 1;
                                        v0[v7] = ((byte)(v10_1 >>> 12 | 480));
                                        v7 = v11 + 1;
                                        v0[v11] = ((byte)(v10_1 >>> 6 & 63 | v6));
                                        v11 = v7 + 1;
                                        v0[v7] = ((byte)(v10_1 & 63 | v6));
                                    label_37:
                                        v7 = v11;
                                        goto label_106;
                                    }

                                    if(v7 > v8 - 4) {
                                        goto label_119;
                                    }

                                    v11 = v5 + 1;
                                    if(v11 != arg13.length()) {
                                        v5_1 = arg13.charAt(v11);
                                        if(Character.isSurrogatePair(v10_1, v5_1)) {
                                            v5 = Character.toCodePoint(v10_1, v5_1);
                                            v10 = v7 + 1;
                                            v0[v7] = ((byte)(v5 >>> 18 | 240));
                                            v7 = v10 + 1;
                                            v0[v10] = ((byte)(v5 >>> 12 & 63 | v6));
                                            v10 = v7 + 1;
                                            v0[v7] = ((byte)(v5 >>> 6 & 63 | v6));
                                            v7 = v10 + 1;
                                            v0[v10] = ((byte)(v5 & 63 | v6));
                                            v5 = v11;
                                            goto label_106;
                                        }
                                        else {
                                            break;
                                        }
                                    }

                                    goto label_109;
                                }
                                else {
                                    v11 = v7 + 1;
                                    v0[v7] = ((byte)v10_1);
                                    goto label_37;
                                }

                            label_106:
                                ++v5;
                                continue;
                            }

                            goto label_132;
                        }

                        v5 = v11;
                    label_109:
                        v14 = new StringBuilder(v1);
                        v14.append("Unpaired surrogate at index ");
                        v14.append(v5 - 1);
                        throw new IllegalArgumentException(v14.toString());
                    label_119:
                        StringBuilder v0_1 = new StringBuilder(37);
                        v0_1.append("Failed writing ");
                        v0_1.append(v10_1);
                        v0_1.append(" at index ");
                        v0_1.append(v7);
                        throw new ArrayIndexOutOfBoundsException(v0_1.toString());
                    }

                label_132:
                    arg14.position(v7 - arg14.arrayOffset());
                    return;
                }
                catch(ArrayIndexOutOfBoundsException v13) {
                    BufferOverflowException v14_1 = new BufferOverflowException();
                    v14_1.initCause(((Throwable)v13));
                    throw v14_1;
                }
            }

            int v0_2 = arg13.length();
            while(v5 < v0_2) {
                char v7_1 = arg13.charAt(v5);
                if(v7_1 >= v6) {
                    if(v7_1 < v4) {
                        v8 = v7_1 >>> 6 | 960;
                    }
                    else {
                        if(v7_1 >= v3) {
                            if(v2 < v7_1) {
                            }
                            else {
                                v8 = v5 + 1;
                                if(v8 != arg13.length()) {
                                    v5_1 = arg13.charAt(v8);
                                    if(Character.isSurrogatePair(v7_1, v5_1)) {
                                        v5 = Character.toCodePoint(v7_1, v5_1);
                                        arg14.put(((byte)(v5 >>> 18 | 240)));
                                        arg14.put(((byte)(v5 >>> 12 & 63 | v6)));
                                        arg14.put(((byte)(v5 >>> 6 & 63 | v6)));
                                        arg14.put(((byte)(v5 & 63 | v6)));
                                        v5 = v8;
                                        goto label_205;
                                    }
                                    else {
                                        v5 = v8;
                                    }
                                }

                                v14 = new StringBuilder(v1);
                                v14.append("Unpaired surrogate at index ");
                                v14.append(v5 - 1);
                                throw new IllegalArgumentException(v14.toString());
                            }
                        }

                        arg14.put(((byte)(v7_1 >>> 12 | 480)));
                        v8 = v7_1 >>> 6 & 63 | v6;
                    }

                    arg14.put(((byte)v8));
                    v7 = v7_1 & 63 | v6;
                    goto label_145;
                }
                else {
                label_145:
                    arg14.put(((byte)v7));
                }

            label_205:
                ++v5;
            }

            return;
        }

        throw new ReadOnlyBufferException();
    }

    public final void zzd(int arg2, int arg3) {
        this.zzc(arg2, 0);
        if(arg3 >= 0) {
            this.zzca(arg3);
            return;
        }

        this.zzbh(((long)arg3));
    }

    public final void zze(int arg2, zzwt arg3) {
        zzut v0 = this.zzys();
        v0.zza(arg2, arg3);
        v0.flush();
        this.zzcfb = this.zzbva.position();
    }

    public static int zzfx(String arg1) {
        int v1 = zzyy.zza(((CharSequence)arg1));
        return zzyy.zzbj(v1) + v1;
    }

    public static int zzh(int arg0, int arg1) {
        return zzyy.zzbb(arg0) + zzyy.zzbc(arg1);
    }

    public final void zzi(int arg2, long arg3) {
        this.zzc(arg2, 0);
        this.zzbh(arg3);
    }

    public static zzyy zzk(byte[] arg1, int arg2, int arg3) {
        return new zzyy(arg1, 0, arg3);
    }

    public static zzyy zzo(byte[] arg2) {
        return zzyy.zzk(arg2, 0, arg2.length);
    }

    public final void zzp(byte[] arg4) {
        int v0 = arg4.length;
        if(this.zzbva.remaining() >= v0) {
            this.zzbva.put(arg4, 0, v0);
            return;
        }

        throw new zzyz(this.zzbva.position(), this.zzbva.limit());
    }

    private final zzut zzys() {
        if(this.zzcfa == null) {
            this.zzcfa = zzut.zza(this.zzbva);
            goto label_5;
        }
        else if(this.zzcfb != this.zzbva.position()) {
            this.zzcfa.write(this.zzbva.array(), this.zzcfb, this.zzbva.position() - this.zzcfb);
        label_5:
            this.zzcfb = this.zzbva.position();
        }

        return this.zzcfa;
    }

    public final void zzyt() {
        if(this.zzbva.remaining() == 0) {
            return;
        }

        throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzbva.remaining())));
    }
}

