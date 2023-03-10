package com.needkk.influxdbjava.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wu Zijie
 * @Date: 2023-01-07 00:04
 * @Description:
 */
@Configuration
public class InfluxDBTemplate {

    private InfluxDB influxDB;

    private final InfluxdbProperties influxdbProperties;

    public InfluxDBTemplate(InfluxdbProperties influxdbProperties) {
        this.influxdbProperties = influxdbProperties;
        getConn();
    }

    //获取连接
    public void getConn() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(influxdbProperties.getUrl(), influxdbProperties.getUsername(), influxdbProperties.getPassword());
            influxDB.setDatabase(influxdbProperties.getDatabase());
        }
    }

    //关闭连接
    public void close() {
        if (influxDB != null) {
            influxDB.close();
        }
    }

    public void save(String measurement, Map<String, String> tags, Map<String, Object> fields) {
        save(measurement, tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }


    public void save(String measurement, Map<String, String> tags, Map<String, Object> fields, Long time, TimeUnit timeUnit) {
        Point point = Point.measurement(measurement)
                .time(time, timeUnit)
                .tag(tags)
                .fields(fields)
                .build();
        influxDB.write(point);
        close();
    }

    /**
     * 指定时间插入
     *
     * @param measurement 表
     * @param tags        标签
     * @param fields      字段
     * @param time        时间
     * @param unit        单位
     */
    public void write(String measurement, Map<String, String> tags, Map<String, Object> fields, long time, TimeUnit unit) {
        Point point = Point.measurement(measurement).tag(tags).fields(fields).time(time, unit).build();
        influxDB.write(influxdbProperties.getDatabase(), influxdbProperties.getRetentionPolicy(), point);
        close();
    }

    /**
     * 插入数据-自动生成时间
     *
     * @param measurement 表
     * @param tags        标签
     * @param fields      字段
     */
    public void write(String measurement, Map<String, String> tags, Map<String, Object> fields) {
        write(measurement, tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 用来执行相关操作
     *
     * @param command 执行命令
     * @return 返回结果
     */
    public QueryResult query(String command) {
        return influxDB.query(new Query(command));
    }

    public <T> List<T> queryByType(String command, Class<T> clazz) {
        List<T> res = new ArrayList<>();
        QueryResult query = influxDB.query(new Query(command));
        query.getResults().forEach(result -> {
            result.getSeries().forEach(series -> {
                List<String> columns = series.getColumns();
                List<List<Object>> values = series.getValues();
                for (int i = 0; i < values.size(); i++) {
                    T instance = null;
                    try {
                        instance = clazz.newInstance();
                        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(instance);
                        Map<String, Object> fields = new HashMap<>();
                        for (int j = 0; j < columns.size(); j++) {
                            String column = columns.get(j);
                            Object value = values.get(i).get(j);
                            if (("time").equals(column)) {
                                beanWrapper.setPropertyValue("time", Timestamp.from(ZonedDateTime.parse(String.valueOf(value)).toInstant()).getTime());
                            } else {
                                fields.put(column, value);
                            }
                        }
                        beanWrapper.setPropertyValue("fields", fields);
                        res.add(instance);
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });
        return res;
    }


}
