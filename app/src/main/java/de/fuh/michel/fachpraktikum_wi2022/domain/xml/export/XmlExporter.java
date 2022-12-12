package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class XmlExporter {

    private static final String TAG = "XmlExporter";
    
    private final XmlSerializer xmlSerializer;

    public XmlExporter() {
        xmlSerializer = Xml.newSerializer();
    }

    public void exportProcessFlow(ProcessFlow processFlow) throws IOException {
        Log.i(TAG, "Start exporting...");

        StringWriter writer = new StringWriter();
        xmlSerializer.setOutput(writer);

        XmlExportVisitor xmlExportVisitor = new XmlExportVisitor(xmlSerializer);

        processFlow.accept(xmlExportVisitor);

        xmlSerializer.setOutput(null);

        Log.i(TAG, writer.toString());

        Log.i(TAG, "Exporting finished successfully!");
    }
}
