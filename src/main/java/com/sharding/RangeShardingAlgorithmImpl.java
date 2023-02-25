package com.sharding;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 范围分表策略
 */
public class RangeShardingAlgorithmImpl implements RangeShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        System.err.println(collection);

        List<String> result = new LinkedList<>();

        String lower = rangeShardingValue.getValueRange().hasLowerBound() ? (String) rangeShardingValue.getValueRange().lowerEndpoint() : null;
        String upper = rangeShardingValue.getValueRange().hasUpperBound() ? (String) rangeShardingValue.getValueRange().upperEndpoint() : null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lowerDate = null;
        Date upperDate = null;
        if (lower != null && upper != null) {
            try {
                lowerDate = sdf.parse(lower);
                upperDate = sdf.parse(upper);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lowerDate);
                while (calendar.getTime().before(upperDate)) {
                    String year = String.valueOf(calendar.get(Calendar.YEAR));
                    String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                    if (month.length() == 1){
                        month = "0" + month;
                    }
                    String tableName = rangeShardingValue.getLogicTableName() + "_" + year + month;
                    if (collection.contains(tableName)) {
                        result.add(tableName);
                    }
                    calendar.add(Calendar.MONTH, 1);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //没有低值 <=
        else if (lower == null){
            try {
                upperDate = sdf.parse(upper);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(upperDate);

                Calendar now = Calendar.getInstance();
                now.setTime(new Date());

                for (Object o:collection) { //test_202301 test_202302 test_202402
                    String start = (String) o;
                    int endYear = calendar.get(Calendar.YEAR);
                    int tempYear = Integer.parseInt(((String) o).substring(5, 9));
                    int tempMonth = Integer.parseInt(((String) o).substring(9, 11));

                    if (endYear >= tempYear){
                        if (tempYear <= now.get(Calendar.YEAR) && tempMonth <= now.get(Calendar.MONTH) + 1)
                            result.add(start);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else {
            //没有高值 >= 202301
            try {
                lowerDate = sdf.parse(lower);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(lowerDate);

                Calendar now = Calendar.getInstance();
                now.setTime(new Date());
                for (Object o:collection) { //test_202301 test_202302 test_202402
                    String start = (String) o;
                    int startYear = calendar.get(Calendar.YEAR);
                    int tempYear = Integer.parseInt(((String) o).substring(5, 9));
                    int tempMonth = Integer.parseInt(((String) o).substring(9, 11));

                    if (startYear <= tempYear){
                        if (tempYear <= now.get(Calendar.YEAR) && tempMonth <= now.get(Calendar.MONTH) + 1)
                            result.add(start);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.err.println("result:" + result);
        return result;


    }
}
