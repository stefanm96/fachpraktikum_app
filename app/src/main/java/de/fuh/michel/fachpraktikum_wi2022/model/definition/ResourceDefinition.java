package de.fuh.michel.fachpraktikum_wi2022.model.definition;

import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class ResourceDefinition implements Definition {

    public static final String DEFINITION_TYPE = "resource";

    private final String name;
    private final String type;
    private final String location;

    public ResourceDefinition(String name, String type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
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

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "ResourceDefinition{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
