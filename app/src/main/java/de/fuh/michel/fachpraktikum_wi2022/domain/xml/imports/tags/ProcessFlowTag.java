package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ProcessFlowTag implements XmlTag {

    public static final String TAG_NAME = "process-flow";

    @Override
    public void apply(XmlPullParser xmlParser, ProcessFlow processFlow) {
        processFlow.setName(xmlParser.getAttributeValue(null, "name"));
        processFlow.setExtension(xmlParser.getAttributeValue(null, "extension"));
        processFlow.setGeneral(Boolean.parseBoolean(xmlParser.getAttributeValue(null, "isGeneral")));
    }
}
