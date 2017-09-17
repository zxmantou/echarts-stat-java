package com.zx.echarts_stat.utils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class SetUtilTest {
    @Test
    public void range() throws Exception {
        ArrayList<Double> range = SetUtil.range(1.0, 5.0, 1.0);
        for(Double d : range){
            System.out.println(d);
        }
    }

}