package com.cuong02n.general.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtil {
    private static final Gson gson = new Gson();
    private static final Gson gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static JsonArray toJsonArray(String src) {
        return gson.fromJson(src, JsonArray.class);
    }

    public static JsonObject toJsonObject(String src) {
        return gson.fromJson(src, JsonObject.class);
    }

    public static String toString(Object src) {
        return gson.toJson(src);
    }

    public static JsonArray loadJsonArray(File f) {
        try {
            return gson.fromJson(new FileReader(f), JsonArray.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + f.getAbsolutePath());
            return null;
        }
    }

    public static JsonObject loadJsonObject(File f) {
        try {
            return gson.fromJson(new FileReader(f), JsonObject.class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + f.getAbsolutePath());
            return null;
        }
    }

}
