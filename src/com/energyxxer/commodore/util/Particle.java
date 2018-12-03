package com.energyxxer.commodore.util;

import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;

import java.util.ArrayList;
import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertParticle;

public class Particle {
    private final Type type;
    private final ArrayList<Object> arguments = new ArrayList<>();

    public Particle(Type type, Object... arguments) {
        this.type = type;
        assertParticle(type);
        String[] expectedArguments = type.getProperty("argument").split("-");
        if(expectedArguments.length == 1 && expectedArguments[0].equals("none")) {
            if(arguments.length > 0) {
                throw new IllegalArgumentException("Particle '" + type + "' takes no arguments; got " + arguments.length + " instead");
            } else return;
        }
        if(arguments.length != expectedArguments.length) throw new IllegalArgumentException("Particle '" + type + "' takes " + expectedArguments.length + " arguments, instead got " + arguments.length);
        for(int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            String expectedArgument = expectedArguments[i];

            if(
                    (argument instanceof ParticleColor && expectedArgument.equals("color")) ||
                    (argument instanceof Block && expectedArgument.equals("block")) ||
                    (argument instanceof Item && expectedArgument.equals("item")) ||
                    (argument instanceof Integer && expectedArgument.equals("int")) ||
                    (argument instanceof Double && expectedArgument.equals("double"))
            ) {
                this.arguments.add(argument);
            } else throw new IllegalArgumentException("Particle '" + type + "' takes '" + expectedArgument + "' as argument " + (i+1) + "; instead, '" + argument + "' was passed");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.getName());
        for(Object arg : arguments) {
            sb.append(' ');
            sb.append(arg);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return Objects.equals(type, particle.type) &&
                Objects.equals(arguments, particle.arguments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(type, arguments);
    }
}
