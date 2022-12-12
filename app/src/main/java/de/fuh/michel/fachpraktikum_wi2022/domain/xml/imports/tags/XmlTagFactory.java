package de.fuh.michel.fachpraktikum_wi2022.domain.xml.imports.tags;

import java.util.HashMap;
import java.util.Map;

public class XmlTagFactory {

    private static final String TAG = "XmlTagFactory";

    private final Map<String, XmlTag> xmlTagMap;

    public XmlTagFactory() {
        xmlTagMap = new HashMap<>();
        xmlTagMap.put(PluginDefinitionTag.TAG_NAME, new PluginDefinitionTag());
        xmlTagMap.put(FusionDefinitionTag.TAG_NAME, new FusionDefinitionTag());
        xmlTagMap.put(ProcessFlowTag.TAG_NAME, new ProcessFlowTag());
        xmlTagMap.put(ExportDefinitionTag.TAG_NAME, new ExportDefinitionTag());
        xmlTagMap.put(ResourceDefinitionTag.TAG_NAME, new ResourceDefinitionTag());

        xmlTagMap.put(ParamTag.TAG_NAME, new ParamTag());
        xmlTagMap.put(FlowSourceTag.TAG_NAME, new FlowSourceTag());
        xmlTagMap.put(MmfgTag.TAG_NAME, new MmfgTag());
        xmlTagMap.put(FusionTag.TAG_NAME, new FusionTag());
        xmlTagMap.put(ExportTag.TAG_NAME, new ExportTag());
    }

    public XmlTag getXmlTag(String xmlTagName) {
        XmlTag xmlTag = xmlTagMap.get(xmlTagName);

        if (xmlTag == null) {
            throw new IllegalArgumentException("XML Tag not found: " + xmlTagName);
        }

        return xmlTag;
    }

}
