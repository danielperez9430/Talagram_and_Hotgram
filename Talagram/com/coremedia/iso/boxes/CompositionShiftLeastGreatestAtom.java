package com.coremedia.iso.boxes;

import com.googlecode.mp4parser.AbstractFullBox;
import com.googlecode.mp4parser.RequiresParseDetailAspect;
import java.nio.ByteBuffer;
import org.a.b.a.a;
import org.a.b.b.b;

public class CompositionShiftLeastGreatestAtom extends AbstractFullBox {
    public static final String TYPE = "cslg";
    int compositionOffsetToDisplayOffsetShift;
    int displayEndTime;
    int displayStartTime;
    int greatestDisplayOffset;
    int leastDisplayOffset;

    static {
        CompositionShiftLeastGreatestAtom.ajc$preClinit();
    }

    public CompositionShiftLeastGreatestAtom() {
        super("cslg");
    }

    public void _parseDetails(ByteBuffer arg2) {
        this.parseVersionAndFlags(arg2);
        this.compositionOffsetToDisplayOffsetShift = arg2.getInt();
        this.leastDisplayOffset = arg2.getInt();
        this.greatestDisplayOffset = arg2.getInt();
        this.displayStartTime = arg2.getInt();
        this.displayEndTime = arg2.getInt();
    }

    private static void ajc$preClinit() {
        b v8 = new b("CompositionShiftLeastGreatestAtom.java", CompositionShiftLeastGreatestAtom.class);
        CompositionShiftLeastGreatestAtom.ajc$tjp_0 = v8.a("method-execution", v8.a("1", "getCompositionOffsetToDisplayOffsetShift", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 66);
        CompositionShiftLeastGreatestAtom.ajc$tjp_1 = v8.a("method-execution", v8.a("1", "setCompositionOffsetToDisplayOffsetShift", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "compositionOffsetToDisplayOffsetShift", "", "void"), 70);
        CompositionShiftLeastGreatestAtom.ajc$tjp_2 = v8.a("method-execution", v8.a("1", "getLeastDisplayOffset", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 74);
        CompositionShiftLeastGreatestAtom.ajc$tjp_3 = v8.a("method-execution", v8.a("1", "setLeastDisplayOffset", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "leastDisplayOffset", "", "void"), 78);
        CompositionShiftLeastGreatestAtom.ajc$tjp_4 = v8.a("method-execution", v8.a("1", "getGreatestDisplayOffset", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 82);
        CompositionShiftLeastGreatestAtom.ajc$tjp_5 = v8.a("method-execution", v8.a("1", "setGreatestDisplayOffset", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "greatestDisplayOffset", "", "void"), 86);
        CompositionShiftLeastGreatestAtom.ajc$tjp_6 = v8.a("method-execution", v8.a("1", "getDisplayStartTime", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 90);
        CompositionShiftLeastGreatestAtom.ajc$tjp_7 = v8.a("method-execution", v8.a("1", "setDisplayStartTime", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "displayStartTime", "", "void"), 94);
        CompositionShiftLeastGreatestAtom.ajc$tjp_8 = v8.a("method-execution", v8.a("1", "getDisplayEndTime", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 98);
        CompositionShiftLeastGreatestAtom.ajc$tjp_9 = v8.a("method-execution", v8.a("1", "setDisplayEndTime", "com.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "displayEndTime", "", "void"), 102);
    }

    public int getCompositionOffsetToDisplayOffsetShift() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_0, this, this));
        return this.compositionOffsetToDisplayOffsetShift;
    }

    protected void getContent(ByteBuffer arg2) {
        this.writeVersionAndFlags(arg2);
        arg2.putInt(this.compositionOffsetToDisplayOffsetShift);
        arg2.putInt(this.leastDisplayOffset);
        arg2.putInt(this.greatestDisplayOffset);
        arg2.putInt(this.displayStartTime);
        arg2.putInt(this.displayEndTime);
    }

    protected long getContentSize() {
        return 24;
    }

    public int getDisplayEndTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_8, this, this));
        return this.displayEndTime;
    }

    public int getDisplayStartTime() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_6, this, this));
        return this.displayStartTime;
    }

    public int getGreatestDisplayOffset() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_4, this, this));
        return this.greatestDisplayOffset;
    }

    public int getLeastDisplayOffset() {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_2, this, this));
        return this.leastDisplayOffset;
    }

    public void setCompositionOffsetToDisplayOffsetShift(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_1, this, this, a.a(arg3)));
        this.compositionOffsetToDisplayOffsetShift = arg3;
    }

    public void setDisplayEndTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_9, this, this, a.a(arg3)));
        this.displayEndTime = arg3;
    }

    public void setDisplayStartTime(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_7, this, this, a.a(arg3)));
        this.displayStartTime = arg3;
    }

    public void setGreatestDisplayOffset(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_5, this, this, a.a(arg3)));
        this.greatestDisplayOffset = arg3;
    }

    public void setLeastDisplayOffset(int arg3) {
        RequiresParseDetailAspect.aspectOf().before(b.a(CompositionShiftLeastGreatestAtom.ajc$tjp_3, this, this, a.a(arg3)));
        this.leastDisplayOffset = arg3;
    }
}

