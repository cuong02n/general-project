package com.cuong02n.general.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class HustCttLoginPage {
    String aspNetSessionIdCookie;
    String htmlContent;
}
