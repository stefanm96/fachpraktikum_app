package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class PluginDefinitionTag implements XmlTag {

    public static final String TAG_NAME = "plugin-definition";

    @Override
    public void apply(XmlPullParser xmlParser, ProcessFlow processFlow) {
        String name = xmlParser.getAttributeValue(null, "name");
        String clazz = xmlParser.getAttributeValue(null, "class");

        processFlow.addPluginDefinition(name, clazz);
    }
}
