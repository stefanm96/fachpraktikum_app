package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;

public class XmlExportVisitorTest {

    private XmlExportVisitor xmlExportVisitor;
    private XmlSerializer xmlSerializerMock;

    @Before
    public void setUp() throws Exception {
        xmlSerializerMock = mock(XmlSerializer.class);
        xmlExportVisitor = new XmlExportVisitor(xmlSerializerMock);
    }

    @Test
    public void visit_validProcessFlow_correctXmlGenerated() throws Exception {
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setName("name");
        processFlow.setExtension("extension");
        processFlow.setGeneral(true);
        PluginDefinition pluginDefinition = new PluginDefinition("name", "clazz");
        FusionDefinition fusionDefinition = new FusionDefinition("name", "clazz");
        ExportDefinition exportDefinition = new ExportDefinition("name", "clazz");
        ResourceDefinition resourceDefinition = new ResourceDefinition("name", "type", "location");

        processFlow.getDefinitions().add(pluginDefinition);
        processFlow.getDefinitions().add(fusionDefinition);
        processFlow.getDefinitions().add(exportDefinition);
        processFlow.getDefinitions().add(resourceDefinition);

        Parameter parameter = new Parameter("name", "value");
        FlowSource flowSource = new FlowSource("name");
        Mmfg mmfg = new Mmfg(new LinkedHashSet<>(Arrays.asList("processor1", "processor2")));
        mmfg.getProcessor().add("processor1");
        mmfg.getProcessor().add("processor2");
        Fusion fusion = new Fusion("processor");
        Export export = new Export("target", "exporter");

        processFlow.getConfigurationElements().add(parameter);
        processFlow.getConfigurationElements().add(flowSource);
        processFlow.getConfigurationElements().add(mmfg);
        processFlow.getConfigurationElements().add(fusion);
        processFlow.getConfigurationElements().add(export);

        xmlExportVisitor.visit(processFlow);

        verify(xmlSerializerMock).startDocument("UTF-8", true);
        verify(xmlSerializerMock).startTag("", "process-flow");
        verify(xmlSerializerMock, times(7)).attribute("", "name", "name");
        verify(xmlSerializerMock).endTag("", "process-flow");
        verify(xmlSerializerMock).endDocument();
    }

    @Test
    public void testPluginDefinition() throws IOException {
        PluginDefinition pluginDefinition = new PluginDefinition("name", "clazz");
        xmlExportVisitor.visit(pluginDefinition);
        verify(xmlSerializerMock).startTag("", "plugin-definition");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).attribute("", "class", "clazz");
        verify(xmlSerializerMock).endTag("", "plugin-definition");
    }

    @Test
    public void testVisitFusionDefinition() throws IOException {
        FusionDefinition fusionDefinition = new FusionDefinition("name", "clazz");
        xmlExportVisitor.visit(fusionDefinition);
        verify(xmlSerializerMock).startTag("", "fusion-definition");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).attribute("", "class", "clazz");
        verify(xmlSerializerMock).endTag("", "fusion-definition");
    }

    @Test
    public void testVisitExportDefinition() throws IOException {
        ExportDefinition exportDefinition = new ExportDefinition("name", "clazz");
        xmlExportVisitor.visit(exportDefinition);
        verify(xmlSerializerMock).startTag("", "export-definition");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).attribute("", "class", "clazz");
        verify(xmlSerializerMock).endTag("", "export-definition");
    }

    @Test
    public void testVisitResourceDefinition() throws Exception {
        ResourceDefinition resourceDefinition = new ResourceDefinition("name", "type", "location");
        xmlExportVisitor.visit(resourceDefinition);
        verify(xmlSerializerMock).startTag("", "resource-definition");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).attribute("", "type", "type");
        verify(xmlSerializerMock).attribute("", "location", "location");
        verify(xmlSerializerMock).endTag("", "resource-definition");
    }

    @Test
    public void testVisitParameter() throws IOException {
        Parameter parameter = new Parameter("name", "value");
        xmlExportVisitor.visit(parameter);
        verify(xmlSerializerMock).startTag("", "param");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).attribute("", "value", "value");
        verify(xmlSerializerMock).endTag("", "param");
    }

    @Test
    public void testVisitFlowSource() throws IOException {
        FlowSource flowSource = new FlowSource("name");
        xmlExportVisitor.visit(flowSource);
        verify(xmlSerializerMock).startTag("", "flow-source");
        verify(xmlSerializerMock).attribute("", "name", "name");
        verify(xmlSerializerMock).endTag("", "flow-source");
    }

    @Test
    public void testVisitMmfg() throws IOException {
        Mmfg mmfg = new Mmfg(new LinkedHashSet<>(Arrays.asList("processor1", "processor2")));
        xmlExportVisitor.visit(mmfg);
        verify(xmlSerializerMock).startTag("", "mmfg");
        verify(xmlSerializerMock).attribute("", "processor", "processor1, processor2");
        verify(xmlSerializerMock).endTag("", "mmfg");
    }

    @Test
    public void testVisitFusion() throws IOException {
        Fusion fusion = new Fusion("processor");
        xmlExportVisitor.visit(fusion);
        verify(xmlSerializerMock).startTag("", "fusion");
        verify(xmlSerializerMock).attribute("", "processor", "processor");
        verify(xmlSerializerMock).endTag("", "fusion");
    }

    @Test
    public void testVisitExport() throws IOException {
        Export export = new Export("target", "exporter");
        xmlExportVisitor.visit(export);
        verify(xmlSerializerMock).startTag("", "export");
        verify(xmlSerializerMock).attribute("", "target", "target");
        verify(xmlSerializerMock).attribute("", "exporter", "exporter");
    }
}