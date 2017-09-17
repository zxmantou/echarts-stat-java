package com.zx.echarts_stat.histogram;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class HistogramTest {
    @Test
    public void computeBins() throws Exception {
        Histogram h = new Histogram();
        String[] girth = {"8.3", "8.6", "8.8", "10.5",
                "10.7", "10.8", "11.0", "11.0", "11.1",
                "11.2", "11.3", "11.4", "11.4",
                "11.7", "12.0", "12.9", "12.9",
                "13.3", "13.7", "13.8", "14.0",
                "14.2", "14.5", "16.0", "16.3",
                "17.3", "17.5", "17.9", "18.0",
                "18.0", "20.6"};
        Map<String, Object> res = h.computeBins(girth, null);
        System.out.println();

    }

}