package de.fuh.michel.fachpraktikum_wi2022;

import android.app.Application;
import android.util.Xml;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.FileProvider;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.export.XmlExporter;
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
        XmlExporter xmlExporter = new XmlExporter(Xml.newSerializer(), getFilesDir());
        fileProvider = new FileProvider(getFilesDir());
        processFlowViewModel = new ProcessFlowViewModel(xmlParser, xmlExporter);
    }

    public ProcessFlowViewModel getProcessFlowViewModel() {
        return processFlowViewModel;
    }

    public FileProvider getFileProvider() {
        return fileProvider;
    }
}
