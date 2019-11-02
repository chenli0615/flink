package com.imooc.flink.course05

import java.{lang, util}

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.datastream.DataStreamSource
import org.apache.flink.streaming.api.scala._

import org.apache.flink.api.common._


object DataStreamTransformationApp {
//  def main(args: Array[String]): Unit = {
//    val env = StreamExecutionEnvironment.getExecutionEnvironment
//
////    filterFunction(env)
////    splitSelectFunction(env)
//    env.execute("DataStreamTransformationApp")
//  }
//
//  def filterFunction(env:StreamExecutionEnvironment): Unit={
//    import org.apache.flink.api.common._
//    val data = env.addSource(new CustomParallelSourceFunction)
//    data.print()
//    //    不知道为什么
//    data.map{x =>x}.filter(_%2 == 0).print().setParallelism(1)
//  }
//
//
//  def splitSelectFunction(env:StreamExecutionEnvironment): Unit ={
//
//    val data = env.addSource(new CustomNonParallelSourceFunction)
//
//    val splits = data.split(new OutputSelector[Long] {
//      override def select(value: Long): lang.Iterable[String] = {
//        val list = new util.ArrayList[String]()
//        if(value % 2 == 0){
//          list.add("even")
//        }else{
//          list.add("odd")
//        }
//        list
//      }
//    })
//
//    splits.select("odd").print().setParallelism(1)
//
//  }

}
