package org.linphone.core;

public interface PresenceModel {
    int addActivity(PresenceActivity arg1);

    int addNote(String arg1, String arg2);

    int addPerson(PresencePerson arg1);

    int addService(PresenceService arg1);

    int clearActivities();

    int clearNotes();

    int clearPersons();

    int clearServices();

    PresenceActivity getActivity();

    PresenceBasicStatus getBasicStatus();

    String getContact();

    long getNbActivities();

    long getNbPersons();

    long getNbServices();

    PresenceNote getNote(String arg1);

    PresenceActivity getNthActivity(long arg1);

    PresencePerson getNthPerson(long arg1);

    PresenceService getNthService(long arg1);

    long getTimestamp();

    int setActivity(PresenceActivityType arg1, String arg2);

    int setBasicStatus(PresenceBasicStatus arg1);

    void setContact(String arg1);
}

