package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.XmlTag;
import de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags.XmlTagFactory;
import de.fuh.michel.fachpraktikum_wi2022.exception.ImportDefinitionFailedException;
import de.fuh.michel.fachpraktikum_wi2022.model.Definition;
import de.fuh.michel.fachpraktikum_wi2022.model.ProcessFlow;

public class XmlParser {

    private static final String TAG = "XmlParser";

    private final XmlTagFactory xmlTagFactory;
    private final XmlPullParserFactory factory;

    public XmlParser(XmlTagFactory xmlTagFactory, XmlPullParserFactory factory) {
        this.xmlTagFactory = xmlTagFactory;
        this.factory = factory;
    }

    public ProcessFlow parseProcessFlow(String xmlProcessFlow) throws XmlPullParserException, IOException {
        XmlPullParser xmlParser = factory.newPullParser();
        try (StringReader reader = new StringReader(xmlProcessFlow)) {
            xmlParser.setInput(reader);

            int eventType = xmlParser.getEventType();

            if (eventType != XmlPullParser.START_DOCUMENT) {
                throw new IllegalArgumentException("Invalid XML provided!");
            }

            ProcessFlow processFlow = new ProcessFlow();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = xmlParser.getName();
                    Log.i(TAG, "Start tag " + tagName);

                    XmlTag xmlTag = xmlTagFactory.getXmlTag(tagName);
                    if (xmlTag != null) {
                        xmlTag.apply(xmlParser, processFlow);
                    }
                }

                eventType = xmlParser.next();
            }

            Log.i(TAG, "Process-Flow: " + processFlow);
            return processFlow;
        }
    }

    public Definition parseDefinition(String content) {
        try {
            XmlPullParser xmlParser = factory.newPullParser();
            try (StringReader reader = new StringReader(content)) {
                xmlParser.setInput(reader);

                int eventType = xmlParser.getEventType();

                if (eventType != XmlPullParser.START_DOCUMENT) {
                    throw new IllegalArgumentException("Invalid XML Provided!");
                }

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        String tagName = xmlParser.getName();
                        Log.i(TAG, "Start tag " + tagName);

                        XmlTag xmlTag = xmlTagFactory.getXmlTag(tagName);
                        if (xmlTag != null) {
                            ProcessFlow processFlow = new ProcessFlow();
                            xmlTag.apply(xmlParser, processFlow);
                            return processFlow.getDefinitions().get(0);
                        }
                    }

                    eventType = xmlParser.next();
                }
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            throw new ImportDefinitionFailedException();
        }

        throw new IllegalArgumentException("Definition not found!");
    }
}
