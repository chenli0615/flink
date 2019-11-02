package com.imooc.flink.course04;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.core.fs.FileSystem;

import java.util.ArrayList;
import java.util.List;

public class JavaDataSetSinkApp {
    public static void main(String[] args) throws Exception{
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        sink(env);

    }


    public static void sink(ExecutionEnvironment env) throws Exception{
        List<Integer> list = new ArrayList<Integer>();
        for(int i=1; i<=10; i++){
            list.add(i);
        }
        DataSource<Integer> data = env.fromCollection(list);

        String filePath = "F:\\project\\data\\flink-train-java\\javaout";

        data.writeAsText(filePath, FileSystem.WriteMode.OVERWRITE).setParallelism(2);

        env.execute("JavaDataSetSinkApp");

    }
}
