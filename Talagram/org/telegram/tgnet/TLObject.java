package org.telegram.tgnet;

public class TLObject {
    final class org.telegram.tgnet.TLObject$1 extends ThreadLocal {
        org.telegram.tgnet.TLObject$1() {
            super();
        }

        protected Object initialValue() {
            return this.initialValue();
        }

        protected NativeByteBuffer initialValue() {
            return new NativeByteBuffer(true);
        }
    }

    public boolean disableFree;
    public int networkType;
    private static final ThreadLocal sizeCalculator;

    static {
        TLObject.sizeCalculator = new org.telegram.tgnet.TLObject$1();
    }

    public TLObject() {
        super();
        this.disableFree = false;
    }

    public TLObject deserializeResponse(AbstractSerializedData arg1, int arg2, boolean arg3) {
        return null;
    }

    public void freeResources() {
    }

    public int getObjectSize() {
        Object v0 = TLObject.sizeCalculator.get();
        ((NativeByteBuffer)v0).rewind();
        this.serializeToStream(TLObject.sizeCalculator.get());
        return ((NativeByteBuffer)v0).length();
    }

    public void readParams(AbstractSerializedData arg1, boolean arg2) {
    }

    public void serializeToStream(AbstractSerializedData arg1) {
    }
}

