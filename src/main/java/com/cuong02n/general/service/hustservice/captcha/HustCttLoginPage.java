package com.cuong02n.general.service.hustservice.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HustCttLoginPage {
    private String aspNetSessionIdCookie;
    private String htmlContent;
}
