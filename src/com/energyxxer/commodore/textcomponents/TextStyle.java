package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.types.Type;
import com.energyxxer.commodore.types.TypeAssert;
import com.energyxxer.commodore.types.defaults.FontReference;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

public class TextStyle {
    @Nullable
    private TextColor color;
    @Nullable
    private Type font;
    private byte flags = 0;
    private byte mask = 0b0011111;

    public static final byte BOLD = 0b0000001;
    public static final byte ITALIC = 0b0000010;
    public static final byte UNDERLINED = 0b0000100;
    public static final byte STRIKETHROUGH = 0b0001000;
    public static final byte OBFUSCATED = 0b0010000;

    public static final TextStyle DEFAULT_STYLE = new TextStyle(0);
    public static final TextStyle EMPTY_STYLE = new TextStyle(0);

    static {
        EMPTY_STYLE.setMask(0);
    }

    public TextStyle(TextStyle other) {
        this.color = other.color;
        this.mask = other.mask;
        this.flags = other.flags;
        this.font = other.font;
    }

    public TextStyle() {
        this(null, (byte) 0);
    }

    public TextStyle(int flags) {
        this(null, flags);
    }

    public TextStyle(TextColor color) {
        this(color, 0);
    }

    public TextStyle(@Nullable TextColor color, int flags) {
        this(color, null, flags);
    }

    public TextStyle(@Nullable TextColor color, @Nullable Type font) {
        this(color, font, 0);
    }

    public TextStyle(@Nullable TextColor color, @Nullable Type font, int flags) {
        this.color = color;
        this.flags = (byte) flags;
        if(font != null) {
            TypeAssert.assertType(font, FontReference.CATEGORY);
        }
        this.font = font;
    }

    @Nullable
    public TextColor getColor() {
        return color;
    }

    public TextStyle setColor(@Nullable TextColor color) {
        this.color = color;
        return this;
    }

    public byte getFlags() {
        return flags;
    }

    public TextStyle setFlags(byte flags) {
        this.flags = flags;
        return this;
    }

    public byte getMask() {
        return mask;
    }

    public TextStyle setMask(int mask) {
        this.mask = (byte) mask;
        return this;
    }

    public byte getMaskForParent(TextStyle parentStyle) {
        if(parentStyle == null) parentStyle = EMPTY_STYLE;
        return (byte) (this.mask & ~(parentStyle.mask & (parentStyle.flags & this.flags)));
    }

    @Override
    public String toString() {
        return this.toString(DEFAULT_STYLE);
    }

    public String toString(TextStyle parentStyle) {
        int mask = getMaskForParent(parentStyle);
        ArrayList<String> properties = new ArrayList<>();

        if(color != null && (parentStyle == null || !color.equals(parentStyle.color))) {
            properties.add("\"color\":\"" + color.toString() + "\"");
        }
        if(font != null && (parentStyle == null || !font.equals(parentStyle.font))) {
            properties.add("\"font\":\"" + font.toString() + "\"");
        }
        if((mask & BOLD) != 0) properties.add("\"bold\":" + getBoolean(BOLD));
        if((mask & ITALIC) != 0) properties.add("\"italic\":" + getBoolean(ITALIC));
        if((mask & UNDERLINED) != 0) properties.add("\"underlined\":" + getBoolean(UNDERLINED));
        if((mask & STRIKETHROUGH) != 0)
            properties.add("\"strikethrough\":" + getBoolean(STRIKETHROUGH));
        if((mask & OBFUSCATED) != 0) properties.add("\"obfuscated\":" + getBoolean(OBFUSCATED));

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
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        TextStyle textStyle = (TextStyle) o;

        return flags == textStyle.flags && color == textStyle.color;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (int) flags;
        return result;
    }

    public TextStyle merge(TextStyle other) {
        if(other == null) return new TextStyle(this);
        int newMask = this.mask | other.mask;
        int newFlags = other.flags | ((~other.mask & this.mask) & this.flags);
        TextColor newColor = this.color;
        if(newColor == null) newColor = other.color;
        TextStyle newStyle = new TextStyle(newColor);
        newStyle.mask = (byte) newMask;
        newStyle.flags = (byte) newFlags;
        return newStyle;
    }

    public void assertAvailable() {
        if(color != null) color.assertAvailable();
        if(font != null) VersionFeatureManager.assertEnabled("textcomponent.font");
    }
}
