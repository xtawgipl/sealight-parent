package com.sealight.osram.load;

import com.sealight.osram.constants.Constant;
import com.sealight.osram.entity.ManufacturerBean;
import com.sealight.osram.excel.ExcelUtil;
import com.sealight.osram.excel.LampExcelBean;
import com.sealight.osram.mapper.ManufacturerBeanMapper;
import com.sealight.osram.service.ExcelServer;
import com.sealight.osram.service.FecthService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangjj
 * @create 2018-05-22 22:44
 **/
@Service
@Slf4j
public class Loader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Loader.class);

    @Resource(name = "fecthService")
    private FecthService fecthService;

    @Resource(name = "excelServer")
    private ExcelServer excelServer;

    @Resource(name = "manufacturerBeanMapper")
    private ManufacturerBeanMapper manufacturerBeanMapper;

    @Override
    public void run(String... args) throws Exception {
        logger.info("---------start------------------");
        fecthService.fetchDataByModel();


        /*List<ManufacturerBean> manufacturerList = manufacturerBeanMapper.findAll();
        for(ManufacturerBean manufacturerBean : manufacturerList){
            if(!new File(Constant.EXCEL_PATH + manufacturerBean.getManufacturerName() +".xlsx").exists()){
                List<LampExcelBean> lampExcelList = excelServer.getLampExcelList(manufacturerBean);
                if(lampExcelList == null || lampExcelList.isEmpty()){
                    log.warn("没有任何数据： {} : {}", manufacturerBean.getManufacturerId(), manufacturerBean.getManufacturerName());
                    continue;
                }
                ExcelUtil.excelExport(manufacturerBean.getManufacturerName(), lampExcelList);
            }
        }*/

        logger.info("---------end------------------");
    }

}
