package com.google.android.gms.flags;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FlagRegistry {
    private final Collection zzacc;
    private final Collection zzacd;
    private final Collection zzace;

    public FlagRegistry() {
        super();
        this.zzacc = new ArrayList();
        this.zzacd = new ArrayList();
        this.zzace = new ArrayList();
    }

    public List getExperimentIdsFromClient() {
        ArrayList v0 = new ArrayList();
        Iterator v1 = this.zzacd.iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next().get();
            if(v2 == null) {
                continue;
            }

            ((List)v0).add(v2);
        }

        return ((List)v0);
    }

    public static void initialize(Context arg1) {
        Singletons.flagValueProvider().initialize(arg1);
    }

    public void registerClientExperimentId(StringFlag arg2) {
        this.zzacd.add(arg2);
    }

    public void registerFlag(Flag arg2) {
        this.zzacc.add(arg2);
    }

    public void registerServiceExperimentId(StringFlag arg2) {
        this.zzace.add(arg2);
    }

    public Collection registeredFlags() {
        return Collections.unmodifiableCollection(this.zzacc);
    }

    public Collection registeredServiceExperimentIdFlags() {
        return Collections.unmodifiableCollection(this.zzace);
    }
}

