package com.energyxxer.commodore.textcomponents;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public abstract class TextComponent {
    @NotNull
    private TextStyle style = TextStyle.EMPTY_STYLE;
    @NotNull
    private final ArrayList<@NotNull TextEvent> events = new ArrayList<>();

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

    public void addEvents(@NotNull TextEvent... events) {
        this.addEvents(Arrays.asList(events));
    }

    public void removeEvent(@NotNull TextEvent event) {
        this.events.remove(event);
    }

    public void clearEvents() {
        this.events.clear();
    }

    protected String getBaseProperties(TextComponent parent) {
        if(!supportsProperties()) return null;

        ArrayList<String> properties = new ArrayList<>();
        String styleString = style.toString(parent != null ? parent.getStyle() : null);
        if(styleString != null) properties.add(styleString);
        events.forEach(e -> properties.add(e.toString()));

        if(properties.isEmpty()) return null;

        StringBuilder sb = new StringBuilder();
        Iterator<String> it = properties.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) sb.append(',');
        }
        return sb.toString();
    }

    public abstract String toString(TextComponent parent);

    @Override
    public String toString() {
        return this.toString(null);
    }

}
