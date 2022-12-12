package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ResourceDefinitionTag implements XmlTag {

    public static final String TAG_NAME = "resource-definition";

    @Override
    public void apply(ProcessFlow processFlow, XmlPullParser xmlParser) {
        String name = xmlParser.getAttributeValue(null, "name");
        String type = xmlParser.getAttributeValue(null, "type");
        String location = xmlParser.getAttributeValue(null, "location");

        processFlow.addResourceDefinition(name, type, location);
    }
}
