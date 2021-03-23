package com.twitter.reader;

public class HtmlGenerator {
    private static final String htmlTop = "<!DOCTYPE html><html><head></head><body>";
    private static final String htmlBottom = "</body></html>";
    public static final String script = "<script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";
    private static final String width = "data-width=\"220\"";

    public static String wrapWithDiv(String html){
        return "<div>" + html + "</div>";
    }

    public static String wrapWithHtmlBoilerplate(String content){
        return htmlTop + content + htmlBottom;
    }

    public static String remove(String removeStr, String content){
        return content.replace(removeStr, "");
    }

    public static String removeScriptAndWidth(String content){
        return remove(width, remove(script, content));
    }

    public static String prepareContent(String content){
        return wrapWithHtmlBoilerplate(removeScriptAndWidth(content));
    }
}
