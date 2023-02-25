package com.controller;

import com.dao.ShardingDao;
import com.pojo.ShardingRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class ShardingController {

    @Autowired
    private ShardingDao shardingDao;

    @RequestMapping("/query")
    public List<ShardingRes> query(@RequestParam("updateTime") String updateTime,
                                   @RequestParam("type") String type,
                                   @RequestParam(value = "startTime", required = false) String startTime,
                                   @RequestParam(value = "endTime", required = false) String endTime) {
        switch (type) {
            case "1":
                return shardingDao.test(updateTime);
            case "2":
                return shardingDao.test2(startTime, endTime);
            case "3":
                return shardingDao.test3(updateTime);
            case "4":
                return shardingDao.test4(updateTime);
            case "5":
                return shardingDao.test5(updateTime);
            default:
                return null;
        }
    }


    @RequestMapping("/add")
    public int add(@RequestParam("updateTime") String updateTime, @RequestParam("name") String name) {
        ShardingRes shardingRes = new ShardingRes();
        shardingRes.setId(UUID.randomUUID().toString().replace("-", ""));
        shardingRes.setName(name);
        shardingRes.setUpdateTime(updateTime);
        return shardingDao.add(shardingRes);
    }
}
