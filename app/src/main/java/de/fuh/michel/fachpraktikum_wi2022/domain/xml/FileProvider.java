package de.fuh.michel.fachpraktikum_wi2022.domain.xml;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileProvider {

    private static final String TAG = "FileProvider";

    private final File filesDir;

    public FileProvider(File filesDir) {
        this.filesDir = filesDir;
    }

    public String getXmlContent(String fileName) throws IOException {
        Log.i(TAG, "Getting xml resource by filename: " + fileName);

        String[] list = filesDir.list();

        for (String s : list) {
            Log.i(TAG, "File: " + s);
        }

        //TODO: implement

        return sampleConfig;
    }

    private static final String sampleConfig = "<process-flow name=\"ImageImport\" extension=\"*.jpg\" isGeneral=\"false\">\n" +
            "\n" +
            "<plugin-definition name=\"plugin1\" class=\"de.swa.bla.Blub\"/>\n" +
            "<plugin-definition name=\"plugin2\" class=\"de.swa.bla.Blub\"/>\n" +
            "<plugin-definition name=\"plugin3\" class=\"de.swa.bla.Blub\"/>\n" +
            "<plugin-definition name=\"plugin4\" class=\"de.swa.bla.Blub\"/>\n" +
            "<plugin-definition name=\"plugin5\" class=\"de.swa.bla.Blub\"/>\n" +
            "\n" +
            "<fusion-definition name=\"merge1\" class=\"de.swa.feat.Bla\"/>\n" +
            "<fusion-definition name=\"merge2\" class=\"de.swa.feat.Bla\"/>\n" +
            "<fusion-definition name=\"merge3\" class=\"de.swa.feat.Bla\"/>\n" +
            "\n" +
            "<export-definition name=\"mpeg7\" class=\"de.swa.exp.Bla\"/>\n" +
            "<export-definition name=\"xml\" class=\"de.swa.exp.Bla\"/>\n" +
            "<export-definition name=\"graphml\" class=\"de.swa.exp.Bla\"/>\n" +
            "\n" +
            "<resource-definition name=\"upload-dir\" type=\"folder\" location=\"temp/upload\"/>\n" +
            "<resource-definition name=\"target-dir\" type=\"folder\" location=\"temp/target\"/>\n" +
            "<resource-definition name=\"export-dir\" type=\"folder\" location=\"temp/export\"/>\n" +
            "<resource-definition name=\"facebook\" type=\"url\" location=\"http://www....\"/>\n" +
            "\n" +
            "<param name=\"plugin1.lod\" value=\"2\"/>\n" +
            "<param name=\"plugin2.output\" value=\"temp\"/>\n" +
            "\n" +
            "<flow-source name=\"upload-dir\"/>\n" +
            "<mmfg processor=\"plugin1, plugin2, plugin3\"/>\n" +
            "<fusion processor=\"merge1\"/>\n" +
            "<param name=\"plugin5.source\" value=\"5\"/>\n" +
            "<mmfg processor=\"plugin5\"/>\n" +
            "<fusion processor=\"merge3\"/>\n" +
            "\n" +
            "<export target=\"export-dir\" exporter=\"mpeg7\"/>\n" +
            "<export target=\"collection\"/>\n" +
            "\n" +
            "</process-flow>";
}
