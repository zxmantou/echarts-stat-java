package com.zx.echarts_stat.histogram;

import com.zx.echarts_stat.utils.StringUtil;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.StringJoiner;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class ThresholdMethod {

    private static Double squareRoot(Double[] data){
        Double bins = Math.ceil(Math.sqrt(data.length));
        return bins > 50 ? 50:bins;
    }

    private static Double scott(Double[] data, Double min, Double max){
//        return Math.ceil((max - min) / (3.5 * deviation(data) * Math.pow(data.length, -1 / 3)));
        return 0.0;
    }

    private static Double freedmanDiaconis(Double[] data, Double min, Double max){
//        data.sort(ascending);
//
//        return Math.ceil(
//                (max - min) / (2 * (quantile(data, 0.75) - quantile(data, 0.25)) * Math.pow(data.length, -1 / 3))
//        );
        return 0.0;
    }

    private static Double sturges(Double[] data, Double min, Double max){
//        return Math.ceil(Math.log(data.length) / Math.log(2)) + 1;
        return 0.0;
    }

    public static Double threshold(Double[] data, Double min, Double max, String binMethod){
        if(StringUtil.isNull(binMethod) || "squareRoot".equals(binMethod)){
            return squareRoot(data);
        }
        return null;
    }




    public static void main(String[] args) {
        Double[] l = {1.0, 2.0, 3.0, 1.0};
        System.out.println(squareRoot(l));
    }
}
