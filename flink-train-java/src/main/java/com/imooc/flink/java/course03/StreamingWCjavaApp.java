package com.imooc.flink.java.course03;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class StreamingWCjavaApp {
    public static void main(String[] args)  throws Exception{

        int port = 0;

        try{
            ParameterTool tool = ParameterTool.fromArgs(args);
            port = tool.getInt("port");
        }catch (Exception e){
            System.err.println("端口未设置，使用默认端口9999");
            port = 9999;
        }


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> text =env.socketTextStream("localhost", 9999);

        text.flatMap(new MyFlatMapFunction())
            .keyBy(
//                    new KeySelector<WC, String>() {
//
//                        @Override
//                        public String getKey(WC wc) throws Exception {
//                            return wc.word;
//                        }
//                    }
                    "word"
            )
                .timeWindow(Time.seconds(5)).sum("count").print().setParallelism(1);

        env.execute("StreamingWCjavaApp");

    }

    public static class MyFlatMapFunction implements  FlatMapFunction<String, WC> {

        @Override
        public void flatMap(String value, Collector<WC> collector) throws Exception {
            String[] tokens = value.toLowerCase().split(",");
            for (String token :tokens){
                if(token.length()>0){
                    collector.collect(new WC(token, 1));
                }
            }
        }
    }

    public static class WC {
        public String word;
        public int count;

        public WC(){
        }
        public WC(String word, int count){
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return "WC{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }

        public String getWord() {
            return word;
        }

        public int getCount() {
            return count;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
