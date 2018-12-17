package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DownloadAction {
    public abstract class Deserializer {
        public final String type;
        public final int version;

        public Deserializer(String arg1, int arg2) {
            super();
            this.type = arg1;
            this.version = arg2;
        }

        public abstract DownloadAction readFromStream(int arg1, DataInputStream arg2);
    }

    public final byte[] data;
    private static Deserializer[] defaultDeserializers;
    public final boolean isRemoveAction;
    public final String type;
    public final Uri uri;
    public final int version;

    protected DownloadAction(String arg1, int arg2, Uri arg3, boolean arg4, byte[] arg5) {
        super();
        this.type = arg1;
        this.version = arg2;
        this.uri = arg3;
        this.isRemoveAction = arg4;
        if(arg5 != null) {
        }
        else {
            arg5 = new byte[0];
        }

        this.data = arg5;
    }

    public abstract Downloader createDownloader(DownloaderConstructorHelper arg1);

    public static DownloadAction deserializeFromStream(Deserializer[] arg6, InputStream arg7) {
        DataInputStream v0 = new DataInputStream(arg7);
        String v7 = v0.readUTF();
        int v1 = v0.readInt();
        int v2 = arg6.length;
        int v3;
        for(v3 = 0; v3 < v2; ++v3) {
            Deserializer v4 = arg6[v3];
            if((v7.equals(v4.type)) && v4.version >= v1) {
                return v4.readFromStream(v1, v0);
            }
        }

        StringBuilder v0_1 = new StringBuilder();
        v0_1.append("No deserializer found for:");
        v0_1.append(v7);
        v0_1.append(", ");
        v0_1.append(v1);
        throw new DownloadException(v0_1.toString());
    }

    public boolean equals(Object arg4) {
        boolean v0 = false;
        if(arg4 != null) {
            if(this.getClass() != arg4.getClass()) {
            }
            else if((this.type.equals(((DownloadAction)arg4).type)) && this.version == ((DownloadAction)arg4).version && (this.uri.equals(((DownloadAction)arg4).uri)) && this.isRemoveAction == ((DownloadAction)arg4).isRemoveAction && (Arrays.equals(this.data, ((DownloadAction)arg4).data))) {
                v0 = true;
            }
        }

        return v0;
    }

    public static Deserializer[] getDefaultDeserializers() {
        int v3_1;
        Class v2;
        int v4;
        Class v3;
        Deserializer[] v1_1;
        Class v0 = DownloadAction.class;
        __monitor_enter(v0);
        try {
            if(DownloadAction.defaultDeserializers == null) {
                goto label_7;
            }

            v1_1 = DownloadAction.defaultDeserializers;
        }
        catch(Throwable v1) {
            goto label_41;
        }

        __monitor_exit(v0);
        return v1_1;
    label_7:
        int v1_2 = 4;
        try {
            v1_1 = new Deserializer[v1_2];
            v1_1[0] = ProgressiveDownloadAction.DESERIALIZER;
        }
        catch(Throwable v1) {
            goto label_41;
        }

        try {
            v3 = Class.forName("com.google.android.exoplayer2.source.dash.offline.DashDownloadAction");
            v4 = 2;
            goto label_16;
        }
        catch(Throwable v1) {
        }
        catch(Exception ) {
            v4 = 1;
            goto label_20;
            try {
            label_16:
                v1_1[1] = DownloadAction.getDeserializer(v3);
                goto label_20;
            }
            catch(Throwable v1) {
            }
            catch(Exception ) {
                try {
                label_20:
                    v2 = Class.forName("com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction");
                    v3_1 = v4 + 1;
                    goto label_23;
                }
                catch(Throwable v1) {
                }
                catch(Exception ) {
                    v3_1 = v4;
                    goto label_27;
                    try {
                    label_23:
                        v1_1[v4] = DownloadAction.getDeserializer(v2);
                        goto label_27;
                    }
                    catch(Throwable v1) {
                    }
                    catch(Exception ) {
                        try {
                        label_27:
                            v2 = Class.forName("com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloadAction");
                            v4 = v3_1 + 1;
                            goto label_30;
                        }
                        catch(Throwable v1) {
                        }
                        catch(Exception ) {
                            v4 = v3_1;
                            goto label_34;
                            try {
                            label_30:
                                v1_1[v3_1] = DownloadAction.getDeserializer(v2);
                                goto label_34;
                            }
                            catch(Throwable v1) {
                            }
                            catch(Exception ) {
                                try {
                                label_34:
                                    DownloadAction.defaultDeserializers = Arrays.copyOf(Assertions.checkNotNull(v1_1), v4);
                                    v1_1 = DownloadAction.defaultDeserializers;
                                }
                                catch(Throwable v1) {
                                label_41:
                                    __monitor_exit(v0);
                                    throw v1;
                                }
                            }
                        }
                    }
                }
            }
        }

        __monitor_exit(v0);
        return v1_1;
    }

    private static Deserializer getDeserializer(Class arg1) {
        return Assertions.checkNotNull(arg1.getDeclaredField("DESERIALIZER").get(null));
    }

    public List getKeys() {
        return Collections.emptyList();
    }

    public int hashCode() {
        return (this.uri.hashCode() * 31 + this.isRemoveAction) * 31 + Arrays.hashCode(this.data);
    }

    public boolean isSameMedia(DownloadAction arg2) {
        return this.uri.equals(arg2.uri);
    }

    public static void serializeToStream(DownloadAction arg1, OutputStream arg2) {
        DataOutputStream v0 = new DataOutputStream(arg2);
        v0.writeUTF(arg1.type);
        v0.writeInt(arg1.version);
        arg1.writeToStream(v0);
        v0.flush();
    }

    public final byte[] toByteArray() {
        ByteArrayOutputStream v0 = new ByteArrayOutputStream();
        try {
            DownloadAction.serializeToStream(this, ((OutputStream)v0));
        }
        catch(IOException ) {
            throw new IllegalStateException();
        }

        return v0.toByteArray();
    }

    protected abstract void writeToStream(DataOutputStream arg1);
}

