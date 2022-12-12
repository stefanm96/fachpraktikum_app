package de.fuh.michel.fachpraktikum_wi2022.domain;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.FileProvider;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.export.XmlExporter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.XmlImporter;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class MainService {

    private static final String TAG = "MainService";

    private final XmlExporter xmlExporter;
    private final XmlImporter xmlImporter;
    private final FileProvider fileProvider;

    private final ProcessFlowViewModel processFlowViewModel;

    public MainService(ProcessFlowViewModel processFlowViewModel, XmlExporter xmlExporter, XmlImporter xmlImporter, FileProvider fileProvider) {
        this.xmlExporter = xmlExporter;
        this.xmlImporter = xmlImporter;
        this.fileProvider = fileProvider;
        this.processFlowViewModel = processFlowViewModel;

        try {
            importProcessFlow("SampleConfig");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewProcessFlow() {
        processFlowViewModel.clearData();
    }

    public void importProcessFlow(String fileName) throws IOException, XmlPullParserException {
        String xmlContent = fileProvider.getXmlContent(fileName);
        ProcessFlow processFlow = xmlImporter.importProcessFlow(xmlContent);
        this.processFlowViewModel.setProcessFlow(processFlow);
    }

    public void exportProcessFlow() {
        try {
            xmlExporter.exportProcessFlow(processFlowViewModel.getProcessFlow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Definition> getDefinitionsForType(String currentDefinitionType) {
        return processFlowViewModel.getDefinitionsForType(currentDefinitionType);
    }

    public long getNumberOfDefinitionTypes() {
        return processFlowViewModel.getNumberOfDefinitionTypes();
    }

    public long getNumberOfDefinitionsForType(String definitionType) {
        return processFlowViewModel.getNumberOfDefinitionsForType(definitionType);
    }

    public Definition getDefinitionsForTypeAt(String currentDefinitionType, int position) {
        return getDefinitionsForType(currentDefinitionType).get(position);
    }

    public void importDefinition(String content) {
        try {
            Definition definition = xmlImporter.importDefinition(content);
            processFlowViewModel.addDefinition(definition);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
