package com.cuong02n.general.service.hustservice.captcha;

import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

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

    public void getAndSaveMultipleCaptchaImage(int time) {
        for (int i = 0; i < time; i++) {
            String captchaUrl = getCaptchaImageUrl();
            System.out.println("Fetching: " + captchaUrl);
            getAndSaveCaptchaImage(captchaUrl);
        }
    }

    public void getAndSaveCaptchaImage(String url) {
        File savedImageFile = new File(rootFolderSavedImage + url.substring(url.lastIndexOf("=") + 1) + ".png");
        if (savedImageFile.exists()) throw new RuntimeException("Captcha exist: " + url);
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(new HttpGet(url));
                FileOutputStream fos = new FileOutputStream(savedImageFile);
        ) {
            byte[] imageByte = response.getEntity().getContent().readAllBytes();
            fos.write(imageByte);
        } catch (Exception e) {
            log.error("Cannot download image {}", url, e);
        }
    }

    public String getCaptchaImageUrl() {
        HustCttLoginPage loginPage = getLoginPage();
        String pathGetCaptchaImage = loginPage.getHtmlContent().substring(loginPage.getHtmlContent().indexOf("src=\"/DXB.axd?DXCache") + 6, endIndexDxCacheSrc);
        return hustBaseUrl + pathGetCaptchaImage;
    }

    /**
     * @return the HTML value of login page, as a String.
     */
    public HustCttLoginPage getLoginPage() {
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(new HttpGet(hustBaseUrl + loginPath))
        ) {
//            System.out.println("Time for sending Get request: " + (System.currentTimeMillis() - t) + " ms");
            if (response.getCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
//                System.out.println("Time for parse content to String: " + (System.currentTimeMillis() - t) + " ms");
                Header[] cookies = response.getHeaders("Set-Cookie");
                for (Header cookie : cookies) {
                    if (cookie.getValue().startsWith("ASP.NET_SessionId"))
                        return new HustCttLoginPage(cookie.getValue().split(";")[0], content);
                }
            }
            throw new RuntimeException("Cannot find [ASP.NET_SessionId] in header, current cookie: " + Arrays.toString(response.getHeaders("Set-Cookie")));
        } catch (Exception e) {
            log.error("Error when getting login page");
            throw new RuntimeException(e);
        }
    }

}
