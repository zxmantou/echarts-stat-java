package com.zx.echarts_stat.utils;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class MathUtil {

    public static boolean isNumber(String num){
        try{
            Double d = Double.valueOf(num);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static Map<String, Double> maxMinValue(Double[] nums){
        Map<String, Double> maxMin = new HashMap<>();
        Double max = Double.MIN_VALUE;
        Double min = Double.MAX_VALUE;
        try{
            for(Double num : nums){
                if(num > max){
                    max = num;
                }
                if(num < min){
                    min = num;
                }
            }
        }catch (NumberFormatException e){

        }
        maxMin.put("MAX", max);
        maxMin.put("MIN", min);
        return maxMin;
    }

}
