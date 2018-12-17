package org.telegram.tgnet;

public abstract class AbstractSerializedData {
    public AbstractSerializedData() {
        super();
    }

    public abstract int getPosition();

    public abstract int length();

    public abstract boolean readBool(boolean arg1);

    public abstract byte[] readByteArray(boolean arg1);

    public abstract NativeByteBuffer readByteBuffer(boolean arg1);

    public abstract void readBytes(byte[] arg1, boolean arg2);

    public abstract byte[] readData(int arg1, boolean arg2);

    public abstract double readDouble(boolean arg1);

    public abstract int readInt32(boolean arg1);

    public abstract long readInt64(boolean arg1);

    public abstract String readString(boolean arg1);

    public abstract int remaining();

    public abstract void skip(int arg1);

    public abstract void writeBool(boolean arg1);

    public abstract void writeByte(byte arg1);

    public abstract void writeByte(int arg1);

    public abstract void writeByteArray(byte[] arg1);

    public abstract void writeByteArray(byte[] arg1, int arg2, int arg3);

    public abstract void writeByteBuffer(NativeByteBuffer arg1);

    public abstract void writeBytes(byte[] arg1);

    public abstract void writeBytes(byte[] arg1, int arg2, int arg3);

    public abstract void writeDouble(double arg1);

    public abstract void writeInt32(int arg1);

    public abstract void writeInt64(long arg1);

    public abstract void writeString(String arg1);
}

