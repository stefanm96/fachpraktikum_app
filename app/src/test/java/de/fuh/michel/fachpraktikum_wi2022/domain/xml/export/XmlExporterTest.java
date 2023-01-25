package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.file.FileWriter;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class XmlExporterTest {

    private XmlSerializer xmlSerializer;
    private FileWriter fileWriter;
    private ProcessFlow processFlow;

    @Before
    public void setUp() {
        xmlSerializer = mock(XmlSerializer.class);
        fileWriter = mock(FileWriter.class);
        processFlow = mock(ProcessFlow.class);
    }

    @Test
    public void testExportProcessFlow() throws IOException {
        XmlExporter xmlExporter = new XmlExporter(xmlSerializer, fileWriter);
        xmlExporter.exportProcessFlow(processFlow);
        verify(processFlow).accept(any(XmlExportVisitor.class));
        verify(fileWriter).writeToFile(anyString(), anyString());
    }

}