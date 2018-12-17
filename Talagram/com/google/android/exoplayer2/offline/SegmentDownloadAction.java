package com.google.android.exoplayer2.offline;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class SegmentDownloadAction extends DownloadAction {
    public abstract class SegmentDownloadActionDeserializer extends Deserializer {
        public SegmentDownloadActionDeserializer(String arg1, int arg2) {
            super(arg1, arg2);
        }

        protected abstract DownloadAction createDownloadAction(Uri arg1, boolean arg2, byte[] arg3, List arg4);

        public final DownloadAction readFromStream(int arg8, DataInputStream arg9) {
            Uri v0 = Uri.parse(arg9.readUTF());
            boolean v1 = arg9.readBoolean();
            byte[] v2 = new byte[arg9.readInt()];
            arg9.readFully(v2);
            int v3 = arg9.readInt();
            ArrayList v4 = new ArrayList();
            int v5;
            for(v5 = 0; v5 < v3; ++v5) {
                ((List)v4).add(this.readKey(arg8, arg9));
            }

            return this.createDownloadAction(v0, v1, v2, ((List)v4));
        }

        protected StreamKey readKey(int arg3, DataInputStream arg4) {
            return new StreamKey(arg4.readInt(), arg4.readInt(), arg4.readInt());
        }
    }

    public final List keys;

    protected SegmentDownloadAction(String arg1, int arg2, Uri arg3, boolean arg4, byte[] arg5, List arg6) {
        List v1;
        super(arg1, arg2, arg3, arg4, arg5);
        if(arg4) {
            Assertions.checkArgument(arg6.isEmpty());
            v1 = Collections.emptyList();
        }
        else {
            ArrayList v1_1 = new ArrayList(((Collection)arg6));
            Collections.sort(((List)v1_1));
            v1 = Collections.unmodifiableList(((List)v1_1));
        }

        this.keys = v1;
    }

    public boolean equals(Object arg2) {
        if(this == (((SegmentDownloadAction)arg2))) {
            return 1;
        }

        if(!super.equals(arg2)) {
            return 0;
        }

        return this.keys.equals(((SegmentDownloadAction)arg2).keys);
    }

    public List getKeys() {
        return this.keys;
    }

    public int hashCode() {
        return super.hashCode() * 31 + this.keys.hashCode();
    }

    private void writeKey(DataOutputStream arg2, StreamKey arg3) {
        arg2.writeInt(arg3.periodIndex);
        arg2.writeInt(arg3.groupIndex);
        arg2.writeInt(arg3.trackIndex);
    }

    public final void writeToStream(DataOutputStream arg3) {
        arg3.writeUTF(this.uri.toString());
        arg3.writeBoolean(this.isRemoveAction);
        arg3.writeInt(this.data.length);
        arg3.write(this.data);
        arg3.writeInt(this.keys.size());
        int v0;
        for(v0 = 0; v0 < this.keys.size(); ++v0) {
            this.writeKey(arg3, this.keys.get(v0));
        }
    }
}

