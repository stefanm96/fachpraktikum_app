package de.fuh.michel.fachpraktikum_wi2022.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitable;
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class ProcessFlow implements Visitable {
    private String name;
    private String extension;
    private boolean isGeneral;

    private List<Definition> definitions;
    private List<ConfigurationElement> configurationElements;

    public ProcessFlow() {
        definitions = new ArrayList<>();
        configurationElements = new ArrayList<>();
    }

    public void addPluginDefinition(String name, String clazz) {
        this.definitions.add(new PluginDefinition(name, clazz));
    }

    public void addFusionDefinition(String name, String clazz) {
        this.definitions.add(new FusionDefinition(name, clazz));
    }

    public void addExportDefinition(String name, String clazz) {
        this.definitions.add(new ExportDefinition(name, clazz));
    }

    public void addResourceDefinition(String name, String type, String location) {
        this.definitions.add(new ResourceDefinition(name, type, location));
    }

    public void addParameter(String name, String value) {
        this.configurationElements.add(new Parameter(name, value));
    }

    public void addFlowSource(String name) {
        this.configurationElements.add(new FlowSource(name));
    }

    public void addMmfg(Set<String> processor) {
        this.configurationElements.add(new Mmfg(processor));
    }

    public void addFusion(String processor) {
        this.configurationElements.add(new Fusion(processor));
    }

    public void addExport(String target, String exporter) {
        this.configurationElements.add(new Export(target, exporter));
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isGeneral() {
        return isGeneral;
    }

    public void setGeneral(boolean general) {
        isGeneral = general;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public List<ConfigurationElement> getConfigurationElements() {
        return configurationElements;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public void setConfigurationElements(List<ConfigurationElement> configurationElements) {
        this.configurationElements = configurationElements;
    }

    @Override
    public String toString() {
        return "ProcessFlow{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", isGeneral=" + isGeneral +
                ", definitionMap=" + definitions +
                ", configurationElements=" + configurationElements +
                '}';
    }
}
