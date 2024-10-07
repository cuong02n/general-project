package com.cuong02n.general.service.hustservice.captcha;

import com.cuong02n.general.model.HustCttLoginPage;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class HustCttCaptchaService {
    private static final Logger log = LoggerFactory.getLogger(HustCttCaptchaService.class);
    @Value("${hust.ctt.url}")
    String hustBaseUrl;

    @Value("${hust.ctt.login.path}")
    String loginPath;

    @Value("${hust.ctt.captcha.start-index-dxcache-src}")
    int startIndexDxCacheSrc;

    @Value("${hust.ctt.captcha.end-index-dxcache-src}")
    int endIndexDxCacheSrc;

    @Value("${hust.ctt.captcha.root-folder-saved-image}")
    String rootFolderSavedImage;

    //    /**
//     * Not use.
//     * @param time: quantity of Captcha image save to server
//     */
//    public void getAndSaveMultipleCaptchaImage(int time) {
//        for (int i = 0; i < time; i++) {
//            String captchaUrl = getCaptchaImageUrl();
//            System.out.println("Fetching: " + captchaUrl);
//            getAndSaveCaptchaImage(captchaUrl);
//        }
//    }
//
    public byte[] getCaptchaImage(String url) {
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
        ) {
            return response.getEntity().getContent().readAllBytes();
        } catch (Exception e) {
            log.error("Cannot download image {}", url, e);
            throw new RuntimeException("Cannot download image: " + url, e);
        }
    }

    public String getCaptchaImageUrl(HustCttLoginPage loginPage) {
        String pathGetCaptchaImage = loginPage.getHtmlContent().substring(startIndexDxCacheSrc, endIndexDxCacheSrc);

        Pattern pattern = Pattern.compile("(DXB\\.axd\\?DXCache=.+?)\"", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(loginPage.getHtmlContent());
        if (matcher.find()) System.out.println(matcher.group(0));
        return hustBaseUrl + pathGetCaptchaImage;
    }


    /**
     * @return the HTML value of login page, as a String.
     */
    public String predictCaptcha(byte[] imageByte) {
        return "45631";
    }
}
