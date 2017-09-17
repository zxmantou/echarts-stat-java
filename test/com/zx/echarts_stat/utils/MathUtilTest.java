package com.zx.echarts_stat.utils;

import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class MathUtilTest {
    @org.junit.Test
    public void isNumber() throws Exception {
        Assert.assertTrue(MathUtil.isNumber("222"));
        Assert.assertTrue(MathUtil.isNumber("222.1"));
        Assert.assertFalse(MathUtil.isNumber("22a.1"));
        Assert.assertFalse(MathUtil.isNumber("222-1"));
    }

    @org.junit.Test
    public void maxValue() throws Exception {
        Double[] data = {1.0, 32.0, 12.3, 45.0, 0.0, -2.0};

        Assert.assertTrue(MathUtil.maxMinValue(data).get("MAX") == 45.0);
        Assert.assertTrue(MathUtil.maxMinValue(data).get("MIN") == -2);
    }

}