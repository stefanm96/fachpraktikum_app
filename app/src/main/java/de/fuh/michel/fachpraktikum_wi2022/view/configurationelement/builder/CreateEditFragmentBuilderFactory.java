package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.builder;

import java.util.HashMap;
import java.util.Map;

import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;

public class CreateEditFragmentBuilderFactory {

    private final Map<String, CreateEditFragmentBuilder> builderMap;

    public CreateEditFragmentBuilderFactory() {
        builderMap = new HashMap<>();
        builderMap.put(Parameter.CONFIGURATION_ELEMENT_TYPE, new ParameterCreateEditFragmentBuilder());
        builderMap.put(FlowSource.CONFIGURATION_ELEMENT_TYPE, new FlowSourceCreateEditFragmentBuilder());
        builderMap.put(Mmfg.CONFIGURATION_ELEMENT_TYPE, new MmfgCreateEditFragmentBuilder());
        builderMap.put(Fusion.CONFIGURATION_ELEMENT_TYPE, new FusionCreateEditFragmentBuilder());
        builderMap.put(Export.CONFIGURATION_ELEMENT_TYPE, new ExportCreateEditFragmentBuilder());
    }

    public CreateEditFragmentBuilder getFragmentBuilder(String configurationType) {
        CreateEditFragmentBuilder viewBuilder = builderMap.get(configurationType);

        if (viewBuilder == null) {
            throw new IllegalArgumentException("CreateEditFragmentBuilder not found: " + configurationType);
        }

        return viewBuilder;
    }

}
