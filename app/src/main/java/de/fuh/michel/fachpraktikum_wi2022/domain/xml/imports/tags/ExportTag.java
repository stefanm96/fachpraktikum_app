package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class ExportTag implements XmlTag {

    public static final String TAG_NAME = "export";

    @Override
    public void apply(XmlPullParser xmlParser, ProcessFlow processFlow) {
        String target = xmlParser.getAttributeValue(null, "target");
        String exporter = xmlParser.getAttributeValue(null, "exporter");
        
        processFlow.addExport(target, exporter);
    }
}
