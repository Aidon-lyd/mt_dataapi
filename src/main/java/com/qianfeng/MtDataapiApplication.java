package com.qianfeng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MtDataapiApplication {
    /**
     * [root@hadoop01 ~]# nohup java -jar /home/mt_dataapi-0.0.1-SNAPSHOT.jar com.qianfeng.MtDataapiApplication > /home/mt.log 2>&1 &
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MtDataapiApplication.class, args);
    }

}
