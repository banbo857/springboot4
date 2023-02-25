package com.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 精准分表策略
 */
public class PreciseShardingAlgorithmImpl implements PreciseShardingAlgorithm {
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        String value = (String) preciseShardingValue.getValue();
        String year = value.substring(0, 4);
        String month = value.substring(5, 7);
        String tableName = logicTableName + "_" + year + month;
        System.err.println(collection);
        if (collection.contains(tableName)) {
            return tableName;
        }
        throw new IllegalArgumentException();
    }
}
