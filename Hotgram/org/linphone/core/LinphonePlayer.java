package org.linphone.core;

public interface LinphonePlayer {
    public interface Listener {
        void endOfFile(LinphonePlayer arg1);
    }

    public enum State {
        public static final enum State closed;
        public static final enum State paused;
        public static final enum State playing;

        static {
            State.closed = new State("closed", 0);
            State.paused = new State("paused", 1);
            State.playing = new State("playing", 2);
            State.$VALUES = new State[]{State.closed, State.paused, State.playing};
        }

        private State(String arg1, int arg2) {
            super(arg1, arg2);
        }

        public static State fromValue(int arg1) {
            if(arg1 == 0) {
                return State.closed;
            }

            if(arg1 == 1) {
                return State.paused;
            }

            if(arg1 == 2) {
                return State.playing;
            }

            return null;
        }

        public static State valueOf(String arg1) {
            return Enum.valueOf(State.class, arg1);
        }

        public static State[] values() {
            // Method was not decompiled
        }
    }

    void close();

    int getCurrentPosition();

    int getDuration();

    State getState();

    int open(String arg1, Listener arg2);

    int pause();

    int seek(int arg1);

    int start();
}

