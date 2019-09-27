package com.energyxxer.commodore.textcomponents;

import com.energyxxer.commodore.functionlogic.coordinates.CoordinateSet;
import com.energyxxer.commodore.functionlogic.entity.Entity;
import com.energyxxer.commodore.functionlogic.nbt.path.NBTPath;
import com.energyxxer.commodore.functionlogic.score.LocalScore;
import com.energyxxer.commodore.versioning.compatibility.VersionFeatureManager;
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

    public void assertAvailable() {
        if(supportsProperties() && style != TextStyle.EMPTY_STYLE) {
            VersionFeatureManager.assertEnabled("textcomponent.styles");
        }
        for(TextEvent event : events) {
            event.assertAvailable();
        }
    };

    public abstract String toString(TextStyle parentStyle);

    @Override
    public String toString() {
        return this.toString(null);
    }


    public static class ShorthandConstructors {
        public static StringTextComponent text(String text, TextStyle style) {
            return new StringTextComponent(text, style);
        }
        public static StringTextComponent text(String text) {
            return new StringTextComponent(text);
        }
        public static SelectorTextComponent selector(Entity entity, TextStyle style) {
            return new SelectorTextComponent(entity, style);
        }
        public static SelectorTextComponent selector(Entity entity) {
            return new SelectorTextComponent(entity);
        }
        public static TranslateTextComponent translate(String key, TextStyle style) {
            return new TranslateTextComponent(key, style);
        }
        public static TranslateTextComponent translate(String key) {
            return new TranslateTextComponent(key);
        }
        public static ScoreTextComponent score(LocalScore score, TextStyle style) {
            return new ScoreTextComponent(score, style);
        }
        public static ScoreTextComponent score(LocalScore score) {
            return new ScoreTextComponent(score);
        }
        public static KeybindTextComponent keybind(String key, TextStyle style) {
            return new KeybindTextComponent(key, style);
        }
        public static KeybindTextComponent keybind(String key) {
            return new KeybindTextComponent(key);
        }
        public static NBTTextComponent nbt(Entity entity, NBTPath path, TextStyle style) {
            NBTTextComponent nbttc = new NBTTextComponent(path, entity);
            nbttc.setStyle(style);
            return nbttc;
        }
        public static NBTTextComponent nbt(Entity entity, NBTPath path) {
            return new NBTTextComponent(path, entity);
        }
        public static NBTTextComponent nbt(CoordinateSet block, NBTPath path, TextStyle style) {
            NBTTextComponent nbttc = new NBTTextComponent(path, block);
            nbttc.setStyle(style);
            return nbttc;
        }
        public static NBTTextComponent nbt(CoordinateSet block, NBTPath path) {
            return new NBTTextComponent(path, block);
        }
        public static ListTextComponent list(TextComponent... components) {
            return new ListTextComponent(components);
        }
    }
}
