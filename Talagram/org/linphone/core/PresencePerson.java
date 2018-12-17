package org.linphone.core;

public interface PresencePerson {
    int addActivitiesNote(PresenceNote arg1);

    int addActivity(PresenceActivity arg1);

    int addNote(PresenceNote arg1);

    int clearActivitesNotes();

    int clearActivities();

    int clearNotes();

    String getId();

    long getNativePtr();

    long getNbActivities();

    long getNbActivitiesNotes();

    long getNbNotes();

    PresenceNote getNthActivitiesNote(long arg1);

    PresenceActivity getNthActivity(long arg1);

    PresenceNote getNthNote(long arg1);

    int setId(String arg1);
}

