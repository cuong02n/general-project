package com.cuong02n.general;

import com.cuong02n.general.crawl.qldthust.CrawlQldtMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneralApplication {

    public static void main(String[] args) throws Exception {
        CrawlQldtMain.crawl(20241);
    }
}
