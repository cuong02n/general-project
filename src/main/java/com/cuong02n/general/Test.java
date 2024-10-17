package com.cuong02n.general;

import com.cuong02n.general.common.util.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.Arrays;
import java.util.List;

public class Test {
    @RemoteServiceRelativePath("dataService")
    public interface DataService extends RemoteService {
        List<String> getAllRefBookIdFromCourseIds(List<String> courseIds);
    }

    public interface DataServiceAsync {
        void getAllRefBookIdFromCourseIds(List<String> courseIds, AsyncCallback<List<String>> callback);
    }

    private final DataServiceAsync dataService = GWT.create(DataService.class);

    public void sendRequest() {
        List<String> courseIds = Arrays.asList("IT3080", "IT3090", "MI3052", "IT4490", "IT4501");

        dataService.getAllRefBookIdFromCourseIds(courseIds, new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Error: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<String> result) {
                System.out.println("Received ref book IDs: " + result);
            }
        });
    }

    public static void main(String[] args) {
        String test = "[{\"classIdInfo\":\"\",\"placeId\":-1,\"place\":\"TC-412\",\"week\":\"25-32,34-42\",\"date\":-1,\"dayTime\":2,\"day\":5,\"from\":4,\"to\":6,\"teacherId\":-1,\"teacherIds\":[],\"teacherNames\":[],\"lesson\":\"\",\"weeks\":[],\"lessonType\":\"\",\"status\":1,\"createdBy\":-1,\"classId\":-1}]";
        JsonArray array = JsonUtil.toJsonArray(test);
        System.out.println(array);
    }
}
