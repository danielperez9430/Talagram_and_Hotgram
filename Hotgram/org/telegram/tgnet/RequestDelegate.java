package org.telegram.tgnet;

public interface RequestDelegate {
    void run(TLObject arg1, TL_error arg2);
}

