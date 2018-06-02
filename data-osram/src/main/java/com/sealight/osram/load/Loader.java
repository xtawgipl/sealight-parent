package com.sealight.osram.load;

import com.sealight.osram.entity.ManufacturerBean;
import com.sealight.osram.excel.ExcelUtil;
import com.sealight.osram.mapper.ManufacturerBeanMapper;
import com.sealight.osram.service.ExcelServer;
import com.sealight.osram.service.FecthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangjj
 * @create 2018-05-22 22:44
 **/
@Service
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
//        fecthService.fetchData();


        List<ManufacturerBean> manufacturerList = manufacturerBeanMapper.findAll();
        for(ManufacturerBean manufacturerBean : manufacturerList){
            ExcelUtil.excelExport(manufacturerBean.getManufacturerName(), excelServer.getLampExcelList(manufacturerBean));
        }

        logger.info("---------end------------------");
    }

}
