package com.energyxxer.commodore.textcomponents;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public abstract class TextComponent {
    @NotNull
    protected TextStyle style = TextStyle.EMPTY_STYLE;
    @NotNull
    protected final ArrayList<@NotNull TextEvent> events = new ArrayList<>();
    protected ListTextComponent extra = null;

    public abstract boolean supportsProperties();

    @NotNull
    public TextStyle getStyle() {
        return style;
    }

    public void setStyle(@Nullable TextStyle style) {
        if(style == null) return;
        if(supportsProperties()) {
            this.style = style;
        } else throw new UnsupportedOperationException(this.getClass().getName() + " does not support text properties");
    }

    public void addEvent(@NotNull TextEvent event) {
        if(supportsProperties()) {
            this.events.add(event);
        } else throw new UnsupportedOperationException(this.getClass().getName() + " does not support text properties");
    }

    public void addEvents(@Nullable Collection<@NotNull TextEvent> events) {
        if(events != null) events.forEach(this::addEvent);
    }

    public void addExtra(@NotNull TextComponent component) {
        if(supportsProperties()) {
            if(this.extra == null) this.extra = new ListTextComponent();
            this.extra.append(component);
        } else throw new UnsupportedOperationException(this.getClass().getName() + " does not support text properties");
    }

    public void addEvents(@NotNull TextEvent... events) {
        this.addEvents(Arrays.asList(events));
    }

    public void removeEvent(@NotNull TextEvent event) {
        this.events.remove(event);
    }

    public void clearEvents() {
        this.events.clear();
    }

    protected String getBaseProperties(TextStyle parentStyle) {
        if(!supportsProperties()) return null;

        ArrayList<String> properties = new ArrayList<>();
        String styleString = style.toString(parentStyle);
        if(styleString != null) properties.add(styleString);
        events.forEach(e -> properties.add(e.toString()));
        if(extra != null) {
            properties.add("\"extra\":" + extra.toString(this.style.merge(parentStyle)));
        }

        if(properties.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        Iterator<String> it = properties.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) sb.append(',');
        }
        return sb.toString();
    }

    public abstract String toString(TextStyle parentStyle);

    @Override
    public String toString() {
        return this.toString(null);
    }

}
