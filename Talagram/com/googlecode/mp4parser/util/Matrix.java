package com.googlecode.mp4parser.util;

import com.coremedia.iso.IsoTypeReader;
import com.coremedia.iso.IsoTypeWriter;
import java.nio.ByteBuffer;

public class Matrix {
    public static final Matrix ROTATE_0;
    public static final Matrix ROTATE_180;
    public static final Matrix ROTATE_270;
    public static final Matrix ROTATE_90;
    double a;
    double b;
    double c;
    double d;
    double tx;
    double ty;
    double u;
    double v;
    double w;

    static {
        Matrix.ROTATE_0 = new Matrix(1, 0, 0, 1, 0, 0, 1, 0, 0);
        Matrix.ROTATE_90 = new Matrix(0, 1, -1, 0, 0, 0, 1, 0, 0);
        Matrix.ROTATE_180 = new Matrix(-1, 0, 0, -1, 0, 0, 1, 0, 0);
        Matrix.ROTATE_270 = new Matrix(0, -1, 1, 0, 0, 0, 1, 0, 0);
    }

    public Matrix(double arg4, double arg6, double arg8, double arg10, double arg12, double arg14, double arg16, double arg18, double arg20) {
        super();
        this.u = arg12;
        this.v = arg14;
        this.w = arg16;
        this.a = arg4;
        this.b = arg6;
        this.c = arg8;
        this.d = arg10;
        this.tx = arg18;
        this.ty = arg20;
    }

    public boolean equals(Object arg7) {
        if(this == (((Matrix)arg7))) {
            return 1;
        }

        if(arg7 != null) {
            if(this.getClass() != arg7.getClass()) {
            }
            else if(Double.compare(((Matrix)arg7).a, this.a) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).b, this.b) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).c, this.c) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).d, this.d) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).tx, this.tx) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).ty, this.ty) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).u, this.u) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).v, this.v) != 0) {
                return 0;
            }
            else if(Double.compare(((Matrix)arg7).w, this.w) != 0) {
                return 0;
            }
            else {
                return 1;
            }
        }

        return 0;
    }

    public static Matrix fromByteBuffer(ByteBuffer arg18) {
        return Matrix.fromFileOrder(IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint0230(arg18), IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint0230(arg18), IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint1616(arg18), IsoTypeReader.readFixedPoint0230(arg18));
    }

    public static Matrix fromFileOrder(double arg20, double arg22, double arg24, double arg26, double arg28, double arg30, double arg32, double arg34, double arg36) {
        return new Matrix(arg20, arg22, arg26, arg28, arg24, arg30, arg36, arg32, arg34);
    }

    public void getContent(ByteBuffer arg3) {
        IsoTypeWriter.writeFixedPoint1616(arg3, this.a);
        IsoTypeWriter.writeFixedPoint1616(arg3, this.b);
        IsoTypeWriter.writeFixedPoint0230(arg3, this.u);
        IsoTypeWriter.writeFixedPoint1616(arg3, this.c);
        IsoTypeWriter.writeFixedPoint1616(arg3, this.d);
        IsoTypeWriter.writeFixedPoint0230(arg3, this.v);
        IsoTypeWriter.writeFixedPoint1616(arg3, this.tx);
        IsoTypeWriter.writeFixedPoint1616(arg3, this.ty);
        IsoTypeWriter.writeFixedPoint0230(arg3, this.w);
    }

    public int hashCode() {
        long v0 = Double.doubleToLongBits(this.u);
        long v3 = Double.doubleToLongBits(this.v);
        int v0_1 = (((int)(v0 ^ v0 >>> 32))) * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.w);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.a);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.b);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.c);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.d);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.tx);
        v0_1 = v0_1 * 31 + (((int)(v3 ^ v3 >>> 32)));
        v3 = Double.doubleToLongBits(this.ty);
        return v0_1 * 31 + (((int)(v3 >>> 32 ^ v3)));
    }

    public String toString() {
        if(this.equals(Matrix.ROTATE_0)) {
            return "Rotate 0°";
        }

        if(this.equals(Matrix.ROTATE_90)) {
            return "Rotate 90°";
        }

        if(this.equals(Matrix.ROTATE_180)) {
            return "Rotate 180°";
        }

        if(this.equals(Matrix.ROTATE_270)) {
            return "Rotate 270°";
        }

        StringBuilder v0 = new StringBuilder("Matrix{u=");
        v0.append(this.u);
        v0.append(", v=");
        v0.append(this.v);
        v0.append(", w=");
        v0.append(this.w);
        v0.append(", a=");
        v0.append(this.a);
        v0.append(", b=");
        v0.append(this.b);
        v0.append(", c=");
        v0.append(this.c);
        v0.append(", d=");
        v0.append(this.d);
        v0.append(", tx=");
        v0.append(this.tx);
        v0.append(", ty=");
        v0.append(this.ty);
        v0.append('}');
        return v0.toString();
    }
}

