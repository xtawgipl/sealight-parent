package com.sealight.osram.load;

import com.sealight.osram.service.FecthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Override
    public void run(String... args) throws Exception {
        logger.info("---------start------------------");
        fecthService.fetchData();
    }

}
