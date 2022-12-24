package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import android.util.Log;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import de.fuh.michel.fachpraktikum_wi2022.exception.ExportFailedException;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class XmlExporter {

    private static final String TAG = "XmlExporter";
    public static final String EXTENSION = ".xml";

    private final XmlSerializer xmlSerializer;
    private final File filesDir;

    public XmlExporter(XmlSerializer xmlSerializer, File filesDir) {
        this.xmlSerializer = xmlSerializer;
        this.filesDir = filesDir;
    }

    public void exportProcessFlow(ProcessFlow processFlow) {
        try {
            Log.i(TAG, "Start exporting...");
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);

            XmlExportVisitor xmlExportVisitor = new XmlExportVisitor(xmlSerializer);

            processFlow.accept(xmlExportVisitor);

            xmlSerializer.setOutput(null);

            Log.i(TAG, writer.toString());
            writeToFile(processFlow, writer);
            Log.i(TAG, "Exporting finished successfully!");
        } catch (IOException e) {
            Log.e(TAG, "Exporting failed!");
            e.printStackTrace();
            throw new ExportFailedException();
        }
    }

    private void writeToFile(ProcessFlow processFlow, StringWriter writer) throws IOException {
        File outputFile = new File(filesDir, processFlow.getName() + EXTENSION);
        FileWriter fileWriter = new FileWriter(outputFile);
        fileWriter.write(writer.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}
