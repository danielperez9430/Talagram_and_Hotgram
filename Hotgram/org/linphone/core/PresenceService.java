package org.linphone.core;

public interface PresenceService {
    int addNote(PresenceNote arg1);

    int clearNotes();

    PresenceBasicStatus getBasicStatus();

    String getContact();

    String getId();

    long getNativePtr();

    long getNbNotes();

    PresenceNote getNthNote(long arg1);

    int setBasicStatus(PresenceBasicStatus arg1);

    int setContact(String arg1);

    int setId(String arg1);
}

