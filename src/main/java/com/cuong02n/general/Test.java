package com.cuong02n.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String html = "src=\"/DXB.axd?DXCache=8b2af093-7683-093a-1f59-0cc94eff5ed8\" alt=";

        // Compile the pattern
        Pattern pattern = Pattern.compile("DXB\\.axd\\?DXCache=.+?\"");

        // Create a matcher
        Matcher matcher = pattern.matcher(html);
        if (matcher.find())
            System.out.println(matcher.group());
        Pattern.compile("(DXB\\.axd\\?DXCache=.+?)\"", Pattern.MULTILINE).matcher(html).group(0);
    }
}
