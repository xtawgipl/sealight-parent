package com.sealight.osram.constants;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目常量配置
 *
 * @author zhangjj
 * @create 2017-08-22 15:07
 **/
public class Constant {


    //主页地址：http://www.jwspeaker.com/ymm/

    //生成的excel表存放地址
    public final static String EXCEL_PATH = "D:/data/jwspeaker/xls/";


    /**
     * 所有车
     */
    public final static Map<String, String> BRAND_DICT = new HashMap<String, String>() {{
        put("BMW", "BMW");
        put("Ducati", "Ducati");
        put("Harley Davidson", "Harley Davidson");
        put("Indian", "Indian");
        put("Moto Guzzi", "Moto Guzzi");
        put("Triumph", "Triumph");
        put("Victory", "Victory");
    }};

    /**
     * document.getElementById('yeardiv').style.display = 'none';document.getElementById('distdiv').style.display = 'none';document.getElementById('regdiv').style.display = 'none';document.getElementById('buylink').style.display = 'none';document.getElementById('modeldiv').style.display = 'block';document.getElementById('tables').innerHTML = '';var select = document.getElementById('model');$('#model').children().remove();select.innerHTML = select.innerHTML + '<option value=\'\' SELECTED DISABLED >Please select a model to filter years</option>';select.innerHTML = select.innerHTML + '<option value=\'R NINE T\' >R NINE T</option>';select.innerHTML = select.innerHTML + '<option value=\'R100R\' >R100R</option>';select.innerHTML = select.innerHTML + '<option value=\'R100R MYSTIK\' >R100R MYSTIK</option>';select.innerHTML = select.innerHTML + '<option value=\'R100S\' >R100S</option>';select.innerHTML = select.innerHTML + '<option value=\'R100T\' >R100T</option>';select.innerHTML = select.innerHTML + '<option value=\'R1100R\' >R1100R</option>';select.innerHTML = select.innerHTML + '<option value=\'R1150R\' >R1150R</option>';select.innerHTML = select.innerHTML + '<option value=\'R1200C\' >R1200C</option>';select.innerHTML = select.innerHTML + '<option value=\'R850R\' >R850R</option>';
     * 型号： 占位符为车 如： BMW
     */
    public final static String GETGARAGEINFO_URL = "https://my.jwspeaker.com/garage/getgarageinfo.php?brand=%s";

    /**
     * 年份地址： 占位符为 制造商：如R100S  车：如BMW
     * document.getElementById('yeardiv').style.display = 'block';document.getElementById('tables').innerHTML = '';var select = document.getElementById('year');$('#year').children().remove();select.innerHTML = select.innerHTML + '<option value=\'\' SELECTED DISABLED >Please select a year</option>';select.innerHTML = select.innerHTML + '<option value=\'2014\' >2014</option>';select.innerHTML = select.innerHTML + '<option value=\'2015\' >2015</option>';select.innerHTML = select.innerHTML + '<option value=\'2016\' >2016</option>';select.innerHTML = select.innerHTML + '<option value=\'2017\' >2017</option>';document.getElementById('distdiv').style.display = 'block';document.getElementById('regdiv').style.display = 'block';document.getElementById('buylink').style.display = 'none';
     */
    public final static String GETGARAGEINFO_URL2 = "https://my.jwspeaker.com/garage/getgarageinfo.php?model=%s&brand=%s";

    /**
     * 灯地址： 占位符为 车：如BMW ,制造商：如R100S , 年份：如1998
     */
    public final static String GETGARAGEINFO_URL3 = "http://my.jwspeaker.com/garage/garage.php";


}
