package de.fuh.michel.fachpraktikum_wi2022.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.export.XmlExporter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.XmlParser;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ProcessFlowViewModel extends ViewModel {

    private final XmlParser xmlParser;
    private final XmlExporter xmlExporter;

    private final MutableLiveData<String> nameLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> extensionLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isGeneralLiveData = new MutableLiveData<>();

    private final MutableLiveData<List<Definition>> definitionsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ConfigurationElement>> configurationElementsLiveData = new MutableLiveData<>();

    public ProcessFlowViewModel(XmlParser xmlParser, XmlExporter xmlExporter) {
        this.xmlParser = xmlParser;
        this.xmlExporter = xmlExporter;
        init();
    }

    private void init() {
        nameLiveData.postValue("");
        extensionLiveData.postValue("");
        isGeneralLiveData.postValue(false);
        definitionsLiveData.postValue(new ArrayList<>());
        configurationElementsLiveData.postValue(new ArrayList<>());
    }

    public void importProcessFlow(String fileContent) throws Exception {
        setProcessFlow(xmlParser.parseProcessFlow(fileContent));
    }

    public void importDefinition(String content) {
        Definition definition = xmlParser.parseDefinition(content);
        addDefinition(definition);
    }

    public void exportProcessFlow() {
        xmlExporter.exportProcessFlow(getProcessFlow());
    }

    public String getProcessFlowContent() {
        return xmlExporter.getProcessFlowContent(getProcessFlow());
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

    public void newProcessFlow() {
        init();
    }

    public List<Definition> getDefinitionsForType(String currentDefinitionType) {
        return definitionsLiveData.getValue().stream()
                .filter(definition -> definition.getDefinitionType().equals(currentDefinitionType))
                .collect(Collectors.toList());
    }

    public MutableLiveData<List<Definition>> getDefinitionsLiveData() {
        return definitionsLiveData;
    }

    public MutableLiveData<List<ConfigurationElement>> getConfigurationElementsLiveData() {
        return configurationElementsLiveData;
    }

    public void addDefinition(Definition definition) {
        applyToLiveDataList(definitionsLiveData, list -> list.add(definition));
    }

    public Definition getDefinition(int definitionPosition) {
        return definitionsLiveData.getValue().get(definitionPosition);
    }

    public void removeDefinition(Definition definition) {
        applyToLiveDataList(definitionsLiveData, list -> list.remove(definition));
    }

    public void addConfigurationElement(ConfigurationElement configurationElement) {
        applyToLiveDataList(configurationElementsLiveData, list -> list.add(configurationElement));
    }

    public void editConfigurationElement(int position, ConfigurationElement configurationElement) {
        applyToLiveDataList(configurationElementsLiveData, list -> list.set(position, configurationElement));
    }

    public void swapConfigurationElements(int fromPosition, int toPosition) {
        applyToLiveDataList(configurationElementsLiveData, list -> Collections.swap(list, fromPosition, toPosition));
    }

    public void removeConfigurationElement(int position) {
        applyToLiveDataList(configurationElementsLiveData, list -> list.remove(position));
    }

    private <T> void applyToLiveDataList(MutableLiveData<List<T>> liveDataList, Consumer<List<T>> listConsumer) {
        List<T> newList = liveDataList.getValue();
        listConsumer.accept(newList);
        liveDataList.postValue(newList);
    }

    public String getName() {
        return nameLiveData.getValue();
    }

    public String getExtension() {
        return extensionLiveData.getValue();
    }

    public boolean getIsGeneral() {
        return Boolean.TRUE.equals(isGeneralLiveData.getValue());
    }

    public void setName(String name) {
        nameLiveData.postValue(name);
    }

    public void setExtension(String extension) {
        extensionLiveData.postValue(extension);
    }

    public void setIsGeneral(boolean isGeneral) {
        isGeneralLiveData.postValue(isGeneral);
    }

}
