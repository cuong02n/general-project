package com.cuong02n.general.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GsonUtil {
    private static final Gson gson = new Gson();
    private static final Gson gsonExpose = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public static JsonArray toJsonArray(String src){
        return gson.fromJson(src, JsonArray.class);
    }
    public static JsonObject toJsonObject(String src){
        return gson.fromJson(src, JsonObject.class);
    }

    public static String toString(Object src){
        return gson.toJson(src);
    }
}
