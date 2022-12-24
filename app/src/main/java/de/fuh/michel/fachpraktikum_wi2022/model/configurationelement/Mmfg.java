package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class Mmfg implements ConfigurationElement {

    public static final String CONFIGURATION_ELEMENT_TYPE = "mmfg";


    private Set<String> processor;

    public Mmfg(Set<String> processor) {
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

    public Set<String> getProcessor() {
        return processor;
    }

    public void setProcessor(Set<String> processor) {
        this.processor = processor;
    }

    @Override
    public String toString() {
        return "Mmfg{" +
                "processor=" + processor +
                '}';
    }
}
