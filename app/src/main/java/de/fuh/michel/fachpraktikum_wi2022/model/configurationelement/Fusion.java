package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class Fusion implements ConfigurationElement {

    public static final String CONFIGURATION_ELEMENT_TYPE = "fusion";

    private String processor;

    public Fusion(String processor) {
        this.processor = processor;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getConfigurationElementType() {
        return CONFIGURATION_ELEMENT_TYPE;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "Fusion{" +
                "processor='" + processor + '\'' +
                '}';
    }
}
