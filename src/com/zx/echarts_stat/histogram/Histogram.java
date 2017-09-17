package com.zx.echarts_stat.histogram;

import com.zx.echarts_stat.utils.MathUtil;
import com.zx.echarts_stat.utils.SetUtil;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangxin on 2017/9/17.
 */
public class Histogram {
    /**
     * Compute bins for histogram
     * @param data
     * @param binMethod
     * @return
     */
    public Map<String, Object> computeBins(String[] data, String binMethod){

        /*
        var values = dataPreprocess(data);
        var maxValue = max(values);
        var minValue = min(values);
         */
        Double[] values = dataPreProcess(data);
        Map<String, Double> maxMinValue = MathUtil.maxMinValue(values);
        Double maxValue = maxMinValue.get("MAX");
        Double minValue = maxMinValue.get("MIN");

        Double binsNumber = ThresholdMethod.threshold(values, binMethod);
        Double step = tickStep(minValue, maxValue, binsNumber);

        /*
        var rangeArray = range(Math.ceil(minValue / step) * step, Math.floor(maxValue / step) * step, step);
         */
        ArrayList<Double> rangeArray = SetUtil.range(Math.ceil(minValue / step) * step, Math.floor(maxValue / step) * step, step);

        /*
        var len = rangeArray.length;

        var bins = new Array(len + 1);
         */
        int len = rangeArray.size();
        ArrayList<Map<String, Object>> bins = new ArrayList<>();
        /*
        for (var i = 0; i <= len; i++) {
            bins[i] = {};
            bins[i].sample = [];
            bins[i].x0 = i > 0 // 不要数组直接挂属性，改成Object
                ? rangeArray[i - 1]
                : (rangeArray[i] - minValue) === step
                ? minValue
                : (rangeArray[i] - step);
            bins[i].x1 = i < len
                ? rangeArray[i]
                : (maxValue - rangeArray[i-1]) === step
                ? maxValue
                : rangeArray[i - 1] + step;
        }
         */
        for(int i = 0; i <= len; i ++){
            Map<String, Object> tmp = new HashMap<String, Object>();
            ArrayList<Double> sample = new ArrayList<Double>();
            tmp.put("sample", sample);
            Double x0 = i > 0 ? rangeArray.get(i-1) : (rangeArray.get(i) - minValue) == step ? minValue : (rangeArray.get(i) - step);
            tmp.put("x0", x0);
            Double x1 = i < len ? rangeArray.get(i) : (maxValue - rangeArray.get(i-1)) == step ? maxValue : (rangeArray.get(i-1) + step);
            tmp.put("x1", x1);

            bins.add(i, tmp);
        }
        /*
        for (var i = 0; i < values.length; i++) {
            if (minValue <= values[i] && values[i] <= maxValue) {
                bins[bisect(rangeArray, values[i], 0, len)].sample.push(values[i]);
            }
        }

        var data = map(bins, function (bin) {
            return [(bin.x0 + bin.x1) / 2, bin.sample.length];
        });

        return {
            bins: bins,
            data: data
        };
         */

        for(int i = 0; i < values.length; i ++){
            if (minValue <= values[i] && values[i] <= maxValue){
                Map<String, Object> tmp = bins.get(bisect(rangeArray, values[i], 0, len));
                ArrayList<Double> sample = (ArrayList<Double>)(tmp.get("sample"));
                sample.add(values[i]);
            }
        }
        Double[][] d = getData(bins);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("bins", bins);
        res.put("data", d);

        return res;
    }

    private Double tickStep(Double start, Double stop, Double count){
        Double step0 = Math.abs(stop - start) / count;
        Double step1 = Math.pow(10, Math.floor(Math.log(step0) / Math.log(10.0)));
        Double error = step0 / step1;

        if( error >= Math.sqrt(50)){
            step1 *= 10;
        }else if(error >= Math.sqrt(10)){
            step1 *= 5;
        }else if(error >= Math.sqrt(2)){
            step1 *= 2;
        }
        return stop >= start ? step1 : -step1;
    }

    private Double[] dataPreProcess(String[] data){
        int arraySize = data.length;
        Double[] preData = new Double[arraySize];
        for(int i = 0; i < arraySize; i ++){
            if(MathUtil.isNumber(data[i])){
                preData[i] = Double.valueOf(data[i]);
            }else{
                preData[i] = 0.0;
            }
        }
        return preData;
    }


    private Integer bisect(ArrayList<Double> array, Double value, Integer start, Integer end){
        if(start == null){
            start = 0;
        }
        if(end == null){
            end = array.size();
        }
        while (start < end){
            int mid = (int)Math.floor((start + end) / 2);
            int compare = Double.compare(array.get(mid), value);
            if (compare > 0) {
                end = mid;
            }
            else if (compare < 0) {
                start = mid + 1;
            }
            else {
                return mid + 1;
            }
        }
        return start;
    }

    private Double[][] getData(ArrayList<Map<String, Object>> bins){
        int len = bins.size();
        Double[][] data = new Double[len][2];
        for(int i = 0; i < len; i ++){
            Map<String, Object> bin = bins.get(i);
            data[i][0] = ((Double)bin.get("x0") + (Double)bin.get("x1")) / 2;
            ArrayList<Double> sample = (ArrayList<Double>)bin.get("sample");
            data[i][1] = (double)sample.size();
        }
        return data;
    }

}
