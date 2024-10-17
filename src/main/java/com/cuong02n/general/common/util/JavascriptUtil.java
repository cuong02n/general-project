package com.cuong02n.general.common.util;

import java.io.IOException;

public class JavascriptUtil {

    static final String GWT_CONVERTER_JS_FILE = "src/main/resources/js/converter-gwt.js";

    public static void runConvertGwtScript(String filePath) throws IOException {
        String command = "node " + GWT_CONVERTER_JS_FILE + " " + filePath;
        Runtime.getRuntime().exec(command);
    }
}
