package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.functionlogic.inspection.CommandEmbeddable;
import com.energyxxer.commodore.functionlogic.score.access.ScoreboardAccess;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class TextComponent {
    private TextStyle style = TextStyle.EMPTY_STYLE;
    private final ArrayList<TextEvent> events = new ArrayList<>();

    public abstract boolean supportsProperties();

    public TextStyle getStyle() {
        return style;
    }

    public void setStyle(TextStyle style) {
        if(style == null) return;
        if(supportsProperties()) {
            this.style = style;
        } else throw new UnsupportedOperationException(this.getClass().getName() + " does not support text properties");
    }

    public void addEvent(TextEvent event) {
        if(supportsProperties()) {
            this.events.add(event);
        } else throw new UnsupportedOperationException(this.getClass().getName() + " does not support text properties");
    }

    public void addEvents(Collection<TextEvent> events) {
        if(events != null) events.forEach(this::addEvent);
    }

    public void addEvents(TextEvent... events) {
        this.addEvents(Arrays.asList(events));
    }

    public void removeEvent(TextEvent event) {
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

    @NotNull
    public Collection<ScoreboardAccess> getScoreboardAccesses() {
        return Collections.emptyList();
    }

    public Collection<CommandEmbeddable> getEmbeddables() {
        return Collections.emptyList();
    }
}
