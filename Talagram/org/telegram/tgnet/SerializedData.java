package org.telegram.tgnet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class SerializedData extends AbstractSerializedData {
    private DataInputStream in;
    private ByteArrayInputStream inbuf;
    protected boolean isOut;
    private boolean justCalc;
    private int len;
    private DataOutputStream out;
    private ByteArrayOutputStream outbuf;

    public SerializedData(byte[] arg3) {
        super();
        this.isOut = true;
        this.justCalc = false;
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(arg3);
        this.in = new DataInputStream(this.inbuf);
        this.len = 0;
    }

    public SerializedData() {
        super();
        this.isOut = true;
        this.justCalc = false;
        this.outbuf = new ByteArrayOutputStream();
        this.out = new DataOutputStream(this.outbuf);
    }

    public SerializedData(int arg2) {
        super();
        this.isOut = true;
        this.justCalc = false;
        this.outbuf = new ByteArrayOutputStream(arg2);
        this.out = new DataOutputStream(this.outbuf);
    }

    public SerializedData(boolean arg4) {
        super();
        this.isOut = true;
        this.justCalc = false;
        if(!arg4) {
            this.outbuf = new ByteArrayOutputStream();
            this.out = new DataOutputStream(this.outbuf);
        }

        this.justCalc = arg4;
        this.len = 0;
    }

    public SerializedData(File arg5) {
        super();
        this.isOut = true;
        this.justCalc = false;
        FileInputStream v1 = new FileInputStream(arg5);
        byte[] v5 = new byte[((int)arg5.length())];
        new DataInputStream(((InputStream)v1)).readFully(v5);
        v1.close();
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(v5);
        this.in = new DataInputStream(this.inbuf);
    }

    public void cleanup() {
        ByteArrayInputStream v0 = null;
        try {
            if(this.inbuf == null) {
                goto label_9;
            }

            this.inbuf.close();
            this.inbuf = v0;
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
        label_9:
            if(this.in == null) {
                goto label_17;
            }

            this.in.close();
            this.in = ((DataInputStream)v0);
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
        label_17:
            if(this.outbuf == null) {
                goto label_25;
            }

            this.outbuf.close();
            this.outbuf = ((ByteArrayOutputStream)v0);
        }
        catch(Exception v1) {
            FileLog.e(((Throwable)v1));
        }

        try {
        label_25:
            if(this.out == null) {
                return;
            }

            this.out.close();
            this.out = ((DataOutputStream)v0);
        }
        catch(Exception v0_1) {
            FileLog.e(((Throwable)v0_1));
        }
    }

    public int getPosition() {
        return this.len;
    }

    public int length() {
        if(!this.justCalc) {
            int v0 = this.isOut ? this.outbuf.size() : this.inbuf.available();
            return v0;
        }

        return this.len;
    }

    public boolean readBool(boolean arg4) {
        int v0 = this.readInt32(arg4);
        if(v0 == -1720552011) {
            return 1;
        }

        if(v0 == -1132882121) {
            return 0;
        }

        if(!arg4) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("Not bool value!");
            }

            return 0;
        }

        throw new RuntimeException("Not bool value!");
    }

    public byte[] readByteArray(boolean arg7) {
        byte[] v4;
        int v1;
        try {
            int v0_1 = this.in.read();
            ++this.len;
            int v3 = 4;
            if(v0_1 >= 254) {
                v0_1 = this.in.read() | this.in.read() << 8 | this.in.read() << 16;
                this.len += 3;
                v1 = 4;
            }
            else {
                v1 = 1;
            }

            v4 = new byte[v0_1];
            this.in.read(v4);
            ++this.len;
            while((v0_1 + v1) % v3 != 0) {
                this.in.read();
                ++this.len;
                ++v1;
            }
        }
        catch(Exception v0) {
            goto label_43;
        }

        return v4;
    label_43:
        if(!arg7) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("read byte array error");
            }

            return null;
        }

        throw new RuntimeException("read byte array error", ((Throwable)v0));
    }

    public NativeByteBuffer readByteBuffer(boolean arg1) {
        return null;
    }

    public void readBytes(byte[] arg2, boolean arg3) {
        try {
            this.in.read(arg2);
            this.len += arg2.length;
        }
        catch(Exception v2) {
            if(!arg3) {
                if(!BuildVars.LOGS_ENABLED) {
                    return;
                }

                FileLog.e("read bytes error");
                return;
            }

            throw new RuntimeException("read bytes error", ((Throwable)v2));
        }
    }

    public byte[] readData(int arg1, boolean arg2) {
        byte[] v1 = new byte[arg1];
        this.readBytes(v1, arg2);
        return v1;
    }

    public double readDouble(boolean arg3) {
        try {
            return Double.longBitsToDouble(this.readInt64(arg3));
        }
        catch(Exception v0) {
            if(!arg3) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("read double error");
                }

                return 0;
            }

            throw new RuntimeException("read double error", ((Throwable)v0));
        }
    }

    public int readInt32(boolean arg6) {
        int v1 = 0;
        int v2 = 0;
        while(v1 < 4) {
            try {
                v2 |= this.in.read() << v1 * 8;
                ++this.len;
                ++v1;
                continue;
            }
            catch(Exception v1_1) {
                if(!arg6) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("read int32 error");
                    }

                    return 0;
                }

                throw new RuntimeException("read int32 error", ((Throwable)v1_1));
            }
        }

        return v2;
    }

    public long readInt64(boolean arg9) {
        int v0 = 0;
        long v1 = 0;
        long v3 = v1;
        while(v0 < 8) {
            try {
                v3 |= (((long)this.in.read())) << v0 * 8;
                ++this.len;
                ++v0;
                continue;
            }
            catch(Exception v0_1) {
                if(!arg9) {
                    if(BuildVars.LOGS_ENABLED) {
                        FileLog.e("read int64 error");
                    }

                    return v1;
                }

                throw new RuntimeException("read int64 error", ((Throwable)v0_1));
            }
        }

        return v3;
    }

    public String readString(boolean arg7) {
        int v1;
        try {
            int v0_1 = this.in.read();
            ++this.len;
            int v3 = 4;
            if(v0_1 >= 254) {
                v0_1 = this.in.read() | this.in.read() << 8 | this.in.read() << 16;
                this.len += 3;
                v1 = 4;
            }
            else {
                v1 = 1;
            }

            byte[] v4 = new byte[v0_1];
            this.in.read(v4);
            ++this.len;
            while((v0_1 + v1) % v3 != 0) {
                this.in.read();
                ++this.len;
                ++v1;
            }

            return new String(v4, "UTF-8");
        }
        catch(Exception v0) {
            if(!arg7) {
                if(BuildVars.LOGS_ENABLED) {
                    FileLog.e("read string error");
                }

                return null;
            }

            throw new RuntimeException("read string error", ((Throwable)v0));
        }
    }

    public int remaining() {
        try {
            return this.in.available();
        }
        catch(Exception ) {
            return 2147483647;
        }
    }

    protected void set(byte[] arg2) {
        this.isOut = false;
        this.inbuf = new ByteArrayInputStream(arg2);
        this.in = new DataInputStream(this.inbuf);
    }

    public void skip(int arg2) {
        if(arg2 == 0) {
            return;
        }

        if(!this.justCalc) {
            if(this.in == null) {
                return;
            }

            try {
                this.in.skipBytes(arg2);
            }
            catch(Exception v2) {
                FileLog.e(((Throwable)v2));
            }
        }
        else {
            this.len += arg2;
        }
    }

    public byte[] toByteArray() {
        return this.outbuf.toByteArray();
    }

    public void writeBool(boolean arg2) {
        if(!this.justCalc) {
            int v2 = arg2 ? -1720552011 : -1132882121;
            this.writeInt32(v2);
        }
        else {
            this.len += 4;
        }
    }

    public void writeByte(byte arg2) {
        try {
            if(!this.justCalc) {
                this.out.writeByte(arg2);
                return;
            }

            ++this.len;
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write byte error");
        }
    }

    public void writeByte(int arg2) {
        try {
            if(!this.justCalc) {
                this.out.writeByte(((byte)arg2));
                return;
            }

            ++this.len;
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write byte error");
        }
    }

    public void writeByteArray(byte[] arg6) {
        int v0;
        try {
            int v1 = 253;
            int v2 = 4;
            if(arg6.length <= v1) {
                if(!this.justCalc) {
                    this.out.write(arg6.length);
                }
                else {
                    ++this.len;
                }
            }
            else if(!this.justCalc) {
                this.out.write(254);
                this.out.write(arg6.length);
                this.out.write(arg6.length >> 8);
                this.out.write(arg6.length >> 16);
            }
            else {
                this.len += v2;
            }

            if(!this.justCalc) {
                this.out.write(arg6);
            }
            else {
                this.len += arg6.length;
            }

            v0 = arg6.length <= v1 ? 1 : 4;
            while(true) {
            label_49:
                if((arg6.length + v0) % v2 == 0) {
                    return;
                }

                if(!this.justCalc) {
                    this.out.write(0);
                }
                else {
                    ++this.len;
                }

                break;
            }
        }
        catch(Exception ) {
            goto label_64;
        }

        ++v0;
        goto label_49;
    label_64:
        if(BuildVars.LOGS_ENABLED) {
            FileLog.e("write byte array error");
        }
    }

    public void writeByteArray(byte[] arg6, int arg7, int arg8) {
        int v6;
        int v0 = 253;
        int v1 = 4;
        if(arg8 <= v0) {
            try {
                if(!this.justCalc) {
                    this.out.write(arg8);
                }
                else {
                    ++this.len;
                    goto label_30;
                label_13:
                    if(!this.justCalc) {
                        this.out.write(254);
                        this.out.write(arg8);
                        this.out.write(arg8 >> 8);
                        this.out.write(arg8 >> 16);
                    }
                    else {
                        this.len += v1;
                    }
                }

            label_30:
                if(!this.justCalc) {
                    this.out.write(arg6, arg7, arg8);
                }
                else {
                    this.len += arg8;
                }

                v6 = arg8 <= v0 ? 1 : 4;
                while(true) {
                label_42:
                    if((arg8 + v6) % v1 == 0) {
                        return;
                    }

                    if(!this.justCalc) {
                        this.out.write(0);
                    }
                    else {
                        ++this.len;
                    }

                    goto label_54;
                }
            }
            catch(Exception ) {
                goto label_56;
            }
        }
        else {
            goto label_13;
        }

        goto label_30;
    label_54:
        ++v6;
        goto label_42;
    label_56:
        if(BuildVars.LOGS_ENABLED) {
            FileLog.e("write byte array error");
        }
    }

    public void writeByteBuffer(NativeByteBuffer arg1) {
    }

    public void writeBytes(byte[] arg2) {
        try {
            if(!this.justCalc) {
                this.out.write(arg2);
                return;
            }

            this.len += arg2.length;
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write raw error");
        }
    }

    public void writeBytes(byte[] arg2, int arg3, int arg4) {
        try {
            if(!this.justCalc) {
                this.out.write(arg2, arg3, arg4);
                return;
            }

            this.len += arg4;
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write bytes error");
        }
    }

    public void writeDouble(double arg1) {
        try {
            this.writeInt64(Double.doubleToRawLongBits(arg1));
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write double error");
        }
    }

    public void writeInt32(int arg2) {
        if(!this.justCalc) {
            this.writeInt32(arg2, this.out);
        }
        else {
            this.len += 4;
        }
    }

    private void writeInt32(int arg3, DataOutputStream arg4) {
        int v0 = 0;
        while(v0 < 4) {
            int v1 = arg3 >> v0 * 8;
            try {
                arg4.write(v1);
                ++v0;
                continue;
            }
            catch(Exception ) {
                if(!BuildVars.LOGS_ENABLED) {
                    return;
                }

                FileLog.e("write int32 error");
                return;
            }
        }
    }

    private void writeInt64(long arg4, DataOutputStream arg6) {
        int v0 = 0;
        while(v0 < 8) {
            int v1 = ((int)(arg4 >> v0 * 8));
            try {
                arg6.write(v1);
                ++v0;
                continue;
            }
            catch(Exception ) {
                if(!BuildVars.LOGS_ENABLED) {
                    return;
                }

                FileLog.e("write int64 error");
                return;
            }
        }
    }

    public void writeInt64(long arg2) {
        if(!this.justCalc) {
            this.writeInt64(arg2, this.out);
        }
        else {
            this.len += 8;
        }
    }

    public void writeString(String arg2) {
        try {
            this.writeByteArray(arg2.getBytes("UTF-8"));
        }
        catch(Exception ) {
            if(!BuildVars.LOGS_ENABLED) {
                return;
            }

            FileLog.e("write string error");
        }
    }
}

