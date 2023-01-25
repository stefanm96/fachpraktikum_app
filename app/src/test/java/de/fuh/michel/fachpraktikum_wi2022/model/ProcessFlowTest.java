package de.fuh.michel.fachpraktikum_wi2022.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Export;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.FlowSource;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Fusion;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Mmfg;
import de.fuh.michel.fachpraktikum_wi2022.model.configurationelement.Parameter;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ExportDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.FusionDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.PluginDefinition;
import de.fuh.michel.fachpraktikum_wi2022.model.definition.ResourceDefinition;

public class ProcessFlowTest {

    private ProcessFlow processFlow;

    @Before
    public void setUp() {
        processFlow = new ProcessFlow();
    }

    @Test
    public void addPluginDefinition_validInput_addsDefinitionToList() {
        processFlow.addPluginDefinition("name", "clazz");

        assertEquals(1, processFlow.getDefinitions().size());
        assertTrue(processFlow.getDefinitions().get(0) instanceof PluginDefinition);
        assertEquals("name", processFlow.getDefinitions().get(0).getName());
        assertEquals("clazz", ((PluginDefinition) processFlow.getDefinitions().get(0)).getClazz());
    }

    @Test
    public void addFusionDefinition_validInput_addsDefinitionToList() {
        processFlow.addFusionDefinition("name", "clazz");

        assertEquals(1, processFlow.getDefinitions().size());
        assertTrue(processFlow.getDefinitions().get(0) instanceof FusionDefinition);
        assertEquals("name", processFlow.getDefinitions().get(0).getName());
        assertEquals("clazz", ((FusionDefinition) processFlow.getDefinitions().get(0)).getClazz());
    }

    @Test
    public void addExportDefinition_validInput_addsDefinitionToList() {
        processFlow.addExportDefinition("name", "clazz");

        assertEquals(1, processFlow.getDefinitions().size());
        assertTrue(processFlow.getDefinitions().get(0) instanceof ExportDefinition);
        assertEquals("name", processFlow.getDefinitions().get(0).getName());
        assertEquals("clazz", ((ExportDefinition) processFlow.getDefinitions().get(0)).getClazz());
    }

    @Test
    public void addResourceDefinition_validInput_addsDefinitionToList() {
        processFlow.addResourceDefinition("name", "type", "location");

        assertEquals(1, processFlow.getDefinitions().size());
        assertTrue(processFlow.getDefinitions().get(0) instanceof ResourceDefinition);
        assertEquals("name", processFlow.getDefinitions().get(0).getName());
        assertEquals("type", ((ResourceDefinition) processFlow.getDefinitions().get(0)).getType());
        assertEquals("location", ((ResourceDefinition) processFlow.getDefinitions().get(0)).getLocation());
    }

    @Test
    public void addParameter_validInput_addsConfigurationElementToList() {
        processFlow.addParameter("name", "value");

        assertEquals(1, processFlow.getConfigurationElements().size());
        assertTrue(processFlow.getConfigurationElements().get(0) instanceof Parameter);
        assertEquals("name", ((Parameter) processFlow.getConfigurationElements().get(0)).getName());
        assertEquals("value", ((Parameter) processFlow.getConfigurationElements().get(0)).getValue());
    }

    @Test
    public void addFlowSource_validInput_addsConfigurationElementToList() {
        processFlow.addFlowSource("name");

        assertEquals(1, processFlow.getConfigurationElements().size());
        assertTrue(processFlow.getConfigurationElements().get(0) instanceof FlowSource);
        assertEquals("name", ((FlowSource) processFlow.getConfigurationElements().get(0)).getName());
    }

    @Test
    public void addMmfg_validInput_addsConfigurationElementToList() {
        Set<String> processors = new LinkedHashSet<>(Arrays.asList("processor1", "processor2"));
        processFlow.addMmfg(processors);

        assertEquals(1, processFlow.getConfigurationElements().size());
        assertTrue(processFlow.getConfigurationElements().get(0) instanceof Mmfg);
        assertEquals(processors, ((Mmfg) processFlow.getConfigurationElements().get(0)).getProcessor());
    }

    @Test
    public void addFusion_validInput_addsConfigurationElementToList() {
        processFlow.addFusion("processor");

        assertEquals(1, processFlow.getConfigurationElements().size());
        assertTrue(processFlow.getConfigurationElements().get(0) instanceof Fusion);
        assertEquals("processor", ((Fusion) processFlow.getConfigurationElements().get(0)).getProcessor());
    }

    @Test
    public void addExport_validInput_addsConfigurationElementToList() {
        processFlow.addExport("target", "exporter");

        assertEquals(1, processFlow.getConfigurationElements().size());
        assertTrue(processFlow.getConfigurationElements().get(0) instanceof Export);
        assertEquals("target", ((Export) processFlow.getConfigurationElements().get(0)).getTarget());
        assertEquals("exporter", ((Export) processFlow.getConfigurationElements().get(0)).getExporter());
    }

    @Test
    public void getName_validInput_returnsName() {
        processFlow.setName("name");
        assertEquals("name", processFlow.getName());
    }

    @Test
    public void getExtension_validInput_returnsExtension() {
        processFlow.setExtension("extension");
        assertEquals("extension", processFlow.getExtension());
    }

    @Test
    public void isGeneral_validInput_returnsIsGeneral() {
        processFlow.setGeneral(true);
        assertTrue(processFlow.isGeneral());
    }

    @Test
    public void getDefinitions_validInput_returnsDefinitions() {
        List<Definition> definitions = Arrays.asList(new PluginDefinition("name1", "clazz1"), new FusionDefinition("name2", "clazz2"));
        processFlow.setDefinitions(definitions);
        assertEquals(definitions, processFlow.getDefinitions());
    }

    @Test
    public void getConfigurationElements_validInput_returnsConfigurationElements() {
        List<ConfigurationElement> configurationElements = Arrays.asList(new Parameter("name1", "value1"), new FlowSource("name2"));
        processFlow.setConfigurationElements(configurationElements);
        assertEquals(configurationElements, processFlow.getConfigurationElements());
    }
}