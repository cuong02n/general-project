package com.cuong02n.general.service.hustservice.loginctt;

import com.cuong02n.general.model.HustCttLoginPage;
import com.cuong02n.general.model.LoginHustCttModel;
import com.cuong02n.general.service.hustservice.captcha.HustCttCaptchaService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class LoginCttService {
    final HustCttCaptchaService captchaService;

    @Value("${hust.ctt.url}")
    String hustBaseUrl;

    @Value("${hust.ctt.login.path}")
    String loginPath;

    public void loginCtt(String username, String password) {
        HustCttLoginPage loginPage = getLoginPage();
        byte[] captchaImage = captchaService.getCaptchaImage(captchaService.getCaptchaImageUrl(loginPage));
        String captcha = captchaService.predictCaptcha(captchaImage);

        if (captcha.length() != 5) throw new RuntimeException("Wrong captcha: " + captcha);

        LoginHustCttModel loginModel = new LoginHustCttModel(loginPage.getHtmlContent(), true, username, password, captcha);
        loginCtt(loginModel);
    }

    private void loginCtt(LoginHustCttModel loginModel) {
        // TODO: login
    }

    public HustCttLoginPage getLoginPage() {
        try (
                CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(new HttpGet(hustBaseUrl + loginPath))
        ) {
            if (response.getCode() == 200) {
                String content = EntityUtils.toString(response.getEntity());
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
