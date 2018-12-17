package com.google.android.gms.internal.config;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzaz {
    private final ByteBuffer zzcg;

    private zzaz(ByteBuffer arg2) {
        super();
        this.zzcg = arg2;
        this.zzcg.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzaz(byte[] arg1, int arg2, int arg3) {
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

    public static zzaz zza(byte[] arg2) {
        return zzaz.zzb(arg2, 0, arg2.length);
    }

    private static void zza(CharSequence arg13, ByteBuffer arg14) {
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

    public final void zza(byte arg3) {
        if(this.zzcg.hasRemaining()) {
            this.zzcg.put(arg3);
            return;
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    }

    public final void zza(int arg2, long arg3) {
        this.zze(arg2, 1);
        if(this.zzcg.remaining() >= 8) {
            this.zzcg.putLong(arg3);
            return;
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    }

    public final void zza(int arg2, zzbh arg3) {
        this.zze(arg2, 2);
        if(arg3.zzcq < 0) {
            arg3.zzah();
        }

        this.zzm(arg3.zzcq);
        arg3.zza(this);
    }

    public final void zza(int arg4, String arg5) {
        this.zze(arg4, 2);
        try {
            arg4 = zzaz.zzn(arg5.length());
            if(arg4 == zzaz.zzn(arg5.length() * 3)) {
                int v0 = this.zzcg.position();
                if(this.zzcg.remaining() >= arg4) {
                    this.zzcg.position(v0 + arg4);
                    zzaz.zza(((CharSequence)arg5), this.zzcg);
                    int v5 = this.zzcg.position();
                    this.zzcg.position(v0);
                    this.zzm(v5 - v0 - arg4);
                    this.zzcg.position(v5);
                    return;
                }

                throw new zzba(v0 + arg4, this.zzcg.limit());
            }

            this.zzm(zzaz.zza(((CharSequence)arg5)));
            zzaz.zza(((CharSequence)arg5), this.zzcg);
            return;
        }
        catch(BufferOverflowException v4) {
            zzba v5_1 = new zzba(this.zzcg.position(), this.zzcg.limit());
            v5_1.initCause(((Throwable)v4));
            throw v5_1;
        }
    }

    public final void zza(int arg2, byte[] arg3) {
        this.zze(arg2, 2);
        this.zzm(arg3.length);
        this.zzc(arg3);
    }

    public final void zzac() {
        if(this.zzcg.remaining() == 0) {
            return;
        }

        throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.zzcg.remaining())));
    }

    public static zzaz zzb(byte[] arg1, int arg2, int arg3) {
        return new zzaz(arg1, 0, arg3);
    }

    public static int zzb(int arg1, zzbh arg2) {
        arg1 = zzaz.zzl(arg1);
        int v2 = arg2.zzah();
        return arg1 + (zzaz.zzn(v2) + v2);
    }

    public static int zzb(int arg1, String arg2) {
        arg1 = zzaz.zzl(arg1);
        int v2 = zzaz.zza(((CharSequence)arg2));
        return arg1 + (zzaz.zzn(v2) + v2);
    }

    public static int zzb(byte[] arg1) {
        return zzaz.zzn(arg1.length) + arg1.length;
    }

    public final void zzc(byte[] arg4) {
        int v0 = arg4.length;
        if(this.zzcg.remaining() >= v0) {
            this.zzcg.put(arg4, 0, v0);
            return;
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    }

    public final void zzc(int arg6, int arg7) {
        this.zze(1, 0);
        if(arg7 >= 0) {
            this.zzm(arg7);
            return;
        }

        long v6;
        for(v6 = ((long)arg7); true; v6 >>>= 7) {
            if((-128 & v6) == 0) {
                goto label_11;
            }

            byte v0 = ((byte)((((int)v6)) & 127 | 128));
            if(!this.zzcg.hasRemaining()) {
                break;
            }

            this.zzcg.put(v0);
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    label_11:
        byte v6_1 = ((byte)(((int)v6)));
        if(this.zzcg.hasRemaining()) {
            this.zzcg.put(v6_1);
            return;
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    }

    public static int zzd(int arg0, int arg1) {
        return zzaz.zzl(1) + zzaz.zzj(arg1);
    }

    public final void zze(int arg1, int arg2) {
        this.zzm(arg1 << 3 | arg2);
    }

    public static int zzj(int arg0) {
        if(arg0 >= 0) {
            return zzaz.zzn(arg0);
        }

        return 10;
    }

    private final void zzk(int arg3) {
        byte v3 = ((byte)arg3);
        if(this.zzcg.hasRemaining()) {
            this.zzcg.put(v3);
            return;
        }

        throw new zzba(this.zzcg.position(), this.zzcg.limit());
    }

    public static int zzl(int arg0) {
        return zzaz.zzn(arg0 << 3);
    }

    public final void zzm(int arg2) {
        while((arg2 & -128) != 0) {
            this.zzk(arg2 & 127 | 128);
            arg2 >>>= 7;
        }

        this.zzk(arg2);
    }

    public static int zzn(int arg1) {
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
}

