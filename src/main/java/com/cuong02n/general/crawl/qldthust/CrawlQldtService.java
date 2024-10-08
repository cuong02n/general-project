package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.common.util.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.cuong02n.general.common.util.GsonUtil.toJsonArray;

@Service
public class CrawlQldtService {
    static final String baseUrl = "https://qldt.hust.edu.vn";
    static final String urlPath = "/soicteducationstudent/data";

    /**
     * should use format
     */
    private static final String payloadPattern = "7|0|10|https://qldt.hust.edu.vn/soicteducationstudent/|5C6CDB13D0FD25B266F3C36FA7FF6ED9|com.soict.edu.core.client.DataService|getCourseMembers|java.lang.Long/4227064769|java.lang.String/2004016611|java.util.List|%s|java.util.Arrays$ArrayList/2507071751|20241|1|2|3|4|3|5|6|7|5|TXbrzIAAA|8|9|1|6|10|";

    Map<String, String> headers = Map.of(
            "x-gwt-permutation", "D1",
            "content-type", "text/x-gwt-rpc; charset=UTF-8"
    );

    public void getStudentEducationData(String studentId) {
        String payload = String.format(payloadPattern, studentId);
        System.out.println(toJsonArray(HttpUtil.post(baseUrl + urlPath, headers, payload).substring(4)));
    }

}
