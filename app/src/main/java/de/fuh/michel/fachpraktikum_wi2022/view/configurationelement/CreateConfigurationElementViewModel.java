package de.fuh.michel.fachpraktikum_wi2022.view.configurationelement;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.LinkedHashSet;
import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;

public class CreateConfigurationElementViewModel extends ViewModel {

    private final MutableLiveData<Set<String>> processorsLiveData = new MutableLiveData<>(new LinkedHashSet<>());
    private final MutableLiveData<String> processorLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> nameLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> valueLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> targetLiveData = new MutableLiveData<>("");
    private final MutableLiveData<String> exporterLiveData = new MutableLiveData<>("");

    public ConfigurationElement createConfigurationElementOfType(
            String configurationElementType) {

        switch (configurationElementType) {
            case Parameter.CONFIGURATION_ELEMENT_TYPE:
                return new Parameter(nameLiveData.getValue(), valueLiveData.getValue());
            case FlowSource.CONFIGURATION_ELEMENT_TYPE:
                return new FlowSource(nameLiveData.getValue());
            case Mmfg.CONFIGURATION_ELEMENT_TYPE:
                return new Mmfg(processorsLiveData.getValue());
            case Fusion.CONFIGURATION_ELEMENT_TYPE:
                return new Fusion(processorLiveData.getValue());
            case Export.CONFIGURATION_ELEMENT_TYPE:
                return new Export(targetLiveData.getValue(), exporterLiveData.getValue());
        }

        throw new IllegalArgumentException("ConfigurationElementType: " + configurationElementType
                + "does not exist!");
    }

    public MutableLiveData<String> getProcessorLiveData() {
        return processorLiveData;
    }

    public MutableLiveData<String> getNameLiveData() {
        return nameLiveData;
    }

    public MutableLiveData<String> getValueLiveData() {
        return valueLiveData;
    }

    public MutableLiveData<String> getTargetLiveData() {
        return targetLiveData;
    }

    public MutableLiveData<String> getExporterLiveData() {
        return exporterLiveData;
    }

    public void setName(String name) {
        nameLiveData.postValue(name);
    }

    public void setValue(String value) {
        valueLiveData.postValue(value);
    }

    public void addProcessor(String pluginName) {
        Set<String> newSet = processorsLiveData.getValue();
        newSet.add(pluginName);
        processorsLiveData.postValue(newSet);
    }

    public void removeProcessor(String pluginName) {
        Set<String> newSet = processorsLiveData.getValue();
        newSet.remove(pluginName);
        processorsLiveData.postValue(newSet);
    }

    public void setProcessor(String processor) {
        processorLiveData.postValue(processor);
    }
}