package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import org.xmlpull.v1.XmlPullParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class FusionTag implements XmlTag {

    public static final String TAG_NAME = "fusion";

    @Override
    public void apply(ProcessFlow processFlow, XmlPullParser xmlParser) {
        String processor = xmlParser.getAttributeValue(null, "processor");

        processFlow.addFusion(processor);
    }
}
