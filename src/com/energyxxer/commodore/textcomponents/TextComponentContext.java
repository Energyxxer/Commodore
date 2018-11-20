package com.energyxxer.commodore.textcomponents;

public class TextComponentContext {
    public static final byte NONE = 0;
    public static final byte CLICK = 0b01;
    public static final byte HOVER = 0b10;

    public static final TextComponentContext CHAT = new TextComponentContext(true, CLICK | HOVER);
    public static final TextComponentContext TITLE = new TextComponentContext(true, NONE);
    public static final TextComponentContext SIGN = new TextComponentContext(true, CLICK);
    public static final TextComponentContext BOOK = new TextComponentContext(true, CLICK | HOVER);
    public static final TextComponentContext TOOLTIP = new TextComponentContext(false, NONE);

    public final int validEvents;
    public final boolean canResolve;

    public TextComponentContext(boolean canResolve, int validEvents) {
        this.validEvents = validEvents;
        this.canResolve = canResolve;
    }

    public boolean canResolve() {
        return canResolve;
    }

    public boolean isClickEnabled() {
        return (validEvents & CLICK) != 0;
    }

    public boolean isHoverEnabled() {
        return (validEvents & HOVER) != 0;
    }
}
