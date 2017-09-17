package com.zx.echarts_stat.utils;

import java.util.ArrayList;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class SetUtil {

    public static ArrayList<Double> range(Double start, Double stop, Double step){

        Double n = Math.ceil((stop - start) / step);
        ArrayList<Double> range = new ArrayList<>();
        for(int i = 0; i < n + 1; i ++){
            range.add(start + i * step);
        }
        return range;
    }

    public static ArrayList<Double> range(Double start, Double stop){
        return range(start, stop, 0.0);
    }

    public static ArrayList<Double> range(Double stop){
        return range(0.0, stop);
    }

}
