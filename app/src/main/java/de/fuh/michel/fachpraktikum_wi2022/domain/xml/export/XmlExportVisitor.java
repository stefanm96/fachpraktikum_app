package de.fuh.michel.fachpraktikum_wi2022.domain.xml.export;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.ExportDefinitionTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.ExportTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.FlowSourceTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.FusionDefinitionTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.FusionTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.MmfgTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.ParamTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.PluginDefinitionTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.ProcessFlowTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.ResourceDefinitionTag;
import de.fuh.michel.fachpraktikum_wi2022.model.ConfigurationElement;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
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
import de.fuh.michel.fachpraktikum_wi2022.model.visitor.Visitor;

public class XmlExportVisitor implements Visitor {
    private final XmlSerializer xmlSerializer;

    public XmlExportVisitor(XmlSerializer xmlSerializer) {
        this.xmlSerializer = xmlSerializer;
    }

    @Override
    public void visit(ProcessFlow processFlow) {
        startDocument();
        startTag(ProcessFlowTag.TAG_NAME);

        for (Definition definition : processFlow.getDefinitions()) {
            definition.accept(this);
        }

        for (ConfigurationElement configurationElement : processFlow.getConfigurationElements()) {
            configurationElement.accept(this);
        }

        endTag(ProcessFlowTag.TAG_NAME);
        endDocument();
    }

    @Override
    public void visit(PluginDefinition pluginDefinition) {
        startTag(PluginDefinitionTag.TAG_NAME);
        attribute("name", pluginDefinition.getName());
        attribute("class", pluginDefinition.getClazz());
        endTag(PluginDefinitionTag.TAG_NAME);
    }

    @Override
    public void visit(FusionDefinition fusionDefinition) {
        startTag(FusionDefinitionTag.TAG_NAME);
        attribute("name", fusionDefinition.getName());
        attribute("class", fusionDefinition.getClazz());
        endTag(FusionDefinitionTag.TAG_NAME);
    }

    @Override
    public void visit(ExportDefinition exportDefinition) {
        startTag(ExportDefinitionTag.TAG_NAME);
        attribute("name", exportDefinition.getName());
        attribute("class", exportDefinition.getClazz());
        endTag(ExportDefinitionTag.TAG_NAME);
    }

    @Override
    public void visit(ResourceDefinition resourceDefinition) {
        startTag(ResourceDefinitionTag.TAG_NAME);
        attribute("name", resourceDefinition.getName());
        attribute("type", resourceDefinition.getType());
        attribute("location", resourceDefinition.getLocation());
        endTag(ResourceDefinitionTag.TAG_NAME);
    }

    @Override
    public void visit(Parameter parameter) {
        startTag(ParamTag.TAG_NAME);
        attribute("name", parameter.getName());
        attribute("value", parameter.getValue());
        endTag(ParamTag.TAG_NAME);
    }

    @Override
    public void visit(FlowSource flowSource) {
        startTag(FlowSourceTag.TAG_NAME);
        attribute("name", flowSource.getName());
        endTag(FlowSourceTag.TAG_NAME);
    }

    @Override
    public void visit(Fusion fusion) {
        startTag(FusionTag.TAG_NAME);
        attribute("processor", fusion.getProcessor());
        endTag(FusionTag.TAG_NAME);
    }

    @Override
    public void visit(Mmfg mmfg) {
        startTag(MmfgTag.TAG_NAME);
        String processor = String.join(", ", mmfg.getProcessor());
        attribute("processor", processor);
        endTag(MmfgTag.TAG_NAME);
    }

    @Override
    public void visit(Export export) {
        startTag(ExportTag.TAG_NAME);
        attribute("target", export.getTarget());

        if (export.getExporter() != null) {
            attribute("exporter", export.getExporter());
        }

        endTag(ExportTag.TAG_NAME);
    }

    private void startDocument() {
        try {
            xmlSerializer.startDocument("UTF-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startTag(String tagName) {
        try {
            xmlSerializer.startTag("", tagName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attribute(String name, String value) {
        try {
            xmlSerializer.attribute("", name, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void endTag(String tagName) {
        try {
            xmlSerializer.endTag("", tagName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void endDocument() {
        try {
            xmlSerializer.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
