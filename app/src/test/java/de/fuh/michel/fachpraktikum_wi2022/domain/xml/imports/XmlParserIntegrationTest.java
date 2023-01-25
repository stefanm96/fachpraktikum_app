package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports;

import static org.junit.Assert.assertEquals;

import androidx.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.XmlTagFactory;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

@RunWith(RobolectricTestRunner.class)
public class XmlParserIntegrationTest {

    private static final String INVALID_XML_PROCESS_FLOW = "invalid xml";
    private static final String VALID_XML_DEFINITION = "<definition></definition>";
    private static final String INVALID_XML_DEFINITION = "invalid xml";

    private XmlParser xmlParser;

    public static String getValidXmlProcessFlow() {
        return "<process-flow name=\"SampleConfig\" extension=\"*.jpg\" isGeneral=\"false\">\n" +
                "    <plugin-definition name=\"plugin1\" class=\"de.swa.bla.Blub\"/>\n" +
                "    <fusion-definition name=\"merge1\" class=\"de.swa.feat.Bla\"/>\n" +
                "    <export-definition name=\"mpeg7\" class=\"de.swa.exp.Bla\"/>\n" +
                "    <resource-definition name=\"upload-dir\" type=\"folder\" location=\"temp/upload\"/>\n" +
                "    <param name=\"plugin1.lod\" value=\"2\"/>\n" +
                "    <flow-source name=\"upload-dir\"/>\n" +
                "    <mmfg processor=\"plugin1, plugin2, plugin3\"/>\n" +
                "    <fusion processor=\"merge1\"/>\n" +
                "    <export target=\"export-dir\" exporter=\"mpeg7\"/>\n" +
                "</process-flow>";
    }

    @Before
    public void setUp() throws XmlPullParserException {
        xmlParser = new XmlParser(new XmlTagFactory(), XmlPullParserFactory.newInstance());
    }

    @Test
    public void parseProcessFlow_validXml_returnsProcessFlow() throws XmlPullParserException, IOException {
        ProcessFlow expectedProcessFlow = getExpectedProcessFlow();
        ProcessFlow actualProcessFlow = xmlParser.parseProcessFlow(getValidXmlProcessFlow());
        assertEquals(expectedProcessFlow.getName(), actualProcessFlow.getName());
        assertEquals(expectedProcessFlow.getExtension(), actualProcessFlow.getExtension());
        assertEquals(expectedProcessFlow.isGeneral(), actualProcessFlow.isGeneral());
    }

//    TODO: fix tests
//    @Test
//    public void parseDefinition_validXml_returnsDefinition() {
//        Definition expectedDefinition = new PluginDefinition("plugin1", "com.example.Plugin1");
//
//        doAnswer(invocation -> {
//            ProcessFlow processFlow = (ProcessFlow) invocation.getArgument(1);
//            processFlow.getDefinitions().add(expectedDefinition);
//            return null;
//        }).when(xmlTag).apply(any(), any());
//
//        Definition actualDefinition = xmlParser.parseDefinition(VALID_XML_DEFINITION);
//        actualDefinition.getDefinitionType();
//        assertEquals(expectedDefinition, actualDefinition);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void parseDefinition_invalidXml_throwsIllegalArgumentException() {
//        xmlParser.parseDefinition(INVALID_XML_DEFINITION);
//    }

//    @Test(expected = ImportDefinitionFailedException.class)
//    public void parseDefinition_throwsXmlPullParserException_throwsImportDefinitionFailedException() throws XmlPullParserException {
//        XmlTag xmlTag = mock(XmlTag.class);
//        when(xmlTagFactory.getXmlTag("definition")).thenReturn(xmlTag);
//        when(xmlTag.apply(mock(XmlPullParser.class), mock(ProcessFlow.class)))
//                .thenThrow(new XmlPullParserException("test exception"));
//
//        xmlParser.parseDefinition(VALID_XML_DEFINITION);
//    }
//
//    @Test(expected = ImportDefinitionFailedException.class)
//    public void parseDefinition_throwsIOException_throwsImportDefinitionFailedException() throws XmlPullParserException, IOException {
//        XmlTag xmlTag = mock(XmlTag.class);
//        when(xmlTagFactory.getXmlTag("definition")).thenReturn(xmlTag);
//        when(xmlTag.apply(mock(XmlPullParser.class), mock(ProcessFlow.class)))
//                .thenThrow(new IOException("test exception"));
//
//        xmlParser.parseDefinition(VALID_XML_DEFINITION);
//    }

    @Test(expected = XmlPullParserException.class)
    public void parseProcessFlow_invalidXml_throwsIllegalArgumentException() throws XmlPullParserException, IOException {
        xmlParser.parseProcessFlow(INVALID_XML_PROCESS_FLOW);
    }

    @NonNull
    private ProcessFlow getExpectedProcessFlow() {
        ProcessFlow expectedProcessFlow = new ProcessFlow();
        expectedProcessFlow.setName("SampleConfig");
        expectedProcessFlow.setExtension("*.jpg");
        expectedProcessFlow.setGeneral(false);

        expectedProcessFlow.addPluginDefinition("plugin1", "de.swa.bla.Blub");
        expectedProcessFlow.addFusionDefinition("merge1", "de.swa.feat.Bla");
        expectedProcessFlow.addExportDefinition("mpeg7", "de.swa.exp.Bla");
        expectedProcessFlow.addResourceDefinition("upload-dir", "folder", "temp/upload");

        expectedProcessFlow.addParameter("plugin1.lod", "2");
        expectedProcessFlow.addFlowSource("upload-dir");
        expectedProcessFlow.addMmfg(new LinkedHashSet<>(Arrays.asList("plugin1", "plugin2", "plugin3")));
        expectedProcessFlow.addFusion("merge1");
        expectedProcessFlow.addExport("export-dir", "mpeg7");

        return expectedProcessFlow;
    }

}