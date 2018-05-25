package com.sealight.jwspeaker.service;

import com.sealight.jwspeaker.entity.BrandBean;
import com.sealight.jwspeaker.entity.LampBean;
import com.sealight.jwspeaker.excel.ExcelUtil;
import com.sealight.jwspeaker.mapper.BrandBeanMapper;
import com.sealight.jwspeaker.mapper.LampBeanMapper;
import com.sealight.jwspeaker.mapper.ModelBeanMapper;
import com.sealight.jwspeaker.mapper.YearBeanMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangjj
 * @create 2018-05-24 20:49
 **/
@Service("excelService")
public class ExcelService {

    @Resource(name = "brandBeanMapper")
    private BrandBeanMapper brandBeanMapper;

    @Resource(name = "modelBeanMapper")
    private ModelBeanMapper modelBeanMapper;

    @Resource(name = "yearBeanMapper")
    private YearBeanMapper yearBeanMapper;

    @Resource(name = "lampBeanMapper")
    private LampBeanMapper lampBeanMapper;

    public List<BrandBean> listAllBrand(){
        return brandBeanMapper.findAll();
    }

    public List<LampBean> listLamp(String brand){
        List<LampBean> lampList = lampBeanMapper.findByBrand(brand);
        Collections.sort(lampList);
        return lampList;
    }


    public void outputExcel(String brand){
        List<LampBean> lampList = listLamp(brand);
        ExcelUtil.excelExport(brand, lampList);
    }

    public void outputAll(){
        List<BrandBean> brandList = listAllBrand();
        for(BrandBean brand : brandList){
            outputExcel(brand.getBrand());
        }
    }
}
