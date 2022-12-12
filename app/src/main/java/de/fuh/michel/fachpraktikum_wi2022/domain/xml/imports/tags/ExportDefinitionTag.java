package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ExportDefinitionTag implements XmlTag {

    public static final String TAG_NAME = "export-definition";

    @Override
    public void apply(ProcessFlow processFlow, XmlPullParser xmlParser) {
        String name = xmlParser.getAttributeValue(null, "name");
        String clazz = xmlParser.getAttributeValue(null, "class");

        processFlow.addExportDefinition(name, clazz);
    }
}
