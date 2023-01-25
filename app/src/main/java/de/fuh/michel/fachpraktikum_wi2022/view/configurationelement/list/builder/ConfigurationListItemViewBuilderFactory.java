package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement.list.builder;

import java.util.HashMap;
import java.util.Map;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;

public class ConfigurationListItemViewBuilderFactory {

    private final Map<String, ConfigurationListItemViewBuilder> builderMap;

    public ConfigurationListItemViewBuilderFactory() {
        builderMap = new HashMap<>();
        builderMap.put(Parameter.CONFIGURATION_ELEMENT_TYPE, new ParameterListItemViewBuilder());
        builderMap.put(FlowSource.CONFIGURATION_ELEMENT_TYPE, new FlowSourceListItemViewBuilder());
        builderMap.put(Mmfg.CONFIGURATION_ELEMENT_TYPE, new MmfgListItemViewBuilder());
        builderMap.put(Fusion.CONFIGURATION_ELEMENT_TYPE, new FusionListItemViewBuilder());
        builderMap.put(Export.CONFIGURATION_ELEMENT_TYPE, new ExportListItemViewBuilder());
    }

    public ConfigurationListItemViewBuilder getViewBuilder(ConfigurationElement configurationElement) {
        String builderName = configurationElement.getConfigurationElementType();
        ConfigurationListItemViewBuilder viewBuilder = builderMap.get(builderName);

        if (viewBuilder == null) {
            throw new IllegalArgumentException("ConfigurationListItemViewBuilder not found: "
                    + builderName);
        }

        return viewBuilder;
    }
}
