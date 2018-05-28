package com.sealight.osram.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目常量配置
 *
 * @author zhangjj
 * @create 2017-08-22 15:07
 **/
public class Constant {


    //生成的excel表存放地址
    public final static String EXCEL_PATH = "D:/data/osram/xls/";


    //获取所有品牌如 BMW等
    public final static String ALL_MANUFACTURER_URL = "https://am-application.osram.info/en/getAllManufacturer/2.json";

    //获取品牌的所有系列(型号),如 BMW的 x5 x6等
    //占位符为品牌的id,如BMW的品牌id为6;
    public final static String ALL_MODEL_URL = "https://am-application.osram.info/en/getAllModel/%s/1.json";

    //根据车型号id查所有灯
    //占位符为型号的id,如X5的型号id为7552
    public final static String ALL_TYPE_URL = "https://am-application.osram.info/en/getAllType/%s.json";

    //根据type_id获取对应下的所有灯（包括前 后 类 三种灯）,占位符为type_id
    public final static String ALL_LIGHT_URL = "https://am-application.osram.info/en/getAllUse/%s.json";

    //根据 use_id, type_id获取灯的工艺类型，第一个点位符为use_id 第二个为type_id
    public final static String ALL_TECHNOLOGY_URL = "https://am-application.osram.info/en/getAllTechnology/%s/%s.json";

    //根据 use_id, type_id， technology_id 获取 灯详情; 第一个点位符为use_id 第二个为type_id 第三个为technology_id
    public final static String LAMPS_BY_USE_URL = "https://am-application.osram.info/en/getLampsByUse/%s/%s/%s.json";

    //前灯pos_id集合
    public final static List<String> FRONT_POS_ID_LIST = new ArrayList<String>() {{
        add("409");
    }};

    //后灯pos_id集合
    public final static List<String> REAR_POS_ID_LIST = new ArrayList<String>() {{
        add("410");
    }};

    //内类pos_id集合
    public final static List<String> INTERNAL_POS_ID_LIST = new ArrayList<String>() {{
        add("411");
    }};

}
