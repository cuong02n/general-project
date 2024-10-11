package com.cuong02n.general;

import com.cuong02n.general.crawl.qldthust.CrawlQldtMain;
import com.cuong02n.general.crawl.qldthust.CrawlQldtService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneralApplication {

    public static void main(String[] args) throws Exception {
        var context = SpringApplication.run(GeneralApplication.class, args);
        CrawlQldtMain.crawl(context);
        // crawl from 2018

    }

}
