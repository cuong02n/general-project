package com.cuong02n.general.common.util;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.util.Map;

public class HttpUtil {
    public static String post(String url, Map<String, String> header, String content) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(content));
        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(httpPost);
        ) {
            return new String(response.getEntity().getContent().readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException("Cannot execute post method: " + url, e);
        }
    }
}
