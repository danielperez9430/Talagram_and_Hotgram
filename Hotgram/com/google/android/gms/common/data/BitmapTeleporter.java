package com.google.android.gms.common.data;

import android.graphics.Bitmap$Config;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

@Class(creator="BitmapTeleporterCreator") public class BitmapTeleporter extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable$Creator CREATOR;
    @Field(id=3) private final int zzac;
    @VersionField(id=1) private final int zzal;
    @Field(id=2) private ParcelFileDescriptor zznb;
    private Bitmap zznc;
    private boolean zznd;
    private File zzne;

    static {
        BitmapTeleporter.CREATOR = new BitmapTeleporterCreator();
    }

    @Constructor BitmapTeleporter(@Param(id=1) int arg1, @Param(id=2) ParcelFileDescriptor arg2, @Param(id=3) int arg3) {
        super();
        this.zzal = arg1;
        this.zznb = arg2;
        this.zzac = arg3;
        this.zznc = null;
        this.zznd = false;
    }

    public BitmapTeleporter(Bitmap arg3) {
        super();
        this.zzal = 1;
        this.zznb = null;
        this.zzac = 0;
        this.zznc = arg3;
        this.zznd = true;
    }

    public Bitmap get() {
        Bitmap$Config v4;
        int v3;
        int v2;
        byte[] v1_2;
        if(!this.zznd) {
            DataInputStream v0 = new DataInputStream(new ParcelFileDescriptor$AutoCloseInputStream(this.zznb));
            try {
                v1_2 = new byte[v0.readInt()];
                v2 = v0.readInt();
                v3 = v0.readInt();
                v4 = Bitmap$Config.valueOf(v0.readUTF());
                v0.read(v1_2);
            }
            catch(Throwable v1) {
            }
            catch(IOException v1_1) {
                try {
                    throw new IllegalStateException("Could not read from parcel file descriptor", ((Throwable)v1_1));
                }
                catch(Throwable v1) {
                    BitmapTeleporter.zza(((Closeable)v0));
                    throw v1;
                }
            }

            BitmapTeleporter.zza(((Closeable)v0));
            ByteBuffer v0_1 = ByteBuffer.wrap(v1_2);
            Bitmap v1_3 = Bitmap.createBitmap(v2, v3, v4);
            v1_3.copyPixelsFromBuffer(((Buffer)v0_1));
            this.zznc = v1_3;
            this.zznd = true;
        }

        return this.zznc;
    }

    public void release() {
        if(!this.zznd) {
            try {
                this.zznb.close();
                return;
            }
            catch(IOException v0) {
                Log.w("BitmapTeleporter", "Could not close PFD", ((Throwable)v0));
            }
        }
    }

    public void setTempDir(File arg2) {
        if(arg2 != null) {
            this.zzne = arg2;
            return;
        }

        throw new NullPointerException("Cannot set null temp directory");
    }

    public void writeToParcel(Parcel arg5, int arg6) {
        if(this.zznb == null) {
            Bitmap v0 = this.zznc;
            ByteBuffer v1 = ByteBuffer.allocate(v0.getRowBytes() * v0.getHeight());
            v0.copyPixelsToBuffer(((Buffer)v1));
            byte[] v1_1 = v1.array();
            DataOutputStream v3 = new DataOutputStream(new BufferedOutputStream(this.zzcj()));
            try {
                v3.writeInt(v1_1.length);
                v3.writeInt(v0.getWidth());
                v3.writeInt(v0.getHeight());
                v3.writeUTF(v0.getConfig().toString());
                v3.write(v1_1);
            }
            catch(Throwable v5) {
            }
            catch(IOException v5_1) {
                try {
                    throw new IllegalStateException("Could not write into unlinked file", ((Throwable)v5_1));
                }
                catch(Throwable v5) {
                    BitmapTeleporter.zza(((Closeable)v3));
                    throw v5;
                }
            }

            BitmapTeleporter.zza(((Closeable)v3));
        }

        int v1_2 = SafeParcelWriter.beginObjectHeader(arg5);
        SafeParcelWriter.writeInt(arg5, 1, this.zzal);
        SafeParcelWriter.writeParcelable(arg5, 2, this.zznb, arg6 | 1, false);
        SafeParcelWriter.writeInt(arg5, 3, this.zzac);
        SafeParcelWriter.finishObjectHeader(arg5, v1_2);
        this.zznb = null;
    }

    private static void zza(Closeable arg2) {
        try {
            arg2.close();
            return;
        }
        catch(IOException v2) {
            Log.w("BitmapTeleporter", "Could not close stream", ((Throwable)v2));
            return;
        }
    }

    private final FileOutputStream zzcj() {
        FileOutputStream v1;
        File v0_1;
        if(this.zzne != null) {
            try {
                v0_1 = File.createTempFile("teleporter", ".tmp", this.zzne);
            }
            catch(IOException v0) {
                throw new IllegalStateException("Could not create temporary file", ((Throwable)v0));
            }

            try {
                v1 = new FileOutputStream(v0_1);
                this.zznb = ParcelFileDescriptor.open(v0_1, 268435456);
            }
            catch(FileNotFoundException ) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }

            v0_1.delete();
            return v1;
        }

        throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
    }
}

