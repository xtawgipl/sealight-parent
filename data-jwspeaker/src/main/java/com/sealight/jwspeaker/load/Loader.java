package com.sealight.jwspeaker.load;

import com.sealight.jwspeaker.constants.Constant;
import com.sealight.jwspeaker.entity.LampBean;
import com.sealight.jwspeaker.entity.ModelBean;
import com.sealight.jwspeaker.entity.UrlanagerBean;
import com.sealight.jwspeaker.entity.YearBean;
import com.sealight.jwspeaker.mapper.*;
import com.sealight.jwspeaker.service.ExcelService;
import com.sealight.util.URLFetcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangjj
 * @create 2018-05-22 22:44
 **/
@Service
public class Loader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Loader.class);

    private static Integer retryNum = 10;

    @Resource(name = "excelService")
    private ExcelService excelService;

    @Resource(name = "brandBeanMapper")
    private BrandBeanMapper brandBeanMapper;

    @Resource(name = "modelBeanMapper")
    private ModelBeanMapper modelBeanMapper;

    @Resource(name = "yearBeanMapper")
    private YearBeanMapper yearBeanMapper;

    @Resource(name = "lampBeanMapper")
    private LampBeanMapper lampBeanMapper;

    @Resource(name = "urlanagerBeanMapper")
    private UrlanagerBeanMapper urlanagerBeanMapper;

    ExecutorService executorService = Executors.newFixedThreadPool(20);


    @Override
    public void run(String... args) throws Exception {
        logger.info("---------start------------------");
        /*final CountDownLatch latch = new CountDownLatch(Constant.BRAND_DICT.size());
        for(Map.Entry<String, String> entry : Constant.BRAND_DICT.entrySet()){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String brandUrl = String.format(Constant.GETGARAGEINFO_URL, entry.getValue());
                        if(urlanagerBeanMapper.find(brandUrl, "brand") == null){
                            String modelData = URLFetcher.pickData(brandUrl);
                            List<String> modelList = parserModel(modelData);
                            for(String model : modelList){
                                modelBeanMapper.insert(new ModelBean(entry.getValue(), model));
                                String modelUrl = String.format(Constant.GETGARAGEINFO_URL2, model, entry.getValue());
                                if(urlanagerBeanMapper.find(modelUrl, "model") == null){
                                    String yearData = URLFetcher.pickData(modelUrl);
                                    if(!StringUtils.isEmpty(yearData)){
                                        List<String> yearList = parserYear(yearData);
                                        System.out.println(yearList);
                                        for(String year : yearList){
                                            yearBeanMapper.insert(new YearBean(entry.getValue(), model, year));
                                            Map<String, String> params = new HashMap<>();
                                            params.put("brand", entry.getValue());
                                            params.put("model", model);
                                            params.put("year", year);
                                            params.put("reg", "dot");
                                            params.put("dist", "jws");
                                            params.put("submit", "Find Products");
                                            params.put("screensize", "1200");
                                            logger.info(String.format("brand = %s ; model = %s ; year = %s", entry.getValue(), model, year));
                                            Map<String, String> lightMap = getLamp(params, retryNum);
                                            if(lightMap != null && !lightMap.isEmpty()){
                                                for(Map.Entry<String, String> lampEntry : lightMap.entrySet()){
                                                    lampBeanMapper.insert(new LampBean(entry.getValue(), model, year, lampEntry.getKey(), lampEntry.getValue()));
                                                }
                                            }
                                        }
                                    }
                                    urlanagerBeanMapper.insert(new UrlanagerBean(modelUrl, "model"));
                                }
                            }
                            urlanagerBeanMapper.insert(new UrlanagerBean(brandUrl, "brand"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }
        latch.await();
        executorService.shutdown();



        logger.info("数据抓取完成...........");*/

        excelService.outputAll();

    }

    private static Map<String, String> getLamp(Map<String, String> params, Integer tryNum){
        logger.info(String.format("第 [%s] 获取, params = %s", (retryNum - tryNum), params));
        String lightPage = URLFetcher.pickDataPost(Constant.GETGARAGEINFO_URL3, params);
        Map<String, String> lightMap = parseLight(lightPage);
        if((lightMap == null || lightMap.isEmpty()) && tryNum > 0){
            logger.error("未获取到lamp信息，重新获取，请求参数为： params --> " + params);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("", e);
            }
            return getLamp(params, --tryNum);
        }
        logger.info("lightMap = " + lightMap);
        return lightMap;
    }

    private static List<String> parserOption(String data){
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("(<option value=(.*?)>)");
        Matcher m = p.matcher(data);
        while(m.find()) {
            String value = m.group(2).replaceAll(Pattern.quote("\\'"), "");
            if(!value.contains("SELECTED DISABLED")){
                list.add(value);
            }
        }
        return list;
    }


    private static List<String> parserModel(String modelData){
        return parserOption(modelData);
    }

    private static List<String> parserYear(String yearData){
        return parserOption(yearData);
    }

    /**
     *
     * key -- Part Number
     * value -- Description
     * @author zhangjj
     * @Date
     * @return
     * @exception
     *
     */
    private static Map<String, String> parseLight(String lightPage){
        Map<String, String> map = new HashMap<>();
        Document document = Jsoup.parse(lightPage);
        Elements table = document.getElementsByClass("sortable");
        if(table == null || table.isEmpty()){
            System.out.println("================================================================");
            System.out.println(lightPage);
            System.out.println("================================================================");
            return null;
        }
        Element lightTable = table.get(0);
        Elements trEles = lightTable.getElementsByTag("tbody").get(0).getElementsByTag("tr");
        for(Element trEle : trEles){
            Elements tdEles = trEle.getElementsByTag("td");
            map.put(tdEles.get(1).text(), tdEles.get(2).text());
        }
        return map;
    }

   /* public static void main(String[] args) {
        for(Map.Entry<String, String> entry : Constant.BRAND_DICT.entrySet()){
            String modelData = URLFetcher.pickData(String.format(Constant.GETGARAGEINFO_URL, entry.getValue()));
            List<String> modelList = parserModel(modelData);
            for(String model : modelList){
                String yearData = URLFetcher.pickData(String.format(Constant.GETGARAGEINFO_URL2, model, entry.getValue()));
                if(!StringUtils.isEmpty(yearData)){
                    List<String> yearList = parserYear(yearData);
                    System.out.println(yearList);
                    for(String year : yearList){
                        Map<String, String> params = new HashMap<>();
                        params.put("brand", entry.getValue());
                        params.put("model", model);
                        params.put("year", year);
                        params.put("reg", "dot");
                        params.put("dist", "jws");
                        params.put("submit", "Find Products");
                        params.put("screensize", "1200");
                        String lightPage = URLFetcher.pickDataPost(Constant.GETGARAGEINFO_URL3, params);
                        Map<String, String> lightMap = parseLight(lightPage);
                        System.out.println(lightMap);
                    }
                }
            }
            break;
        }
    }*/

}
