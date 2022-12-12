package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class FlowSource implements ConfigurationElement {

    private String name;

    public FlowSource(String name) {
        this.name = name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
