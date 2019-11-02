package com.imooc.flink.course04

import org.apache.flink.api.common.accumulators.IntCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem.WriteMode

object CounterApp {

  def main(args: Array[String]): Unit = {


    //    data.map(new RichMapFunction[String, Long] {
    //      var counter = 0l
    //      override def map(value: String): Long = {
    //        counter = counter +1
    //        println("counter: "+ counter)
    //        counter
    //      }
    //    }).setParallelism(4).print()


    val env = ExecutionEnvironment.getExecutionEnvironment
    val data = env.fromElements("a","b","c","d")

    val info = data.map(new RichMapFunction[String, String]() {
      // 1、定义计数器
      val counter = new IntCounter
      override def open(parameters: Configuration): Unit = {
        // 2、注册计数器
        getRuntimeContext.addAccumulator("ele-counts-scala", counter)
      }
      override def map(value: String): String = {
        counter.add(1)
        value
      }
    })
    val filePath = "file:///F:\\project\\data\\flink-train-java\\sink-counter"
    info.writeAsText(filePath, WriteMode.OVERWRITE).setParallelism(5)

    val jobResult = env.execute("CounterApp")

    // 3、获取计数器
    val num = jobResult.getAccumulatorResult[Int]("ele-counts-scala")

    println(num)
  }

}
