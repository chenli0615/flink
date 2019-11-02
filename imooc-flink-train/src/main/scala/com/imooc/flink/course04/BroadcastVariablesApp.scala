package com.imooc.flink.course04

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration

object BroadcastVariablesApp {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment
    val toBroadcast = env.fromElements(1,2,3)
    val data = env.fromElements("a","b")
    data.map(new RichMapFunction[String,String] {
      var broadcastSet : Traversable[String] = null
      override def open(parameters: Configuration): Unit = {
        import scala.collection.JavaConverters._
        broadcastSet = getRuntimeContext.getBroadcastVariable("broadcastSetname").asScala
      }
      override def map(value: String): String = {
        value
      }
    }).withBroadcastSet(toBroadcast, "broadcastSetname").print()



  }
}
