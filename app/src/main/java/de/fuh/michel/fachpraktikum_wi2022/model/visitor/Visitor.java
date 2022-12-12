package de.fuh.michel.fachpraktikum_wi2022.model.visitor;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;

public interface Visitor {

    void visit(ProcessFlow processFlow);

    // Definitions

    void visit(PluginDefinition pluginDefinition);

    void visit(FusionDefinition fusionDefinition);

    void visit(ExportDefinition exportDefinition);

    void visit(ResourceDefinition resourceDefinition);

    // ConfigurationElements

    void visit(Parameter parameter);

    void visit(FlowSource flowSource);

    void visit(Fusion fusion);

    void visit(Mmfg mmfg);

    void visit(Export export);

}
