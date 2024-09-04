package com.cuong02n.general.model;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class LoginHustCttModel {
    static Pattern viewStatePattern = Pattern.compile("__VIEWSTATE\" value=\"(.+?)\"");
    static Pattern viewStateGeneratorPattern = Pattern.compile("__VIEWSTATEGENERATOR\" value=\"(.+?)\"");
    static Pattern eventValidationPattern = Pattern.compile("__EVENTVALIDATION\" value=\"(.+?)\"");
    static

    final String eventTarget = "";
    final String eventArgument = "";
    final String viewState;
    final String viewStateGenerator;
    final String eventValidation;
    final String ctl00$ctl00$TopMenuPane$menuTop = "{&quot;selectedItemIndexPath&quot;:&quot;&quot;,&quot;checkedState&quot;:&quot;&quot;}";
    final String ctl00$ctl00$TopMenuPane$ctl10$menuTop = "{&quot;selectedItemIndexPath&quot;:&quot;&quot;,&quot;checkedState&quot;:&quot;&quot;}";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType = "0";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB0 = "C";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB1 = "U";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$rblAccountType$RB2 = "U";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName$State = "{&quot;rawValue&quot;:&quot;123&quot;,&quot;validationState&quot;:&quot;&quot;}";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName;
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword$State = "{&quot;rawValue&quot;:&quot;321&quot;,&quot;validationState&quot;:&quot;&quot;}";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword;
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB$State = "{&quot;validationState&quot;:&quot;&quot;}";
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB;
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$btLogin = "Đăng nhập";
    /**
     *
     * function getpass(plaintext) {
     * <p>
     *      var key = md5(tbUserName.GetText().toLowerCase() + "." + plaintext);
     * <p>
     *      var pass = encryptByDES(plaintext, key);
     * <p>
     *      return pass;
     * <p>
     * }
     */
    final String ctl00$ctl00$contentPane$MainPanel$MainContent$hfInput = "{&quot;data&quot;:&quot;12|#|user|4|9|1%spass|4|13|1Llg1xa232TM=#&quot;}";//{"data":"12|#|user|4|9|1123pass|4|13|1Llg1xa232TM=#"}
    final String DXScript = "1_9,1_10,1_253,1_21,1_62,1_12,1_13,1_46,1_15,1_22,1_31,1_181,1_184,1_187,1_182,1_201,1_180,1_30";
    final String DXCss = "0_2976,1_66,1_67,0_2981,0_2,0_2890,1_207,0_2895,1_208,1_205,1_204";

    public LoginHustCttModel(String htmlContent, boolean useRegex, String username, String password, String captcha) {
        if (!useRegex) throw new RuntimeException("Not supported without regex");
        ctl00$ctl00$contentPane$MainPanel$MainContent$tbUserName = username;
        ctl00$ctl00$contentPane$MainPanel$MainContent$tbPassword = password;
        ctl00$ctl00$contentPane$MainPanel$MainContent$ASPxCaptcha1$TB = captcha;

        Matcher viewStateMatcher = viewStatePattern.matcher(htmlContent);
        if (viewStateMatcher.find()) viewState = viewStateMatcher.group();
        else throw new RuntimeException("Cannot find viewState in .aspx");

        Matcher viewStateGeneratorMatcher = viewStateGeneratorPattern.matcher(htmlContent);
        if (viewStateGeneratorMatcher.find()) viewStateGenerator = viewStateGeneratorMatcher.group();
        else throw new RuntimeException("Cannot find viewStateGenerator in .aspx");

        Matcher eventValidationMatcher = eventValidationPattern.matcher(htmlContent);
        if (eventValidationMatcher.find()) eventValidation = eventValidationMatcher.group();
        else throw new RuntimeException("Cannot find eventValidation in .aspx");
        // parse parameter
    }
}
