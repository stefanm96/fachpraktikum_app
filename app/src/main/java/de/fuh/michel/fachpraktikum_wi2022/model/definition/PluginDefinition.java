package de.fuh.michel.fachpraktikum_wi2022.model.definition;

import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class PluginDefinition implements Definition {

    public static final String DEFINITION_TYPE = "plugin";

    private final String name;
    private final String clazz;

    public PluginDefinition(String name, String clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getDefinitionType() {
        return DEFINITION_TYPE;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "PluginDefinition{" +
                "name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
