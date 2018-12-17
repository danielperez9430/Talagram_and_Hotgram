package com.google.android.exoplayer2.source;

import java.util.Arrays;
import java.util.Random;

public interface ShuffleOrder {
    public class DefaultShuffleOrder implements ShuffleOrder {
        private final int[] indexInShuffled;
        private final Random random;
        private final int[] shuffled;

        public DefaultShuffleOrder(int arg2) {
            this(arg2, new Random());
        }

        private DefaultShuffleOrder(int arg1, Random arg2) {
            this(DefaultShuffleOrder.createShuffledList(arg1, arg2), arg2);
        }

        public DefaultShuffleOrder(int arg2, long arg3) {
            this(arg2, new Random(arg3));
        }

        private DefaultShuffleOrder(int[] arg3, Random arg4) {
            super();
            this.shuffled = arg3;
            this.random = arg4;
            this.indexInShuffled = new int[arg3.length];
            int v4;
            for(v4 = 0; v4 < arg3.length; ++v4) {
                this.indexInShuffled[arg3[v4]] = v4;
            }
        }

        public ShuffleOrder cloneAndClear() {
            return new DefaultShuffleOrder(0, new Random(this.random.nextLong()));
        }

        public ShuffleOrder cloneAndInsert(int arg9, int arg10) {
            int v4;
            int v5;
            int[] v0 = new int[arg10];
            int[] v1 = new int[arg10];
            int v2 = 0;
            int v3;
            for(v3 = 0; v3 < arg10; v3 = v5) {
                v0[v3] = this.random.nextInt(this.shuffled.length + 1);
                v5 = v3 + 1;
                v4 = this.random.nextInt(v5);
                v1[v3] = v1[v4];
                v1[v4] = v3 + arg9;
            }

            Arrays.sort(v0);
            int[] v3_1 = new int[this.shuffled.length + arg10];
            v4 = 0;
            v5 = 0;
            while(v2 < this.shuffled.length + arg10) {
                if(v4 >= arg10 || v5 != v0[v4]) {
                    int v7 = v5 + 1;
                    v3_1[v2] = this.shuffled[v5];
                    if(v3_1[v2] >= arg9) {
                        v3_1[v2] += arg10;
                    }

                    v5 = v7;
                }
                else {
                    v3_1[v2] = v1[v4];
                    ++v4;
                }

                ++v2;
            }

            return new DefaultShuffleOrder(v3_1, new Random(this.random.nextLong()));
        }

        public ShuffleOrder cloneAndRemove(int arg7) {
            int[] v0 = new int[this.shuffled.length - 1];
            int v2 = 0;
            int v3 = 0;
            while(v2 < this.shuffled.length) {
                if(this.shuffled[v2] == arg7) {
                    v3 = 1;
                }
                else {
                    int v4 = v3 != 0 ? v2 - 1 : v2;
                    int v5 = this.shuffled[v2] > arg7 ? this.shuffled[v2] - 1 : this.shuffled[v2];
                    v0[v4] = v5;
                }

                ++v2;
            }

            return new DefaultShuffleOrder(v0, new Random(this.random.nextLong()));
        }

        private static int[] createShuffledList(int arg5, Random arg6) {
            int[] v0 = new int[arg5];
            int v1;
            for(v1 = 0; v1 < arg5; v1 = v2) {
                int v2 = v1 + 1;
                int v3 = arg6.nextInt(v2);
                v0[v1] = v0[v3];
                v0[v3] = v1;
            }

            return v0;
        }

        public int getFirstIndex() {
            int v0 = this.shuffled.length > 0 ? this.shuffled[0] : -1;
            return v0;
        }

        public int getLastIndex() {
            int v0 = this.shuffled.length > 0 ? this.shuffled[this.shuffled.length - 1] : -1;
            return v0;
        }

        public int getLength() {
            return this.shuffled.length;
        }

        public int getNextIndex(int arg2) {
            arg2 = this.indexInShuffled[arg2] + 1;
            return arg2 < this.shuffled.length ? this.shuffled[arg2] : -1;
        }

        public int getPreviousIndex(int arg2) {
            int v0 = -1;
            arg2 = this.indexInShuffled[arg2] + v0;
            if(arg2 >= 0) {
                v0 = this.shuffled[arg2];
            }

            return v0;
        }
    }

    public final class UnshuffledShuffleOrder implements ShuffleOrder {
        private final int length;

        public UnshuffledShuffleOrder(int arg1) {
            super();
            this.length = arg1;
        }

        public ShuffleOrder cloneAndClear() {
            return new UnshuffledShuffleOrder(0);
        }

        public ShuffleOrder cloneAndInsert(int arg2, int arg3) {
            return new UnshuffledShuffleOrder(this.length + arg3);
        }

        public ShuffleOrder cloneAndRemove(int arg2) {
            return new UnshuffledShuffleOrder(this.length - 1);
        }

        public int getFirstIndex() {
            int v0 = this.length > 0 ? 0 : -1;
            return v0;
        }

        public int getLastIndex() {
            int v0 = this.length > 0 ? this.length - 1 : -1;
            return v0;
        }

        public int getLength() {
            return this.length;
        }

        public int getNextIndex(int arg2) {
            ++arg2;
            if(arg2 < this.length) {
            }
            else {
                arg2 = -1;
            }

            return arg2;
        }

        public int getPreviousIndex(int arg2) {
            --arg2;
            if(arg2 >= 0) {
            }
            else {
                arg2 = -1;
            }

            return arg2;
        }
    }

    ShuffleOrder cloneAndClear();

    ShuffleOrder cloneAndInsert(int arg1, int arg2);

    ShuffleOrder cloneAndRemove(int arg1);

    int getFirstIndex();

    int getLastIndex();

    int getLength();

    int getNextIndex(int arg1);

    int getPreviousIndex(int arg1);
}

