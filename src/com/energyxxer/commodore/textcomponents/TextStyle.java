package com.energyxxer.commodore.textcomponents;

import java.util.ArrayList;
import java.util.Iterator;

public class TextStyle {
    private TextColor color;
    private byte flags = 0;
    private byte mask = 0b0011111;

    public static final byte BOLD           =    0b0000001;
    public static final byte ITALIC         =    0b0000010;
    public static final byte UNDERLINE      =    0b0000100;
    public static final byte STRIKETHROUGH  =    0b0001000;
    public static final byte OBFUSCATED     =    0b0010000;

    public static final TextStyle DEFAULT_STYLE = new TextStyle(0);
    public static final TextStyle EMPTY_STYLE = new TextStyle(0);

    static {
        EMPTY_STYLE.setMask(0);
    }

    public TextStyle() {
        this(null, (byte) 0);
    }

    public TextStyle(int flags) {
        this(null, flags);
    }

    public TextStyle(TextColor color) {
        this(color, (byte) 0);
    }

    public TextStyle(TextColor color, int flags) {
        this.color = color;
        this.flags = (byte) flags;
    }

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public byte getFlags() {
        return flags;
    }

    public void setFlags(byte flags) {
        this.flags = flags;
    }

    public byte getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = (byte) mask;
    }

    public byte getMaskForParent(TextStyle parentStyle) {
        if(parentStyle == null) parentStyle = EMPTY_STYLE;
        return (byte) ((parentStyle.mask | this.mask) & (parentStyle.flags ^ this.flags));
    }

    @Override
    public String toString() {
        return this.toString(DEFAULT_STYLE);
    }

    public String toString(TextStyle parentStyle) {
        int mask = getMaskForParent(parentStyle);
        ArrayList<String> properties = new ArrayList<>();

        if(color != null && (parentStyle == null || color != parentStyle.color)) {
            properties.add("\"color\":\"" + color.name().toLowerCase() + "\"");
        }
        if((mask & BOLD) != 0) properties.add("\"bold\":"+String.valueOf(getBoolean(BOLD)));
        if((mask & ITALIC) != 0) properties.add("\"italic\":"+String.valueOf(getBoolean(ITALIC)));
        if((mask & UNDERLINE) != 0) properties.add("\"underline\":"+String.valueOf(getBoolean(UNDERLINE)));
        if((mask & STRIKETHROUGH) != 0) properties.add("\"strikethrough\":"+String.valueOf(getBoolean(STRIKETHROUGH)));
        if((mask & OBFUSCATED) != 0) properties.add("\"obfuscated\":"+String.valueOf(getBoolean(OBFUSCATED)));

        StringBuilder sb = new StringBuilder();
        Iterator<String> it = properties.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) sb.append(',');
        }

        return (sb.length() != 0) ? sb.toString() : null;
    }

    private boolean getBoolean(byte property) {
        return (flags & property) != 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextStyle textStyle = (TextStyle) o;

        if (flags != textStyle.flags) return false;
        return color == textStyle.color;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (int) flags;
        return result;
    }
}
