package com.google.android.exoplayer2.upstream;

public interface LoaderErrorThrower {
    public final class Dummy implements LoaderErrorThrower {
        public Dummy() {
            super();
        }

        public void maybeThrowError() {
        }

        public void maybeThrowError(int arg1) {
        }
    }

    void maybeThrowError();

    void maybeThrowError(int arg1);
}

