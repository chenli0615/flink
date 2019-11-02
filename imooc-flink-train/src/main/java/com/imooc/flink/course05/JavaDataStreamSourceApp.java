package com.imooc.flink.course05;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class JavaDataStreamSourceApp {
    public static void main(String[] args) throws Exception{

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        socketFunction(env);
//        nonParallelSourceFunction(env);
        richParallelSourceFunction(env);

        env.execute("JavaDataStreamSourceApp");
    }

    public static void socketFunction(StreamExecutionEnvironment env){
        DataStreamSource<String> data = env.socketTextStream("localhost",9999);
        data.print().setParallelism(1);
    }

    public static void nonParallelSourceFunction(StreamExecutionEnvironment env){
        DataStreamSource<Long> data = env.addSource(new JavaCustomNonParallelSourceFunction()).setParallelism(1);
        data.print().setParallelism(1);
    }

    public static void richParallelSourceFunction(StreamExecutionEnvironment env){
        DataStreamSource<Long> data = env.addSource(new JavaCustomNonParallelSourceFunction()).setParallelism(1);
        data.print().setParallelism(1);
    }
}
