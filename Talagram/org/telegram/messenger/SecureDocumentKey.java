package org.telegram.messenger;

public class SecureDocumentKey {
    public byte[] file_iv;
    public byte[] file_key;

    public SecureDocumentKey(byte[] arg1, byte[] arg2) {
        super();
        this.file_key = arg1;
        this.file_iv = arg2;
    }
}

