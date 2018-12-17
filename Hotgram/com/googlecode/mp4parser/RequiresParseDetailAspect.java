package com.googlecode.mp4parser;

import org.a.a.a;
import org.a.a.b;

public class RequiresParseDetailAspect {
    static {
        try {
            RequiresParseDetailAspect.ajc$postClinit();
        }
        catch(Throwable v0) {
            RequiresParseDetailAspect.ajc$initFailureCause = v0;
        }
    }

    public RequiresParseDetailAspect() {
        super();
    }

    private static void ajc$postClinit() {
        RequiresParseDetailAspect.ajc$perSingletonInstance = new RequiresParseDetailAspect();
    }

    public static RequiresParseDetailAspect aspectOf() {
        if(RequiresParseDetailAspect.ajc$perSingletonInstance != null) {
            return RequiresParseDetailAspect.ajc$perSingletonInstance;
        }

        throw new b("com.googlecode.mp4parser.RequiresParseDetailAspect", RequiresParseDetailAspect.ajc$initFailureCause);
    }

    public void before(a arg3) {
        if((arg3.a() instanceof AbstractBox)) {
            if(!arg3.a().isParsed()) {
                arg3.a().parseDetails();
            }

            return;
        }

        StringBuilder v0 = new StringBuilder("Only methods in subclasses of ");
        v0.append(AbstractBox.class.getName());
        v0.append(" can  be annotated with ParseDetail");
        throw new RuntimeException(v0.toString());
    }

    public static boolean hasAspect() {
        if(RequiresParseDetailAspect.ajc$perSingletonInstance != null) {
            return 1;
        }

        return 0;
    }
}

