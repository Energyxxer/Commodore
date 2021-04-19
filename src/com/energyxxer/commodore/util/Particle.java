package com.energyxxer.commodore.util;

import com.energyxxer.commodore.CommodoreException;
import com.energyxxer.commodore.block.Block;
import com.energyxxer.commodore.item.Item;
import com.energyxxer.commodore.types.Type;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import static com.energyxxer.commodore.types.TypeAssert.assertParticle;
import static com.energyxxer.commodore.util.MiscValidator.assertFinite;

public class Particle {
    @NotNull
    private final Type type;
    @NotNull
    private final ArrayList<Object> arguments = new ArrayList<>();

    public Particle(@NotNull Type type, @NotNull Object... arguments) {
        this.type = type;
        assertParticle(type);
        String argumentProperty = type.getProperty("argument");
        String[] expectedArguments = argumentProperty != null ? argumentProperty.split("-") : null;
        if(expectedArguments == null || (expectedArguments.length == 1 && expectedArguments[0].equals("none"))) {
            if(arguments.length > 0) {
                throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Particle '" + type + "' takes no arguments; got " + arguments.length + " instead", arguments);
            } else return;
        }
        if(arguments.length != expectedArguments.length) throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Particle '" + type + "' takes " + expectedArguments.length + " arguments, instead got " + arguments.length, arguments);
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
                if (argument instanceof Double) {
                    assertFinite((Double) argument, "particle argument");
                }
                this.arguments.add(argument);
            } else throw new CommodoreException(CommodoreException.Source.API_ARGUMENT_ERROR, "Particle '" + type + "' takes '" + expectedArgument + "' as argument " + (i+1) + "; instead, '" + argument + "' was passed", argument);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toSafeString());
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
