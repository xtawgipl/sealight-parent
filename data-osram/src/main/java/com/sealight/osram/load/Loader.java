package com.sealight.osram.load;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangjj
 * @create 2018-05-22 22:44
 **/
@Service
public class Loader implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Loader.class);

    private static Integer retryNum = 10;


    ExecutorService executorService = Executors.newFixedThreadPool(20);


    @Override
    public void run(String... args) throws Exception {
        logger.info("---------start------------------");

    }

}
