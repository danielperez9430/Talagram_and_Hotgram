package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.util.Assertions;

public interface SeekMap {
    public final class SeekPoints {
        public final SeekPoint first;
        public final SeekPoint second;

        public SeekPoints(SeekPoint arg1) {
            this(arg1, arg1);
        }

        public SeekPoints(SeekPoint arg1, SeekPoint arg2) {
            super();
            this.first = Assertions.checkNotNull(arg1);
            this.second = Assertions.checkNotNull(arg2);
        }

        public boolean equals(Object arg5) {
            boolean v0 = true;
            if(this == (((SeekPoints)arg5))) {
                return 1;
            }

            if(arg5 != null) {
                if(this.getClass() != arg5.getClass()) {
                }
                else {
                    if(!this.first.equals(((SeekPoints)arg5).first) || !this.second.equals(((SeekPoints)arg5).second)) {
                        v0 = false;
                    }
                    else {
                    }

                    return v0;
                }
            }

            return 0;
        }

        public int hashCode() {
            return this.first.hashCode() * 31 + this.second.hashCode();
        }

        public String toString() {
            StringBuilder v0 = new StringBuilder();
            v0.append("[");
            v0.append(this.first);
            String v1 = this.first.equals(this.second) ? "" : ", " + this.second;
            v0.append(v1);
            v0.append("]");
            return v0.toString();
        }
    }

    public final class Unseekable implements SeekMap {
        private final long durationUs;
        private final SeekPoints startSeekPoints;

        public Unseekable(long arg3) {
            this(arg3, 0);
        }

        public Unseekable(long arg3, long arg5) {
            super();
            this.durationUs = arg3;
            long v0 = 0;
            SeekPoint v4 = arg5 == v0 ? SeekPoint.START : new SeekPoint(v0, arg5);
            this.startSeekPoints = new SeekPoints(v4);
        }

        public long getDurationUs() {
            return this.durationUs;
        }

        public SeekPoints getSeekPoints(long arg1) {
            return this.startSeekPoints;
        }

        public boolean isSeekable() {
            return 0;
        }
    }

    long getDurationUs();

    SeekPoints getSeekPoints(long arg1);

    boolean isSeekable();
}

