package de.fuh.michel.fachpraktikum_wi2022;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;

import de.fuh.michel.fachpraktikum_wi2022.domain.MainService;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.FileProvider;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.export.XmlExporter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.XmlImporter;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.XmlTagFactory;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class GmafApplication extends Application {

    private MainService mainService;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    public void onCreate() {
        super.onCreate();

        XmlTagFactory xmlTagFactory = new XmlTagFactory();
        XmlImporter xmlImporter = new XmlImporter(xmlTagFactory);
        XmlExporter xmlExporter = new XmlExporter();
        FileProvider fileProvider = new FileProvider(getFilesDir());
        processFlowViewModel = new ProcessFlowViewModel();

        mainService = new MainService(processFlowViewModel, xmlExporter, xmlImporter, fileProvider);
    }

    public MainService getMainService() {
        return mainService;
    }

    public ProcessFlowViewModel getProcessFlowViewModel() {
        return processFlowViewModel;
    }
}
