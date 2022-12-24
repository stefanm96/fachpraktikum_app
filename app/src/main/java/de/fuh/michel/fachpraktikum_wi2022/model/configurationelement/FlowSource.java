package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class FlowSource implements ConfigurationElement {

    public static final String CONFIGURATION_ELEMENT_TYPE = "flow-source";

    private String name;

    public FlowSource(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "FlowSource{" +
                "name='" + name + '\'' +
                '}';
    }
}
