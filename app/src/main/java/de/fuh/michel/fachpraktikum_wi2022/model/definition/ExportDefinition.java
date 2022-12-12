package de.fuh.michel.fachpraktikum_wi2022.model.definition;

import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class ExportDefinition implements Definition {

    public static final String DEFINITION_TYPE = "export";

    private String name;
    private String clazz;

    public ExportDefinition(String name, String clazz) {
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
        return "ExportDefinition{" +
                "name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
