package android.support.d;

import android.content.res.AssetManager$AssetInputStream;
import android.util.Log;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class a {
    class android.support.d.a$a extends InputStream implements DataInput {
        final int a;
        int b;
        private static final ByteOrder c;
        private static final ByteOrder d;
        private DataInputStream e;
        private ByteOrder f;

        static {
            android.support.d.a$a.c = ByteOrder.LITTLE_ENDIAN;
            android.support.d.a$a.d = ByteOrder.BIG_ENDIAN;
        }

        public android.support.d.a$a(byte[] arg2) {
            this(new ByteArrayInputStream(arg2));
        }

        public android.support.d.a$a(InputStream arg2) {
            super();
            this.f = ByteOrder.BIG_ENDIAN;
            this.e = new DataInputStream(arg2);
            this.a = this.e.available();
            this.b = 0;
            this.e.mark(this.a);
        }

        public void a(ByteOrder arg1) {
            this.f = arg1;
        }

        public void a(long arg4) {
            if((((long)this.b)) > arg4) {
                this.b = 0;
                this.e.reset();
                this.e.mark(this.a);
            }
            else {
                arg4 -= ((long)this.b);
            }

            int v4 = ((int)arg4);
            if(this.skipBytes(v4) == v4) {
                return;
            }

            throw new IOException("Couldn\'t seek up to the byteCount");
        }

        public int a() {
            return this.b;
        }

        public int available() {
            return this.e.available();
        }

        public long b() {
            return (((long)this.readInt())) & 4294967295L;
        }

        public int read() {
            ++this.b;
            return this.e.read();
        }

        public int read(byte[] arg2, int arg3, int arg4) {
            int v2 = this.e.read(arg2, arg3, arg4);
            this.b += v2;
            return v2;
        }

        public boolean readBoolean() {
            ++this.b;
            return this.e.readBoolean();
        }

        public byte readByte() {
            ++this.b;
            if(this.b <= this.a) {
                int v0 = this.e.read();
                if(v0 >= 0) {
                    return ((byte)v0);
                }

                throw new EOFException();
            }

            throw new EOFException();
        }

        public char readChar() {
            this.b += 2;
            return this.e.readChar();
        }

        public double readDouble() {
            return Double.longBitsToDouble(this.readLong());
        }

        public float readFloat() {
            return Float.intBitsToFloat(this.readInt());
        }

        public void readFully(byte[] arg4) {
            this.b += arg4.length;
            if(this.b <= this.a) {
                if(this.e.read(arg4, 0, arg4.length) == arg4.length) {
                    return;
                }

                throw new IOException("Couldn\'t read up to the length of buffer");
            }

            throw new EOFException();
        }

        public void readFully(byte[] arg3, int arg4, int arg5) {
            this.b += arg5;
            if(this.b <= this.a) {
                if(this.e.read(arg3, arg4, arg5) == arg5) {
                    return;
                }

                throw new IOException("Couldn\'t read up to the length of buffer");
            }

            throw new EOFException();
        }

        public int readInt() {
            this.b += 4;
            if(this.b <= this.a) {
                int v0 = this.e.read();
                int v1 = this.e.read();
                int v2 = this.e.read();
                int v3 = this.e.read();
                if((v0 | v1 | v2 | v3) >= 0) {
                    if(this.f == android.support.d.a$a.c) {
                        return (v3 << 24) + (v2 << 16) + (v1 << 8) + v0;
                    }

                    if(this.f == android.support.d.a$a.d) {
                        return (v0 << 24) + (v1 << 16) + (v2 << 8) + v3;
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Invalid byte order: ");
                    v1_1.append(this.f);
                    throw new IOException(v1_1.toString());
                }

                throw new EOFException();
            }

            throw new EOFException();
        }

        public String readLine() {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        public long readLong() {
            android.support.d.a$a v0 = this;
            v0.b += 8;
            if(v0.b <= v0.a) {
                int v1 = v0.e.read();
                int v3 = v0.e.read();
                int v4 = v0.e.read();
                int v5 = v0.e.read();
                int v6 = v0.e.read();
                int v7 = v0.e.read();
                int v8 = v0.e.read();
                int v9 = v0.e.read();
                if((v1 | v3 | v4 | v5 | v6 | v7 | v8 | v9) >= 0) {
                    int v12 = 16;
                    int v13 = 24;
                    int v14 = 32;
                    int v15 = 40;
                    int v16 = 48;
                    int v17 = 56;
                    if(v0.f == android.support.d.a$a.c) {
                        return ((((long)v9)) << v17) + ((((long)v8)) << v16) + ((((long)v7)) << v15) + ((((long)v6)) << v14) + ((((long)v5)) << v13) + ((((long)v4)) << v12) + ((((long)v3)) << 8) + (((long)v1));
                    }

                    int v2 = v3;
                    if(v0.f == android.support.d.a$a.d) {
                        return ((((long)v1)) << v17) + ((((long)v2)) << v16) + ((((long)v4)) << v15) + ((((long)v5)) << v14) + ((((long)v6)) << v13) + ((((long)v7)) << v12) + ((((long)v8)) << 8) + (((long)v9));
                    }

                    StringBuilder v2_1 = new StringBuilder();
                    v2_1.append("Invalid byte order: ");
                    v2_1.append(v0.f);
                    throw new IOException(v2_1.toString());
                }

                throw new EOFException();
            }

            throw new EOFException();
        }

        public short readShort() {
            this.b += 2;
            if(this.b <= this.a) {
                int v0 = this.e.read();
                int v1 = this.e.read();
                if((v0 | v1) >= 0) {
                    if(this.f == android.support.d.a$a.c) {
                        return ((short)((v1 << 8) + v0));
                    }

                    if(this.f == android.support.d.a$a.d) {
                        return ((short)((v0 << 8) + v1));
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Invalid byte order: ");
                    v1_1.append(this.f);
                    throw new IOException(v1_1.toString());
                }

                throw new EOFException();
            }

            throw new EOFException();
        }

        public String readUTF() {
            this.b += 2;
            return this.e.readUTF();
        }

        public int readUnsignedByte() {
            ++this.b;
            return this.e.readUnsignedByte();
        }

        public int readUnsignedShort() {
            this.b += 2;
            if(this.b <= this.a) {
                int v0 = this.e.read();
                int v1 = this.e.read();
                if((v0 | v1) >= 0) {
                    if(this.f == android.support.d.a$a.c) {
                        return (v1 << 8) + v0;
                    }

                    if(this.f == android.support.d.a$a.d) {
                        return (v0 << 8) + v1;
                    }

                    StringBuilder v1_1 = new StringBuilder();
                    v1_1.append("Invalid byte order: ");
                    v1_1.append(this.f);
                    throw new IOException(v1_1.toString());
                }

                throw new EOFException();
            }

            throw new EOFException();
        }

        public int skipBytes(int arg4) {
            arg4 = Math.min(arg4, this.a - this.b);
            int v0;
            for(v0 = 0; v0 < arg4; v0 += this.e.skipBytes(arg4 - v0)) {
            }

            this.b += v0;
            return v0;
        }
    }

    class b {
        public final int a;
        public final int b;
        public final byte[] c;

        b(int arg1, int arg2, byte[] arg3) {
            super();
            this.a = arg1;
            this.b = arg2;
            this.c = arg3;
        }

        public static b a(String arg3) {
            StringBuilder v0 = new StringBuilder();
            v0.append(arg3);
            v0.append('\u0000');
            byte[] v3 = v0.toString().getBytes(a.i);
            return new b(2, v3.length, v3);
        }

        public static b a(long arg2, ByteOrder arg4) {
            return b.a(new long[]{arg2}, arg4);
        }

        Object a(ByteOrder arg9) {
            long[] v9_8;
            int[] v9_7;
            String v9_6;
            double[] v9_3;
            d[] v9_2;
            int v3;
            int v2;
            android.support.d.a$a v1;
            Object v0 = null;
            try {
                v1 = new android.support.d.a$a(this.c);
            }
            catch(Throwable v9) {
                v1 = ((android.support.d.a$a)v0);
                goto label_245;
            }
            catch(IOException v9_1) {
                v1 = ((android.support.d.a$a)v0);
                goto label_233;
            }

            try {
                v1.a(arg9);
                v2 = 1;
                v3 = 0;
                switch(this.a) {
                    case 3: {
                        goto label_124;
                    }
                    case 4: {
                        goto label_109;
                    }
                    case 5: {
                        goto label_91;
                    }
                    case 1: 
                    case 6: {
                        goto label_184;
                    }
                    case 2: 
                    case 7: {
                        goto label_139;
                    }
                    case 8: {
                        goto label_76;
                    }
                    case 9: {
                        goto label_61;
                    }
                    case 10: {
                        goto label_41;
                    }
                    case 11: {
                        goto label_25;
                    }
                    case 12: {
                        goto label_10;
                    }
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v9_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v9_1));
            }

            return v0;
            try {
            label_41:
                v9_2 = new d[this.b];
                while(v3 < this.b) {
                    v9_2[v3] = new d(((long)v1.readInt()), ((long)v1.readInt()));
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_2;
            try {
            label_10:
                v9_3 = new double[this.b];
                while(v3 < this.b) {
                    v9_3[v3] = v1.readDouble();
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_3;
            try {
            label_139:
                if(this.b >= a.g.length) {
                    int v9_4 = 0;
                    while(v9_4 < a.g.length) {
                        if(this.c[v9_4] != a.g[v9_4]) {
                            v2 = 0;
                        }
                        else {
                            ++v9_4;
                            continue;
                        }

                        break;
                    }

                    if(v2 == 0) {
                        goto label_159;
                    }

                    v3 = a.g.length;
                }

            label_159:
                StringBuilder v9_5 = new StringBuilder();
                while(v3 < this.b) {
                    v2 = this.c[v3];
                    if(v2 == 0) {
                    }
                    else {
                        if(v2 >= 32) {
                            v9_5.append(((char)v2));
                        }
                        else {
                            v9_5.append('?');
                        }

                        ++v3;
                        continue;
                    }

                    break;
                }

                v9_6 = v9_5.toString();
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_6;
            try {
            label_76:
                v9_7 = new int[this.b];
                while(v3 < this.b) {
                    v9_7[v3] = v1.readShort();
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_7;
            try {
            label_109:
                v9_8 = new long[this.b];
                while(v3 < this.b) {
                    v9_8[v3] = v1.b();
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_8;
            try {
            label_184:
                if(this.c.length == 1 && this.c[0] >= 0 && this.c[0] <= 1) {
                    v9_6 = new String(new char[]{((char)(this.c[0] + 48))});
                    goto label_201;
                }

                goto label_208;
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
            label_201:
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_6;
            try {
            label_208:
                v9_6 = new String(this.c, a.i);
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_6;
            try {
            label_25:
                v9_3 = new double[this.b];
                while(v3 < this.b) {
                    v9_3[v3] = ((double)v1.readFloat());
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_3;
            try {
            label_91:
                v9_2 = new d[this.b];
                while(v3 < this.b) {
                    v9_2[v3] = new d(v1.b(), v1.b());
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_2;
            try {
            label_124:
                v9_7 = new int[this.b];
                while(v3 < this.b) {
                    v9_7[v3] = v1.readUnsignedShort();
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_7;
            try {
            label_61:
                v9_7 = new int[this.b];
                while(v3 < this.b) {
                    v9_7[v3] = v1.readInt();
                    ++v3;
                }
            }
            catch(IOException v9_1) {
                goto label_227;
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v0_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
            }

            return v9_7;
        label_227:
            try {
            label_233:
                Log.w("ExifInterface", "IOException occurred during reading a value", ((Throwable)v9_1));
                if(v1 == null) {
                    return v0;
                }
            }
            catch(Throwable v9) {
                goto label_245;
            }

            try {
                v1.close();
            }
            catch(IOException v9_1) {
                Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v9_1));
            }

            return v0;
        label_245:
            if(v1 != null) {
                try {
                    v1.close();
                }
                catch(IOException v0_1) {
                    Log.e("ExifInterface", "IOException occurred while closing InputStream", ((Throwable)v0_1));
                }
            }

            throw v9;
        }

        public static b a(int arg2, ByteOrder arg3) {
            return b.a(new int[]{arg2}, arg3);
        }

        public static b a(d arg2, ByteOrder arg3) {
            return b.a(new d[]{arg2}, arg3);
        }

        public static b a(int[] arg4, ByteOrder arg5) {
            int v1 = 3;
            ByteBuffer v0 = ByteBuffer.wrap(new byte[a.f[v1] * arg4.length]);
            v0.order(arg5);
            int v5 = arg4.length;
            int v2;
            for(v2 = 0; v2 < v5; ++v2) {
                v0.putShort(((short)arg4[v2]));
            }

            return new b(v1, arg4.length, v0.array());
        }

        public static b a(long[] arg5, ByteOrder arg6) {
            int v1 = 4;
            ByteBuffer v0 = ByteBuffer.wrap(new byte[a.f[v1] * arg5.length]);
            v0.order(arg6);
            int v6 = arg5.length;
            int v2;
            for(v2 = 0; v2 < v6; ++v2) {
                v0.putInt(((int)arg5[v2]));
            }

            return new b(v1, arg5.length, v0.array());
        }

        public static b a(d[] arg6, ByteOrder arg7) {
            int v1 = 5;
            ByteBuffer v0 = ByteBuffer.wrap(new byte[a.f[v1] * arg6.length]);
            v0.order(arg7);
            int v7 = arg6.length;
            int v2;
            for(v2 = 0; v2 < v7; ++v2) {
                d v3 = arg6[v2];
                v0.putInt(((int)v3.a));
                v0.putInt(((int)v3.b));
            }

            return new b(v1, arg6.length, v0.array());
        }

        public double b(ByteOrder arg4) {
            Object v4 = this.a(arg4);
            if(v4 != null) {
                if((v4 instanceof String)) {
                    return Double.parseDouble(((String)v4));
                }

                if((v4 instanceof long[])) {
                    if(v4.length == 1) {
                        return ((double)v4[0]);
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                if((v4 instanceof int[])) {
                    if(v4.length == 1) {
                        return ((double)v4[0]);
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                if((v4 instanceof double[])) {
                    if(v4.length == 1) {
                        return v4[0];
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                if((v4 instanceof d[])) {
                    if(v4.length == 1) {
                        return v4[0].a();
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                throw new NumberFormatException("Couldn\'t find a double value");
            }

            throw new NumberFormatException("NULL can\'t be converted to a double value");
        }

        public int c(ByteOrder arg4) {
            Object v4 = this.a(arg4);
            if(v4 != null) {
                if((v4 instanceof String)) {
                    return Integer.parseInt(((String)v4));
                }

                if((v4 instanceof long[])) {
                    if(v4.length == 1) {
                        return ((int)v4[0]);
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                if((v4 instanceof int[])) {
                    if(v4.length == 1) {
                        return v4[0];
                    }

                    throw new NumberFormatException("There are more than one component");
                }

                throw new NumberFormatException("Couldn\'t find a integer value");
            }

            throw new NumberFormatException("NULL can\'t be converted to a integer value");
        }

        public String d(ByteOrder arg7) {
            Object v7 = this.a(arg7);
            String v0 = null;
            if(v7 == null) {
                return v0;
            }

            if((v7 instanceof String)) {
                return ((String)v7);
            }

            StringBuilder v1 = new StringBuilder();
            int v3 = 0;
            if((v7 instanceof long[])) {
                while(v3 < v7.length) {
                    v1.append(v7[v3]);
                    ++v3;
                    if(v3 == v7.length) {
                        continue;
                    }

                    v1.append(",");
                }

                return v1.toString();
            }

            if((v7 instanceof int[])) {
                while(v3 < v7.length) {
                    v1.append(v7[v3]);
                    ++v3;
                    if(v3 == v7.length) {
                        continue;
                    }

                    v1.append(",");
                }

                return v1.toString();
            }

            if((v7 instanceof double[])) {
                while(v3 < v7.length) {
                    v1.append(v7[v3]);
                    ++v3;
                    if(v3 == v7.length) {
                        continue;
                    }

                    v1.append(",");
                }

                return v1.toString();
            }

            if((v7 instanceof d[])) {
                while(v3 < v7.length) {
                    v1.append(v7[v3].a);
                    v1.append('/');
                    v1.append(v7[v3].b);
                    ++v3;
                    if(v3 == v7.length) {
                        continue;
                    }

                    v1.append(",");
                }

                return v1.toString();
            }

            return v0;
        }

        public String toString() {
            return "(" + a.e[this.a] + ", data length:" + this.c.length + ")";
        }
    }

    class c {
        public final int a;
        public final String b;
        public final int c;
        public final int d;

        c(String arg1, int arg2, int arg3) {
            super();
            this.b = arg1;
            this.a = arg2;
            this.c = arg3;
            this.d = -1;
        }

        c(String arg1, int arg2, int arg3, int arg4) {
            super();
            this.b = arg1;
            this.a = arg2;
            this.c = arg3;
            this.d = arg4;
        }

        boolean a(int arg4) {
            int v1 = 7;
            if(this.c != v1) {
                if(arg4 == v1) {
                }
                else if(this.c != arg4) {
                    if(this.d == arg4) {
                    }
                    else {
                        v1 = 4;
                        if((this.c == v1 || this.d == v1) && arg4 == 3) {
                            return 1;
                        }

                        v1 = 9;
                        if((this.c == v1 || this.d == v1) && arg4 == 8) {
                            return 1;
                        }

                        v1 = 12;
                        if((this.c == v1 || this.d == v1) && arg4 == 11) {
                            return 1;
                        }

                        return 0;
                    }
                }
            }

            return 1;
        }
    }

    class d {
        public final long a;
        public final long b;

        d(long arg4, long arg6) {
            super();
            long v0 = 0;
            if(arg6 == v0) {
                this.a = v0;
                this.b = 1;
                return;
            }

            this.a = arg4;
            this.b = arg6;
        }

        public double a() {
            double v0 = ((double)this.a);
            double v2 = ((double)this.b);
            Double.isNaN(v0);
            Double.isNaN(v2);
            return v0 / v2;
        }

        public String toString() {
            return this.a + "/" + this.b;
        }
    }

    private static final c A;
    private static final c B;
    private static final HashMap[] C;
    private static final HashMap[] D;
    private static final HashSet E;
    private static final HashMap F;
    private final String G;
    private final AssetManager$AssetInputStream H;
    private int I;
    private final HashMap[] J;
    private Set K;
    private ByteOrder L;
    private boolean M;
    private int N;
    private int O;
    private byte[] P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private int V;
    private boolean W;
    private static final Pattern X;
    private static final Pattern Y;
    public static final int[] a;
    public static final int[] b;
    public static final int[] c;
    static final byte[] d;
    static final String[] e;
    static final int[] f;
    static final byte[] g;
    static final c[][] h;
    static final Charset i;
    static final byte[] j;
    private static final List k;
    private static final List l;
    private static final byte[] m;
    private static final byte[] n;
    private static SimpleDateFormat o;
    private static final c[] p;
    private static final c[] q;
    private static final c[] r;
    private static final c[] s;
    private static final c[] t;
    private static final c u;
    private static final c[] v;
    private static final c[] w;
    private static final c[] x;
    private static final c[] y;
    private static final c[] z;

    static {
        int v0 = 4;
        Integer[] v1 = new Integer[v0];
        v1[0] = Integer.valueOf(1);
        v1[1] = Integer.valueOf(6);
        int v5 = 3;
        int v7 = 2;
        v1[v7] = Integer.valueOf(v5);
        int v6 = 8;
        v1[v5] = Integer.valueOf(v6);
        a.k = Arrays.asList(((Object[])v1));
        v1 = new Integer[v0];
        v1[0] = Integer.valueOf(v7);
        int v8 = 7;
        v1[1] = Integer.valueOf(v8);
        v1[v7] = Integer.valueOf(v0);
        int v9 = 5;
        v1[v5] = Integer.valueOf(v9);
        a.l = Arrays.asList(((Object[])v1));
        a.a = new int[]{8, 8, 8};
        a.b = new int[]{v0};
        a.c = new int[]{v6};
        a.d = new byte[]{-1, -40, -1};
        a.m = new byte[]{79, 76, 89, 77, 80, 0};
        a.n = new byte[]{79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
        String[] v11 = new String[13];
        v11[0] = "";
        v11[1] = "BYTE";
        v11[v7] = "STRING";
        v11[v5] = "USHORT";
        v11[v0] = "ULONG";
        v11[v9] = "URATIONAL";
        v11[6] = "SBYTE";
        v11[v8] = "UNDEFINED";
        v11[v6] = "SSHORT";
        v11[9] = "SLONG";
        v11[10] = "SRATIONAL";
        v11[11] = "SINGLE";
        v11[12] = "DOUBLE";
        a.e = v11;
        a.f = new int[]{0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
        a.g = new byte[]{65, 83, 67, 73, 73, 0, 0, 0};
        c[] v12 = new c[41];
        v12[0] = new c("NewSubfileType", 254, v0);
        v12[1] = new c("SubfileType", 255, v0);
        v12[v7] = new c("ImageWidth", 256, v5, v0);
        v12[v5] = new c("ImageLength", 257, v5, v0);
        v12[v0] = new c("BitsPerSample", 258, v5);
        v12[v9] = new c("Compression", 259, v5);
        v12[6] = new c("PhotometricInterpretation", 262, v5);
        v12[v8] = new c("ImageDescription", 270, v7);
        v12[v6] = new c("Make", 271, v7);
        v12[9] = new c("Model", 272, v7);
        v12[10] = new c("StripOffsets", 273, v5, v0);
        v12[11] = new c("Orientation", 274, v5);
        v12[12] = new c("SamplesPerPixel", 277, v5);
        v12[13] = new c("RowsPerStrip", 278, v5, v0);
        v12[14] = new c("StripByteCounts", 279, v5, v0);
        v12[15] = new c("XResolution", 282, v9);
        v12[16] = new c("YResolution", 283, v9);
        v12[17] = new c("PlanarConfiguration", 284, v5);
        v12[18] = new c("ResolutionUnit", 296, v5);
        v12[19] = new c("TransferFunction", 301, v5);
        v12[20] = new c("Software", 305, v7);
        v12[21] = new c("DateTime", 306, v7);
        v12[22] = new c("Artist", 315, v7);
        v12[23] = new c("WhitePoint", 318, v9);
        v12[24] = new c("PrimaryChromaticities", 319, v9);
        v12[25] = new c("SubIFDPointer", 330, v0);
        v12[26] = new c("JPEGInterchangeFormat", 513, v0);
        v12[27] = new c("JPEGInterchangeFormatLength", 514, v0);
        v12[28] = new c("YCbCrCoefficients", 529, v9);
        v12[29] = new c("YCbCrSubSampling", 530, v5);
        v12[30] = new c("YCbCrPositioning", 531, v5);
        v12[31] = new c("ReferenceBlackWhite", 532, v9);
        v12[32] = new c("Copyright", 33432, v7);
        v12[33] = new c("ExifIFDPointer", 34665, v0);
        v12[34] = new c("GPSInfoIFDPointer", 34853, v0);
        v12[35] = new c("SensorTopBorder", v0, v0);
        v12[36] = new c("SensorLeftBorder", v9, v0);
        v12[37] = new c("SensorBottomBorder", 6, v0);
        v12[38] = new c("SensorRightBorder", v8, v0);
        v12[39] = new c("ISO", 23, v5);
        v12[40] = new c("JpgFromRaw", 46, v8);
        a.p = v12;
        c[] v10 = new c[59];
        v10[0] = new c("ExposureTime", 33434, v9);
        v10[1] = new c("FNumber", 33437, v9);
        v10[v7] = new c("ExposureProgram", 34850, v5);
        v10[v5] = new c("SpectralSensitivity", 34852, v7);
        v10[v0] = new c("PhotographicSensitivity", 34855, v5);
        v10[v9] = new c("OECF", 34856, v8);
        v10[6] = new c("ExifVersion", 36864, v7);
        v10[v8] = new c("DateTimeOriginal", 36867, v7);
        v10[v6] = new c("DateTimeDigitized", 36868, v7);
        v10[9] = new c("ComponentsConfiguration", 37121, v8);
        v10[10] = new c("CompressedBitsPerPixel", 37122, v9);
        v10[11] = new c("ShutterSpeedValue", 37377, 10);
        v10[12] = new c("ApertureValue", 37378, v9);
        v10[13] = new c("BrightnessValue", 37379, 10);
        v10[14] = new c("ExposureBiasValue", 37380, 10);
        v10[15] = new c("MaxApertureValue", 37381, v9);
        v10[16] = new c("SubjectDistance", 37382, v9);
        v10[17] = new c("MeteringMode", 37383, v5);
        v10[18] = new c("LightSource", 37384, v5);
        v10[19] = new c("Flash", 37385, v5);
        v10[20] = new c("FocalLength", 37386, v9);
        v10[21] = new c("SubjectArea", 37396, v5);
        v10[22] = new c("MakerNote", 37500, v8);
        v10[23] = new c("UserComment", 37510, v8);
        v10[24] = new c("SubSecTime", 37520, v7);
        v10[25] = new c("SubSecTimeOriginal", 37521, v7);
        v10[26] = new c("SubSecTimeDigitized", 37522, v7);
        v10[27] = new c("FlashpixVersion", 40960, v8);
        v10[28] = new c("ColorSpace", 40961, v5);
        v10[29] = new c("PixelXDimension", 40962, v5, v0);
        v10[30] = new c("PixelYDimension", 40963, v5, v0);
        v10[31] = new c("RelatedSoundFile", 40964, v7);
        v10[32] = new c("InteroperabilityIFDPointer", 40965, v0);
        v10[33] = new c("FlashEnergy", 41483, v9);
        v10[34] = new c("SpatialFrequencyResponse", 41484, v8);
        v10[35] = new c("FocalPlaneXResolution", 41486, v9);
        v10[36] = new c("FocalPlaneYResolution", 41487, v9);
        v10[37] = new c("FocalPlaneResolutionUnit", 41488, v5);
        v10[38] = new c("SubjectLocation", 41492, v5);
        v10[39] = new c("ExposureIndex", 41493, v9);
        v10[40] = new c("SensingMethod", 41495, v5);
        v10[41] = new c("FileSource", 41728, v8);
        v10[42] = new c("SceneType", 41729, v8);
        v10[43] = new c("CFAPattern", 41730, v8);
        v10[44] = new c("CustomRendered", 41985, v5);
        v10[45] = new c("ExposureMode", 41986, v5);
        v10[46] = new c("WhiteBalance", 41987, v5);
        v10[47] = new c("DigitalZoomRatio", 41988, v9);
        v10[48] = new c("FocalLengthIn35mmFilm", 41989, v5);
        v10[49] = new c("SceneCaptureType", 41990, v5);
        v10[50] = new c("GainControl", 41991, v5);
        v10[51] = new c("Contrast", 41992, v5);
        v10[52] = new c("Saturation", 41993, v5);
        v10[53] = new c("Sharpness", 41994, v5);
        v10[54] = new c("DeviceSettingDescription", 41995, v8);
        v10[55] = new c("SubjectDistanceRange", 41996, v5);
        v10[56] = new c("ImageUniqueID", 42016, v7);
        v10[57] = new c("DNGVersion", 50706, 1);
        v10[58] = new c("DefaultCropSize", 50720, v5, v0);
        a.q = v10;
        v10 = new c[31];
        v10[0] = new c("GPSVersionID", 0, 1);
        v10[1] = new c("GPSLatitudeRef", 1, v7);
        v10[v7] = new c("GPSLatitude", v7, v9);
        v10[v5] = new c("GPSLongitudeRef", v5, v7);
        v10[v0] = new c("GPSLongitude", v0, v9);
        v10[v9] = new c("GPSAltitudeRef", v9, 1);
        v10[6] = new c("GPSAltitude", 6, v9);
        v10[v8] = new c("GPSTimeStamp", v8, v9);
        v10[v6] = new c("GPSSatellites", v6, v7);
        v10[9] = new c("GPSStatus", 9, v7);
        v10[10] = new c("GPSMeasureMode", 10, v7);
        v10[11] = new c("GPSDOP", 11, v9);
        v10[12] = new c("GPSSpeedRef", 12, v7);
        v10[13] = new c("GPSSpeed", 13, v9);
        v10[14] = new c("GPSTrackRef", 14, v7);
        v10[15] = new c("GPSTrack", 15, v9);
        v10[16] = new c("GPSImgDirectionRef", 16, v7);
        v10[17] = new c("GPSImgDirection", 17, v9);
        v10[18] = new c("GPSMapDatum", 18, v7);
        v10[19] = new c("GPSDestLatitudeRef", 19, v7);
        v10[20] = new c("GPSDestLatitude", 20, v9);
        v10[21] = new c("GPSDestLongitudeRef", 21, v7);
        v10[22] = new c("GPSDestLongitude", 22, v9);
        v10[23] = new c("GPSDestBearingRef", 23, v7);
        v10[24] = new c("GPSDestBearing", 24, v9);
        v10[25] = new c("GPSDestDistanceRef", 25, v7);
        v10[26] = new c("GPSDestDistance", 26, v9);
        v10[27] = new c("GPSProcessingMethod", 27, v8);
        v10[28] = new c("GPSAreaInformation", 28, v8);
        v10[29] = new c("GPSDateStamp", 29, v7);
        v10[30] = new c("GPSDifferential", 30, v5);
        a.r = v10;
        a.s = new c[]{new c("InteroperabilityIndex", 1, v7)};
        v10 = new c[37];
        v10[0] = new c("NewSubfileType", 254, v0);
        v10[1] = new c("SubfileType", 255, v0);
        v10[v7] = new c("ThumbnailImageWidth", 256, v5, v0);
        v10[v5] = new c("ThumbnailImageLength", 257, v5, v0);
        v10[v0] = new c("BitsPerSample", 258, v5);
        v10[v9] = new c("Compression", 259, v5);
        v10[6] = new c("PhotometricInterpretation", 262, v5);
        v10[v8] = new c("ImageDescription", 270, v7);
        v10[v6] = new c("Make", 271, v7);
        v10[9] = new c("Model", 272, v7);
        v10[10] = new c("StripOffsets", 273, v5, v0);
        v10[11] = new c("Orientation", 274, v5);
        v10[12] = new c("SamplesPerPixel", 277, v5);
        v10[13] = new c("RowsPerStrip", 278, v5, v0);
        v10[14] = new c("StripByteCounts", 279, v5, v0);
        v10[15] = new c("XResolution", 282, v9);
        v10[16] = new c("YResolution", 283, v9);
        v10[17] = new c("PlanarConfiguration", 284, v5);
        v10[18] = new c("ResolutionUnit", 296, v5);
        v10[19] = new c("TransferFunction", 301, v5);
        v10[20] = new c("Software", 305, v7);
        v10[21] = new c("DateTime", 306, v7);
        v10[22] = new c("Artist", 315, v7);
        v10[23] = new c("WhitePoint", 318, v9);
        v10[24] = new c("PrimaryChromaticities", 319, v9);
        v10[25] = new c("SubIFDPointer", 330, v0);
        v10[26] = new c("JPEGInterchangeFormat", 513, v0);
        v10[27] = new c("JPEGInterchangeFormatLength", 514, v0);
        v10[28] = new c("YCbCrCoefficients", 529, v9);
        v10[29] = new c("YCbCrSubSampling", 530, v5);
        v10[30] = new c("YCbCrPositioning", 531, v5);
        v10[31] = new c("ReferenceBlackWhite", 532, v9);
        v10[32] = new c("Copyright", 33432, v7);
        v10[33] = new c("ExifIFDPointer", 34665, v0);
        v10[34] = new c("GPSInfoIFDPointer", 34853, v0);
        v10[35] = new c("DNGVersion", 50706, 1);
        v10[36] = new c("DefaultCropSize", 50720, v5, v0);
        a.t = v10;
        a.u = new c("StripOffsets", 273, v5);
        v10 = new c[v5];
        v10[0] = new c("ThumbnailImage", 256, v8);
        v10[1] = new c("CameraSettingsIFDPointer", 8224, v0);
        v10[v7] = new c("ImageProcessingIFDPointer", 8256, v0);
        a.v = v10;
        v10 = new c[v7];
        v10[0] = new c("PreviewImageStart", 257, v0);
        v10[1] = new c("PreviewImageLength", 258, v0);
        a.w = v10;
        a.x = new c[]{new c("AspectFrame", 4371, v5)};
        a.y = new c[]{new c("ColorSpace", 55, v5)};
        c[][] v1_1 = new c[10][];
        v1_1[0] = a.p;
        v1_1[1] = a.q;
        v1_1[v7] = a.r;
        v1_1[v5] = a.s;
        v1_1[v0] = a.t;
        v1_1[v9] = a.p;
        v1_1[6] = a.v;
        v1_1[v8] = a.w;
        v1_1[v6] = a.x;
        v1_1[9] = a.y;
        a.h = v1_1;
        c[] v1_2 = new c[6];
        v1_2[0] = new c("SubIFDPointer", 330, v0);
        v1_2[1] = new c("ExifIFDPointer", 34665, v0);
        v1_2[v7] = new c("GPSInfoIFDPointer", 34853, v0);
        v1_2[v5] = new c("InteroperabilityIFDPointer", 40965, v0);
        v1_2[v0] = new c("CameraSettingsIFDPointer", 8224, 1);
        v1_2[v9] = new c("ImageProcessingIFDPointer", 8256, 1);
        a.z = v1_2;
        a.A = new c("JPEGInterchangeFormat", 513, v0);
        a.B = new c("JPEGInterchangeFormatLength", 514, v0);
        a.C = new HashMap[a.h.length];
        a.D = new HashMap[a.h.length];
        String[] v3 = new String[v9];
        v3[0] = "FNumber";
        v3[1] = "DigitalZoomRatio";
        v3[v7] = "ExposureTime";
        v3[v5] = "SubjectDistance";
        v3[v0] = "GPSTimeStamp";
        a.E = new HashSet(Arrays.asList(((Object[])v3)));
        a.F = new HashMap();
        a.i = Charset.forName("US-ASCII");
        a.j = "Exif\u0000\u0000".getBytes(a.i);
        a.o = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        a.o.setTimeZone(TimeZone.getTimeZone("UTC"));
        int v1_3;
        for(v1_3 = 0; v1_3 < a.h.length; ++v1_3) {
            a.C[v1_3] = new HashMap();
            a.D[v1_3] = new HashMap();
            c[] v3_1 = a.h[v1_3];
            int v10_1 = v3_1.length;
            int v11_1;
            for(v11_1 = 0; v11_1 < v10_1; ++v11_1) {
                c v12_1 = v3_1[v11_1];
                a.C[v1_3].put(Integer.valueOf(v12_1.a), v12_1);
                a.D[v1_3].put(v12_1.b, v12_1);
            }
        }

        a.F.put(Integer.valueOf(a.z[0].a), Integer.valueOf(v9));
        a.F.put(Integer.valueOf(a.z[1].a), Integer.valueOf(1));
        a.F.put(Integer.valueOf(a.z[v7].a), Integer.valueOf(v7));
        a.F.put(Integer.valueOf(a.z[v5].a), Integer.valueOf(v5));
        a.F.put(Integer.valueOf(a.z[v0].a), Integer.valueOf(v8));
        a.F.put(Integer.valueOf(a.z[v9].a), Integer.valueOf(v6));
        a.X = Pattern.compile(".*[1-9].*");
        a.Y = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    }

    public a(String arg3) {
        FileInputStream v0_1;
        FileInputStream v1;
        super();
        this.J = new HashMap[a.h.length];
        this.K = new HashSet(a.h.length);
        this.L = ByteOrder.BIG_ENDIAN;
        if(arg3 == null) {
            goto label_27;
        }

        AssetManager$AssetInputStream v0 = null;
        this.H = v0;
        this.G = arg3;
        try {
            v1 = new FileInputStream(arg3);
        }
        catch(Throwable v3) {
            goto label_25;
        }

        try {
            this.a(((InputStream)v1));
            goto label_19;
        }
        catch(Throwable v3) {
            v0_1 = v1;
        }

    label_25:
        a.a(((Closeable)v0_1));
        throw v3;
    label_19:
        a.a(((Closeable)v1));
        return;
    label_27:
        throw new IllegalArgumentException("filename cannot be null");
    }

    private void a(InputStream arg5) {
        int v1 = 0;
        try {
            while(v1 < a.h.length) {
                this.J[v1] = new HashMap();
                ++v1;
            }

            BufferedInputStream v1_1 = new BufferedInputStream(arg5, 5000);
            this.I = this.a(v1_1);
            android.support.d.a$a v5_1 = new android.support.d.a$a(((InputStream)v1_1));
            switch(this.I) {
                case 4: {
                    this.a(v5_1, 0, 0);
                    break;
                }
                case 7: {
                    this.c(v5_1);
                    break;
                }
                case 9: {
                    this.b(v5_1);
                    break;
                }
                case 10: {
                    this.d(v5_1);
                    break;
                }
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 5: 
                case 6: 
                case 8: 
                case 11: {
                    this.a(v5_1);
                    break;
                }
                default: {
                    break;
                }
            }

            this.f(v5_1);
            this.W = true;
        }
        catch(Throwable v5) {
        }
        catch(IOException ) {
            try {
                this.W = false;
            }
            catch(Throwable v5) {
                this.a();
                throw v5;
            }
        }

        this.a();
    }

    private static void a(Closeable arg0) {
        if(arg0 != null) {
            try {
                arg0.close();
                return;
            }
            catch(Exception ) {
                return;
            }
            catch(RuntimeException v0) {
                throw v0;
            }
        }
    }

    private int a(BufferedInputStream arg2) {
        arg2.mark(5000);
        byte[] v0 = new byte[5000];
        arg2.read(v0);
        arg2.reset();
        if(a.a(v0)) {
            return 4;
        }

        if(this.b(v0)) {
            return 9;
        }

        if(this.c(v0)) {
            return 7;
        }

        if(this.d(v0)) {
            return 10;
        }

        return 0;
    }

    private static boolean a(byte[] arg4) {
        int v1;
        for(v1 = 0; v1 < a.d.length; ++v1) {
            if(arg4[v1] != a.d[v1]) {
                return 0;
            }
        }

        return 1;
    }

    private void a() {
        String v0 = this.a("DateTimeOriginal");
        if(v0 != null && this.a("DateTime") == null) {
            this.J[0].put("DateTime", b.a(v0));
        }

        long v2 = 0;
        if(this.a("ImageWidth") == null) {
            this.J[0].put("ImageWidth", b.a(v2, this.L));
        }

        if(this.a("ImageLength") == null) {
            this.J[0].put("ImageLength", b.a(v2, this.L));
        }

        if(this.a("Orientation") == null) {
            this.J[0].put("Orientation", b.a(v2, this.L));
        }

        if(this.a("LightSource") == null) {
            this.J[1].put("LightSource", b.a(v2, this.L));
        }
    }

    public String a(String arg7) {
        b v0 = this.b(arg7);
        String v1 = null;
        if(v0 == null) {
            return v1;
        }

        if(!a.E.contains(arg7)) {
            return v0.d(this.L);
        }

        if(arg7.equals("GPSTimeStamp")) {
            if(v0.a != 5 && v0.a != 10) {
                Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + v0.a);
                return v1;
            }

            Object v7 = v0.a(this.L);
            if(v7 != null) {
                int v2_1 = 3;
                if(v7.length != v2_1) {
                }
                else {
                    return String.format("%02d:%02d:%02d", Integer.valueOf(((int)((((float)v7[0].a)) / (((float)v7[0].b))))), Integer.valueOf(((int)((((float)v7[1].a)) / (((float)v7[1].b))))), Integer.valueOf(((int)((((float)v7[2].a)) / (((float)v7[2].b))))));
                }
            }

            Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(((Object[])v7)));
            return v1;
        }

        try {
            return Double.toString(v0.b(this.L));
        }
        catch(NumberFormatException ) {
            return v1;
        }
    }

    private void a(int arg6, int arg7) {
        if(!this.J[arg6].isEmpty()) {
            if(this.J[arg7].isEmpty()) {
            }
            else {
                Object v0 = this.J[arg6].get("ImageLength");
                Object v1 = this.J[arg6].get("ImageWidth");
                Object v2 = this.J[arg7].get("ImageLength");
                Object v3 = this.J[arg7].get("ImageWidth");
                if(v0 != null) {
                    if(v1 == null) {
                    }
                    else if(v2 != null) {
                        if(v3 == null) {
                        }
                        else {
                            int v0_1 = ((b)v0).c(this.L);
                            int v1_1 = ((b)v1).c(this.L);
                            int v2_1 = ((b)v2).c(this.L);
                            int v3_1 = ((b)v3).c(this.L);
                            if(v0_1 < v2_1 && v1_1 < v3_1) {
                                HashMap v0_2 = this.J[arg6];
                                this.J[arg6] = this.J[arg7];
                                this.J[arg7] = v0_2;
                            }
                        }
                    }
                }
            }
        }
    }

    private void a(android.support.d.a$a arg5) {
        this.a(arg5, arg5.available());
        this.b(arg5, 0);
        this.d(arg5, 0);
        this.d(arg5, 5);
        this.d(arg5, 4);
        this.b(((InputStream)arg5));
        if(this.I == 8) {
            Object v5 = this.J[1].get("MakerNote");
            if(v5 != null) {
                android.support.d.a$a v1 = new android.support.d.a$a(((b)v5).c);
                v1.a(this.L);
                v1.a(6);
                this.b(v1, 9);
                v5 = this.J[9].get("ColorSpace");
                if(v5 != null) {
                    this.J[1].put("ColorSpace", v5);
                }
            }
        }
    }

    private void a(android.support.d.a$a arg4, int arg5) {
        StringBuilder v5;
        this.L = this.e(arg4);
        arg4.a(this.L);
        int v0 = arg4.readUnsignedShort();
        if(this.I != 7 && this.I != 10) {
            if(v0 == 42) {
            }
            else {
                v5 = new StringBuilder();
                v5.append("Invalid start code: ");
                v5.append(Integer.toHexString(v0));
                throw new IOException(v5.toString());
            }
        }

        v0 = arg4.readInt();
        if(v0 >= 8 && v0 < arg5) {
            v0 += -8;
            if(v0 > 0) {
                if(arg4.skipBytes(v0) == v0) {
                }
                else {
                    v5 = new StringBuilder();
                    v5.append("Couldn\'t jump to first Ifd: ");
                    v5.append(v0);
                    throw new IOException(v5.toString());
                }
            }

            return;
        }

        v5 = new StringBuilder();
        v5.append("Invalid first Ifd offset: ");
        v5.append(v0);
        throw new IOException(v5.toString());
    }

    private void a(android.support.d.a$a arg9, int arg10, int arg11) {
        byte[] v0_1;
        arg9.a(ByteOrder.BIG_ENDIAN);
        arg9.a(((long)arg10));
        int v0 = arg9.readByte();
        int v1 = -1;
        if(v0 != v1) {
            goto label_155;
        }

        ++arg10;
        if(arg9.readByte() != -40) {
            goto label_144;
        }

        ++arg10;
        while(true) {
            v0 = arg9.readByte();
            if(v0 != v1) {
                goto label_133;
            }

            v0 = arg9.readByte();
            arg10 += 2;
            if(v0 != -39) {
                if(v0 == -38) {
                }
                else {
                    int v3 = arg9.readUnsignedShort() - 2;
                    arg10 += 2;
                    if(v3 >= 0) {
                        if(v0 == -31) {
                            v0 = 6;
                            if(v3 < v0) {
                                goto label_101;
                            }

                            byte[] v4 = new byte[v0];
                            if(arg9.read(v4) != v0) {
                                goto label_122;
                            }

                            arg10 += 6;
                            v3 += -6;
                            if(!Arrays.equals(v4, a.j)) {
                                goto label_101;
                            }

                            if(v3 <= 0) {
                                goto label_118;
                            }

                            this.R = arg10;
                            v0_1 = new byte[v3];
                            if(arg9.read(v0_1) != v3) {
                                goto label_114;
                            }

                            arg10 += v3;
                            this.a(v0_1, arg11);
                        label_75:
                            v3 = 0;
                        }
                        else if(v0 != -2) {
                            switch(v0) {
                                case -64: 
                                case -63: 
                                case -62: 
                                case -61: {
                                    break;
                                }
                                default: {
                                    switch(v0) {
                                        case -59: 
                                        case -58: 
                                        case -57: {
                                            goto label_37;
                                        }
                                        default: {
                                            switch(v0) {
                                                case -55: 
                                                case -54: 
                                                case -53: {
                                                    goto label_37;
                                                }
                                                default: {
                                                    switch(v0) {
                                                        case -51: 
                                                        case -50: 
                                                        case -49: {
                                                            goto label_37;
                                                        }
                                                        default: {
                                                            goto label_101;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                        label_37:
                            if(arg9.skipBytes(1) == 1) {
                                this.J[arg11].put("ImageLength", b.a(((long)arg9.readUnsignedShort()), this.L));
                                this.J[arg11].put("ImageWidth", b.a(((long)arg9.readUnsignedShort()), this.L));
                                v3 += -5;
                                goto label_101;
                            }

                            throw new IOException("Invalid SOFx");
                        }
                        else {
                            v0_1 = new byte[v3];
                            if(arg9.read(v0_1) == v3) {
                                if(this.a("UserComment") != null) {
                                    goto label_75;
                                }

                                this.J[1].put("UserComment", b.a(new String(v0_1, a.i)));
                                goto label_75;
                            }

                            throw new IOException("Invalid exif");
                        }

                    label_101:
                        if(v3 < 0) {
                            goto label_110;
                        }

                        if(arg9.skipBytes(v3) != v3) {
                            break;
                        }

                        arg10 += v3;
                        continue;
                    }
                    else {
                        goto label_126;
                    }
                }
            }

            goto label_130;
        }

        throw new IOException("Invalid JPEG segment");
    label_110:
        throw new IOException("Invalid length");
    label_114:
        throw new IOException("Invalid exif");
    label_118:
        throw new IOException("Invalid exif");
    label_122:
        throw new IOException("Invalid exif");
    label_126:
        throw new IOException("Invalid length");
    label_130:
        arg9.a(this.L);
        return;
    label_133:
        StringBuilder v10 = new StringBuilder();
        v10.append("Invalid marker:");
        v10.append(Integer.toHexString(v0 & 255));
        throw new IOException(v10.toString());
    label_144:
        v10 = new StringBuilder();
        v10.append("Invalid marker: ");
        v10.append(Integer.toHexString(v0 & 255));
        throw new IOException(v10.toString());
    label_155:
        v10 = new StringBuilder();
        v10.append("Invalid marker: ");
        v10.append(Integer.toHexString(v0 & 255));
        throw new IOException(v10.toString());
    }

    private void a(byte[] arg2, int arg3) {
        android.support.d.a$a v0 = new android.support.d.a$a(arg2);
        this.a(v0, arg2.length);
        this.b(v0, arg3);
    }

    private void a(android.support.d.a$a arg4, HashMap arg5) {
        int v1;
        Object v0 = arg5.get("JPEGInterchangeFormat");
        Object v5 = arg5.get("JPEGInterchangeFormatLength");
        if(v0 != null && v5 != null) {
            int v0_1 = ((b)v0).c(this.L);
            int v5_1 = Math.min(((b)v5).c(this.L), arg4.available() - v0_1);
            if(this.I == 4 || this.I == 9 || this.I == 10) {
                v1 = this.R;
            label_29:
                v0_1 += v1;
            }
            else if(this.I == 7) {
                v1 = this.S;
                goto label_29;
            }

            if(v0_1 <= 0) {
                return;
            }

            if(v5_1 <= 0) {
                return;
            }

            this.M = true;
            this.N = v0_1;
            this.O = v5_1;
            if(this.G != null) {
                return;
            }

            if(this.H != null) {
                return;
            }

            byte[] v5_2 = new byte[v5_1];
            arg4.a(((long)v0_1));
            arg4.readFully(v5_2);
            this.P = v5_2;
        }
    }

    private boolean a(HashMap arg5) {
        Object v0 = arg5.get("BitsPerSample");
        if(v0 != null) {
            v0 = ((b)v0).a(this.L);
            if(Arrays.equals(a.a, ((int[])v0))) {
                return 1;
            }
            else if(this.I == 3) {
                Object v5 = arg5.get("PhotometricInterpretation");
                if(v5 != null) {
                    int v5_1 = ((b)v5).c(this.L);
                    if(v5_1 != 1 || !Arrays.equals(((int[])v0), a.c)) {
                        if(v5_1 != 6) {
                        }
                        else if(Arrays.equals(((int[])v0), a.a)) {
                            return 1;
                        }

                        return 0;
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    private static long[] a(Object arg4) {
        if((arg4 instanceof int[])) {
            long[] v0 = new long[arg4.length];
            int v1;
            for(v1 = 0; v1 < arg4.length; ++v1) {
                v0[v1] = ((long)arg4[v1]);
            }

            return v0;
        }

        if((arg4 instanceof long[])) {
            return arg4;
        }

        return null;
    }

    public int a(String arg2, int arg3) {
        b v2 = this.b(arg2);
        if(v2 == null) {
            return arg3;
        }

        try {
            return v2.c(this.L);
        }
        catch(NumberFormatException ) {
            return arg3;
        }
    }

    private boolean b(byte[] arg6) {
        byte[] v0 = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        int v2;
        for(v2 = 0; v2 < v0.length; ++v2) {
            if(arg6[v2] != v0[v2]) {
                return 0;
            }
        }

        return 1;
    }

    private void b(android.support.d.a$a arg21, int arg22) {
        String v4_1;
        String v1_1;
        long v3_4;
        String v3_3;
        String v2_3;
        b v3_1;
        int v18;
        int v19;
        int v17;
        int v6_2;
        int v14;
        long v6_1;
        StringBuilder v7;
        String v6;
        a v0 = this;
        android.support.d.a$a v1 = arg21;
        int v2 = arg22;
        v0.K.add(Integer.valueOf(v1.b));
        if(v1.b + 2 > v1.a) {
            return;
        }

        int v3 = arg21.readShort();
        if(v1.b + v3 * 12 <= v1.a) {
            if(v3 <= 0) {
            }
            else {
                int v5 = 0;
                while(v5 < v3) {
                    int v9 = arg21.readUnsignedShort();
                    int v10 = arg21.readUnsignedShort();
                    int v11 = arg21.readInt();
                    long v12 = (((long)arg21.a())) + 4;
                    Object v4 = a.C[v2].get(Integer.valueOf(v9));
                    int v8 = 7;
                    if(v4 == null) {
                        Log.w("ExifInterface", "Skip the tag entry since tag number is not defined: " + v9);
                        goto label_95;
                    }
                    else {
                        if(v10 <= 0 || v10 >= a.f.length) {
                            v6 = "ExifInterface";
                            v7 = new StringBuilder();
                            v7.append("Skip the tag entry since data format is invalid: ");
                            v7.append(v10);
                        }
                        else if(!((c)v4).a(v10)) {
                            v6 = "ExifInterface";
                            v7 = new StringBuilder();
                            v7.append("Skip the tag entry since data format (");
                            v7.append(a.e[v10]);
                            v7.append(") is unexpected for tag: ");
                            v7.append(((c)v4).b);
                        }
                        else {
                            if(v10 == v8) {
                                v10 = ((c)v4).c;
                            }

                            v6_1 = (((long)v11)) * (((long)a.f[v10]));
                            if(v6_1 >= 0) {
                                if(v6_1 > 2147483647) {
                                }
                                else {
                                    v14 = 1;
                                    goto label_97;
                                }
                            }

                            Log.w("ExifInterface", "Skip the tag entry since the number of components is invalid: " + v11);
                            goto label_96;
                        }

                        Log.w(v6, v7.toString());
                    label_95:
                        v6_1 = 0;
                    label_96:
                        v14 = 0;
                    }

                label_97:
                    if(v14 == 0) {
                        v1.a(v12);
                        v6_2 = v2;
                        v17 = v3;
                    }
                    else {
                        if(v6_1 > 4) {
                            v8 = arg21.readInt();
                            if(v0.I == 7) {
                                if("MakerNote".equals(((c)v4).b)) {
                                    v0.S = v8;
                                }
                                else if(v2 == 6 && ("ThumbnailImage".equals(((c)v4).b))) {
                                    v0.T = v8;
                                    v0.U = v11;
                                    b v14_1 = b.a(6, v0.L);
                                    v17 = v3;
                                    b v2_1 = b.a(((long)v0.T), v0.L);
                                    v19 = v10;
                                    v18 = v11;
                                    v3_1 = b.a(((long)v0.U), v0.L);
                                    v0.J[4].put("Compression", v14_1);
                                    v0.J[4].put("JPEGInterchangeFormat", v2_1);
                                    v0.J[4].put("JPEGInterchangeFormatLength", v3_1);
                                    goto label_164;
                                }

                                v17 = v3;
                                v19 = v10;
                                v18 = v11;
                            }
                            else {
                                v17 = v3;
                                v19 = v10;
                                v18 = v11;
                                if(v0.I != 10) {
                                    goto label_164;
                                }

                                if(!"JpgFromRaw".equals(((c)v4).b)) {
                                    goto label_164;
                                }

                                v0.V = v8;
                            }

                        label_164:
                            long v2_2 = ((long)v8);
                            if(v2_2 + v6_1 <= (((long)v1.a))) {
                                v1.a(v2_2);
                                goto label_185;
                            }

                            v2_3 = "ExifInterface";
                            v3_3 = "Skip the tag entry since data offset is invalid: " + v8;
                            goto label_178;
                        }
                        else {
                            v17 = v3;
                            v19 = v10;
                            v18 = v11;
                        label_185:
                            Object v2_4 = a.F.get(Integer.valueOf(v9));
                            if(v2_4 == null) {
                                goto label_237;
                            }

                            v3_4 = -1;
                            switch(v19) {
                                case 3: {
                                    goto label_199;
                                }
                                case 4: {
                                    goto label_197;
                                }
                                case 8: {
                                    goto label_195;
                                }
                                case 9: 
                                case 13: {
                                    goto label_193;
                                }
                            }

                            goto label_191;
                        label_193:
                            v3 = arg21.readInt();
                            goto label_200;
                        label_195:
                            v3 = arg21.readShort();
                            goto label_200;
                        label_197:
                            v3_4 = arg21.b();
                            goto label_191;
                        label_199:
                            v3 = arg21.readUnsignedShort();
                        label_200:
                            v3_4 = ((long)v3);
                        label_191:
                            v6_1 = 0;
                            if(v3_4 > v6_1 && v3_4 < (((long)v1.a))) {
                                if(!v0.K.contains(Integer.valueOf(((int)v3_4)))) {
                                    v1.a(v3_4);
                                    v0.b(v1, ((Integer)v2_4).intValue());
                                }
                                else {
                                    Log.w("ExifInterface", "Skip jump into the IFD since it has already been read: IfdType " + v2_4 + " (at " + v3_4 + ")");
                                }

                                goto label_179;
                            }

                            v2_3 = "ExifInterface";
                            v3_3 = "Skip jump into the IFD since its offset is invalid: " + v3_4;
                        label_178:
                            Log.w(v2_3, v3_3);
                        }

                    label_179:
                        v1.a(v12);
                        v6_2 = arg22;
                        goto label_282;
                    label_237:
                        byte[] v2_5 = new byte[((int)v6_1)];
                        v1.readFully(v2_5);
                        v3_1 = new b(v19, v18, v2_5);
                        v6_2 = arg22;
                        v0.J[v6_2].put(((c)v4).b, v3_1);
                        if("DNGVersion".equals(((c)v4).b)) {
                            v0.I = 3;
                        }

                        if((("Make".equals(((c)v4).b)) || ("Model".equals(((c)v4).b))) && (v3_1.d(v0.L).contains("PENTAX")) || ("Compression".equals(((c)v4).b)) && v3_1.c(v0.L) == 65535) {
                            v0.I = 8;
                        }

                        if((((long)arg21.a())) == v12) {
                            goto label_282;
                        }

                        v1.a(v12);
                    }

                label_282:
                    short v5_1 = ((short)(v5_1 + 1));
                    v2 = v6_2;
                    v3 = v17;
                }

                if(arg21.a() + 4 > v1.a) {
                    return;
                }

                v2 = arg21.readInt();
                v3_4 = ((long)v2);
                if(v3_4 <= 0 || v2 >= v1.a) {
                    v1_1 = "ExifInterface";
                    v3_2 = new StringBuilder();
                    v4_1 = "Stop reading file since a wrong offset may cause an infinite loop: ";
                }
                else if(!v0.K.contains(Integer.valueOf(v2))) {
                    v1.a(v3_4);
                    v3 = 4;
                    if(!v0.J[v3].isEmpty()) {
                        v3 = 5;
                        if(v0.J[v3].isEmpty()) {
                        }
                        else {
                            return;
                        }
                    }

                    v0.b(v1, v3);
                    return;
                }
                else {
                    v1_1 = "ExifInterface";
                    v3_2 = new StringBuilder();
                    v4_1 = "Stop reading file since re-reading an IFD may cause an infinite loop: ";
                }

                v3_2.append(v4_1);
                v3_2.append(v2);
                Log.w(v1_1, v3_2.toString());
            }
        }
    }

    private void b(InputStream arg7) {
        int v0 = 5;
        this.a(0, v0);
        int v1 = 4;
        this.a(0, v1);
        this.a(v0, v1);
        Object v2 = this.J[1].get("PixelXDimension");
        Object v3 = this.J[1].get("PixelYDimension");
        if(v2 != null && v3 != null) {
            this.J[0].put("ImageWidth", v2);
            this.J[0].put("ImageLength", v3);
        }

        if((this.J[v1].isEmpty()) && (this.b(this.J[v0]))) {
            this.J[v1] = this.J[v0];
            this.J[v0] = new HashMap();
        }

        if(!this.b(this.J[v1])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
    }

    private void b(android.support.d.a$a arg7) {
        arg7.skipBytes(84);
        byte[] v1 = new byte[4];
        byte[] v2 = new byte[4];
        arg7.read(v1);
        arg7.skipBytes(4);
        arg7.read(v2);
        int v0 = ByteBuffer.wrap(v1).getInt();
        int v1_1 = ByteBuffer.wrap(v2).getInt();
        this.a(arg7, v0, 5);
        arg7.a(((long)v1_1));
        arg7.a(ByteOrder.BIG_ENDIAN);
        v0 = arg7.readInt();
        int v2_1;
        for(v2_1 = 0; v2_1 < v0; ++v2_1) {
            int v3 = arg7.readUnsignedShort();
            int v4 = arg7.readUnsignedShort();
            if(v3 == a.u.a) {
                v0 = arg7.readShort();
                int v7 = arg7.readShort();
                b v0_1 = b.a(v0, this.L);
                b v7_1 = b.a(v7, this.L);
                this.J[0].put("ImageLength", v0_1);
                this.J[0].put("ImageWidth", v7_1);
                return;
            }

            arg7.skipBytes(v4);
        }
    }

    private b b(String arg3) {
        if("ISOSpeedRatings".equals(arg3)) {
            arg3 = "PhotographicSensitivity";
        }

        int v0;
        for(v0 = 0; v0 < a.h.length; ++v0) {
            Object v1 = this.J[v0].get(arg3);
            if(v1 != null) {
                return ((b)v1);
            }
        }

        return null;
    }

    private void b(android.support.d.a$a arg11, HashMap arg12) {
        String v12_2;
        String v11;
        Object v0 = arg12.get("StripOffsets");
        Object v12 = arg12.get("StripByteCounts");
        if(v0 != null && v12 != null) {
            long[] v0_1 = a.a(((b)v0).a(this.L));
            long[] v12_1 = a.a(((b)v12).a(this.L));
            if(v0_1 == null) {
                v11 = "ExifInterface";
                v12_2 = "stripOffsets should not be null.";
            }
            else if(v12_1 == null) {
                v11 = "ExifInterface";
                v12_2 = "stripByteCounts should not be null.";
            }
            else {
                goto label_21;
            }

            Log.w(v11, v12_2);
            return;
        label_21:
            int v3 = v12_1.length;
            long v5 = 0;
            int v1;
            for(v1 = 0; v1 < v3; ++v1) {
                v5 += v12_1[v1];
            }

            byte[] v1_1 = new byte[((int)v5)];
            int v2 = 0;
            v3 = 0;
            int v5_1 = 0;
            while(v2 < v0_1.length) {
                int v6 = ((int)v0_1[v2]);
                int v7 = ((int)v12_1[v2]);
                v6 -= v3;
                if(v6 < 0) {
                    Log.d("ExifInterface", "Invalid strip offset value");
                }

                arg11.a(((long)v6));
                v3 += v6;
                byte[] v6_1 = new byte[v7];
                arg11.read(v6_1);
                v3 += v7;
                System.arraycopy(v6_1, 0, v1_1, v5_1, v6_1.length);
                v5_1 += v6_1.length;
                ++v2;
            }

            this.M = true;
            this.P = v1_1;
            this.O = v1_1.length;
        }
    }

    private boolean b(HashMap arg3) {
        Object v0 = arg3.get("ImageLength");
        Object v3 = arg3.get("ImageWidth");
        if(v0 != null && v3 != null) {
            int v0_1 = ((b)v0).c(this.L);
            int v3_1 = ((b)v3).c(this.L);
            int v1 = 512;
            if(v0_1 <= v1 && v3_1 <= v1) {
                return 1;
            }
        }

        return 0;
    }

    private boolean c(byte[] arg2) {
        android.support.d.a$a v0 = new android.support.d.a$a(arg2);
        this.L = this.e(v0);
        v0.a(this.L);
        int v2 = v0.readShort();
        v0.close();
        boolean v2_1 = v2 == 20306 || v2 == 21330 ? true : false;
        return v2_1;
    }

    private void c(android.support.d.a$a arg7) {
        long v2_1;
        this.a(arg7);
        Object v7 = this.J[1].get("MakerNote");
        if(v7 != null) {
            android.support.d.a$a v1 = new android.support.d.a$a(((b)v7).c);
            v1.a(this.L);
            byte[] v7_1 = new byte[a.m.length];
            v1.readFully(v7_1);
            v1.a(0);
            byte[] v2 = new byte[a.n.length];
            v1.readFully(v2);
            if(Arrays.equals(v7_1, a.m)) {
                v2_1 = 8;
                goto label_26;
            }
            else if(Arrays.equals(v2, a.n)) {
                v2_1 = 12;
            label_26:
                v1.a(v2_1);
            }

            this.b(v1, 6);
            v7 = this.J[7].get("PreviewImageStart");
            Object v1_1 = this.J[7].get("PreviewImageLength");
            if(v7 != null && v1_1 != null) {
                this.J[5].put("JPEGInterchangeFormat", v7);
                this.J[5].put("JPEGInterchangeFormatLength", v1_1);
            }

            v7 = this.J[8].get("AspectFrame");
            if(v7 == null) {
                return;
            }

            v7 = ((b)v7).a(this.L);
            if(v7 != null) {
                if(v7.length != 4) {
                }
                else {
                    int v1_2 = 2;
                    if(v7[v1_2] > v7[0]) {
                        int v2_2 = 3;
                        if(v7[v2_2] > v7[1]) {
                            v1_2 = v7[v1_2] - v7[0] + 1;
                            v2_2 = v7[v2_2] - v7[1] + 1;
                            if(v1_2 < v2_2) {
                                v1_2 += v2_2;
                                v2_2 = v1_2 - v2_2;
                                v1_2 -= v2_2;
                            }

                            b v7_2 = b.a(v1_2, this.L);
                            b v0 = b.a(v2_2, this.L);
                            this.J[0].put("ImageWidth", v7_2);
                            this.J[0].put("ImageLength", v0);
                        }
                        else {
                        }
                    }
                    else {
                    }

                    return;
                }
            }

            Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(((int[])v7)));
        }
    }

    private void c(android.support.d.a$a arg4, int arg5) {
        Object v0 = this.J[arg5].get("ImageLength");
        Object v1 = this.J[arg5].get("ImageWidth");
        if(v0 == null || v1 == null) {
            v0 = this.J[arg5].get("JPEGInterchangeFormat");
            if(v0 != null) {
                this.a(arg4, ((b)v0).c(this.L), arg5);
            }
        }
    }

    private boolean d(byte[] arg2) {
        android.support.d.a$a v0 = new android.support.d.a$a(arg2);
        this.L = this.e(v0);
        v0.a(this.L);
        int v2 = v0.readShort();
        v0.close();
        boolean v2_1 = v2 == 85 ? true : false;
        return v2_1;
    }

    private void d(android.support.d.a$a arg7, int arg8) {
        String v7_2;
        StringBuilder v0_2;
        String v8;
        b v7_1;
        b v0_1;
        Object v7;
        Object v0 = this.J[arg8].get("DefaultCropSize");
        Object v1 = this.J[arg8].get("SensorTopBorder");
        Object v2 = this.J[arg8].get("SensorLeftBorder");
        Object v3 = this.J[arg8].get("SensorBottomBorder");
        Object v4 = this.J[arg8].get("SensorRightBorder");
        if(v0 != null) {
            int v4_1 = 2;
            if(((b)v0).a == 5) {
                v7 = ((b)v0).a(this.L);
                if(v7 != null) {
                    if(v7.length != v4_1) {
                    }
                    else {
                        v0_1 = b.a(v7[0], this.L);
                        v7_1 = b.a(v7[1], this.L);
                        goto label_62;
                    }
                }

                v8 = "ExifInterface";
                v0_2 = new StringBuilder();
                v0_2.append("Invalid crop size values. cropSize=");
                v7_2 = Arrays.toString(((Object[])v7));
            }
            else {
                v7 = ((b)v0).a(this.L);
                if(v7 != null) {
                    if(v7.length != v4_1) {
                    }
                    else {
                        v0_1 = b.a(v7[0], this.L);
                        v7_1 = b.a(v7[1], this.L);
                    label_62:
                        this.J[arg8].put("ImageWidth", v0_1);
                        this.J[arg8].put("ImageLength", v7_1);
                        return;
                    }
                }

                v8 = "ExifInterface";
                v0_2 = new StringBuilder();
                v0_2.append("Invalid crop size values. cropSize=");
                v7_2 = Arrays.toString(((int[])v7));
            }

            v0_2.append(v7_2);
            Log.w(v8, v0_2.toString());
            return;
        }
        else {
            if(v1 != null && v2 != null && v3 != null && v4 != null) {
                int v7_3 = ((b)v1).c(this.L);
                int v0_3 = ((b)v3).c(this.L);
                int v1_1 = ((b)v4).c(this.L);
                int v2_1 = ((b)v2).c(this.L);
                if(v0_3 <= v7_3) {
                }
                else if(v1_1 > v2_1) {
                    v7_1 = b.a(v0_3 - v7_3, this.L);
                    v0_1 = b.a(v1_1 - v2_1, this.L);
                    this.J[arg8].put("ImageLength", v7_1);
                    this.J[arg8].put("ImageWidth", v0_1);
                }
                else {
                }

                return;
            }

            this.c(arg7, arg8);
        }
    }

    private void d(android.support.d.a$a arg4) {
        this.a(arg4);
        if(this.J[0].get("JpgFromRaw") != null) {
            this.a(arg4, this.V, 5);
        }

        Object v4 = this.J[0].get("ISO");
        Object v0 = this.J[1].get("PhotographicSensitivity");
        if(v4 != null && v0 == null) {
            this.J[1].put("PhotographicSensitivity", v4);
        }
    }

    private ByteOrder e(android.support.d.a$a arg4) {
        int v4 = arg4.readShort();
        if(v4 != 18761) {
            if(v4 == 19789) {
                return ByteOrder.BIG_ENDIAN;
            }

            StringBuilder v1 = new StringBuilder();
            v1.append("Invalid byte order: ");
            v1.append(Integer.toHexString(v4));
            throw new IOException(v1.toString());
        }

        return ByteOrder.LITTLE_ENDIAN;
    }

    private void f(android.support.d.a$a arg4) {
        HashMap v0 = this.J[4];
        Object v1 = v0.get("Compression");
        if(v1 != null) {
            this.Q = ((b)v1).c(this.L);
            int v1_1 = this.Q;
            if(v1_1 != 1) {
                switch(v1_1) {
                    case 6: {
                        goto label_20;
                    }
                    case 7: {
                        goto label_14;
                    }
                }

                return;
            }

        label_14:
            if(!this.a(v0)) {
                return;
            }

            this.b(arg4, v0);
        }
        else {
            this.Q = 6;
        label_20:
            this.a(arg4, v0);
        }
    }
}

