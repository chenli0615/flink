package com.imooc.flink.course04;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

public class JavaDataSetTransformationApp{

    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        mapFunction(env);
    }

    public static void  mapFunction(ExecutionEnvironment env) throws Exception{
        List<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=5; i++){
            list.add(i);
        }

        DataSource<Integer> data = env.fromCollection(list);
        data.map(new MapFunction<Integer, Integer>() {
            @Override
            public Integer map(Integer value) throws Exception {
                return value +  1;
            }
        }).print();

    }
}
