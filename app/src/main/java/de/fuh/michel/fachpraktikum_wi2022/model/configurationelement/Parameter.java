package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import java.util.Objects;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class Parameter implements ConfigurationElement {

    public static final String CONFIGURATION_ELEMENT_TYPE = "parameter";

    private String name;
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getConfigurationElementType() {
        return CONFIGURATION_ELEMENT_TYPE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
