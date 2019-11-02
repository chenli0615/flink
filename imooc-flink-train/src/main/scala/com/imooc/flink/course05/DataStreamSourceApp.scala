package com.imooc.flink.course05

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._

object DataStreamSourceApp {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
//    socketFunction(env)

//    nonParallelSourceFunction(env)
    richParallelSourceFunction(env)
//    parallelSourceFunction(env)
    env.execute("DataStreamSourceApp")

  }

  def richParallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomRichParallelSourceFunction).setParallelism(2)
    data.print()
  }

  def parallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomParallelSourceFunction).setParallelism(2)
    data.print()
  }

  def nonParallelSourceFunction(env: StreamExecutionEnvironment): Unit ={
    val data = env.addSource(new CustomNonParallelSourceFunction).setParallelism(1)
    data.print().setParallelism(1)
  }

  def socketFunction(env: StreamExecutionEnvironment): Unit ={
    val data: DataStream[String] = env.socketTextStream("localhost", 9999)
    data.print()
        .setParallelism(2)
  }

}
