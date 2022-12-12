package de.fuh.michel.fachpraktikum_wi2022.model.configurationelement;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class Export implements ConfigurationElement {

    private String target;
    private String exporter;

    public Export(String target, String exporter) {
        this.target = target;
        this.exporter = exporter;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getExporter() {
        return exporter;
    }

    public void setExporter(String exporter) {
        this.exporter = exporter;
    }

    @Override
    public String toString() {
        return "Export{" +
                "target='" + target + '\'' +
                ", exporter='" + exporter + '\'' +
                '}';
    }
}
