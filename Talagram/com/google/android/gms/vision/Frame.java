package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import java.nio.ByteBuffer;

public class Frame {
    public class Builder {
        private Frame zzas;

        public Builder() {
            super();
            this.zzas = new Frame(null);
        }

        public Frame build() {
            if(Frame.zza(this.zzas) == null) {
                if(Frame.zzb(this.zzas) != null) {
                }
                else {
                    throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
                }
            }

            return this.zzas;
        }

        public Builder setBitmap(Bitmap arg4) {
            int v0 = arg4.getWidth();
            int v1 = arg4.getHeight();
            Frame.zza(this.zzas, arg4);
            Metadata v4 = this.zzas.getMetadata();
            Metadata.zza(v4, v0);
            Metadata.zzb(v4, v1);
            return this;
        }

        public Builder setId(int arg2) {
            Metadata.zzd(this.zzas.getMetadata(), arg2);
            return this;
        }

        public Builder setImageData(ByteBuffer arg3, int arg4, int arg5, int arg6) {
            if(arg3 != null) {
                if(arg3.capacity() >= arg4 * arg5) {
                    if(arg6 != 842094169) {
                        switch(arg6) {
                            case 16: 
                            case 17: {
                                goto label_17;
                            }
                            default: {
                                StringBuilder v5 = new StringBuilder(37);
                                v5.append("Unsupported image format: ");
                                v5.append(arg6);
                                throw new IllegalArgumentException(v5.toString());
                            }
                        }
                    }

                label_17:
                    Frame.zza(this.zzas, arg3);
                    Metadata v3 = this.zzas.getMetadata();
                    Metadata.zza(v3, arg4);
                    Metadata.zzb(v3, arg5);
                    Metadata.zzc(v3, arg6);
                    return this;
                }

                throw new IllegalArgumentException("Invalid image data size.");
            }

            throw new IllegalArgumentException("Null image data supplied.");
        }

        public Builder setRotation(int arg2) {
            Metadata.zze(this.zzas.getMetadata(), arg2);
            return this;
        }

        public Builder setTimestampMillis(long arg2) {
            Metadata.zza(this.zzas.getMetadata(), arg2);
            return this;
        }
    }

    public class Metadata {
        private int format;
        private int height;
        private int id;
        private int rotation;
        private int width;
        private long zzat;

        public Metadata(Metadata arg3) {
            super();
            this.format = -1;
            this.width = arg3.getWidth();
            this.height = arg3.getHeight();
            this.id = arg3.getId();
            this.zzat = arg3.getTimestampMillis();
            this.rotation = arg3.getRotation();
        }

        public Metadata() {
            super();
            this.format = -1;
        }

        public int getFormat() {
            return this.format;
        }

        public int getHeight() {
            return this.height;
        }

        public int getId() {
            return this.id;
        }

        public int getRotation() {
            return this.rotation;
        }

        public long getTimestampMillis() {
            return this.zzat;
        }

        public int getWidth() {
            return this.width;
        }

        static int zza(Metadata arg0, int arg1) {
            arg0.width = arg1;
            return arg1;
        }

        static long zza(Metadata arg0, long arg1) {
            arg0.zzat = arg1;
            return arg1;
        }

        static int zzb(Metadata arg0, int arg1) {
            arg0.height = arg1;
            return arg1;
        }

        static int zzc(Metadata arg0, int arg1) {
            arg0.format = arg1;
            return arg1;
        }

        public final void zzd() {
            if(this.rotation % 2 != 0) {
                int v0 = this.width;
                this.width = this.height;
                this.height = v0;
            }

            this.rotation = 0;
        }

        static int zzd(Metadata arg0, int arg1) {
            arg0.id = arg1;
            return arg1;
        }

        static int zze(Metadata arg0, int arg1) {
            arg0.rotation = arg1;
            return arg1;
        }
    }

    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private Metadata zzap;
    private ByteBuffer zzaq;
    private Bitmap zzar;

    private Frame() {
        super();
        this.zzap = new Metadata();
        this.zzaq = null;
        this.zzar = null;
    }

    Frame(zzb arg1) {
        this();
    }

    public Bitmap getBitmap() {
        return this.zzar;
    }

    public ByteBuffer getGrayscaleImageData() {
        if(this.zzar != null) {
            int v7 = this.zzar.getWidth();
            int v8 = this.zzar.getHeight();
            int v0 = v7 * v8;
            int[] v9 = new int[v0];
            this.zzar.getPixels(v9, 0, v7, 0, 0, v7, v8);
            byte[] v0_1 = new byte[v0];
            int v1;
            for(v1 = 0; v1 < v9.length; ++v1) {
                v0_1[v1] = ((byte)(((int)((((float)Color.red(v9[v1]))) * 0.299f + (((float)Color.green(v9[v1]))) * 0.587f + (((float)Color.blue(v9[v1]))) * 0.114f))));
            }

            return ByteBuffer.wrap(v0_1);
        }

        return this.zzaq;
    }

    public Metadata getMetadata() {
        return this.zzap;
    }

    static Bitmap zza(Frame arg0, Bitmap arg1) {
        arg0.zzar = arg1;
        return arg1;
    }

    static ByteBuffer zza(Frame arg0) {
        return arg0.zzaq;
    }

    static ByteBuffer zza(Frame arg0, ByteBuffer arg1) {
        arg0.zzaq = arg1;
        return arg1;
    }

    static Bitmap zzb(Frame arg0) {
        return arg0.zzar;
    }
}

