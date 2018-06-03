package com.sealight.osram.excel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zhangjj
 * @create 2018-06-02 16:10
 **/
@Setter
@Getter
@ToString
public class LampExcelBean {

    private String manufacturer;

    private String model;

    private String type;

    private Map<String, String> headLampMap ;
    private Map<String, String> innerLampmap ;
    private Map<String, String> outerLampMap;


}
