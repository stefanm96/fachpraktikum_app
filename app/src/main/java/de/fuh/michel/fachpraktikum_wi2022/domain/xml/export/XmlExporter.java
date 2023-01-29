package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import android.util.Log;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.file.FileWriter;
import de.fuh.michel.fachpraktikum_wi2022.exception.ExportFailedException;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class XmlExporter {

    private static final String TAG = "XmlExporter";
    public static final String EXTENSION = ".xml";

    private final XmlSerializer xmlSerializer;
    private final FileWriter fileWriter;

    public XmlExporter(XmlSerializer xmlSerializer, FileWriter fileWriter) {
        this.xmlSerializer = xmlSerializer;
        this.fileWriter = fileWriter;
    }

    public void exportProcessFlow(ProcessFlow processFlow) {
        try {
            Log.i(TAG, "Start exporting...");

            String processFlowContent = getProcessFlowContent(processFlow);
            fileWriter.writeToFile(processFlow.getName() + EXTENSION, processFlowContent);

            Log.i(TAG, "Exporting finished successfully!");
        } catch (IOException e) {
            Log.e(TAG, "Exporting failed!");
            e.printStackTrace();
            throw new ExportFailedException();
        }
    }

    public String getProcessFlowContent(ProcessFlow processFlow) {
        try {
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);

            XmlExportVisitor xmlExportVisitor = new XmlExportVisitor(xmlSerializer);

            processFlow.accept(xmlExportVisitor);

            xmlSerializer.setOutput(null);

            Log.i(TAG, writer.toString());
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
