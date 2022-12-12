package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class MmfgTag implements XmlTag {

    public static final String TAG_NAME = "mmfg";

    @Override
    public void apply(ProcessFlow processFlow, XmlPullParser xmlParser) {
        String processorString = xmlParser.getAttributeValue(null, "processor");

        Log.i(TAG_NAME, processorString);

        String[] split = processorString.split("\\s*,\\s*");
        Set<String> processor = new LinkedHashSet<>(Arrays.asList(split));

        processFlow.addMmfg(processor);
    }
}
