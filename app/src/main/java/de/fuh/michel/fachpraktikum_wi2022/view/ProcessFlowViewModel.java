package de.fuh.michel.fachpraktikum_wi2022.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ProcessFlowViewModel extends ViewModel {

    private final MutableLiveData<String> nameLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> extensionLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isGeneralLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<Definition>> definitionsLiveData =
            new MutableLiveData<>();

    private final MutableLiveData<List<ConfigurationElement>> configurationElementsLiveData =
            new MutableLiveData<>();

    public ProcessFlowViewModel() {
        init();
    }

    private void init() {
        nameLiveData.postValue("");
        extensionLiveData.postValue("");
        isGeneralLiveData.postValue(false);
        definitionsLiveData.postValue(new ArrayList<>());
        configurationElementsLiveData.postValue(new ArrayList<>());
    }

    public ProcessFlow getProcessFlow() {
        ProcessFlow processFlow = new ProcessFlow();

        processFlow.setName(nameLiveData.getValue());
        processFlow.setExtension(extensionLiveData.getValue());
        processFlow.setGeneral(isGeneralLiveData.getValue());

        processFlow.setDefinitions(definitionsLiveData.getValue());
        processFlow.setConfigurationElements(configurationElementsLiveData.getValue());

        return processFlow;
    }

    public void setProcessFlow(ProcessFlow processFlow) {
        nameLiveData.postValue(processFlow.getName());
        extensionLiveData.postValue(processFlow.getExtension());
        isGeneralLiveData.postValue(processFlow.isGeneral());

        definitionsLiveData.postValue(processFlow.getDefinitions());
        configurationElementsLiveData.postValue(processFlow.getConfigurationElements());
    }

    public void clearData() {
        init();
    }

    public void removeDefinition(String type, String name) {
        List<Definition> newList = definitionsLiveData.getValue();

        if (newList.size() < 1) {
            return;
        }

        newList.removeIf(definition -> definition.getDefinitionType().equals(type) &&
                definition.getName().equals(name));
        definitionsLiveData.postValue(newList);
    }

    public long getNumberOfDefinitionTypes() {
        return definitionsLiveData.getValue().stream()
                .map(Definition::getDefinitionType)
                .distinct()
                .count();
    }

    public long getNumberOfDefinitionsForType(String definitionType) {
        return definitionsLiveData.getValue().stream()
                .map(Definition::getDefinitionType)
                .filter(definitionType::equals)
                .count();
    }

    public List<Definition> getDefinitionsForType(String currentDefinitionType) {
        return definitionsLiveData.getValue().stream()
                .filter(definition -> definition.getDefinitionType().equals(currentDefinitionType))
                .collect(Collectors.toList());
    }

    public MutableLiveData<String> getNameLiveData() {
        return nameLiveData;
    }

    public MutableLiveData<String> getExtensionLiveData() {
        return extensionLiveData;
    }

    public MutableLiveData<Boolean> getIsGeneralLiveData() {
        return isGeneralLiveData;
    }

    public MutableLiveData<List<Definition>> getDefinitionsLiveData() {
        return definitionsLiveData;
    }

    public MutableLiveData<List<ConfigurationElement>> getConfigurationElementsLiveData() {
        return configurationElementsLiveData;
    }

    public void removeDefinition(Definition definition) {
        List<Definition> newList = definitionsLiveData.getValue();
        newList.remove(definition);
        definitionsLiveData.postValue(newList);
    }

    public void addDefinition(Definition definition) {
        List<Definition> newList = definitionsLiveData.getValue();
        newList.add(definition);
        definitionsLiveData.postValue(newList);
    }
}
