package de.fuh.michel.fachpraktikum_wi2022;

import android.app.Application;
import android.util.Xml;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.file.FileProvider;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.export.XmlExporter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.file.FileWriter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.XmlParser;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.XmlTagFactory;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class GmafApplication extends Application {

    private ProcessFlowViewModel processFlowViewModel;
    private FileProvider fileProvider;

    @Override
    public void onCreate() {
        super.onCreate();

        XmlParser xmlParser = new XmlParser(new XmlTagFactory());
        FileWriter fileWriter = new FileWriter(getFilesDir());
        XmlExporter xmlExporter = new XmlExporter(Xml.newSerializer(), fileWriter);
        fileProvider = new FileProvider(getFilesDir());
        processFlowViewModel = new ProcessFlowViewModel(xmlParser, xmlExporter);
        try {
            processFlowViewModel.importProcessFlow(fileProvider.getFileContent("SampleConfig.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ProcessFlowViewModel getProcessFlowViewModel() {
        return processFlowViewModel;
    }

    public FileProvider getFileProvider() {
        return fileProvider;
    }
}
