package com.cuong02n.general.crawl.qldthust;

import com.cuong02n.general.common.util.HttpUtil;
import com.cuong02n.general.common.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CrawlQldtService {
    static final String baseUrl = "https://qldt.hust.edu.vn";
    static final String urlPath = "/soicteducationstudent/data";
    static final HashMap<String, String> headers = new HashMap<>();

    static final String payloadPattern = "7|0|10|https://qldt.hust.edu.vn/soicteducationstudent/|5C6CDB13D0FD25B266F3C36FA7FF6ED9|com.soict.edu.core.client.DataService|getCourseMembers|java.lang.Long/4227064769|java.lang.String/2004016611|java.util.List|%s|java.util.Arrays$ArrayList/2507071751|%d|1|2|3|4|3|5|6|7|5|TXbrzIAAA|8|9|1|6|10|";

    static {
        headers.put("content-type", "text/x-gwt-rpc; charset=UTF-8");
    }

    public static String getStudentEducationData(String studentId, int semester) {
        String payload = String.format(payloadPattern, studentId, semester);
        headers.put("x-gwt-permutation", StringUtil.randomHexString(32));
        return HttpUtil.post(baseUrl + urlPath, headers, payload);
    }


    public static void main(String[] args) {

    }

}
