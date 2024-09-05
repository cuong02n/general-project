package com.cuong02n.general.service.hustservice.loginctt;

import com.cuong02n.general.model.HustCttLoginPage;
import com.cuong02n.general.model.LoginHustCttModel;
import com.cuong02n.general.service.hustservice.captcha.HustCttCaptchaService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
public class LoginCttService {
    private static final Logger log = LoggerFactory.getLogger(LoginCttService.class);
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
        LoginHustCttModel loginModel = new LoginHustCttModel(loginPage.getHtmlContent(), true, username, password, captcha, loginPage.getAspNetSessionIdCookie());
        loginCtt(loginModel);
    }

    private void loginCtt(LoginHustCttModel loginModel) {
        // body Form-Url-Encoded
        List<BasicHeader> form = List.of(
                new BasicHeader("__EVENTTARGET", loginModel.getEventTarget()),
                new BasicHeader("__EVENTARGUMENT", loginModel.getEventArgument()),
                new BasicHeader("__VIEWSTATE", loginModel.getViewState()),
                new BasicHeader("__VIEWSTATEGENERATOR", loginModel.getViewStateGenerator()),
                new BasicHeader("__EVENTVALIDATION", loginModel.getEventValidation()),
                new BasicHeader("ctl00$ctl00$TopMenuPane$menuTop", loginModel.getCtl00$ctl00$TopMenuPane$menuTop()),
                new BasicHeader("ctl00$ctl00$TopMenuPane$ctl10$menuTop", loginModel.getCtl00$ctl00$TopMenuPane$ctl10$menuTop()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB0", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB0()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB1", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB1()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB2", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB2()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName$State", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$tbUserName$State()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$tbUserName()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword$State", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$tbPassword$State()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$tbPassword()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB$State", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB$State()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$btLogin", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$btLogin()),
                new BasicHeader("ctl00$ctl00$contentPane$MainPanel$MainContent$hfInput", loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$hfInput()),
                new BasicHeader("DXScript", loginModel.getDXScript()),
                new BasicHeader("DXCss", loginModel.getDXCss())
        );

        // Cookie
        CookieHandler.setDefault(new CookieManager());

        HttpCookie sessionCookie = new HttpCookie("ASP.NET_SessionId", loginModel.getAspSessionId());
        sessionCookie.setPath("/");
        sessionCookie.setDomain("ctt-sis.hust.edu.vn");
        sessionCookie.setHttpOnly(true);
        sessionCookie.setVersion(0);

//        HttpClient httpClient = HttpClient.newBuilder().cookieHandler(CookieHandler.getDefault()).build() ;
        try (
                CloseableHttpClient client = HttpClients.createDefault();
                UrlEncodedFormEntity urlEncoded = new UrlEncodedFormEntity(form, UTF_8);
        ) {
            HttpPost postRequest = new HttpPost(hustBaseUrl + loginPath);
            postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
            postRequest.setEntity(urlEncoded);
            postRequest.setHeader("Cookie", "ASP.NET_SessionId=" + loginModel.getAspSessionId());
            postRequest.setHeader("Accept", "*/*");
            postRequest.setHeader("accept-encoding","gzip, deflate, br, zstd");
            postRequest.setHeader("upgrade-insecure-requests","1");
            CloseableHttpResponse response = client.execute(postRequest);
            log.info("{}", Arrays.toString(postRequest.getHeaders()));
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            log.error("Cannot login: {} {}", loginModel, e.getMessage());
            throw new RuntimeException("Cannot login: " + loginModel.getCtl00$ctl00$contentPane$MainPanel$MainContent$tbUserName(), e);
        }
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
                        return new HustCttLoginPage(cookie.getValue().split(";")[0].split("=")[1], content);
                }
            }
            throw new RuntimeException("Cannot find [ASP.NET_SessionId] in header, current cookie: " + Arrays.toString(response.getHeaders("Set-Cookie")));
        } catch (Exception e) {
            log.error("Error when getting login page");
            throw new RuntimeException(e);
        }
    }
}
