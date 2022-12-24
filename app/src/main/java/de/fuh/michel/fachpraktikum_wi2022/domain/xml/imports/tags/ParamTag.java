package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ParamTag implements XmlTag {

    public static final String TAG_NAME = "param";

    @Override
    public void apply(XmlPullParser xmlParser, ProcessFlow processFlow) {
        String name = xmlParser.getAttributeValue(null, "name");
        String value = xmlParser.getAttributeValue(null, "value");

        processFlow.addParameter(name, value);
    }
}
