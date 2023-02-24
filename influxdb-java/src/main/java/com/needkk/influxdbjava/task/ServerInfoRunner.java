package com.needkk.influxdbjava.task;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.needkk.influxdbjava.config.InfluxDBTemplate;
import com.needkk.influxdbjava.config.ThreadPoolConfig;
import com.needkk.influxdbjava.entity.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-06 17:41
 * @Description:
 */
@Component
@EnableScheduling
@EnableAsync
public class ServerInfoRunner {
    private final InfluxDBTemplate influxDBTemplate;

    private static ThreadFactory namedFactory = new ThreadFactoryBuilder().setNamePrefix("serverInfo-save").build();


    // customize thread pool
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            ThreadPoolConfig.corePoolSize,
            ThreadPoolConfig.maximumPoolSize,
            ThreadPoolConfig.keepAliveTime,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(ThreadPoolConfig.queueSize),
            namedFactory
            //, new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    public ServerInfoRunner(InfluxDBTemplate influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }


    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void serverInfoTask() {
        CompletableFuture.runAsync(() -> {
            //启动线程读取服务器信息
            ServerInfo serverInfo = new ServerInfo();
            double usedCpu = serverInfo.getCpu().getUsed();
            double usedMem = serverInfo.getMem().getUsed();
            Map<String, String> tags = new HashMap<>(3);
            tags.put("host", "server");
            Map<String, Object> fields = new HashMap<>(5);
            fields.put("cpu", usedCpu);
            fields.put("ram", usedMem);
            influxDBTemplate.write("serverInfo_java", tags, fields);
        }, threadPoolExecutor);
    }
}
