package com.imooc.flink.java.course02;


import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class BatchWCjavaApp {

    public static void main(String[] args) throws Exception {
        System.out.println("slfjsl");
        String input = "file:///F:\\project\\data\\flink-train-java";
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<String> text = env.readTextFile(input);

        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] tokens = value.toLowerCase().split("\t");
                for(String token :tokens){
                    if(token.length() >0){
                        collector.collect(new Tuple2<String, Integer>(token,1));
                    }
                }

            }
        }).groupBy(0).sum(1).print();
    }
}
