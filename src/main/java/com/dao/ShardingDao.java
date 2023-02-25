package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.ShardingRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShardingDao extends BaseMapper<ShardingRes> {

    List<ShardingRes> test(@Param("updateTime") String updateTime);

    List<ShardingRes> test2(@Param("startTime") String startTime,
                            @Param("endTime") String endTime);

    List<ShardingRes> test3(@Param("updateTime") String updateTime);

    List<ShardingRes> test4(@Param("updateTime") String updateTime);

    List<ShardingRes> test5(@Param("updateTime") String updateTime);

    int add(@Param("shardingRes") ShardingRes shardingRes);
}
