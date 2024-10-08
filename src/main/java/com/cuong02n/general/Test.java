package com.cuong02n.general;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;

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
        new Test().sendRequest();
    }
}
