package org.linphone.core;

public interface PresenceActivity {
    String getDescription();

    long getNativePtr();

    PresenceActivityType getType();

    int setDescription(String arg1);

    int setType(PresenceActivityType arg1);

    String toString();
}

